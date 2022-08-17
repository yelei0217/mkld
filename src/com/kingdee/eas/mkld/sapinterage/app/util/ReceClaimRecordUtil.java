package com.kingdee.eas.mkld.sapinterage.app.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.ormapping.ObjectStringPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
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
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordFactory;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordInfo;
import com.kingdee.eas.mkld.sapinterage.app.AmtCateMenu;
import com.kingdee.eas.mkld.sapinterage.app.ClaimStatusMenu;
import com.kingdee.eas.mkld.sapinterage.app.ClaimTypeMenu;
import com.kingdee.eas.mkld.sapinterage.app.InterResultMenu;
import com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu;
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
	
	public static void savaRecordBill(Context ctx,String rId){
		try {
			ReceivingBillInfo bInfo = ReceivingBillFactory.getLocalInstance(ctx).getReceivingBillInfo(new ObjectStringPK(rId));
			if (bInfo.getSourceBillId() != null && !"".equals(bInfo.getSourceBillId())) {
				String sId = bInfo.getSourceBillId();
				String stype = BOSUuid.read(sId).getType().toString();
				//交易明细
				if("380D4F63".equals(stype)){
					TransDetailInfo tdInfo = TransDetailFactory.getLocalInstance(ctx).getTransDetailInfo(new ObjectStringPK(sId));
	                FilterInfo filter = new FilterInfo();
	                filter.getFilterItems().add(new FilterItemInfo("PaymentId",rId,CompareType.EQUALS));
		            IReceClaimRecord ibiz = ReceClaimRecordFactory.getLocalInstance(ctx);
	                if(!ibiz.exists(filter)){
	                	ReceClaimRecordInfo rInfo = new ReceClaimRecordInfo();
						rInfo.setNumber(getCurrentTimeStrS()+tdInfo.getNumber());
						
						CompanyOrgUnitInfo company = CompanyOrgUnitFactory.getLocalInstance(ctx).getCompanyOrgUnitInfo(new ObjectUuidPK(bInfo.getCompany().getId()));
						rInfo.setFICompany(bInfo.getCompany());
						rInfo.setCompanyNumber(company.getNumber());
						rInfo.setBizDate(bInfo.getBizDate());
						//往来户类型
						//rInfo.setCustomerNo("999999");
						rInfo.setClaimType(ClaimTypeMenu.NextMonth);
						rInfo.setClaimStatus(ClaimStatusMenu.No);
						if(bInfo.getPayerType()!=null && bInfo.getPayerType().getId() != null && !"".equals( bInfo.getPayerType().getId().toString())){
							if("YW3xsAEJEADgAAUWwKgTB0c4VZA=".equals(bInfo.getPayerType().getId().toString()))//客户
							{
								rInfo.setCustomerNo(bInfo.getPayerNumber());
								rInfo.setClaimType(ClaimTypeMenu.CurrMonth);
								rInfo.setClaimStatus(ClaimStatusMenu.Yes);
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
						AccountBankInfo accountInfo = AccountBankFactory.getLocalInstance(ctx).getAccountBankInfo(new ObjectUuidPK(bInfo.getPayeeAccountBank().getId()));
						rInfo.setBankAccount(accountInfo.getBankAccountNumber());
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
	
	public static void updateRecordSendSta(Context ctx,Set rIds,String type,String oper){
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
	    	  String field = "CFFirstSentFlag";
	    	  if("3".equals(type))
	    		   field = "CFSendSentFlag";
	    	 
	    		  
	    	  String sql ="update CT_SIG_ReceClaimRecord set "+field+" = "+sendFlag+" where fid in ("+ids+")";
	    	  try {
					DbUtil.execute(ctx, sql);
				} catch (BOSException e) {
		 			e.printStackTrace();
				}
	      }
	
	 	}
	}
	
	
	
}
