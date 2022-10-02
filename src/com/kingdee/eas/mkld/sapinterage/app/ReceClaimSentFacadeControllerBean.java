package com.kingdee.eas.mkld.sapinterage.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogFactory;
import com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogInfo;
import com.kingdee.eas.mkld.sapinterage.app.util.ReceClaimRecordUtil;
import com.kingdee.eas.mkld.sapinterage.app.util.ReceiptSentRecordUtil;
import com.kingdee.eas.mkld.sapinterage.common.DMSInterfaceUtil;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.LowTimer;

public class ReceClaimSentFacadeControllerBean extends AbstractReceClaimSentFacadeControllerBean
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5623787730806306563L;
	private static Logger logger = Logger.getLogger("com.kingdee.eas.mkld.sapinterage.app.ReceClaimSentFacadeControllerBean");
	private LowTimer timer = new LowTimer();

	
	/**
	 * 
	 * EAS收款认领结果传SAP-本月认领
	 * @author Lei.Ye
	 */
	@Override
	protected String _sentReceClaim(Context ctx) throws BOSException {
		this.timer.reset(); 
		ReceClaimRecordUtil.doSendClaimCurrentMonth(ctx);
		logger.info("EAS收款认领结果传SAP-当月认领，花费时间：" + this.timer.msValue());
		System.out.println("EAS收款认领结果传SAP-当月认领，花费时间：" + this.timer.msValue());
		return super._sentReceClaim(ctx);
	}


	/**
	 *  EAS收款认领结果传SAP-次月认领
	 * @author Lei.Ye
	 * 
	 */
	@Override
	protected String _sentClaimAgain(Context ctx) throws BOSException {
		this.timer.reset(); 
		ReceClaimRecordUtil.doSendClaimAgain(ctx);
		logger.info("EAS收款认领结果传SAP-次月认领，花费时间：" + this.timer.msValue());
		System.out.println("EAS收款认领结果传SAP-次月认领，花费时间：" + this.timer.msValue());
		return super._sentClaimAgain(ctx);
	}

	
	/**
	 * 
	 * EAS月末未认领银行流水传SAP
	 * @author Lei.Ye
	 */
	@Override
	protected String _sentReceNoClaim(Context ctx) throws BOSException {
		this.timer.reset(); 
		ReceClaimRecordUtil.doSentReceNoClaim(ctx);
		System.out.println("EAS月末未认领银行流水传SAP，花费时间：" + this.timer.msValue());
		logger.info("EAS月末未认领银行流水传SAP，花费时间：" + this.timer.msValue());
		return super._sentReceNoClaim(ctx);
	}


	/**
	 * 
	 * 生成回单发送记录
	 * 
	 */
	@Override
	protected String _genReceiptRecord(Context ctx) throws BOSException {
		this.timer.reset(); 
		ReceiptSentRecordUtil.doGenReceiptRecord(ctx);
		System.out.println("生成回单发送记录，花费时间：" + this.timer.msValue());
		logger.info("生成回单发送记录，花费时间：" + this.timer.msValue());
		return super._genReceiptRecord(ctx);
	}


	/**
	 * 
	 * 发送电子回单
	 * 
	 */
	@Override
	protected String _SentReceiptRecord(Context ctx) throws BOSException {
		this.timer.reset(); 
		ReceiptSentRecordUtil.doSentReceiptRecord(ctx);
		System.out.println("发送电子回单，花费时间：" + this.timer.msValue());
		logger.info("发送电子回单，花费时间：" + this.timer.msValue());
		return super._SentReceiptRecord(ctx);
	}

	/***
	 * 
	 * 发送客至DMS
	 * 
	 */
	@Override
	protected String _sentCustomer2DMS(Context ctx) throws BOSException {
		this.timer.reset(); 
		String sql ="/*dialect*/select distinct CFPayerName,CFBusDeptName from CT_SIG_ReceClaimRecord where CFDmsSendStatus = 0";
		IRowSet rs = DbUtil.executeQuery(ctx, sql);
		List lists= new ArrayList();
//		Set rIds = new HashSet();
		  try {
			if(rs!=null){
				  while(rs.next()){
					 if(rs.getObject("CFPayerName") !=null && !"".equals(rs.getObject("CFPayerName").toString()) && 
							 rs.getObject("CFBusDeptName") !=null && !"".equals(rs.getObject("CFBusDeptName").toString()) ){
							Map<String,String> mp = new HashMap<String,String>();
							mp.put("business", rs.getObject("CFBusDeptName").toString());
							mp.put("customername", rs.getObject("CFPayerName").toString());
//							rIds.add(rs.getObject("FID").toString());
							lists.add(mp);
					 }
				  } 
					
				  if(lists !=null && lists.size() > 0){
					  Map sentMp =new HashMap();
					  sentMp.put("kx_channelcustomers_wait", lists);
					  Gson goso = new Gson();
					  String dataStr = goso.toJson(sentMp);

					  Date currentDate = new Date();
					  String sapReceRsp =  DMSInterfaceUtil.sendCustomer2dms(dataStr);
					  String msgId = ReceClaimRecordUtil.getCurrentTimeStrS()+(int)(Math.random()*10000);
					  SAPInterfaceLogInfo logInfo = new SAPInterfaceLogInfo();
				        logInfo.setNumber(msgId);
				        logInfo.setBizDate(currentDate);
				        logInfo.setInterType(SAPInterTypeMenu.CUSTOMER_DMS);
				        logInfo.setClaimType(ClaimTypeMenu.CurrMonth);
				        logInfo.setInterResult(InterResultMenu.SUCCESS);
				        logInfo.setReqTime(currentDate);
				        logInfo.setRequest(dataStr);
				        logInfo.setRespond(sapReceRsp);
				        logInfo.setDescription("未匹配到客户名称发送至DMS");
				        try {
							SAPInterfaceLogFactory.getLocalInstance(ctx).addnew(logInfo);
						} catch (EASBizException e) {
							e.printStackTrace();
						 }
				        
				  }
			  }
		} catch (SQLException e) {
 			e.printStackTrace();
		} 
		return super._sentCustomer2DMS(ctx);
	}
	
}