package com.kingdee.eas.mkld.sapinterage.app.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import com.kingdee.bos.dao.ormapping.ObjectStringPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.basedata.assistant.AccountBankFactory;
import com.kingdee.eas.basedata.assistant.AccountBankInfo;
import com.kingdee.eas.basedata.assistant.CurrencyFactory;
import com.kingdee.eas.basedata.assistant.CurrencyInfo;
import com.kingdee.eas.basedata.org.CompanyOrgUnitFactory;
import com.kingdee.eas.basedata.org.CompanyOrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.fi.cas.ReceivingBillFactory;
import com.kingdee.eas.fi.cas.ReceivingBillInfo;
import com.kingdee.eas.fm.be.TransDetailFactory;
import com.kingdee.eas.fm.be.TransDetailInfo;
import com.kingdee.eas.mkld.sapinterage.IReceClaimRecord;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordCollection;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordFactory;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordInfo;
import com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogFactory;
import com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogInfo;
import com.kingdee.eas.mkld.sapinterage.app.AmtCateMenu;
import com.kingdee.eas.mkld.sapinterage.app.ClaimStatusMenu;
import com.kingdee.eas.mkld.sapinterage.app.ClaimTypeMenu;
import com.kingdee.eas.mkld.sapinterage.app.InterResultMenu;
import com.kingdee.eas.mkld.sapinterage.app.SAPInterTypeMenu;
import com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu;
import com.kingdee.eas.mkld.sapinterage.app.dto.MonthEndNoClaimDTO;
import com.kingdee.eas.mkld.sapinterage.app.dto.ReceClaimDTO;
import com.kingdee.eas.mkld.sapinterage.common.InterfaceResource;
import com.kingdee.eas.mkld.sapinterage.common.SAPInterfaceUtil;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.util.UuidException;
 
public class ReceClaimRecordUtil {

	/**
	 *  获取当前时间 格式化
	 * @return yyyyMMddHHmmss
	 */
	public static String getCurrentTimeStr(){
  		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
	}

	/**
	 *  获取当前时间 格式化
	 * @return yyyyMMddHHmmssSSS
	 */
	public static String getCurrentTimeStrS(){
 		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(System.currentTimeMillis()));
	}
	
	/**
	 *  收款单申请单保存时 新增付款认领记录单
	 * @param ctx
	 * @param rId 付款单ID
	 */
	public static void savaRecordBill(Context ctx,String rId){
		try {
			ReceivingBillInfo bInfo = ReceivingBillFactory.getLocalInstance(ctx).getReceivingBillInfo(new ObjectStringPK(rId));
			//收款单-财务组织ID在   收款公司id集合
			if (InterfaceResource.getReceCompanyIdSets(ctx).contains(bInfo.getCompany().getId().toString()) &&
					bInfo.getSourceBillId() != null && !"".equals(bInfo.getSourceBillId())) {
				String sId = bInfo.getSourceBillId();
				String stype = BOSUuid.read(sId).getType().toString();
				//收款 账号
				AccountBankInfo accountInfo = AccountBankFactory.getLocalInstance(ctx).getAccountBankInfo(new ObjectUuidPK(bInfo.getPayeeAccountBank().getId()));
				String bankAccount = accountInfo.getBankAccountNumber();
				//交易明细
				if("380D4F63".equals(stype) && InterfaceResource.getReceAccountIdSets(ctx).contains(bankAccount)){
					TransDetailInfo tdInfo = TransDetailFactory.getLocalInstance(ctx).getTransDetailInfo(new ObjectStringPK(sId));
	                FilterInfo filter = new FilterInfo();
	                filter.getFilterItems().add(new FilterItemInfo("PaymentId",rId,CompareType.EQUALS));
		            IReceClaimRecord ibiz = ReceClaimRecordFactory.getLocalInstance(ctx);
	                if(!ibiz.exists(filter) && tdInfo.getBizType() == com.kingdee.eas.fm.be.BizTypeEnum.normal){
	                	ReceClaimRecordInfo rInfo = new ReceClaimRecordInfo();
						rInfo.setNumber(getCurrentTimeStrS()+tdInfo.getNumber());
						
						CompanyOrgUnitInfo company = CompanyOrgUnitFactory.getLocalInstance(ctx).getCompanyOrgUnitInfo(new ObjectUuidPK(bInfo.getCompany().getId()));
						rInfo.setFICompany(bInfo.getCompany());
						rInfo.setCompanyNumber(company.getNumber());
						rInfo.setBizDate(bInfo.getBizDate());
						//往来户类型
						rInfo.setCustomerNo("E99999999");
						rInfo.setClaimType(ClaimTypeMenu.CurrMonth);
						rInfo.setClaimStatus(ClaimStatusMenu.No);
						
						boolean isCustoemr = false ;
						if(bInfo.getPayerType()!=null && bInfo.getPayerType().getId() != null && !"".equals( bInfo.getPayerType().getId().toString())){
							if("YW3xsAEJEADgAAUWwKgTB0c4VZA=".equals(bInfo.getPayerType().getId().toString()))//客户
							{
								rInfo.setCustomerNo(bInfo.getPayerNumber());
							//	rInfo.setClaimType(ClaimTypeMenu.CurrMonth);
								rInfo.setClaimStatus(ClaimStatusMenu.Yes);
								isCustoemr = true;
							}
						} 
						
						rInfo.setPayerName(tdInfo.getOppUnit());
						rInfo.setDescription(tdInfo.getNumber());
						rInfo.setAbstract(bInfo.getDescription());
						 
						rInfo.setPaymentId(rId);
						rInfo.setPaymentNo(bInfo.getNumber());
						rInfo.setTrsreq(tdInfo.getTranPackageID());
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						String bizdateStr = format.format(bInfo.getBizDate());
						rInfo.setYear(Integer.parseInt(bizdateStr.substring(0, 4)));
						rInfo.setMonth(Integer.parseInt(bizdateStr.substring(5, 7)));
				
						rInfo.setBankAccount(bankAccount);
						
						//根据收款银行账号获取事业部
						if(InterfaceResource.getAccount2MDSRale(ctx).get(bankAccount) !=null && !"".equals(InterfaceResource.getAccount2MDSRale(ctx).get(bankAccount))){
							if(isCustoemr)
								rInfo.setDmsSendStatus(SendStatusMenu.SentS);
							else
								rInfo.setDmsSendStatus(SendStatusMenu.UnSent);
							rInfo.setBusDeptName(InterfaceResource.getAccount2MDSRale(ctx).get(bankAccount));
						}else
							rInfo.setDmsSendStatus(SendStatusMenu.SentS);
						
						rInfo.setReceDate(bInfo.getBizDate());
						rInfo.setReceAmount(bInfo.getActRecAmt());
						rInfo.setLoans(BigDecimal.ZERO);//贷款
						rInfo.setMargin(BigDecimal.ZERO);//保证金
						rInfo.setDeposit(BigDecimal.ZERO);//押金
						if(bInfo.getDescription()!=null && !"".equals(bInfo.getDescription())){
							
							if(bInfo.getDescription().contains(AmtCateMenu.Loans.getAlias())){
								rInfo.setLoans(bInfo.getActRecAmt());
							}
							if(bInfo.getDescription().contains(AmtCateMenu.Margin.getAlias())){
								rInfo.setMargin(bInfo.getActRecAmt());
							}
							if(bInfo.getDescription().contains(AmtCateMenu.Deposit.getAlias())){
								rInfo.setDeposit(bInfo.getActRecAmt());
							}
						}
						if(rInfo.getLoans().compareTo(BigDecimal.ZERO)==0 &&
								rInfo.getMargin().compareTo(BigDecimal.ZERO)==0 &&
								rInfo.getDeposit().compareTo(BigDecimal.ZERO)==0 )
							rInfo.setLoans(bInfo.getActRecAmt());
						
 						rInfo.setFirstSentFlag(SendStatusMenu.UnSent);
						rInfo.setSendSentFlag(SendStatusMenu.UnSent);
						CurrencyInfo currInfo =CurrencyFactory.getLocalInstance(ctx).getCurrencyInfo(new ObjectUuidPK(bInfo.getCurrency().getId()));
						rInfo.setCurrencyNo(currInfo.getIsoCode());
 						ibiz.addnew(rInfo);
	                }
				}
			}
		} catch (EASBizException e) {
 			e.printStackTrace();
		} catch (BOSException e) {
 			e.printStackTrace();
		} catch (UuidException e) {
 			e.printStackTrace();
		} 
	}
	
	/**
	 *  修改收款认领记录单 同步状态
	 * @param ctx
	 * @param rIds
	 * @param type
	 * @param oper
	 */
	private static void updateRecordSendSta(Context ctx,Set rIds,String type,String oper){
	 if(rIds !=null && rIds.size() > 0 && oper !=null &&!"".equals(oper)&& type !=null &&!"".equals(type)){
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
	    	 // String field = "CFFirstSentFlag";
	    	  String updateSql = "";
	    	  if("3".equals(type))
	    		//   field = "CFSendSentFlag";
	    		  updateSql=" CFSendSentFlag ="+sendFlag +" ";
	    	  else if("2".equals(type))
		    		//   field = "CFSendSentFlag";
	    		  updateSql=" CFFirstSentFlag ="+sendFlag +",CFClaimType = 'B' ";
	    	  else
	    		  updateSql=" CFFirstSentFlag ="+sendFlag +" ";
	    	  
	    	  String sql ="update CT_SIG_ReceClaimRecord set "+updateSql+" where fid in ("+ids+")";
//	    	  System.out.println("SQL:"+sql);
	    	  try {
					DbUtil.execute(ctx, sql);
				} catch (BOSException e) {
		 			e.printStackTrace();
				}
	      	}
	
	 	}
	}
	
	/**
	 *  修改收款认领记录单 同步状态
	 * @param ctx
	 * @param rIds
	 * @param type
	 * @param oper
	 */
	private static void updateRecordSendSta(Context ctx,String id,String type,String oper){
	 if( id !=null &&!"".equals(id) &&!"".equals(oper)&& type !=null &&!"".equals(type)){
		//StringBuffer sbr = new StringBuffer();
	      int sendFlag = 0;
	      if(InterResultMenu.FAIL_VALUE.equals(oper))
	    	  sendFlag = 2;
	      else if(InterResultMenu.SUCCESS_VALUE.equals(oper)) 
	    	  sendFlag = 1;
//	      if (sbr.length() > 0)
//	      {
	    	  String updateSql = "";
	    	  if("3".equals(type))
	    		  updateSql=" CFSendSentFlag ="+sendFlag +" ";
	    	  else if("2".equals(type))
	    		  updateSql=" CFFirstSentFlag ="+sendFlag +",CFClaimType = 'B' ";
	    	  else
	    		  updateSql=" CFFirstSentFlag ="+sendFlag +" ";
	    	  
	    	  String sql ="update CT_SIG_ReceClaimRecord set "+updateSql+" where fid = '"+id+"'";

	    	  try {
					DbUtil.execute(ctx, sql);
				} catch (BOSException e) {
		 			e.printStackTrace();
				}
//	      	}
	 	}
	}
	
	
	/**
	 * @param ctx
	 * @throws BOSException
	 * 
	 *  EAS收款认领结果传SAP-本月认领
	 * 
	 */
	public static void doSendClaimCurrentMonth(Context ctx) throws BOSException {
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
       // List<ReceClaimDTO> lists = null ;
        if(rcoll !=null && rcoll.size() > 0){
        	Iterator it = rcoll.iterator();
        	Date currentDate = new Date();
        	Gson gson = new Gson();
        	//Set rIds = new HashSet();
        	while(it.hasNext()){
        		List<ReceClaimDTO> 	lists = new ArrayList<ReceClaimDTO>();
        		String rid ="";
        		ReceClaimRecordInfo rInfo = (ReceClaimRecordInfo) it.next();
        		ReceClaimDTO dto = new  ReceClaimDTO();
        		dto.setEASID(rInfo.getPaymentNo());//  收款单单号
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
        		rid = rInfo.getId().toString();
        		lists.add(dto);
        		
                //1、根据封装的 list 拼接发送信息
             
                Map dataMp = new HashMap();
                dataMp.put("ITEMS", lists);
                String dataStr  = gson.toJson(dataMp);
                String msgId = getCurrentTimeStrS()+(int)(Math.random()*10000);
                String snedData = SAPInterfaceUtil.createSendReqStr(msgId,"FICO_I012",dataStr);
                System.out.println("snedData:"+snedData);
                String sapReceRsp = SAPInterfaceUtil.sendSapRequest(snedData);
                System.out.println("sapReceRsp:"+sapReceRsp);
                
                lists.clear();
                dataMp.clear();
                
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
        			
        		     if(rid !=null && !"".equals(rid) && InterResultMenu.SUCCESS_VALUE.equals(resultemnu.getValue())) 
        		    	 updateRecordSendSta(ctx, rid,"1",InterResultMenu.SUCCESS_VALUE);
        		     else
        		    	 updateRecordSendSta(ctx, rid,"1",InterResultMenu.FAIL_VALUE);
        	} 

        }
	}
	
	/**
	 * @param ctx
	 * @throws BOSException
	 * @author Lei.ye
	 * 
	 *  EAS收款认领结果传SAP-本月认领 二次发送
	 */
	public static void doSendClaimAgain(Context ctx) throws BOSException {
		//获取已认领，一次未发送的记录数据
        IReceClaimRecord ibiz = ReceClaimRecordFactory.getLocalInstance(ctx);
        EntityViewInfo viewInfo = new EntityViewInfo();
        FilterInfo filter = new FilterInfo();
        filter.getFilterItems().add(new FilterItemInfo("ClaimType",ClaimTypeMenu.NextMonth,CompareType.EQUALS));//认领类型：次月认领
        filter.getFilterItems().add(new FilterItemInfo("ClaimStatus",ClaimStatusMenu.Yes,CompareType.EQUALS));//认领状态：已认领
        filter.getFilterItems().add(new FilterItemInfo("FirstSentFlag",SendStatusMenu.SentS,CompareType.EQUALS));//第一次发送SAP状态:发送成功
        filter.getFilterItems().add(new FilterItemInfo("SendSentFlag",SendStatusMenu.UnSent,CompareType.EQUALS));//第二次发送SAP状态:未发送
        filter.getFilterItems().add(new FilterItemInfo("SendSentFlag",SendStatusMenu.SentF,CompareType.EQUALS));//第二次发送SAP状态:发送失败
//        filter.getFilterItems().add(new FilterItemInfo("CustomerNo","800000",CompareType.EQUALS));//客户编码： 一次性客户编码 800000
        filter.getFilterItems().add(new FilterItemInfo("CustomerNo","E99999999",CompareType.EQUALS));//客户编码：未匹配到客户 E99999999
 
        filter.setMaskString("#0 and #1 and #2 and (#3 or #4) and #5");
        viewInfo.setFilter(filter);
        ReceClaimRecordCollection rcoll = ibiz.getReceClaimRecordCollection(viewInfo);
       // List<ReceClaimDTO> lists = null ;
        if(rcoll !=null && rcoll.size() > 0){
        	Iterator it = rcoll.iterator();
        	//lists = new ArrayList<ReceClaimDTO>();
        	Date currentDate = new Date();
        	//Set rIds = new HashSet();          
        	Gson gson = new Gson();
        	while(it.hasNext()){
        		List<ReceClaimDTO> lists  = new ArrayList<ReceClaimDTO>();
        		String rid ="";
        		
        		ReceClaimRecordInfo rInfo = (ReceClaimRecordInfo) it.next();
        		ReceClaimDTO dto = new  ReceClaimDTO();
        		dto.setEASID(rInfo.getPaymentNo());//  收款单单号
        		dto.setZTYPE(ClaimTypeMenu.NEXTMONTH_VALUE);//    认领类型
        		dto.setBUKRS(rInfo.getCompanyNumber());//   公司代码
        		dto.setBANKN(rInfo.getBankAccount());//   收款银行账号
        		dto.setBUDAT(new SimpleDateFormat("yyyyMMdd").format(rInfo.getReceDate()));//   收款日期
        		dto.setBUDAT2(new SimpleDateFormat("yyyyMMdd").format(rInfo.getAgainClaimDate()));//  再次认领日期
        		dto.setWAERS(rInfo.getCurrencyNo());
        		dto.setKUNNR(rInfo.getAgainClaimCusNo());//   再次认领客户编码
        		dto.setDMBTR(rInfo.getReceAmount());//   收款金额
        		dto.setDMBTR_HK(rInfo.getLoans());//  其中货款金额
        		dto.setDMBTR_BZJ(rInfo.getMargin());//  其中保证金金额
        		dto.setDMBTR_YJ(rInfo.getDeposit());//  其中押金金额
        		dto.setSGTXT(rInfo.getAbstract());//    摘要    
        		rid = rInfo.getId().toString();
        		lists.add(dto);
        		
        	    //1、根据封装的 list 拼接发送信息
      
                Map dataMp = new HashMap();
                dataMp.put("ITEMS", lists);
                String dataStr  = gson.toJson(dataMp);

                String msgId = getCurrentTimeStrS()+(int)(Math.random()*10000);
                String snedData = SAPInterfaceUtil.createSendReqStr(msgId,"FICO_I012",dataStr);
                System.out.println("snedData:"+snedData);
                String sapReceRsp = SAPInterfaceUtil.sendSapRequest(snedData);
                System.out.println("sapReceRsp:"+sapReceRsp);
                
                lists.clear();
                dataMp.clear();
                
        		 boolean sendResult = SAPInterfaceUtil.judgeInteSuccess(sapReceRsp);
               InterResultMenu resultemnu = InterResultMenu.FAIL;
                if(sendResult)
            	   resultemnu = InterResultMenu.SUCCESS;
                SAPInterfaceLogInfo logInfo = new SAPInterfaceLogInfo();
                logInfo.setNumber(msgId);
                logInfo.setBizDate(currentDate);
                logInfo.setInterType(SAPInterTypeMenu.FICO_I012);
                logInfo.setClaimType(ClaimTypeMenu.NextMonth);
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
        			
        			if(rid !=null && !"".equals(rid) && InterResultMenu.SUCCESS_VALUE.equals(resultemnu.getValue())) 
        		    	 updateRecordSendSta(ctx, rid,"3",InterResultMenu.SUCCESS_VALUE);
        		     else
        		    	 updateRecordSendSta(ctx, rid,"3",InterResultMenu.FAIL_VALUE);
        	} 
    
        }
	}


	/**
	 * 
	 * EAS月末未认领银行流水传SAP
	 * @author Lei.Ye
	 */
	public static void doSentReceNoClaim(Context ctx) throws BOSException {
		//获取已认领，一次未发送的记录数据
        IReceClaimRecord ibiz = ReceClaimRecordFactory.getLocalInstance(ctx);
        EntityViewInfo viewInfo = new EntityViewInfo();
        FilterInfo filter = new FilterInfo();
        filter.getFilterItems().add(new FilterItemInfo("ClaimType",ClaimTypeMenu.CurrMonth,CompareType.EQUALS));//认领类型：本月认领
        filter.getFilterItems().add(new FilterItemInfo("ClaimStatus",ClaimStatusMenu.No,CompareType.EQUALS));//认领状态：未认领
        filter.getFilterItems().add(new FilterItemInfo("FirstSentFlag",SendStatusMenu.UnSent,CompareType.EQUALS));//第一次发送SAP状态:未发送
        filter.getFilterItems().add(new FilterItemInfo("FirstSentFlag",SendStatusMenu.SentF,CompareType.EQUALS));//第一次发送SAP状态:未发送
//        filter.getFilterItems().add(new FilterItemInfo("CustomerNo","800000",CompareType.EQUALS));//客户编码： 一次性客户编码 800000
        filter.getFilterItems().add(new FilterItemInfo("CustomerNo","E99999999",CompareType.EQUALS));//客户编码： 未匹配到客户编码 E99999999
        filter.setMaskString("#0 and #1 and (#2 or #3) and #4");
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
        		dto.setEASID(rInfo.getPaymentNo());//  收款单单号
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

        String msgId = getCurrentTimeStrS()+(int)(Math.random()*10000);
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
        logInfo.setClaimType(ClaimTypeMenu.CurrMonth);
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
		    	 updateRecordSendSta(ctx, rIds,"2",InterResultMenu.SUCCESS_VALUE);
		     else
		    	 updateRecordSendSta(ctx, rIds,"2",InterResultMenu.FAIL_VALUE);
        } 
	}
	
	
	/***
	 * 修改认领记录单状态
	 * @param ctx 上下文
	 * @param cusNumber 客户编码
	 * @param cusName   客户名称
	 * @throws BOSException
	 */
	public static void doUpdateClaimStaByCusName(Context ctx,String cusNumber,String cusName) throws BOSException {
		if(cusName !=null && !"".equals(cusName) && cusNumber !=null && !"".equals(cusNumber)){
			// 如果没有发送第一次，或者第一次发送失败
			String updateSql = "update CT_SIG_ReceClaimRecord set CFCustomerNo='"+cusNumber+"',CFClaimStatus=1,CFDmsSendStatus=1,CFClaimType='A' where CFCustomerNo ='E99999999' and CFPayerName ='"+cusName+"' and CFFirstSentFlag != 1 and CFSendSentFlag = 0 ";
			DbUtil.execute(ctx,updateSql);
		
			// 已完成月底未认领发送
			updateSql = "/*dialect*/update CT_SIG_ReceClaimRecord set CFAgainClaimDate =sysdate, CFAgainClaimCusNo='"+cusNumber+"',CFClaimStatus=1,CFDmsSendStatus=1,CFClaimType='B' where CFCustomerNo ='E99999999' and CFPayerName ='"+cusName+"' and CFFirstSentFlag = 1 and CFSendSentFlag != 1 ";
			DbUtil.execute(ctx,updateSql);
		}
	}
	

	/**
	 * 	当付款单付完款-根据付款单上扩展字段“收款单编号”修改认领记录单状态【客户编码默认：800000】
	 * @param ctx	上下文
	 * @param number EAS收款单编号
	 * @throws BOSException
	 */
	public static void doUpdateClaimStaByNumber(Context ctx,String number) throws BOSException {
		if(number !=null && !"".equals(number)){
			String updateSql = "update CT_SIG_ReceClaimRecord set CFCustomerNo='"+InterfaceResource.DEFAULT_CUSTOMER_NO+"',CFClaimStatus=1,CFDmsSendStatus=1 where CFCustomerNo='E99999999' and CFClaimStatus = 0 and CFPaymentNo ='"+number+"' and CFFirstSentFlag != 1 and CFSendSentFlag = 0 ";
			DbUtil.execute(ctx,updateSql);
		}
	}
	
}
