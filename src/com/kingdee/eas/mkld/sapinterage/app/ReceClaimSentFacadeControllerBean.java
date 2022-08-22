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
	 * EAS�տ���������SAP-��������
	 * @author Lei.Ye
	 */
	@Override
	protected String _sentReceClaim(Context ctx) throws BOSException {
		this.timer.reset(); 
		ReceClaimRecordUtil.doSendClaimCurrentMonth(ctx);
		logger.info("EAS�տ���������SAP-�������죬����ʱ�䣺" + this.timer.msValue());
		return super._sentReceClaim(ctx);
	}


	/**
	 *  EAS�տ���������SAP-��������
	 * @author Lei.Ye
	 * 
	 */
	@Override
	protected String _sentClaimAgain(Context ctx) throws BOSException {
		this.timer.reset(); 
		ReceClaimRecordUtil.doSendClaimAgain(ctx);
		logger.info("EAS�տ���������SAP-�������죬����ʱ�䣺" + this.timer.msValue());
		return super._sentClaimAgain(ctx);
	}

	
	/**
	 * 
	 * EAS��ĩδ����������ˮ��SAP
	 * @author Lei.Ye
	 */
	@Override
	protected String _sentReceNoClaim(Context ctx) throws BOSException {
		this.timer.reset(); 
		ReceClaimRecordUtil.doSentReceNoClaim(ctx);
		logger.info("EAS��ĩδ����������ˮ��SAP������ʱ�䣺" + this.timer.msValue());
		return super._sentReceNoClaim(ctx);
	} 
	
}