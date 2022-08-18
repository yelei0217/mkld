package com.kingdee.eas.fm.be.app;

import java.util.Map;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.fm.be.BankPayingBillStateEnum;
import com.kingdee.eas.fm.be.app.bankpay.BankPayingResultSynUtil;

public class BankPayingBillControllerBeanEx extends BankPayingBillControllerBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6654478325311378323L;

	@Override
	protected Map _updateState(Context ctx, IObjectPK[] pks,
			BankPayingBillStateEnum state) throws BOSException, EASBizException {
		Map result =  super._updateState(ctx, pks, state);
		for (IObjectPK objectPK : pks) {
			BankPayingResultSynUtil.synPayMentBill(ctx, objectPK.toString());
		}
 		return result;
	}

	
}
