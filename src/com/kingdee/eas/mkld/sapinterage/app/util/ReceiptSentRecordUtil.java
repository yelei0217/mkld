package com.kingdee.eas.mkld.sapinterage.app.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogFactory;
import com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogInfo;
import com.kingdee.eas.mkld.sapinterage.app.ClaimTypeMenu;
import com.kingdee.eas.mkld.sapinterage.app.InterResultMenu;
import com.kingdee.eas.mkld.sapinterage.app.SAPInterTypeMenu;
import com.kingdee.eas.mkld.sapinterage.common.InterfaceResource;
import com.kingdee.eas.mkld.sapinterage.common.SAPInterfaceUtil;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;

public class ReceiptSentRecordUtil {

	/**
	 * 
	 * 根据 交易记录生成回单发送记录
	 * @param ctx
	 * @throws BOSException
	 */
	public static void doGenReceiptRecord(Context ctx) throws BOSException {
		//删除 7天之前的 数据
		String delSql = "/*dialect*/ delete from CT_SIG_ReceiptSentRecord where FCREATETIME < to_date(to_char(sysdate-7,'yyyy/mm/dd'),'yyyy/mm/dd') ";
		DbUtil.execute(ctx, delSql);
		
		StringBuffer sbr = new StringBuffer();
		sbr.append("/*dialect*/insert into CT_SIG_ReceiptSentRecord ").append("\r\n");
		sbr.append(" (FID,FNumber,FName_l2,CFRECEIPTNO,CFSOURCETYPE,CFSENTFLAG,FSIMPLENAME,CFTRANPACKAGEID,CFCOMPANYID,FCREATETIME,FLASTUPDATETIME )").append("\r\n");
		sbr.append(" select  newbosid('314BE638'),a.FID,a.FRECEDBILLNUMBER, a.FRECEIPTNO,1,0,a.FBANKCHECKFLAG,a.FTRANPACKAGEID,a.FCOMPANYID, sysdate,a.FBIZTIME    ").append("\r\n");
		sbr.append(" from T_BE_TransDetail a ").append("\r\n");
		sbr.append(" inner join T_BD_AccountBanks b on a.FBANKACCOUNTID = b.FID ").append("\r\n");
		sbr.append(" where b.FBANKACCOUNTNUMBER in ( ").append(InterfaceResource.Rece_Account_Ids).append(" ) ").append("\r\n");// -- 业务类型 普通
		sbr.append(" and a.FRECEIPTNO  is not null ").append("\r\n");// -- 是否跟电子回单匹配 是
		sbr.append(" and a.FRECEDBILLTYPE='FA44FD5B' ").append("\r\n");// -- 接收单据类型 收款单
		sbr.append(" and a.FBIZTIME between to_date(to_char(sysdate-4,'yyyy/mm/dd'),'yyyy/mm/dd') and to_date(to_char(sysdate-1,'yyyy/mm/dd'),'yyyy/mm/dd')").append("\r\n");
		//sbr.append(" and to_char(FBIZTIME,'yyyy/mm/dd') ='2022/05/20' ").append("\r\n"); //测试固定时间
		sbr.append(" and not exists (select * from CT_SIG_ReceiptSentRecord r where r.FNUMBER = a.FID ) ").append("\r\n");
		sbr.append(" order by  a.FBIZDATE desc  ");
		DbUtil.execute(ctx, sbr.toString());
		sbr.setLength(0);
		sbr = new StringBuffer();
		sbr.append(" /*dialect*/insert into CT_SIG_ReceiptSentRecord").append("\r\n");
		sbr.append(" (FID,FNumber,FName_l2,CFRECEIPTNO,CFSOURCETYPE,CFSENTFLAG,FSIMPLENAME,CFTRANPACKAGEID,CFCOMPANYID,FCREATETIME,FLASTUPDATETIME )").append("\r\n");
		sbr.append(" select newbosid('314BE638'),a.FID,c.FNUMBER , a.FRECEIPTNO,2,0,a.FBANKCHECKFLAG,a.FTRANPACKAGEID,a.FCOMPANYID, sysdate,a.FBIZTIME").append("\r\n");
		sbr.append(" from T_BE_TransDetail a ").append("\r\n");
		sbr.append(" inner join T_BE_BankPayingBill b on a.FBANKCHECKFLAG = b.FBANKCHECKFLAG ").append("\r\n");
		sbr.append(" inner join T_CAS_PaymentBill c on  b.FSOURCEBILLID = c.FID ").append("\r\n");
		sbr.append(" inner join T_BD_AccountBanks d on a.FBANKACCOUNTID = d.FID ").append("\r\n");
		sbr.append(" where d.FBANKACCOUNTNUMBER in ( ").append(InterfaceResource.Rece_Account_Ids).append(" ) ").append("\r\n");// //-- 业务类型 普通
		sbr.append(" and a.FBizType = 1 and  a.FRECEIPTNO  is not null ").append("\r\n"); //-- 是否跟电子回单匹配 是
		sbr.append(" and a.FIsKDRetFlag= 1 ").append("\r\n");  //-- 是否为EAS银企付款
//		sbr.append(" and to_char(FBIZTIME,'yyyy/mm/dd') ='2022/05/20'").append("\r\n");
		sbr.append(" and not exists (select * from CT_SIG_ReceiptSentRecord r where r.FNUMBER = a.FID )").append("\r\n");
		sbr.append(" and a.FBIZTIME between to_date(to_char(sysdate-4,'yyyy/mm/dd'),'yyyy/mm/dd') and to_date(to_char(sysdate-1,'yyyy/mm/dd'),'yyyy/mm/dd')").append("\r\n");
		sbr.append(" order by a.FBIZDATE desc ");
		DbUtil.execute(ctx, sbr.toString());
		
    	Date currentDate = new Date();

		 String msgId = ReceClaimRecordUtil.getCurrentTimeStrS()+(int)(Math.random()*10000);
	     SAPInterfaceLogInfo logInfo = new SAPInterfaceLogInfo();
	     logInfo.setNumber(msgId);
	     logInfo.setBizDate(currentDate);
	     logInfo.setInterType(SAPInterTypeMenu.Receipt_Sent);
	     logInfo.setClaimType(ClaimTypeMenu.CurrMonth);
	     logInfo.setInterResult(InterResultMenu.SUCCESS);
	     logInfo.setReqTime(currentDate);
	     logInfo.setRequest("");
	     logInfo.setRespond("");
	     logInfo.setDescription("根据交易记录生成回单发送记录");
	     try {
		 	SAPInterfaceLogFactory.getLocalInstance(ctx).addnew(logInfo);
		 } catch (EASBizException e) {
			 e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * EAS银行回单号码传SAP
	 * @param ctx
	 * @throws BOSException
	 */
	public static void doSentReceiptRecord(Context ctx) throws BOSException {
		String sql ="select FID,FNumber,FName_l2,CFRECEIPTNO,CFSOURCETYPE,FSIMPLENAME,CFTRANPACKAGEID,CFCOMPANYID FROM CT_SIG_ReceiptSentRecord WHERE CFSENTFLAG != 1 ";
		IRowSet rs = DbUtil.executeQuery(ctx, sql);
		List lists= new ArrayList();
		Set rIds = new HashSet();
		  try {
			if(rs!=null){
				  while(rs.next()){
					 if(rs.getObject("FName_l2") !=null && !"".equals(rs.getObject("FName_l2").toString()) && 
							 rs.getObject("CFRECEIPTNO") !=null && !"".equals(rs.getObject("CFRECEIPTNO").toString()) &&
							 rs.getObject("FID") !=null && !"".equals(rs.getObject("FID").toString()) 
					 ){
							Map<String,String> mp = new HashMap<String,String>();
							mp.put("EASID", rs.getObject("FName_l2").toString());
							mp.put("BANKNO", rs.getObject("CFRECEIPTNO").toString());
							//mp.put("", rs.getObject("CFSOURCETYPE").toString());
							rIds.add(rs.getObject("FID").toString());
							lists.add(mp);
					 }
				  }
			  }
		} catch (SQLException e) {
 			e.printStackTrace();
		}
		  
		if(lists != null && lists.size() > 0){
	        //1、根据封装的 list 拼接发送信息
	        Gson gson = new Gson();
	        Map dataMp = new HashMap();
	        dataMp.put("ITEMS", lists);
	        String dataStr  = gson.toJson(dataMp);
	        
	        String msgId = ReceClaimRecordUtil.getCurrentTimeStrS()+(int)(Math.random()*10000);
	        String snedData = SAPInterfaceUtil.createSendReqStr(msgId,"FICO_I049",dataStr);
	        System.out.println("snedData:"+snedData);
	        String sapReceRsp = SAPInterfaceUtil.sendSapRequest(snedData);
	        System.out.println("sapReceRsp:"+sapReceRsp);
	    	Date currentDate = new Date();
			 boolean sendResult = SAPInterfaceUtil.judgeInteSuccess(sapReceRsp);
	       InterResultMenu resultemnu = InterResultMenu.FAIL;
 	        if(sendResult)
	    	   resultemnu = InterResultMenu.SUCCESS;
	        SAPInterfaceLogInfo logInfo = new SAPInterfaceLogInfo();
	        logInfo.setNumber(msgId);
	        logInfo.setBizDate(currentDate);
	        logInfo.setInterType(SAPInterTypeMenu.Receipt_Sent);
	        logInfo.setClaimType(ClaimTypeMenu.CurrMonth);
	        logInfo.setInterResult(resultemnu);
	        logInfo.setReqTime(currentDate);
	        logInfo.setRequest(dataStr);
	        logInfo.setRespond(sapReceRsp);
	        logInfo.setDescription("EAS银行回单号码传SAP");
		     try {
			 	SAPInterfaceLogFactory.getLocalInstance(ctx).addnew(logInfo);
			 } catch (EASBizException e) {
					e.printStackTrace();
			 }
		   if(rIds !=null && rIds.size() >0 && InterResultMenu.SUCCESS_VALUE.equals(resultemnu.getValue())) 
			      updateRecordSendSta(ctx, rIds,InterResultMenu.SUCCESS_VALUE);
			   else
			      updateRecordSendSta(ctx, rIds,InterResultMenu.FAIL_VALUE);	
	       
		   rIds.clear();
		   lists.clear();
		}
	}
	
	/**
	 *  修改记录单 同步状态
	 * @param ctx
	 * @param rIds
	 * @param type
	 * @param oper
	 */
	private static void updateRecordSendSta(Context ctx,Set rIds,String oper){
	 if(rIds !=null && rIds.size() > 0 ){
		StringBuffer sbr = new StringBuffer();
		Iterator it = rIds.iterator();
	    String fid = "";
	      while (it.hasNext())
	      {
	        fid = (String)it.next();
	        sbr.append("'").append(fid).append("'").append(",");
	      }
	      String ids = "";
	      
	      	int sendFlag = 0;
	      if(InterResultMenu.FAIL_VALUE.equals(oper))
	    	  sendFlag = 2;
	      else if(InterResultMenu.SUCCESS_VALUE.equals(oper)) 
	    	  sendFlag = 1;
	      
	      if (sbr.length() > 0)
	      {
	    	  ids = sbr.substring(0, sbr.length() - 1);
	    	  String sql ="update CT_SIG_ReceiptSentRecord set CFSENTFLAG = "+sendFlag+" where fid in ("+ids+")";
	    	  System.out.println("SQL:"+sql);
	    	  try {
					DbUtil.execute(ctx, sql);
				} catch (BOSException e) {
		 			e.printStackTrace();
				}
	      }
	
	 	}
	}
}
