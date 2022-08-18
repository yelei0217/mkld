package com.kingdee.eas.mkld.sapinterage.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.client.HttpClient;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.mkld.sapinterage.IReceClaimRecord;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordCollection;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordFactory;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordInfo;
import com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogFactory;
import com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogInfo;
import com.kingdee.eas.mkld.sapinterage.app.dto.MonthEndNoClaimDTO;
import com.kingdee.eas.mkld.sapinterage.app.dto.ReceClaimDTO;
import com.kingdee.eas.mkld.sapinterage.app.dto.SapReceRspDTO;
import com.kingdee.eas.mkld.sapinterage.app.util.ReceClaimRecordUtil;
import com.kingdee.eas.mkld.sapinterage.common.SAPInterfaceUtil;
import com.kingdee.eas.mkld.sapinterage.common.InterfaceResource;
import com.kingdee.eas.mkld.sapinterage.rest.HTTPSClientUtil;
import com.kingdee.eas.mkld.sapinterage.rest.HTTPSTrustClient;

public class ReceClaimSentFacadeControllerBean extends AbstractReceClaimSentFacadeControllerBean
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5623787730806306563L;
	private static Logger logger = Logger.getLogger("com.kingdee.eas.mkld.sapinterage.app.ReceClaimSentFacadeControllerBean");
	
	
	/**
	 * 
	 * 发送收款认领结果
	 * @author Lei.Ye
	 */
	@Override
	protected String _sentReceClaim(Context ctx) throws BOSException {
		sendClaimCurrentMonth(ctx); 
		
		sendClaimNexttMonth(ctx);
		return super._sentReceClaim(ctx);
	}



	/**
	 * @param ctx
	 * @throws BOSException
	 * 
	 * 本月认领 传递SAP
	 * 
	 */
	private void sendClaimCurrentMonth(Context ctx) throws BOSException {
		//获取已认领，一次未发送的记录数据
        IReceClaimRecord ibiz = ReceClaimRecordFactory.getLocalInstance(ctx);
        EntityViewInfo viewInfo = new EntityViewInfo();
        FilterInfo filter = new FilterInfo();
        filter.getFilterItems().add(new FilterItemInfo("ClaimType",ClaimTypeMenu.CurrMonth,CompareType.EQUALS));//认领类型：本月认领
        filter.getFilterItems().add(new FilterItemInfo("ClaimStatus",ClaimStatusMenu.Yes,CompareType.EQUALS));//认领状态：已认领
        filter.getFilterItems().add(new FilterItemInfo("FirstSentFlag",SendStatusMenu.UnSent,CompareType.EQUALS));//第一次发送SAP状态:未发送
        filter.getFilterItems().add(new FilterItemInfo("FirstSentFlag",SendStatusMenu.SentF,CompareType.EQUALS));//第一次发送SAP状态:未发送
        filter.setMaskString("#0 and #1 and (#2 or #3)");
        viewInfo.setFilter(filter);
        ReceClaimRecordCollection rcoll = ibiz.getReceClaimRecordCollection(viewInfo);
        List<ReceClaimDTO> lists = null ;
        if(rcoll !=null && rcoll.size() > 0){
        	Iterator it = rcoll.iterator();
        	lists = new ArrayList<ReceClaimDTO>();
        	Date currentDate = new Date();
        	Set rIds = new HashSet();
        	while(it.hasNext()){
        		ReceClaimRecordInfo rInfo = (ReceClaimRecordInfo) it.next();
        		ReceClaimDTO dto = new  ReceClaimDTO();
        		dto.setEASID(rInfo.getPaymentId());//  收款单ID
        		dto.setZTYPE(ClaimTypeMenu.CURRMONTH_VALUE);//    认领类型
        		dto.setBUKRS(rInfo.getCompanyNumber());//   公司代码
        		dto.setBANKN(rInfo.getBankAccount());//   收款银行账号
        		dto.setBUDAT(new SimpleDateFormat("yyyyMMdd").format(rInfo.getReceDate()));//   收款日期
        		//dto.setBUDAT2();//  再次认领日期
        		dto.setWAERS(rInfo.getCurrencyNo());
        		dto.setKUNNR(rInfo.getCustomerNo());//   客户编码
        		dto.setDMBTR(rInfo.getReceAmount());//   收款金额
        		dto.setDMBTR_HK(rInfo.getLoans());//  其中货款金额
        		dto.setDMBTR_BZJ(rInfo.getMargin());//  其中保证金金额
        		dto.setDMBTR_YJ(rInfo.getDeposit());//  其中押金金额
        		dto.setSGTXT(rInfo.getAbstract());//    摘要    
        		rIds.add(rInfo.getId().toString());
        		lists.add(dto);
        	} 
        	
        //1、根据封装的 list 拼接发送信息
        Gson gson = new Gson();
        Map dataMp = new HashMap();
        dataMp.put("ITEMS", lists);
        String dataStr  = gson.toJson(dataMp);

        String msgId = ReceClaimRecordUtil.getCurrentTimeStrS()+(int)(Math.random()*10000);
        String snedData = SAPInterfaceUtil.createSendReqStr(msgId,"FICO_I012",dataStr);
        System.out.println("snedData:"+snedData);
        String sapReceRsp = SAPInterfaceUtil.sendSapRequest(snedData);
        System.out.println("sapReceRsp:"+sapReceRsp);
		 boolean sendResult = SAPInterfaceUtil.judgeInteSuccess(sapReceRsp);
       InterResultMenu resultemnu = InterResultMenu.FAIL;
        if(sendResult)
    	   resultemnu = InterResultMenu.SUCCESS;
        SAPInterfaceLogInfo logInfo = new SAPInterfaceLogInfo();
        logInfo.setNumber(msgId);
        logInfo.setBizDate(currentDate);
        logInfo.setInterType(SAPInterTypeMenu.FICO_I012);
        logInfo.setClaimType(ClaimTypeMenu.CurrMonth);
        logInfo.setInterResult(resultemnu);
        logInfo.setReqTime(currentDate);
        logInfo.setRequest(dataStr);
        logInfo.setRespond(sapReceRsp);
        logInfo.setDescription("EAS收款认领结果传SAP:本月认领");
	        try {
				SAPInterfaceLogFactory.getLocalInstance(ctx).addnew(logInfo);
			} catch (EASBizException e) {
				e.printStackTrace();
			 }
			
		     if(rIds !=null && rIds.size() >0 && InterResultMenu.SUCCESS_VALUE.equals(resultemnu.getValue())) 
		    	 ReceClaimRecordUtil.updateRecordSendSta(ctx, rIds,"",InterResultMenu.SUCCESS_VALUE);
		     else
		    	 ReceClaimRecordUtil.updateRecordSendSta(ctx, rIds,"",InterResultMenu.FAIL_VALUE);
        }
	}
	
	/**
	 * @param ctx
	 * @throws BOSException
	 * 
	 * 次月认领 传递SAP
	 */
	private void sendClaimNexttMonth(Context ctx) throws BOSException {
		//获取已认领，一次未发送的记录数据
        IReceClaimRecord ibiz = ReceClaimRecordFactory.getLocalInstance(ctx);
        EntityViewInfo viewInfo = new EntityViewInfo();
        FilterInfo filter = new FilterInfo();
        filter.getFilterItems().add(new FilterItemInfo("ClaimType",ClaimTypeMenu.NextMonth,CompareType.EQUALS));//认领类型：本月认领
        filter.getFilterItems().add(new FilterItemInfo("ClaimStatus",ClaimStatusMenu.Yes,CompareType.EQUALS));//认领状态：已认领
        filter.getFilterItems().add(new FilterItemInfo("FirstSentFlag",SendStatusMenu.SentS,CompareType.EQUALS));//第一次发送SAP状态:发送成功
        filter.getFilterItems().add(new FilterItemInfo("SendSentFlag",SendStatusMenu.UnSent,CompareType.EQUALS));//第二次发送SAP状态:未发送
        filter.getFilterItems().add(new FilterItemInfo("SendSentFlag",SendStatusMenu.SentF,CompareType.EQUALS));//第二次发送SAP状态:发送失败
        filter.setMaskString("#0 and #1 and #2 and (#3 or #4)");
        viewInfo.setFilter(filter);
        ReceClaimRecordCollection rcoll = ibiz.getReceClaimRecordCollection(viewInfo);
        List<ReceClaimDTO> lists = null ;
        if(rcoll !=null && rcoll.size() > 0){
        	Iterator it = rcoll.iterator();
        	lists = new ArrayList<ReceClaimDTO>();
        	Date currentDate = new Date();
        	Set rIds = new HashSet();
        	while(it.hasNext()){
        		ReceClaimRecordInfo rInfo = (ReceClaimRecordInfo) it.next();
        		ReceClaimDTO dto = new  ReceClaimDTO();
        		dto.setEASID(rInfo.getPaymentId());//  收款单ID
        		dto.setZTYPE(ClaimTypeMenu.NEXTMONTH_VALUE);//    认领类型
        		dto.setBUKRS(rInfo.getCompanyNumber());//   公司代码
        		dto.setBANKN(rInfo.getBankAccount());//   收款银行账号
        		dto.setBUDAT(new SimpleDateFormat("yyyyMMdd").format(rInfo.getReceDate()));//   收款日期
        		//dto.setBUDAT2();//  再次认领日期
        		dto.setWAERS(rInfo.getCurrencyNo());
        		dto.setKUNNR(rInfo.getCustomerNo());//   客户编码
        		dto.setDMBTR(rInfo.getReceAmount());//   收款金额
        		dto.setDMBTR_HK(rInfo.getLoans());//  其中货款金额
        		dto.setDMBTR_BZJ(rInfo.getMargin());//  其中保证金金额
        		dto.setDMBTR_YJ(rInfo.getDeposit());//  其中押金金额
        		dto.setSGTXT(rInfo.getAbstract());//    摘要    
        		rIds.add(rInfo.getId().toString());
        		lists.add(dto);
        	} 
        	
        //1、根据封装的 list 拼接发送信息
        Gson gson = new Gson();
        Map dataMp = new HashMap();
        dataMp.put("ITEMS", lists);
        String dataStr  = gson.toJson(dataMp);

        String msgId = ReceClaimRecordUtil.getCurrentTimeStrS()+(int)(Math.random()*10000);
        String snedData = SAPInterfaceUtil.createSendReqStr(msgId,"FICO_I012",dataStr);
        System.out.println("snedData:"+snedData);
        String sapReceRsp = SAPInterfaceUtil.sendSapRequest(snedData);
        System.out.println("sapReceRsp:"+sapReceRsp);
		 boolean sendResult = SAPInterfaceUtil.judgeInteSuccess(sapReceRsp);
       InterResultMenu resultemnu = InterResultMenu.FAIL;
        if(sendResult)
    	   resultemnu = InterResultMenu.SUCCESS;
        SAPInterfaceLogInfo logInfo = new SAPInterfaceLogInfo();
        logInfo.setNumber(msgId);
        logInfo.setBizDate(currentDate);
        logInfo.setInterType(SAPInterTypeMenu.FICO_I012);
        logInfo.setClaimType(ClaimTypeMenu.CurrMonth);
        logInfo.setInterResult(resultemnu);
        logInfo.setReqTime(currentDate);
        logInfo.setRequest(dataStr);
        logInfo.setRespond(sapReceRsp);
        logInfo.setDescription("EAS收款认领结果传SAP:次月认领");
	        try {
				SAPInterfaceLogFactory.getLocalInstance(ctx).addnew(logInfo);
			} catch (EASBizException e) {
				e.printStackTrace();
			 }
			
		     if(rIds !=null && rIds.size() >0 && InterResultMenu.SUCCESS_VALUE.equals(resultemnu.getValue())) 
		    	 ReceClaimRecordUtil.updateRecordSendSta(ctx, rIds,"3",InterResultMenu.SUCCESS_VALUE);
		     else
		    	 ReceClaimRecordUtil.updateRecordSendSta(ctx, rIds,"3",InterResultMenu.FAIL_VALUE);
        }
	}
	
	/**
	 * 
	 * 发送月末未认领
	 * @author Lei.Ye
	 */
	@Override
	protected String _sentReceNoClaim(Context ctx) throws BOSException {
		//获取已认领，一次未发送的记录数据
        IReceClaimRecord ibiz = ReceClaimRecordFactory.getLocalInstance(ctx);
        EntityViewInfo viewInfo = new EntityViewInfo();
        FilterInfo filter = new FilterInfo();
        filter.getFilterItems().add(new FilterItemInfo("ClaimType",ClaimTypeMenu.NextMonth,CompareType.EQUALS));//认领类型：次月认领
        filter.getFilterItems().add(new FilterItemInfo("ClaimStatus",ClaimStatusMenu.No,CompareType.EQUALS));//认领状态：未认领
        filter.getFilterItems().add(new FilterItemInfo("FirstSentFlag",SendStatusMenu.UnSent,CompareType.EQUALS));//第一次发送SAP状态:未发送
        filter.getFilterItems().add(new FilterItemInfo("FirstSentFlag",SendStatusMenu.SentF,CompareType.EQUALS));//第一次发送SAP状态:未发送
        filter.setMaskString("#0 and #1 and (#2 or #3)");
        viewInfo.setFilter(filter);
        ReceClaimRecordCollection rcoll = ibiz.getReceClaimRecordCollection(viewInfo);
        List<MonthEndNoClaimDTO> lists = null ;
        if(rcoll !=null && rcoll.size() > 0){
        	Iterator it = rcoll.iterator();
        	lists = new ArrayList<MonthEndNoClaimDTO>();
        	Date currentDate = new Date();
        	Set rIds = new HashSet();
        	while(it.hasNext()){
        		ReceClaimRecordInfo rInfo = (ReceClaimRecordInfo) it.next();
        		MonthEndNoClaimDTO dto = new  MonthEndNoClaimDTO();
        		dto.setEASID(rInfo.getPaymentId());//  收款单ID
         		dto.setBUKRS(rInfo.getCompanyNumber());//   公司代码
         		dto.setGJAHR(rInfo.getYear()+"");
         		dto.setMONAT(rInfo.getMonth()+"");
         		dto.setWAERS(rInfo.getCurrencyNo());
        		dto.setBANKN(rInfo.getBankAccount());//   收款银行账号
        		dto.setBUDAT(new SimpleDateFormat("yyyyMMdd").format(rInfo.getReceDate()));//   收款日期
        		dto.setDMBTR(rInfo.getReceAmount());
        		dto.setSGTXT(rInfo.getAbstract());//    摘要    
        		rIds.add(rInfo.getId().toString());
        		lists.add(dto);
        	} 
        	
        //1、根据封装的 list 拼接发送信息
        Gson gson = new Gson();
        Map dataMp = new HashMap();
        dataMp.put("ITEMS", lists);
        String dataStr  = gson.toJson(dataMp);

        String msgId = ReceClaimRecordUtil.getCurrentTimeStrS()+(int)(Math.random()*10000);
        String snedData = SAPInterfaceUtil.createSendReqStr(msgId,"FICO_I013",dataStr);
        System.out.println("snedData:"+snedData);
        String sapReceRsp = SAPInterfaceUtil.sendSapRequest(snedData);
        System.out.println("sapReceRsp:"+sapReceRsp);
		 boolean sendResult = SAPInterfaceUtil.judgeInteSuccess(sapReceRsp);
       InterResultMenu resultemnu = InterResultMenu.FAIL;
        if(sendResult)
    	   resultemnu = InterResultMenu.SUCCESS;
        SAPInterfaceLogInfo logInfo = new SAPInterfaceLogInfo();
        logInfo.setNumber(msgId);
        logInfo.setBizDate(currentDate);
        logInfo.setInterType(SAPInterTypeMenu.FICO_I013);
        logInfo.setClaimType(ClaimTypeMenu.NextMonth);
        logInfo.setInterResult(resultemnu);
        logInfo.setReqTime(currentDate);
        logInfo.setRequest(dataStr);
        logInfo.setRespond(sapReceRsp);
        logInfo.setDescription("EAS月末未认领银行流水传SAP");
	        try {
				SAPInterfaceLogFactory.getLocalInstance(ctx).addnew(logInfo);
			} catch (EASBizException e) {
				e.printStackTrace();
			 }
			
		     if(rIds !=null && rIds.size() >0 && InterResultMenu.SUCCESS_VALUE.equals(resultemnu.getValue())) 
		    	 ReceClaimRecordUtil.updateRecordSendSta(ctx, rIds,"",InterResultMenu.SUCCESS_VALUE);
		     else
		    	 ReceClaimRecordUtil.updateRecordSendSta(ctx, rIds,"",InterResultMenu.FAIL_VALUE);
        } 
		return super._sentReceNoClaim(ctx);
	
	} 
	
}