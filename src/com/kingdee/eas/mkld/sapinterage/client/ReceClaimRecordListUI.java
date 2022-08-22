/**
 * output package name
 */
package com.kingdee.eas.mkld.sapinterage.client;

import java.awt.event.*;

import org.apache.log4j.Logger;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.framework.*;
import com.kingdee.eas.mkld.sapinterage.ReceClaimSentFacadeFactory;
import com.kingdee.eas.util.client.MsgBox;

/**
 * output class name
 */
public class ReceClaimRecordListUI extends AbstractReceClaimRecordListUI
{
    private static final Logger logger = CoreUIObject.getLogger(ReceClaimRecordListUI.class);
    
    /**
     * output class constructor
     */
    public ReceClaimRecordListUI() throws Exception
    {
        super();
    }

    /**
     * output storeFields method
     */
    public void storeFields()
    {
        super.storeFields();
    }
    
    @Override
    public void actionHelp_actionPerformed(ActionEvent e) throws Exception {
     	super.actionHelp_actionPerformed(e);
    }

    @Override
	public void actionSentNoClaimMonthEnd_actionPerformed(ActionEvent e)
			throws Exception {
    	ReceClaimSentFacadeFactory.getRemoteInstance().sentReceNoClaim();
    	MsgBox.showInfo("EAS��ĩδ����������ˮ��SAP--���������");
	}

	@Override
	public void actionSentReceClaim_actionPerformed(ActionEvent e)
			throws Exception {
		ReceClaimSentFacadeFactory.getRemoteInstance().sentReceClaim();
		MsgBox.showInfo("EAS�տ���������SAP--���������");
	}

	@Override
	public void actionSentClaimAgain_actionPerformed(ActionEvent e)
			throws Exception {
		ReceClaimSentFacadeFactory.getRemoteInstance().sentClaimAgain();
		MsgBox.showInfo("EAS�տ����������δ���SAP--���������");
	}

	/**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.mkld.sapinterage.ReceClaimRecordFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected com.kingdee.bos.dao.IObjectValue createNewData()
    {
        com.kingdee.eas.mkld.sapinterage.ReceClaimRecordInfo objectValue = new com.kingdee.eas.mkld.sapinterage.ReceClaimRecordInfo();
		
        return objectValue;
    }

}