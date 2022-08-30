/**
 * output package name
 */
package com.kingdee.eas.mkld.sapinterage.client;

import java.awt.event.*;
import org.apache.log4j.Logger;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.framework.*;
import com.kingdee.bos.ctrl.kdf.table.KDTable;

/**
 * output class name
 */
public class ReceClaimRecordEditUI extends AbstractReceClaimRecordEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(ReceClaimRecordEditUI.class);
    
    @Override
    public void onLoad() throws Exception {
    	super.onLoad();
     	
    	this.btnAddNew.setVisible(false);
     	//this.btnEdit.setVisible(false);
     	this.btnRemove.setVisible(false);
     	this.btnCopy.setVisible(false);
     	this.btnCreateFrom.setVisible(false);
     	this.btnCreateTo.setVisible(false);
     	this.btnAddLine.setVisible(false);
     	
     	this.menuItemAddNew.setVisible(false);
     	//this.menuItemEdit.setVisible(false);
     	this.menuItemRemove.setVisible(false);
     	this.menuItemCopy.setVisible(false);
     	this.menuItemCreateFrom.setVisible(false);
     	this.menuItemCreateTo.setVisible(false);	
    }
    
    /**
     * output class constructor
     */
    public ReceClaimRecordEditUI() throws Exception
    {
        super();
    }
    /**
     * output loadFields method
     */
    public void loadFields()
    {
        super.loadFields();
    }

    /**
     * output storeFields method
     */
    public void storeFields()
    {
        super.storeFields();
    }

    
    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.mkld.sapinterage.ReceClaimRecordFactory.getRemoteInstance();
    }

    /**
     * output createNewDetailData method
     */
    protected IObjectValue createNewDetailData(KDTable table)
    {
		
        return null;
    }

    /**
     * output createNewData method
     */
    protected com.kingdee.bos.dao.IObjectValue createNewData()
    {
        com.kingdee.eas.mkld.sapinterage.ReceClaimRecordInfo objectValue = new com.kingdee.eas.mkld.sapinterage.ReceClaimRecordInfo();
				if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Company")) != null && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Company")).getBoolean("isBizUnit"))
			objectValue.put("FICompany",com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Company")));
 
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));
		
        return objectValue;
    }

}