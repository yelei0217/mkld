/**
 * output package name
 */
package com.kingdee.eas.mkld.sapinterage.client;

import java.awt.event.*;
import org.apache.log4j.Logger;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.framework.*;
import com.kingdee.eas.mkld.sapinterage.common.DMSInterfaceUtil;
import com.kingdee.eas.util.client.MsgBox;

/**
 * output class name
 */
public class DmsSendUI extends AbstractDmsSendUI
{
    private static final Logger logger = CoreUIObject.getLogger(DmsSendUI.class);
    
    /**
     * output class constructor
     */
    public DmsSendUI() throws Exception
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
     * output btnOK_actionPerformed method
     */
    protected void btnOK_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
        if(this.txtSentJson.getText() !=null && !"".equals(this.txtSentJson.getText())){
        	DMSInterfaceUtil.sendCustomer2dms(this.txtPaymentNo.getText());
        }else
        	MsgBox.showError("请输入请求参数");
    }

    /**
     * output btnCancel_actionPerformed method
     */
    protected void btnCancel_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    	 this.disposeUIWindow();
    }

     

}