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
    	ReceClaimSentFacadeFactory.getRemoteInstance().sentReceClaim();
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