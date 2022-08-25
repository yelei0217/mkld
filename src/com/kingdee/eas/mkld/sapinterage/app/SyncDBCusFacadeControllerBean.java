package com.kingdee.eas.mkld.sapinterage.app;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.fm.be.app.bankpay.BankPayingResultSynToOAUtil;

public class SyncDBCusFacadeControllerBean extends AbstractSyncDBCusFacadeControllerBean
{
    private static Logger logger =
        Logger.getLogger("com.kingdee.eas.mkld.sapinterage.app.SyncDBCusFacadeControllerBean");

	@Override
	protected void _SyncCustomer(Context ctx, String data) throws BOSException {
		// TODO Auto-generated method stub
		super._SyncCustomer(ctx, data);
		BankPayingResultSynToOAUtil.synPayMentBill(ctx, "");
	}
    
    
}