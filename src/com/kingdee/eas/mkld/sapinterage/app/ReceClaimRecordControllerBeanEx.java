package com.kingdee.eas.mkld.sapinterage.app;

import org.apache.log4j.Logger;
import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import com.kingdee.bos.*;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.metadata.IMetaDataPK;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.metadata.rule.RuleExecutor;
import com.kingdee.bos.metadata.MetaDataPK;
//import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.framework.ejb.AbstractEntityControllerBean;
import com.kingdee.bos.framework.ejb.AbstractBizControllerBean;
//import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.IObjectCollection;
import com.kingdee.bos.service.ServiceContext;
import com.kingdee.bos.service.IServiceContext;

import com.kingdee.eas.fi.cas.IPaymentBill;
import com.kingdee.eas.fi.cas.PaymentBillCollection;
import com.kingdee.eas.fi.cas.PaymentBillFactory;
import com.kingdee.eas.fi.cas.PaymentBillInfo;
import com.kingdee.eas.fm.be.app.bankpay.BankPayingResultSynToOAUtil;
import com.kingdee.eas.framework.app.CoreBillBaseControllerBean;
import com.kingdee.eas.framework.ObjectBaseCollection;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordCollection;
import com.kingdee.eas.mkld.sapinterage.ReceClaimSentFacadeFactory;

import java.lang.String;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBillBaseCollection;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordInfo;
import com.kingdee.eas.mkld.sapinterage.common.InterfaceResource;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.util.NumericExceptionSubItem;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;

public class ReceClaimRecordControllerBeanEx extends com.kingdee.eas.mkld.sapinterage.app.ReceClaimRecordControllerBean
{
    private static Logger logger =
        Logger.getLogger("com.kingdee.eas.mkld.sapinterage.app.ReceClaimRecordControllerBeanEx");
    protected void _sentReceClaim(Context ctx, IObjectValue model)throws BOSException
    {
	     super._sentReceClaim(ctx, model);
	     
	     ReceClaimSentFacadeFactory.getLocalInstance(ctx).sentReceClaim();
	     
    }
    protected void _sentNoClaimMonthEnd(Context ctx, IObjectValue model)throws BOSException
    {
    	 Map<String, String> mp = InterfaceResource.getAccount2MDSRale(ctx);
    	 System.out.println(mp.size());
    	 
    		String receAccountIds = "";
			HashSet<String> set = InterfaceResource.getReceAccountIdSets(ctx);
			Iterator it = set.iterator();
			while(it.hasNext()){
				receAccountIds = receAccountIds+"'"+it.next()+"',";
			}
			receAccountIds = receAccountIds.substring(0, receAccountIds.length()-1);
			
    	 HashSet<String>  set2 = InterfaceResource.getReceCompanyIdSets(ctx);
    	 
	     //super._sentNoClaimMonthEnd(ctx, model);
//    	ReceClaimRecordInfo info = (ReceClaimRecordInfo)model;
//	     if(info.getNumber()!=null && !"".equals(info.getNumber()) ){
//	    	 String paymentNo = info.getNumber();
//	    	 
//				if( BankPayingResultSynToOAUtil.judgePayMentExists(ctx,"p",paymentNo)){
//					BankPayingResultSynToOAUtil.synPayMentBillByBillNo(ctx,"p",paymentNo);
//				}else{
//				     String str = String.format("付款单单号:%s不存在", new Object[] { paymentNo });
// 				    throw new BOSException("付款单发送异常【"+str+"】");
//				}
//	     }
	    	 
    }
}				
