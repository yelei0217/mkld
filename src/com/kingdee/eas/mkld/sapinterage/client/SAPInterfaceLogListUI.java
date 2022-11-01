/**
 * output package name
 */
package com.kingdee.eas.mkld.sapinterage.client;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.mkld.sapinterage.ReceClaimSentFacadeFactory;
import com.kingdee.eas.mkld.sapinterage.SyncDBCusFacadeFactory;

/**
 * output class name
 */
public class SAPInterfaceLogListUI extends AbstractSAPInterfaceLogListUI
{
    private static final Logger logger = CoreUIObject.getLogger(SAPInterfaceLogListUI.class);
   
    @Override
    public void onLoad() throws Exception {
     	super.onLoad();
     	
//     	this.btnAddNew.setVisible(false);
//     	this.btnEdit.setVisible(false);
//     	this.btnRemove.setVisible(false);
//     	this.btnCreateTo.setVisible(false);
//
//     	this.menuItemAddNew.setVisible(false);
//     	this.menuItemEdit.setVisible(false);
//     	this.menuItemRemove.setVisible(false);
//     	this.menuItemCreateTo.setVisible(false);
    }
    /**
     * output class constructor
     */
    public SAPInterfaceLogListUI() throws Exception
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
    	// TODO Auto-generated method stub
//    	super.actionHelp_actionPerformed(e);
//    	SyncDBCusFacadeFactory.getRemoteInstance().SyncCustomer("");
    	
    	ReceClaimSentFacadeFactory.getRemoteInstance().sentCustomer2DMS();
     
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected com.kingdee.bos.dao.IObjectValue createNewData()
    {
        com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogInfo objectValue = new com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogInfo();
		
        return objectValue;
    }

}