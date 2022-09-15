package com.kingdee.eas.fm.be.app;

import java.util.Map;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.fm.be.BankPayingBillStateEnum;
import com.kingdee.eas.fm.be.app.bankpay.BankPayingResultSynToOAUtil;
import com.kingdee.eas.mkld.sapinterage.common.InterfaceResource;

public class BankPayingBillControllerBeanEx extends BankPayingBillControllerBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6654478325311378323L;

	@Override
	protected Map _updateState(Context ctx, IObjectPK[] pks,
			BankPayingBillStateEnum state) throws BOSException, EASBizException {
		Map result =  super._updateState(ctx, pks, state);
		 if(InterfaceResource.MKLD_DB_ID.equals(ctx.getAIS())){
			for (IObjectPK objectPK : pks) {
				//判断oa 是否符合传递条件
				if(BankPayingResultSynToOAUtil.judgePayMentExists(ctx, "ep", objectPK.toString()))
					BankPayingResultSynToOAUtil.synPayMentBillByBillNo(ctx, "ep", objectPK.toString());
			}
		 }
 		return result;
	}

	
}
