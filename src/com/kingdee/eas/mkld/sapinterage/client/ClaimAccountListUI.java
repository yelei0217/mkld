/**
 * output package name
 */
package com.kingdee.eas.mkld.sapinterage.client;

import java.awt.event.*;
import org.apache.log4j.Logger;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.framework.*;

/**
 * output class name
 */
public class ClaimAccountListUI extends AbstractClaimAccountListUI
{
    private static final Logger logger = CoreUIObject.getLogger(ClaimAccountListUI.class);
    
    /**
     * output class constructor
     */
    public ClaimAccountListUI() throws Exception
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
        return com.kingdee.eas.mkld.sapinterage.ClaimAccountFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected com.kingdee.bos.dao.IObjectValue createNewData()
    {
        com.kingdee.eas.mkld.sapinterage.ClaimAccountInfo objectValue = new com.kingdee.eas.mkld.sapinterage.ClaimAccountInfo();
		
        return objectValue;
    }

}