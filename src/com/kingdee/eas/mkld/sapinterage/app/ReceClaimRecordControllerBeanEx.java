package com.kingdee.eas.mkld.sapinterage.app;

import org.apache.log4j.Logger;
import javax.ejb.*;
import java.rmi.RemoteException;
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
	     //super._sentNoClaimMonthEnd(ctx, model);
    	ReceClaimRecordInfo info = (ReceClaimRecordInfo)model;
	     if(info.getNumber()!=null && !"".equals(info.getNumber()) ){
	    	 String paymentNo = info.getNumber();
//	    	 try {
//	    		  IPaymentBill ibiz = PaymentBillFactory.getLocalInstance(ctx);
//			  	  EntityViewInfo viewInfo = new EntityViewInfo();
//			        FilterInfo filter = new FilterInfo();
//			        filter.getFilterItems().add(new FilterItemInfo("number",paymentNo,CompareType.EQUALS));//付款单号
//			        viewInfo.setFilter(filter);
//			        if(ibiz.exists(filter)){
//			     		PaymentBillCollection coll = ibiz.getPaymentBillCollection(viewInfo);
//		        		if(coll !=null && coll.size() > 0){
//		        			PaymentBillInfo pInfo = coll.get(0);
//		        			BankPayingResultSynToOAUtil.synPayMentBill(ctx, pInfo.getId().toString());
//		        		}else {
//						     String str = String.format("付款单单号:%s不存在", new Object[] { paymentNo });
//						     throw new EASBizException(new NumericExceptionSubItem(str, str));
//				        }
//			        }else {
//					     String str = String.format("付款单单号:%s不存在", new Object[] { paymentNo });
//					     throw new EASBizException(new NumericExceptionSubItem(str, str));
//			        }
//			} catch (EASBizException e) {
// 				e.printStackTrace();
//			} 
	    	 
	    	try {
				if( BankPayingResultSynToOAUtil.judgePayMentExists(ctx,"p",paymentNo)){
					BankPayingResultSynToOAUtil.synPayMentBillByBillNo(ctx,"p",paymentNo);
				}else{
				     String str = String.format("付款单单号:%s不存在", new Object[] { paymentNo });
				     throw new EASBizException(new NumericExceptionSubItem(str, str));
				}
			} catch (EASBizException e) {
 				e.printStackTrace();
			}
	    		
	     }
	    	 
    }
}				
