package com.kingdee.eas.fi.cas.app;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectStringPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.fi.cas.ReceivingBillFactory;
import com.kingdee.eas.fi.cas.ReceivingBillInfo;
import com.kingdee.eas.fm.be.TransDetailFactory;
import com.kingdee.eas.fm.be.TransDetailInfo;
import com.kingdee.eas.mkld.sapinterage.IReceClaimRecord;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecord;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordFactory;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordInfo;
import com.kingdee.eas.mkld.sapinterage.app.util.ReceClaimRecordUtil;
import com.kingdee.eas.mkld.sapinterage.common.InterfaceResource;

public class ReceivingBillControllerBeanPIEx extends ReceivingBillControllerBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1482146512956572715L;
	
	@Override
	protected IObjectPK _addnew(Context ctx, IObjectValue model)
			throws BOSException, EASBizException {
 		IObjectPK pk = super._addnew(ctx, model);
 		 if(InterfaceResource.MKLD_DB_ID.equals(ctx.getAIS()))
 			 ReceClaimRecordUtil.savaRecordBill(ctx, pk.toString());
		return pk;
	}
	

}
