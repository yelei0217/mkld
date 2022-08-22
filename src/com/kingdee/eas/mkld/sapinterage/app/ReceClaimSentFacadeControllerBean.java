package com.kingdee.eas.mkld.sapinterage.app;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.mkld.sapinterage.app.util.ReceClaimRecordUtil;
import com.kingdee.util.LowTimer;

public class ReceClaimSentFacadeControllerBean extends AbstractReceClaimSentFacadeControllerBean
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5623787730806306563L;
	private static Logger logger = Logger.getLogger("com.kingdee.eas.mkld.sapinterage.app.ReceClaimSentFacadeControllerBean");
	private LowTimer timer = new LowTimer();

	
	/**
	 * 
	 * EAS收款认领结果传SAP-本月认领
	 * @author Lei.Ye
	 */
	@Override
	protected String _sentReceClaim(Context ctx) throws BOSException {
		this.timer.reset(); 
		ReceClaimRecordUtil.doSendClaimCurrentMonth(ctx);
		logger.info("EAS收款认领结果传SAP-当月认领，花费时间：" + this.timer.msValue());
		return super._sentReceClaim(ctx);
	}


	/**
	 *  EAS收款认领结果传SAP-次月认领
	 * @author Lei.Ye
	 * 
	 */
	@Override
	protected String _sentClaimAgain(Context ctx) throws BOSException {
		this.timer.reset(); 
		ReceClaimRecordUtil.doSendClaimAgain(ctx);
		logger.info("EAS收款认领结果传SAP-次月认领，花费时间：" + this.timer.msValue());
		return super._sentClaimAgain(ctx);
	}

	
	/**
	 * 
	 * EAS月末未认领银行流水传SAP
	 * @author Lei.Ye
	 */
	@Override
	protected String _sentReceNoClaim(Context ctx) throws BOSException {
		this.timer.reset(); 
		ReceClaimRecordUtil.doSentReceNoClaim(ctx);
		logger.info("EAS月末未认领银行流水传SAP，花费时间：" + this.timer.msValue());
		return super._sentReceNoClaim(ctx);
	} 
	
}