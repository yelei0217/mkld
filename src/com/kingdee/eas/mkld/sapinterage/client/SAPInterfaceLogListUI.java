/**
 * output package name
 */
package com.kingdee.eas.mkld.sapinterage.client;

import org.apache.log4j.Logger;

import com.kingdee.bos.ui.face.CoreUIObject;

/**
 * output class name
 */
public class SAPInterfaceLogListUI extends AbstractSAPInterfaceLogListUI
{
    private static final Logger logger = CoreUIObject.getLogger(SAPInterfaceLogListUI.class);
    
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