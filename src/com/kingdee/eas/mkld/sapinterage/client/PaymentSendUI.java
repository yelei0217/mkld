/**
 * output package name
 */
package com.kingdee.eas.mkld.sapinterage.client;

import java.awt.event.*;
import org.apache.log4j.Logger;

import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.fi.cas.IPaymentBill;
import com.kingdee.eas.fi.cas.PaymentBillCollection;
import com.kingdee.eas.fi.cas.PaymentBillFactory;
import com.kingdee.eas.fi.cas.PaymentBillInfo;
import com.kingdee.eas.fm.be.app.bankpay.BankPayingResultSynToOAUtil;
import com.kingdee.eas.framework.*;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordFactory;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordInfo;
import com.kingdee.eas.mkld.sapinterage.app.ClaimStatusMenu;
import com.kingdee.eas.mkld.sapinterage.app.ClaimTypeMenu;
import com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu;
import com.kingdee.eas.util.client.MsgBox;

/**
 * output class name
 */
public class PaymentSendUI extends AbstractPaymentSendUI
{
    private static final Logger logger = CoreUIObject.getLogger(PaymentSendUI.class);
    
    /**
     * output class constructor
     */
    public PaymentSendUI() throws Exception
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
        //super.btnOK_actionPerformed(e);
        if(this.txtPaymentNo.getText() !=null && !"".equals(this.txtPaymentNo.getText() )){
        	ReceClaimRecordInfo receClaimInfo = new ReceClaimRecordInfo();
			receClaimInfo.setNumber(this.txtPaymentNo.getText());
   			try {
				ReceClaimRecordFactory.getRemoteInstance().sentNoClaimMonthEnd(receClaimInfo);
				MsgBox.showInfo("付款单同步OA完成");
			} catch (Exception e1) {
				MsgBox.showError(e1.getMessage());
 				e1.printStackTrace();
			}
         }else
        	MsgBox.showError("请输入付款单号");
    }

    /**
     * output btnCancel_actionPerformed method
     */
    protected void btnCancel_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
      //  super.btnCancel_actionPerformed(e);
        this.disposeUIWindow();
    }


}