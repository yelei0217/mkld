/**
 * output package name
 */
package com.kingdee.eas.mkld.sapinterage.client;

import org.apache.log4j.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.event.*;
import javax.swing.KeyStroke;

import com.kingdee.bos.ctrl.swing.*;
import com.kingdee.bos.ctrl.kdf.table.*;
import com.kingdee.bos.ctrl.kdf.data.event.*;
import com.kingdee.bos.dao.*;
import com.kingdee.bos.dao.query.*;
import com.kingdee.bos.metadata.*;
import com.kingdee.bos.metadata.entity.*;
import com.kingdee.bos.ui.face.*;
import com.kingdee.bos.ui.util.ResourceBundleHelper;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.bos.service.ServiceContext;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.enums.EnumUtils;
import com.kingdee.bos.ui.face.UIRuleUtil;
import com.kingdee.bos.ctrl.swing.event.*;
import com.kingdee.bos.ctrl.kdf.table.event.*;
import com.kingdee.bos.ctrl.extendcontrols.*;
import com.kingdee.bos.ctrl.kdf.util.render.*;
import com.kingdee.bos.ui.face.IItemAction;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.bos.ui.util.IUIActionPostman;
import com.kingdee.bos.appframework.client.servicebinding.ActionProxyFactory;
import com.kingdee.bos.appframework.uistatemanage.ActionStateConst;
import com.kingdee.bos.appframework.validator.ValidateHelper;
import com.kingdee.bos.appframework.uip.UINavigator;


/**
 * output class name
 */
public abstract class AbstractReceClaimRecordEditUI extends com.kingdee.eas.framework.client.CoreBillEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractReceClaimRecordEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreator;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLastUpdateUser;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBizDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDescription;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAuditor;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contFICompany;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contPaymentId;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contpaymentNo;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conttrsreq;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contyear;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contmonth;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBankAccount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contReceDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contReceAmount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLoans;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contMargin;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDeposit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAbstract;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAgainClaimDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCustomerNo;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contClaimType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contClaimStatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contPayerName;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contSendSentFlag;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contFirstSentFlag;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCurrencyNo;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcompanyNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAgainClaimCusNo;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contdmsSendStatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBusDeptName;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtCreator;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDDateCreateTime;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtLastUpdateUser;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDDateLastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtNumber;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkBizDate;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtDescription;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtAuditor;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtFICompany;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtPaymentId;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtpaymentNo;
    protected com.kingdee.bos.ctrl.swing.KDTextField txttrsreq;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtyear;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtmonth;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtBankAccount;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkReceDate;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtReceAmount;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtLoans;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtMargin;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtDeposit;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtAbstract;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkAgainClaimDate;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtCustomerNo;
    protected com.kingdee.bos.ctrl.swing.KDComboBox ClaimType;
    protected com.kingdee.bos.ctrl.swing.KDComboBox ClaimStatus;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtPayerName;
    protected com.kingdee.bos.ctrl.swing.KDComboBox SendSentFlag;
    protected com.kingdee.bos.ctrl.swing.KDComboBox FirstSentFlag;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtCurrencyNo;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtcompanyNumber;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtAgainClaimCusNo;
    protected com.kingdee.bos.ctrl.swing.KDComboBox dmsSendStatus;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtBusDeptName;
    protected com.kingdee.eas.mkld.sapinterage.ReceClaimRecordInfo editData = null;
    protected ActionSentReceClaim actionSentReceClaim = null;
    protected ActionSentNoClaimMonthEnd actionSentNoClaimMonthEnd = null;
    protected ActionSentClaimAgain actionSentClaimAgain = null;
    /**
     * output class constructor
     */
    public AbstractReceClaimRecordEditUI() throws Exception
    {
        super();
        this.defaultObjectName = "editData";
        jbInit();
        
        initUIP();
    }

    /**
     * output jbInit method
     */
    private void jbInit() throws Exception
    {
        this.resHelper = new ResourceBundleHelper(AbstractReceClaimRecordEditUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionSubmit
        String _tempStr = null;
        actionSubmit.setEnabled(true);
        actionSubmit.setDaemonRun(false);

        actionSubmit.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
        _tempStr = resHelper.getString("ActionSubmit.SHORT_DESCRIPTION");
        actionSubmit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.LONG_DESCRIPTION");
        actionSubmit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.NAME");
        actionSubmit.putValue(ItemAction.NAME, _tempStr);
        this.actionSubmit.setBindWorkFlow(true);
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionPrint
        actionPrint.setEnabled(true);
        actionPrint.setDaemonRun(false);

        actionPrint.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl P"));
        _tempStr = resHelper.getString("ActionPrint.SHORT_DESCRIPTION");
        actionPrint.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrint.LONG_DESCRIPTION");
        actionPrint.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrint.NAME");
        actionPrint.putValue(ItemAction.NAME, _tempStr);
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionPrintPreview
        actionPrintPreview.setEnabled(true);
        actionPrintPreview.setDaemonRun(false);

        actionPrintPreview.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift ctrl P"));
        _tempStr = resHelper.getString("ActionPrintPreview.SHORT_DESCRIPTION");
        actionPrintPreview.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrintPreview.LONG_DESCRIPTION");
        actionPrintPreview.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrintPreview.NAME");
        actionPrintPreview.putValue(ItemAction.NAME, _tempStr);
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionSentReceClaim
        this.actionSentReceClaim = new ActionSentReceClaim(this);
        getActionManager().registerAction("actionSentReceClaim", actionSentReceClaim);
        this.actionSentReceClaim.setExtendProperty("canForewarn", "true");
        this.actionSentReceClaim.setExtendProperty("userDefined", "true");
        this.actionSentReceClaim.setExtendProperty("isObjectUpdateLock", "false");
         this.actionSentReceClaim.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionSentReceClaim.addService(new com.kingdee.eas.framework.client.service.ForewarnService());
        //actionSentNoClaimMonthEnd
        this.actionSentNoClaimMonthEnd = new ActionSentNoClaimMonthEnd(this);
        getActionManager().registerAction("actionSentNoClaimMonthEnd", actionSentNoClaimMonthEnd);
        this.actionSentNoClaimMonthEnd.setExtendProperty("canForewarn", "true");
        this.actionSentNoClaimMonthEnd.setExtendProperty("userDefined", "true");
        this.actionSentNoClaimMonthEnd.setExtendProperty("isObjectUpdateLock", "false");
         this.actionSentNoClaimMonthEnd.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionSentNoClaimMonthEnd.addService(new com.kingdee.eas.framework.client.service.ForewarnService());
        //actionSentClaimAgain
        this.actionSentClaimAgain = new ActionSentClaimAgain(this);
        getActionManager().registerAction("actionSentClaimAgain", actionSentClaimAgain);
        this.actionSentClaimAgain.setExtendProperty("canForewarn", "true");
        this.actionSentClaimAgain.setExtendProperty("userDefined", "true");
        this.actionSentClaimAgain.setExtendProperty("isObjectUpdateLock", "false");
         this.actionSentClaimAgain.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionSentClaimAgain.addService(new com.kingdee.eas.framework.client.service.ForewarnService());
        this.contCreator = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCreateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contLastUpdateUser = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contLastUpdateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBizDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDescription = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAuditor = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contFICompany = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contPaymentId = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contpaymentNo = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.conttrsreq = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contyear = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contmonth = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBankAccount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contReceDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contReceAmount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contLoans = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contMargin = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDeposit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAbstract = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAgainClaimDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCustomerNo = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contClaimType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contClaimStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contPayerName = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contSendSentFlag = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contFirstSentFlag = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCurrencyNo = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contcompanyNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAgainClaimCusNo = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contdmsSendStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBusDeptName = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.prmtCreator = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDDateCreateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtLastUpdateUser = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDDateLastUpdateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkBizDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtDescription = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtAuditor = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtFICompany = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtPaymentId = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtpaymentNo = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txttrsreq = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtyear = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtmonth = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtBankAccount = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkReceDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtReceAmount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtLoans = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtMargin = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtDeposit = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtAbstract = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkAgainClaimDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtCustomerNo = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.ClaimType = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.ClaimStatus = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.txtPayerName = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.SendSentFlag = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.FirstSentFlag = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.txtCurrencyNo = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtcompanyNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtAgainClaimCusNo = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.dmsSendStatus = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.txtBusDeptName = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.contCreator.setName("contCreator");
        this.contCreateTime.setName("contCreateTime");
        this.contLastUpdateUser.setName("contLastUpdateUser");
        this.contLastUpdateTime.setName("contLastUpdateTime");
        this.contNumber.setName("contNumber");
        this.contBizDate.setName("contBizDate");
        this.contDescription.setName("contDescription");
        this.contAuditor.setName("contAuditor");
        this.contFICompany.setName("contFICompany");
        this.contPaymentId.setName("contPaymentId");
        this.contpaymentNo.setName("contpaymentNo");
        this.conttrsreq.setName("conttrsreq");
        this.contyear.setName("contyear");
        this.contmonth.setName("contmonth");
        this.contBankAccount.setName("contBankAccount");
        this.contReceDate.setName("contReceDate");
        this.contReceAmount.setName("contReceAmount");
        this.contLoans.setName("contLoans");
        this.contMargin.setName("contMargin");
        this.contDeposit.setName("contDeposit");
        this.contAbstract.setName("contAbstract");
        this.contAgainClaimDate.setName("contAgainClaimDate");
        this.contCustomerNo.setName("contCustomerNo");
        this.contClaimType.setName("contClaimType");
        this.contClaimStatus.setName("contClaimStatus");
        this.contPayerName.setName("contPayerName");
        this.contSendSentFlag.setName("contSendSentFlag");
        this.contFirstSentFlag.setName("contFirstSentFlag");
        this.contCurrencyNo.setName("contCurrencyNo");
        this.contcompanyNumber.setName("contcompanyNumber");
        this.contAgainClaimCusNo.setName("contAgainClaimCusNo");
        this.contdmsSendStatus.setName("contdmsSendStatus");
        this.contBusDeptName.setName("contBusDeptName");
        this.prmtCreator.setName("prmtCreator");
        this.kDDateCreateTime.setName("kDDateCreateTime");
        this.prmtLastUpdateUser.setName("prmtLastUpdateUser");
        this.kDDateLastUpdateTime.setName("kDDateLastUpdateTime");
        this.txtNumber.setName("txtNumber");
        this.pkBizDate.setName("pkBizDate");
        this.txtDescription.setName("txtDescription");
        this.prmtAuditor.setName("prmtAuditor");
        this.prmtFICompany.setName("prmtFICompany");
        this.txtPaymentId.setName("txtPaymentId");
        this.txtpaymentNo.setName("txtpaymentNo");
        this.txttrsreq.setName("txttrsreq");
        this.txtyear.setName("txtyear");
        this.txtmonth.setName("txtmonth");
        this.txtBankAccount.setName("txtBankAccount");
        this.pkReceDate.setName("pkReceDate");
        this.txtReceAmount.setName("txtReceAmount");
        this.txtLoans.setName("txtLoans");
        this.txtMargin.setName("txtMargin");
        this.txtDeposit.setName("txtDeposit");
        this.txtAbstract.setName("txtAbstract");
        this.pkAgainClaimDate.setName("pkAgainClaimDate");
        this.txtCustomerNo.setName("txtCustomerNo");
        this.ClaimType.setName("ClaimType");
        this.ClaimStatus.setName("ClaimStatus");
        this.txtPayerName.setName("txtPayerName");
        this.SendSentFlag.setName("SendSentFlag");
        this.FirstSentFlag.setName("FirstSentFlag");
        this.txtCurrencyNo.setName("txtCurrencyNo");
        this.txtcompanyNumber.setName("txtcompanyNumber");
        this.txtAgainClaimCusNo.setName("txtAgainClaimCusNo");
        this.dmsSendStatus.setName("dmsSendStatus");
        this.txtBusDeptName.setName("txtBusDeptName");
        // CoreUI		
        this.btnTraceUp.setVisible(false);		
        this.btnTraceDown.setVisible(false);		
        this.btnCreateTo.setVisible(true);		
        this.btnAddLine.setVisible(false);		
        this.btnCopyLine.setVisible(false);		
        this.btnInsertLine.setVisible(false);		
        this.btnRemoveLine.setVisible(false);		
        this.btnAuditResult.setVisible(false);		
        this.separator1.setVisible(false);		
        this.menuItemCreateTo.setVisible(true);		
        this.separator3.setVisible(false);		
        this.menuItemTraceUp.setVisible(false);		
        this.menuItemTraceDown.setVisible(false);		
        this.menuTable1.setVisible(false);		
        this.menuItemAddLine.setVisible(false);		
        this.menuItemCopyLine.setVisible(false);		
        this.menuItemInsertLine.setVisible(false);		
        this.menuItemRemoveLine.setVisible(false);		
        this.menuItemViewSubmitProccess.setVisible(false);		
        this.menuItemViewDoProccess.setVisible(false);		
        this.menuItemAuditResult.setVisible(false);
        // contCreator		
        this.contCreator.setBoundLabelText(resHelper.getString("contCreator.boundLabelText"));		
        this.contCreator.setBoundLabelLength(100);		
        this.contCreator.setBoundLabelUnderline(true);		
        this.contCreator.setEnabled(false);		
        this.contCreator.setVisible(false);
        // contCreateTime		
        this.contCreateTime.setBoundLabelText(resHelper.getString("contCreateTime.boundLabelText"));		
        this.contCreateTime.setBoundLabelLength(100);		
        this.contCreateTime.setBoundLabelUnderline(true);		
        this.contCreateTime.setEnabled(false);		
        this.contCreateTime.setVisible(false);
        // contLastUpdateUser		
        this.contLastUpdateUser.setBoundLabelText(resHelper.getString("contLastUpdateUser.boundLabelText"));		
        this.contLastUpdateUser.setBoundLabelLength(100);		
        this.contLastUpdateUser.setBoundLabelUnderline(true);		
        this.contLastUpdateUser.setEnabled(false);		
        this.contLastUpdateUser.setVisible(false);
        // contLastUpdateTime		
        this.contLastUpdateTime.setBoundLabelText(resHelper.getString("contLastUpdateTime.boundLabelText"));		
        this.contLastUpdateTime.setBoundLabelLength(100);		
        this.contLastUpdateTime.setBoundLabelUnderline(true);		
        this.contLastUpdateTime.setEnabled(false);		
        this.contLastUpdateTime.setVisible(false);
        // contNumber		
        this.contNumber.setBoundLabelText(resHelper.getString("contNumber.boundLabelText"));		
        this.contNumber.setBoundLabelLength(100);		
        this.contNumber.setBoundLabelUnderline(true);
        // contBizDate		
        this.contBizDate.setBoundLabelText(resHelper.getString("contBizDate.boundLabelText"));		
        this.contBizDate.setBoundLabelLength(100);		
        this.contBizDate.setBoundLabelUnderline(true);		
        this.contBizDate.setBoundLabelAlignment(7);		
        this.contBizDate.setVisible(true);
        // contDescription		
        this.contDescription.setBoundLabelText(resHelper.getString("contDescription.boundLabelText"));		
        this.contDescription.setBoundLabelLength(100);		
        this.contDescription.setBoundLabelUnderline(true);
        // contAuditor		
        this.contAuditor.setBoundLabelText(resHelper.getString("contAuditor.boundLabelText"));		
        this.contAuditor.setBoundLabelLength(100);		
        this.contAuditor.setBoundLabelUnderline(true);		
        this.contAuditor.setVisible(false);
        // contFICompany		
        this.contFICompany.setBoundLabelText(resHelper.getString("contFICompany.boundLabelText"));		
        this.contFICompany.setBoundLabelLength(100);		
        this.contFICompany.setBoundLabelUnderline(true);		
        this.contFICompany.setVisible(true);
        // contPaymentId		
        this.contPaymentId.setBoundLabelText(resHelper.getString("contPaymentId.boundLabelText"));		
        this.contPaymentId.setBoundLabelLength(100);		
        this.contPaymentId.setBoundLabelUnderline(true);		
        this.contPaymentId.setVisible(true);
        // contpaymentNo		
        this.contpaymentNo.setBoundLabelText(resHelper.getString("contpaymentNo.boundLabelText"));		
        this.contpaymentNo.setBoundLabelLength(100);		
        this.contpaymentNo.setBoundLabelUnderline(true);		
        this.contpaymentNo.setVisible(true);
        // conttrsreq		
        this.conttrsreq.setBoundLabelText(resHelper.getString("conttrsreq.boundLabelText"));		
        this.conttrsreq.setBoundLabelLength(100);		
        this.conttrsreq.setBoundLabelUnderline(true);		
        this.conttrsreq.setVisible(true);
        // contyear		
        this.contyear.setBoundLabelText(resHelper.getString("contyear.boundLabelText"));		
        this.contyear.setBoundLabelLength(100);		
        this.contyear.setBoundLabelUnderline(true);		
        this.contyear.setVisible(true);
        // contmonth		
        this.contmonth.setBoundLabelText(resHelper.getString("contmonth.boundLabelText"));		
        this.contmonth.setBoundLabelLength(100);		
        this.contmonth.setBoundLabelUnderline(true);		
        this.contmonth.setVisible(true);
        // contBankAccount		
        this.contBankAccount.setBoundLabelText(resHelper.getString("contBankAccount.boundLabelText"));		
        this.contBankAccount.setBoundLabelLength(100);		
        this.contBankAccount.setBoundLabelUnderline(true);		
        this.contBankAccount.setVisible(true);
        // contReceDate		
        this.contReceDate.setBoundLabelText(resHelper.getString("contReceDate.boundLabelText"));		
        this.contReceDate.setBoundLabelLength(100);		
        this.contReceDate.setBoundLabelUnderline(true);		
        this.contReceDate.setVisible(true);
        // contReceAmount		
        this.contReceAmount.setBoundLabelText(resHelper.getString("contReceAmount.boundLabelText"));		
        this.contReceAmount.setBoundLabelLength(100);		
        this.contReceAmount.setBoundLabelUnderline(true);		
        this.contReceAmount.setVisible(true);
        // contLoans		
        this.contLoans.setBoundLabelText(resHelper.getString("contLoans.boundLabelText"));		
        this.contLoans.setBoundLabelLength(100);		
        this.contLoans.setBoundLabelUnderline(true);		
        this.contLoans.setVisible(true);
        // contMargin		
        this.contMargin.setBoundLabelText(resHelper.getString("contMargin.boundLabelText"));		
        this.contMargin.setBoundLabelLength(100);		
        this.contMargin.setBoundLabelUnderline(true);		
        this.contMargin.setVisible(true);
        // contDeposit		
        this.contDeposit.setBoundLabelText(resHelper.getString("contDeposit.boundLabelText"));		
        this.contDeposit.setBoundLabelLength(100);		
        this.contDeposit.setBoundLabelUnderline(true);		
        this.contDeposit.setVisible(true);
        // contAbstract		
        this.contAbstract.setBoundLabelText(resHelper.getString("contAbstract.boundLabelText"));		
        this.contAbstract.setBoundLabelLength(100);		
        this.contAbstract.setBoundLabelUnderline(true);		
        this.contAbstract.setVisible(true);
        // contAgainClaimDate		
        this.contAgainClaimDate.setBoundLabelText(resHelper.getString("contAgainClaimDate.boundLabelText"));		
        this.contAgainClaimDate.setBoundLabelLength(100);		
        this.contAgainClaimDate.setBoundLabelUnderline(true);		
        this.contAgainClaimDate.setVisible(true);
        // contCustomerNo		
        this.contCustomerNo.setBoundLabelText(resHelper.getString("contCustomerNo.boundLabelText"));		
        this.contCustomerNo.setBoundLabelLength(100);		
        this.contCustomerNo.setBoundLabelUnderline(true);		
        this.contCustomerNo.setVisible(true);
        // contClaimType		
        this.contClaimType.setBoundLabelText(resHelper.getString("contClaimType.boundLabelText"));		
        this.contClaimType.setBoundLabelLength(100);		
        this.contClaimType.setBoundLabelUnderline(true);		
        this.contClaimType.setVisible(true);
        // contClaimStatus		
        this.contClaimStatus.setBoundLabelText(resHelper.getString("contClaimStatus.boundLabelText"));		
        this.contClaimStatus.setBoundLabelLength(100);		
        this.contClaimStatus.setBoundLabelUnderline(true);		
        this.contClaimStatus.setVisible(true);
        // contPayerName		
        this.contPayerName.setBoundLabelText(resHelper.getString("contPayerName.boundLabelText"));		
        this.contPayerName.setBoundLabelLength(100);		
        this.contPayerName.setBoundLabelUnderline(true);		
        this.contPayerName.setVisible(true);
        // contSendSentFlag		
        this.contSendSentFlag.setBoundLabelText(resHelper.getString("contSendSentFlag.boundLabelText"));		
        this.contSendSentFlag.setBoundLabelLength(100);		
        this.contSendSentFlag.setBoundLabelUnderline(true);		
        this.contSendSentFlag.setVisible(true);
        // contFirstSentFlag		
        this.contFirstSentFlag.setBoundLabelText(resHelper.getString("contFirstSentFlag.boundLabelText"));		
        this.contFirstSentFlag.setBoundLabelLength(100);		
        this.contFirstSentFlag.setBoundLabelUnderline(true);		
        this.contFirstSentFlag.setVisible(true);
        // contCurrencyNo		
        this.contCurrencyNo.setBoundLabelText(resHelper.getString("contCurrencyNo.boundLabelText"));		
        this.contCurrencyNo.setBoundLabelLength(100);		
        this.contCurrencyNo.setBoundLabelUnderline(true);		
        this.contCurrencyNo.setVisible(true);
        // contcompanyNumber		
        this.contcompanyNumber.setBoundLabelText(resHelper.getString("contcompanyNumber.boundLabelText"));		
        this.contcompanyNumber.setBoundLabelLength(100);		
        this.contcompanyNumber.setBoundLabelUnderline(true);		
        this.contcompanyNumber.setVisible(true);
        // contAgainClaimCusNo		
        this.contAgainClaimCusNo.setBoundLabelText(resHelper.getString("contAgainClaimCusNo.boundLabelText"));		
        this.contAgainClaimCusNo.setBoundLabelLength(100);		
        this.contAgainClaimCusNo.setBoundLabelUnderline(true);		
        this.contAgainClaimCusNo.setVisible(true);
        // contdmsSendStatus		
        this.contdmsSendStatus.setBoundLabelText(resHelper.getString("contdmsSendStatus.boundLabelText"));		
        this.contdmsSendStatus.setBoundLabelLength(100);		
        this.contdmsSendStatus.setBoundLabelUnderline(true);		
        this.contdmsSendStatus.setVisible(true);
        // contBusDeptName		
        this.contBusDeptName.setBoundLabelText(resHelper.getString("contBusDeptName.boundLabelText"));		
        this.contBusDeptName.setBoundLabelLength(100);		
        this.contBusDeptName.setBoundLabelUnderline(true);		
        this.contBusDeptName.setVisible(true);
        // prmtCreator		
        this.prmtCreator.setEnabled(false);		
        this.prmtCreator.setVisible(false);
        // kDDateCreateTime		
        this.kDDateCreateTime.setTimeEnabled(true);		
        this.kDDateCreateTime.setEnabled(false);		
        this.kDDateCreateTime.setVisible(false);
        // prmtLastUpdateUser		
        this.prmtLastUpdateUser.setEnabled(false);		
        this.prmtLastUpdateUser.setVisible(false);
        // kDDateLastUpdateTime		
        this.kDDateLastUpdateTime.setTimeEnabled(true);		
        this.kDDateLastUpdateTime.setEnabled(false);		
        this.kDDateLastUpdateTime.setVisible(false);
        // txtNumber		
        this.txtNumber.setMaxLength(80);
        // pkBizDate		
        this.pkBizDate.setEnabled(true);
        // txtDescription		
        this.txtDescription.setMaxLength(80);
        // prmtAuditor		
        this.prmtAuditor.setEnabled(false);		
        this.prmtAuditor.setVisible(false);
        // prmtFICompany		
        this.prmtFICompany.setQueryInfo("com.kingdee.eas.basedata.org.app.CompanyOrgUnitQuery4AsstAcct");		
        this.prmtFICompany.setEditable(true);		
        this.prmtFICompany.setDisplayFormat("$name$");		
        this.prmtFICompany.setEditFormat("$number$");		
        this.prmtFICompany.setCommitFormat("$number$");		
        this.prmtFICompany.setRequired(true);
        		setOrgF7(prmtFICompany,com.kingdee.eas.basedata.org.OrgType.getEnum("Company"));
					
        // txtPaymentId		
        this.txtPaymentId.setHorizontalAlignment(2);		
        this.txtPaymentId.setMaxLength(100);		
        this.txtPaymentId.setRequired(false);
        // txtpaymentNo		
        this.txtpaymentNo.setHorizontalAlignment(2);		
        this.txtpaymentNo.setMaxLength(100);		
        this.txtpaymentNo.setRequired(false);
        // txttrsreq		
        this.txttrsreq.setHorizontalAlignment(2);		
        this.txttrsreq.setMaxLength(100);		
        this.txttrsreq.setRequired(false);
        // txtyear		
        this.txtyear.setHorizontalAlignment(2);		
        this.txtyear.setDataType(0);		
        this.txtyear.setSupportedEmpty(true);		
        this.txtyear.setRequired(false);
        // txtmonth		
        this.txtmonth.setHorizontalAlignment(2);		
        this.txtmonth.setDataType(0);		
        this.txtmonth.setSupportedEmpty(true);		
        this.txtmonth.setRequired(false);
        // txtBankAccount		
        this.txtBankAccount.setHorizontalAlignment(2);		
        this.txtBankAccount.setMaxLength(100);		
        this.txtBankAccount.setRequired(false);
        // pkReceDate		
        this.pkReceDate.setRequired(false);
        // txtReceAmount		
        this.txtReceAmount.setHorizontalAlignment(2);		
        this.txtReceAmount.setDataType(1);		
        this.txtReceAmount.setSupportedEmpty(true);		
        this.txtReceAmount.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtReceAmount.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtReceAmount.setPrecision(10);		
        this.txtReceAmount.setRequired(false);
        // txtLoans		
        this.txtLoans.setHorizontalAlignment(2);		
        this.txtLoans.setDataType(1);		
        this.txtLoans.setSupportedEmpty(true);		
        this.txtLoans.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtLoans.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtLoans.setPrecision(10);		
        this.txtLoans.setRequired(false);
        // txtMargin		
        this.txtMargin.setHorizontalAlignment(2);		
        this.txtMargin.setDataType(1);		
        this.txtMargin.setSupportedEmpty(true);		
        this.txtMargin.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtMargin.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtMargin.setPrecision(10);		
        this.txtMargin.setRequired(false);
        // txtDeposit		
        this.txtDeposit.setHorizontalAlignment(2);		
        this.txtDeposit.setDataType(1);		
        this.txtDeposit.setSupportedEmpty(true);		
        this.txtDeposit.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtDeposit.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtDeposit.setPrecision(10);		
        this.txtDeposit.setRequired(false);
        // txtAbstract		
        this.txtAbstract.setHorizontalAlignment(2);		
        this.txtAbstract.setMaxLength(255);		
        this.txtAbstract.setRequired(false);
        // pkAgainClaimDate		
        this.pkAgainClaimDate.setRequired(false);
        // txtCustomerNo		
        this.txtCustomerNo.setHorizontalAlignment(2);		
        this.txtCustomerNo.setMaxLength(100);		
        this.txtCustomerNo.setRequired(false);
        // ClaimType		
        this.ClaimType.addItems(EnumUtils.getEnumList("com.kingdee.eas.mkld.sapinterage.app.ClaimTypeMenu").toArray());		
        this.ClaimType.setRequired(false);
        // ClaimStatus		
        this.ClaimStatus.addItems(EnumUtils.getEnumList("com.kingdee.eas.mkld.sapinterage.app.ClaimStatusMenu").toArray());		
        this.ClaimStatus.setRequired(false);
        // txtPayerName		
        this.txtPayerName.setHorizontalAlignment(2);		
        this.txtPayerName.setMaxLength(200);		
        this.txtPayerName.setRequired(false);
        // SendSentFlag		
        this.SendSentFlag.addItems(EnumUtils.getEnumList("com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu").toArray());		
        this.SendSentFlag.setRequired(false);
        // FirstSentFlag		
        this.FirstSentFlag.addItems(EnumUtils.getEnumList("com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu").toArray());		
        this.FirstSentFlag.setRequired(false);
        // txtCurrencyNo		
        this.txtCurrencyNo.setHorizontalAlignment(2);		
        this.txtCurrencyNo.setMaxLength(100);		
        this.txtCurrencyNo.setRequired(false);
        // txtcompanyNumber		
        this.txtcompanyNumber.setVisible(true);		
        this.txtcompanyNumber.setHorizontalAlignment(2);		
        this.txtcompanyNumber.setMaxLength(100);		
        this.txtcompanyNumber.setRequired(false);
        // txtAgainClaimCusNo		
        this.txtAgainClaimCusNo.setVisible(true);		
        this.txtAgainClaimCusNo.setHorizontalAlignment(2);		
        this.txtAgainClaimCusNo.setMaxLength(100);		
        this.txtAgainClaimCusNo.setRequired(false);
        // dmsSendStatus		
        this.dmsSendStatus.setVisible(true);		
        this.dmsSendStatus.addItems(EnumUtils.getEnumList("com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu").toArray());		
        this.dmsSendStatus.setRequired(false);
        // txtBusDeptName		
        this.txtBusDeptName.setVisible(true);		
        this.txtBusDeptName.setHorizontalAlignment(2);		
        this.txtBusDeptName.setMaxLength(100);		
        this.txtBusDeptName.setRequired(false);
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {prmtFICompany,txtNumber,pkBizDate,txtDescription,prmtAuditor,prmtCreator,kDDateCreateTime,prmtLastUpdateUser,kDDateLastUpdateTime,txtPaymentId,txtpaymentNo,txttrsreq,txtyear,txtmonth,txtBankAccount,pkReceDate,txtReceAmount,txtLoans,txtMargin,txtDeposit,txtAbstract,pkAgainClaimDate,txtCustomerNo,ClaimType,ClaimStatus,txtPayerName,FirstSentFlag,SendSentFlag,txtCurrencyNo,txtcompanyNumber,txtAgainClaimCusNo,dmsSendStatus,txtBusDeptName}));
        this.setFocusCycleRoot(true);
		//Register control's property binding
		registerBindings();
		registerUIState();


    }

	public com.kingdee.bos.ctrl.swing.KDToolBar[] getUIMultiToolBar(){
		java.util.List list = new java.util.ArrayList();
		com.kingdee.bos.ctrl.swing.KDToolBar[] bars = super.getUIMultiToolBar();
		if (bars != null) {
			list.addAll(java.util.Arrays.asList(bars));
		}
		return (com.kingdee.bos.ctrl.swing.KDToolBar[])list.toArray(new com.kingdee.bos.ctrl.swing.KDToolBar[list.size()]);
	}




    /**
     * output initUIContentLayout method
     */
    public void initUIContentLayout()
    {
        this.setBounds(new Rectangle(0, 0, 992, 441));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(0, 0, 992, 441));
        contCreator.setBounds(new Rectangle(344, 404, 270, 19));
        this.add(contCreator, new KDLayout.Constraints(344, 404, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contCreateTime.setBounds(new Rectangle(344, 372, 270, 19));
        this.add(contCreateTime, new KDLayout.Constraints(344, 372, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contLastUpdateUser.setBounds(new Rectangle(681, 404, 270, 19));
        this.add(contLastUpdateUser, new KDLayout.Constraints(681, 404, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contLastUpdateTime.setBounds(new Rectangle(681, 372, 270, 19));
        this.add(contLastUpdateTime, new KDLayout.Constraints(681, 372, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contNumber.setBounds(new Rectangle(28, 22, 270, 19));
        this.add(contNumber, new KDLayout.Constraints(28, 22, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contBizDate.setBounds(new Rectangle(358, 264, 270, 19));
        this.add(contBizDate, new KDLayout.Constraints(358, 264, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contDescription.setBounds(new Rectangle(681, 264, 270, 19));
        this.add(contDescription, new KDLayout.Constraints(681, 264, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contAuditor.setBounds(new Rectangle(28, 406, 270, 19));
        this.add(contAuditor, new KDLayout.Constraints(28, 406, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contFICompany.setBounds(new Rectangle(358, 22, 270, 19));
        this.add(contFICompany, new KDLayout.Constraints(358, 22, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contPaymentId.setBounds(new Rectangle(28, 112, 270, 19));
        this.add(contPaymentId, new KDLayout.Constraints(28, 112, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contpaymentNo.setBounds(new Rectangle(358, 112, 270, 19));
        this.add(contpaymentNo, new KDLayout.Constraints(358, 112, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        conttrsreq.setBounds(new Rectangle(681, 112, 270, 19));
        this.add(conttrsreq, new KDLayout.Constraints(681, 112, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contyear.setBounds(new Rectangle(28, 142, 270, 19));
        this.add(contyear, new KDLayout.Constraints(28, 142, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contmonth.setBounds(new Rectangle(358, 142, 270, 19));
        this.add(contmonth, new KDLayout.Constraints(358, 142, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contBankAccount.setBounds(new Rectangle(28, 172, 270, 19));
        this.add(contBankAccount, new KDLayout.Constraints(28, 172, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contReceDate.setBounds(new Rectangle(358, 172, 270, 19));
        this.add(contReceDate, new KDLayout.Constraints(358, 172, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contReceAmount.setBounds(new Rectangle(681, 172, 270, 19));
        this.add(contReceAmount, new KDLayout.Constraints(681, 172, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contLoans.setBounds(new Rectangle(28, 202, 270, 19));
        this.add(contLoans, new KDLayout.Constraints(28, 202, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contMargin.setBounds(new Rectangle(358, 202, 270, 19));
        this.add(contMargin, new KDLayout.Constraints(358, 202, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contDeposit.setBounds(new Rectangle(681, 202, 270, 19));
        this.add(contDeposit, new KDLayout.Constraints(681, 202, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contAbstract.setBounds(new Rectangle(28, 82, 268, 19));
        this.add(contAbstract, new KDLayout.Constraints(28, 82, 268, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contAgainClaimDate.setBounds(new Rectangle(681, 142, 270, 19));
        this.add(contAgainClaimDate, new KDLayout.Constraints(681, 142, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contCustomerNo.setBounds(new Rectangle(28, 52, 270, 19));
        this.add(contCustomerNo, new KDLayout.Constraints(28, 52, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contClaimType.setBounds(new Rectangle(358, 82, 270, 19));
        this.add(contClaimType, new KDLayout.Constraints(358, 82, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contClaimStatus.setBounds(new Rectangle(681, 82, 270, 19));
        this.add(contClaimStatus, new KDLayout.Constraints(681, 82, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contPayerName.setBounds(new Rectangle(358, 52, 270, 19));
        this.add(contPayerName, new KDLayout.Constraints(358, 52, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contSendSentFlag.setBounds(new Rectangle(358, 232, 270, 19));
        this.add(contSendSentFlag, new KDLayout.Constraints(358, 232, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contFirstSentFlag.setBounds(new Rectangle(28, 232, 270, 19));
        this.add(contFirstSentFlag, new KDLayout.Constraints(28, 232, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contCurrencyNo.setBounds(new Rectangle(681, 232, 270, 19));
        this.add(contCurrencyNo, new KDLayout.Constraints(681, 232, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contcompanyNumber.setBounds(new Rectangle(681, 20, 270, 19));
        this.add(contcompanyNumber, new KDLayout.Constraints(681, 20, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contAgainClaimCusNo.setBounds(new Rectangle(681, 52, 270, 19));
        this.add(contAgainClaimCusNo, new KDLayout.Constraints(681, 52, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contdmsSendStatus.setBounds(new Rectangle(28, 264, 270, 19));
        this.add(contdmsSendStatus, new KDLayout.Constraints(28, 264, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contBusDeptName.setBounds(new Rectangle(28, 298, 270, 19));
        this.add(contBusDeptName, new KDLayout.Constraints(28, 298, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //contCreator
        contCreator.setBoundEditor(prmtCreator);
        //contCreateTime
        contCreateTime.setBoundEditor(kDDateCreateTime);
        //contLastUpdateUser
        contLastUpdateUser.setBoundEditor(prmtLastUpdateUser);
        //contLastUpdateTime
        contLastUpdateTime.setBoundEditor(kDDateLastUpdateTime);
        //contNumber
        contNumber.setBoundEditor(txtNumber);
        //contBizDate
        contBizDate.setBoundEditor(pkBizDate);
        //contDescription
        contDescription.setBoundEditor(txtDescription);
        //contAuditor
        contAuditor.setBoundEditor(prmtAuditor);
        //contFICompany
        contFICompany.setBoundEditor(prmtFICompany);
        //contPaymentId
        contPaymentId.setBoundEditor(txtPaymentId);
        //contpaymentNo
        contpaymentNo.setBoundEditor(txtpaymentNo);
        //conttrsreq
        conttrsreq.setBoundEditor(txttrsreq);
        //contyear
        contyear.setBoundEditor(txtyear);
        //contmonth
        contmonth.setBoundEditor(txtmonth);
        //contBankAccount
        contBankAccount.setBoundEditor(txtBankAccount);
        //contReceDate
        contReceDate.setBoundEditor(pkReceDate);
        //contReceAmount
        contReceAmount.setBoundEditor(txtReceAmount);
        //contLoans
        contLoans.setBoundEditor(txtLoans);
        //contMargin
        contMargin.setBoundEditor(txtMargin);
        //contDeposit
        contDeposit.setBoundEditor(txtDeposit);
        //contAbstract
        contAbstract.setBoundEditor(txtAbstract);
        //contAgainClaimDate
        contAgainClaimDate.setBoundEditor(pkAgainClaimDate);
        //contCustomerNo
        contCustomerNo.setBoundEditor(txtCustomerNo);
        //contClaimType
        contClaimType.setBoundEditor(ClaimType);
        //contClaimStatus
        contClaimStatus.setBoundEditor(ClaimStatus);
        //contPayerName
        contPayerName.setBoundEditor(txtPayerName);
        //contSendSentFlag
        contSendSentFlag.setBoundEditor(SendSentFlag);
        //contFirstSentFlag
        contFirstSentFlag.setBoundEditor(FirstSentFlag);
        //contCurrencyNo
        contCurrencyNo.setBoundEditor(txtCurrencyNo);
        //contcompanyNumber
        contcompanyNumber.setBoundEditor(txtcompanyNumber);
        //contAgainClaimCusNo
        contAgainClaimCusNo.setBoundEditor(txtAgainClaimCusNo);
        //contdmsSendStatus
        contdmsSendStatus.setBoundEditor(dmsSendStatus);
        //contBusDeptName
        contBusDeptName.setBoundEditor(txtBusDeptName);

    }


    /**
     * output initUIMenuBarLayout method
     */
    public void initUIMenuBarLayout()
    {
        this.menuBar.add(menuFile);
        this.menuBar.add(menuEdit);
        this.menuBar.add(MenuService);
        this.menuBar.add(menuView);
        this.menuBar.add(menuBiz);
        this.menuBar.add(menuTable1);
        this.menuBar.add(menuTool);
        this.menuBar.add(menuWorkflow);
        this.menuBar.add(menuHelp);
        //menuFile
        menuFile.add(menuItemAddNew);
        menuFile.add(kDSeparator1);
        menuFile.add(menuItemCloudFeed);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemCloudScreen);
        menuFile.add(menuItemSubmit);
        menuFile.add(menuItemCloudShare);
        menuFile.add(menuSubmitOption);
        menuFile.add(kdSeparatorFWFile1);
        menuFile.add(rMenuItemSubmit);
        menuFile.add(rMenuItemSubmitAndAddNew);
        menuFile.add(rMenuItemSubmitAndPrint);
        menuFile.add(separatorFile1);
        menuFile.add(MenuItemAttachment);
        menuFile.add(kDSeparator2);
        menuFile.add(menuItemPageSetup);
        menuFile.add(menuItemPrint);
        menuFile.add(menuItemPrintPreview);
        menuFile.add(kDSeparator6);
        menuFile.add(menuItemSendMail);
        menuFile.add(kDSeparator3);
        menuFile.add(menuItemExitCurrent);
        //menuSubmitOption
        menuSubmitOption.add(chkMenuItemSubmitAndAddNew);
        menuSubmitOption.add(chkMenuItemSubmitAndPrint);
        //menuEdit
        menuEdit.add(menuItemCopy);
        menuEdit.add(menuItemEdit);
        menuEdit.add(menuItemRemove);
        menuEdit.add(kDSeparator4);
        menuEdit.add(menuItemReset);
        menuEdit.add(separator1);
        menuEdit.add(menuItemCreateFrom);
        menuEdit.add(menuItemCreateTo);
        menuEdit.add(menuItemCopyFrom);
        menuEdit.add(separatorEdit1);
        menuEdit.add(menuItemEnterToNextRow);
        menuEdit.add(separator2);
        //MenuService
        MenuService.add(MenuItemKnowStore);
        MenuService.add(MenuItemAnwser);
        MenuService.add(SepratorService);
        MenuService.add(MenuItemRemoteAssist);
        //menuView
        menuView.add(menuItemFirst);
        menuView.add(menuItemPre);
        menuView.add(menuItemNext);
        menuView.add(menuItemLast);
        menuView.add(separator3);
        menuView.add(menuItemTraceUp);
        menuView.add(menuItemTraceDown);
        menuView.add(kDSeparator7);
        menuView.add(menuItemLocate);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        menuBiz.add(MenuItemVoucher);
        menuBiz.add(menuItemDelVoucher);
        menuBiz.add(MenuItemPCVoucher);
        menuBiz.add(menuItemDelPCVoucher);
        //menuTable1
        menuTable1.add(menuItemAddLine);
        menuTable1.add(menuItemCopyLine);
        menuTable1.add(menuItemInsertLine);
        menuTable1.add(menuItemRemoveLine);
        //menuTool
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemMsgFormat);
        menuTool.add(menuItemCalculator);
        menuTool.add(menuItemToolBarCustom);
        //menuWorkflow
        menuWorkflow.add(menuItemStartWorkFlow);
        menuWorkflow.add(separatorWF1);
        menuWorkflow.add(menuItemViewSubmitProccess);
        menuWorkflow.add(menuItemViewDoProccess);
        menuWorkflow.add(MenuItemWFG);
        menuWorkflow.add(menuItemWorkFlowList);
        menuWorkflow.add(separatorWF2);
        menuWorkflow.add(menuItemMultiapprove);
        menuWorkflow.add(menuItemNextPerson);
        menuWorkflow.add(menuItemAuditResult);
        menuWorkflow.add(kDSeparator5);
        menuWorkflow.add(kDMenuItemSendMessage);
        //menuHelp
        menuHelp.add(menuItemHelp);
        menuHelp.add(kDSeparator12);
        menuHelp.add(menuItemRegPro);
        menuHelp.add(menuItemPersonalSite);
        menuHelp.add(helpseparatorDiv);
        menuHelp.add(menuitemProductval);
        menuHelp.add(kDSeparatorProduct);
        menuHelp.add(menuItemAbout);

    }

    /**
     * output initUIToolBarLayout method
     */
    public void initUIToolBarLayout()
    {
        this.toolBar.add(btnAddNew);
        this.toolBar.add(btnCloud);
        this.toolBar.add(btnEdit);
        this.toolBar.add(btnXunTong);
        this.toolBar.add(btnSave);
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(btnReset);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(btnCopy);
        this.toolBar.add(btnRemove);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnCancel);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(btnFirst);
        this.toolBar.add(btnPre);
        this.toolBar.add(btnNext);
        this.toolBar.add(btnLast);
        this.toolBar.add(separatorFW3);
        this.toolBar.add(btnTraceUp);
        this.toolBar.add(btnTraceDown);
        this.toolBar.add(btnWorkFlowG);
        this.toolBar.add(btnSignature);
        this.toolBar.add(btnViewSignature);
        this.toolBar.add(separatorFW4);
        this.toolBar.add(btnNumberSign);
        this.toolBar.add(separatorFW7);
        this.toolBar.add(btnCreateFrom);
        this.toolBar.add(btnCopyFrom);
        this.toolBar.add(btnCreateTo);
        this.toolBar.add(separatorFW5);
        this.toolBar.add(separatorFW8);
        this.toolBar.add(btnAddLine);
        this.toolBar.add(btnCopyLine);
        this.toolBar.add(btnInsertLine);
        this.toolBar.add(btnRemoveLine);
        this.toolBar.add(separatorFW6);
        this.toolBar.add(separatorFW9);
        this.toolBar.add(btnVoucher);
        this.toolBar.add(btnDelVoucher);
        this.toolBar.add(btnPCVoucher);
        this.toolBar.add(btnDelPCVoucher);
        this.toolBar.add(btnAuditResult);
        this.toolBar.add(btnMultiapprove);
        this.toolBar.add(btnWFViewdoProccess);
        this.toolBar.add(btnWFViewSubmitProccess);
        this.toolBar.add(btnNextPerson);


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("creator", com.kingdee.eas.base.permission.UserInfo.class, this.prmtCreator, "data");
		dataBinder.registerBinding("createTime", java.sql.Timestamp.class, this.kDDateCreateTime, "value");
		dataBinder.registerBinding("lastUpdateUser", com.kingdee.eas.base.permission.UserInfo.class, this.prmtLastUpdateUser, "data");
		dataBinder.registerBinding("lastUpdateTime", java.sql.Timestamp.class, this.kDDateLastUpdateTime, "value");
		dataBinder.registerBinding("number", String.class, this.txtNumber, "text");
		dataBinder.registerBinding("bizDate", java.util.Date.class, this.pkBizDate, "value");
		dataBinder.registerBinding("description", String.class, this.txtDescription, "text");
		dataBinder.registerBinding("auditor", com.kingdee.eas.base.permission.UserInfo.class, this.prmtAuditor, "data");
		dataBinder.registerBinding("FICompany", com.kingdee.eas.basedata.org.CompanyOrgUnitInfo.class, this.prmtFICompany, "data");
		dataBinder.registerBinding("PaymentId", String.class, this.txtPaymentId, "text");
		dataBinder.registerBinding("paymentNo", String.class, this.txtpaymentNo, "text");
		dataBinder.registerBinding("trsreq", String.class, this.txttrsreq, "text");
		dataBinder.registerBinding("year", int.class, this.txtyear, "value");
		dataBinder.registerBinding("month", int.class, this.txtmonth, "value");
		dataBinder.registerBinding("BankAccount", String.class, this.txtBankAccount, "text");
		dataBinder.registerBinding("ReceDate", java.util.Date.class, this.pkReceDate, "value");
		dataBinder.registerBinding("ReceAmount", java.math.BigDecimal.class, this.txtReceAmount, "value");
		dataBinder.registerBinding("Loans", java.math.BigDecimal.class, this.txtLoans, "value");
		dataBinder.registerBinding("Margin", java.math.BigDecimal.class, this.txtMargin, "value");
		dataBinder.registerBinding("Deposit", java.math.BigDecimal.class, this.txtDeposit, "value");
		dataBinder.registerBinding("Abstract", String.class, this.txtAbstract, "text");
		dataBinder.registerBinding("AgainClaimDate", java.util.Date.class, this.pkAgainClaimDate, "value");
		dataBinder.registerBinding("CustomerNo", String.class, this.txtCustomerNo, "text");
		dataBinder.registerBinding("ClaimType", com.kingdee.eas.mkld.sapinterage.app.ClaimTypeMenu.class, this.ClaimType, "selectedItem");
		dataBinder.registerBinding("ClaimStatus", com.kingdee.eas.mkld.sapinterage.app.ClaimStatusMenu.class, this.ClaimStatus, "selectedItem");
		dataBinder.registerBinding("PayerName", String.class, this.txtPayerName, "text");
		dataBinder.registerBinding("SendSentFlag", com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu.class, this.SendSentFlag, "selectedItem");
		dataBinder.registerBinding("FirstSentFlag", com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu.class, this.FirstSentFlag, "selectedItem");
		dataBinder.registerBinding("CurrencyNo", String.class, this.txtCurrencyNo, "text");
		dataBinder.registerBinding("companyNumber", String.class, this.txtcompanyNumber, "text");
		dataBinder.registerBinding("AgainClaimCusNo", String.class, this.txtAgainClaimCusNo, "text");
		dataBinder.registerBinding("dmsSendStatus", com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu.class, this.dmsSendStatus, "selectedItem");
		dataBinder.registerBinding("BusDeptName", String.class, this.txtBusDeptName, "text");		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.mkld.sapinterage.app.ReceClaimRecordEditUIHandler";
	}
	public IUIActionPostman prepareInit() {
		IUIActionPostman clientHanlder = super.prepareInit();
		if (clientHanlder != null) {
			RequestContext request = new RequestContext();
    		request.setClassName(getUIHandlerClassName());
			clientHanlder.setRequestContext(request);
		}
		return clientHanlder;
    }
	
	public boolean isPrepareInit() {
    	return false;
    }
    protected void initUIP() {
        super.initUIP();
    }


    /**
     * output onShow method
     */
    public void onShow() throws Exception
    {
        super.onShow();
        this.prmtFICompany.requestFocusInWindow();
    }

	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
        this.editData = (com.kingdee.eas.mkld.sapinterage.ReceClaimRecordInfo)ov;
    }
    protected void removeByPK(IObjectPK pk) throws Exception {
    	IObjectValue editData = this.editData;
    	super.removeByPK(pk);
    	recycleNumberByOrg(editData,"Company",editData.getString("number"));
    }
    
    protected void recycleNumberByOrg(IObjectValue editData,String orgType,String number) {
        if (!StringUtils.isEmpty(number))
        {
            try {
            	String companyID = null;            
            	com.kingdee.eas.base.codingrule.ICodingRuleManager iCodingRuleManager = com.kingdee.eas.base.codingrule.CodingRuleManagerFactory.getRemoteInstance();
				if(!com.kingdee.util.StringUtils.isEmpty(orgType) && !"NONE".equalsIgnoreCase(orgType) && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType))!=null) {
					companyID =com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType)).getString("id");
				}
				else if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit() != null) {
					companyID = ((com.kingdee.eas.basedata.org.OrgUnitInfo)com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit()).getString("id");
            	}				
				if (!StringUtils.isEmpty(companyID) && iCodingRuleManager.isExist(editData, companyID) && iCodingRuleManager.isUseIntermitNumber(editData, companyID)) {
					iCodingRuleManager.recycleNumber(editData,companyID,number);					
				}
            }
            catch (Exception e)
            {
                handUIException(e);
            }
        }
    }
    protected void setAutoNumberByOrg(String orgType) {
    	if (editData == null) return;
		if (editData.getNumber() == null) {
            try {
            	String companyID = null;
				if(!com.kingdee.util.StringUtils.isEmpty(orgType) && !"NONE".equalsIgnoreCase(orgType) && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType))!=null) {
					companyID = com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType)).getString("id");
				}
				else if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit() != null) {
					companyID = ((com.kingdee.eas.basedata.org.OrgUnitInfo)com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit()).getString("id");
            	}
				com.kingdee.eas.base.codingrule.ICodingRuleManager iCodingRuleManager = com.kingdee.eas.base.codingrule.CodingRuleManagerFactory.getRemoteInstance();
		        if (iCodingRuleManager.isExist(editData, companyID)) {
		            if (iCodingRuleManager.isAddView(editData, companyID)) {
		            	editData.setNumber(iCodingRuleManager.getNumber(editData,companyID));
		            }
	                txtNumber.setEnabled(false);
		        }
            }
            catch (Exception e) {
                handUIException(e);
                this.oldData = editData;
                com.kingdee.eas.util.SysUtil.abort();
            } 
        } 
        else {
            if (editData.getNumber().trim().length() > 0) {
                txtNumber.setText(editData.getNumber());
            }
        }
    }
			protected com.kingdee.eas.basedata.org.OrgType getMainBizOrgType() {
			return com.kingdee.eas.basedata.org.OrgType.getEnum("Company");
		}

	protected KDBizPromptBox getMainBizOrg() {
		return prmtFICompany;
}


    /**
     * output loadFields method
     */
    public void loadFields()
    {
        		setAutoNumberByOrg("Company");
        dataBinder.loadFields();
    }
		protected void setOrgF7(KDBizPromptBox f7,com.kingdee.eas.basedata.org.OrgType orgType) throws Exception
		{
			com.kingdee.eas.basedata.org.client.f7.NewOrgUnitFilterInfoProducer oufip = new com.kingdee.eas.basedata.org.client.f7.NewOrgUnitFilterInfoProducer(orgType);
			oufip.getModel().setIsCUFilter(true);
			f7.setFilterInfoProducer(oufip);
		}

    /**
     * output storeFields method
     */
    public void storeFields()
    {
		dataBinder.storeFields();
    }

	/**
	 * ????????§µ??
	 */
	protected void registerValidator() {
    	getValidateHelper().setCustomValidator( getValidator() );
		getValidateHelper().registerBindProperty("creator", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("createTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateUser", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("number", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bizDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("description", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("auditor", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("FICompany", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("PaymentId", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("paymentNo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("trsreq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("year", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("month", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BankAccount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("ReceDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("ReceAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Loans", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Margin", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Deposit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Abstract", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("AgainClaimDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("CustomerNo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("ClaimType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("ClaimStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("PayerName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("SendSentFlag", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("FirstSentFlag", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("CurrencyNo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("companyNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("AgainClaimCusNo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("dmsSendStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BusDeptName", ValidateHelper.ON_SAVE);    		
	}



    /**
     * output setOprtState method
     */
    public void setOprtState(String oprtType)
    {
        super.setOprtState(oprtType);
        if (STATUS_ADDNEW.equals(this.oprtState)) {
        } else if (STATUS_EDIT.equals(this.oprtState)) {
        } else if (STATUS_VIEW.equals(this.oprtState)) {
        } else if (STATUS_FINDVIEW.equals(this.oprtState)) {
        }
    }

    /**
     * output getSelectors method
     */
    public SelectorItemCollection getSelectors()
    {
        SelectorItemCollection sic = new SelectorItemCollection();
		String selectorAll = System.getProperty("selector.all");
		if(StringUtils.isEmpty(selectorAll)){
			selectorAll = "true";
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("creator.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("creator.id"));
        	sic.add(new SelectorItemInfo("creator.number"));
        	sic.add(new SelectorItemInfo("creator.name"));
		}
        sic.add(new SelectorItemInfo("createTime"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("lastUpdateUser.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("lastUpdateUser.id"));
        	sic.add(new SelectorItemInfo("lastUpdateUser.number"));
        	sic.add(new SelectorItemInfo("lastUpdateUser.name"));
		}
        sic.add(new SelectorItemInfo("lastUpdateTime"));
        sic.add(new SelectorItemInfo("number"));
        sic.add(new SelectorItemInfo("bizDate"));
        sic.add(new SelectorItemInfo("description"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("auditor.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("auditor.id"));
        	sic.add(new SelectorItemInfo("auditor.number"));
        	sic.add(new SelectorItemInfo("auditor.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("FICompany.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("FICompany.id"));
        	sic.add(new SelectorItemInfo("FICompany.number"));
        	sic.add(new SelectorItemInfo("FICompany.name"));
		}
        sic.add(new SelectorItemInfo("PaymentId"));
        sic.add(new SelectorItemInfo("paymentNo"));
        sic.add(new SelectorItemInfo("trsreq"));
        sic.add(new SelectorItemInfo("year"));
        sic.add(new SelectorItemInfo("month"));
        sic.add(new SelectorItemInfo("BankAccount"));
        sic.add(new SelectorItemInfo("ReceDate"));
        sic.add(new SelectorItemInfo("ReceAmount"));
        sic.add(new SelectorItemInfo("Loans"));
        sic.add(new SelectorItemInfo("Margin"));
        sic.add(new SelectorItemInfo("Deposit"));
        sic.add(new SelectorItemInfo("Abstract"));
        sic.add(new SelectorItemInfo("AgainClaimDate"));
        sic.add(new SelectorItemInfo("CustomerNo"));
        sic.add(new SelectorItemInfo("ClaimType"));
        sic.add(new SelectorItemInfo("ClaimStatus"));
        sic.add(new SelectorItemInfo("PayerName"));
        sic.add(new SelectorItemInfo("SendSentFlag"));
        sic.add(new SelectorItemInfo("FirstSentFlag"));
        sic.add(new SelectorItemInfo("CurrencyNo"));
        sic.add(new SelectorItemInfo("companyNumber"));
        sic.add(new SelectorItemInfo("AgainClaimCusNo"));
        sic.add(new SelectorItemInfo("dmsSendStatus"));
        sic.add(new SelectorItemInfo("BusDeptName"));
        return sic;
    }        
    	

    /**
     * output actionSubmit_actionPerformed method
     */
    public void actionSubmit_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSubmit_actionPerformed(e);
    }
    	

    /**
     * output actionPrint_actionPerformed method
     */
    public void actionPrint_actionPerformed(ActionEvent e) throws Exception
    {
        ArrayList idList = new ArrayList();
    	if (editData != null && !StringUtils.isEmpty(editData.getString("id"))) {
    		idList.add(editData.getString("id"));
    	}
        if (idList == null || idList.size() == 0 || getTDQueryPK() == null || getTDFileName() == null)
            return;
        com.kingdee.bos.ctrl.kdf.data.impl.BOSQueryDelegate data = new com.kingdee.eas.framework.util.CommonDataProvider(idList,getTDQueryPK());
        com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper appHlp = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper();
        appHlp.print(getTDFileName(), data, javax.swing.SwingUtilities.getWindowAncestor(this));
    }
    	

    /**
     * output actionPrintPreview_actionPerformed method
     */
    public void actionPrintPreview_actionPerformed(ActionEvent e) throws Exception
    {
        ArrayList idList = new ArrayList();
        if (editData != null && !StringUtils.isEmpty(editData.getString("id"))) {
    		idList.add(editData.getString("id"));
    	}
        if (idList == null || idList.size() == 0 || getTDQueryPK() == null || getTDFileName() == null)
            return;
        com.kingdee.bos.ctrl.kdf.data.impl.BOSQueryDelegate data = new com.kingdee.eas.framework.util.CommonDataProvider(idList,getTDQueryPK());
        com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper appHlp = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper();
        appHlp.printPreview(getTDFileName(), data, javax.swing.SwingUtilities.getWindowAncestor(this));
    }
    	

    /**
     * output actionSentReceClaim_actionPerformed method
     */
    public void actionSentReceClaim_actionPerformed(ActionEvent e) throws Exception
    {
        com.kingdee.eas.mkld.sapinterage.ReceClaimRecordFactory.getRemoteInstance().sentReceClaim(editData);
    }
    	

    /**
     * output actionSentNoClaimMonthEnd_actionPerformed method
     */
    public void actionSentNoClaimMonthEnd_actionPerformed(ActionEvent e) throws Exception
    {
        com.kingdee.eas.mkld.sapinterage.ReceClaimRecordFactory.getRemoteInstance().sentNoClaimMonthEnd(editData);
    }
    	

    /**
     * output actionSentClaimAgain_actionPerformed method
     */
    public void actionSentClaimAgain_actionPerformed(ActionEvent e) throws Exception
    {
        com.kingdee.eas.mkld.sapinterage.ReceClaimRecordFactory.getRemoteInstance().sentClaimAgain(editData);
    }
	public RequestContext prepareActionSubmit(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionSubmit(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSubmit() {
    	return false;
    }
	public RequestContext prepareActionPrint(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionPrint(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPrint() {
    	return false;
    }
	public RequestContext prepareActionPrintPreview(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionPrintPreview(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPrintPreview() {
    	return false;
    }
	public RequestContext prepareActionSentReceClaim(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSentReceClaim() {
    	return false;
    }
	public RequestContext prepareActionSentNoClaimMonthEnd(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSentNoClaimMonthEnd() {
    	return false;
    }
	public RequestContext prepareActionSentClaimAgain(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSentClaimAgain() {
    	return false;
    }

    /**
     * output ActionSentReceClaim class
     */     
    protected class ActionSentReceClaim extends ItemAction {     
    
        public ActionSentReceClaim()
        {
            this(null);
        }

        public ActionSentReceClaim(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionSentReceClaim.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSentReceClaim.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSentReceClaim.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceClaimRecordEditUI.this, "ActionSentReceClaim", "actionSentReceClaim_actionPerformed", e);
        }
    }

    /**
     * output ActionSentNoClaimMonthEnd class
     */     
    protected class ActionSentNoClaimMonthEnd extends ItemAction {     
    
        public ActionSentNoClaimMonthEnd()
        {
            this(null);
        }

        public ActionSentNoClaimMonthEnd(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionSentNoClaimMonthEnd.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSentNoClaimMonthEnd.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSentNoClaimMonthEnd.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceClaimRecordEditUI.this, "ActionSentNoClaimMonthEnd", "actionSentNoClaimMonthEnd_actionPerformed", e);
        }
    }

    /**
     * output ActionSentClaimAgain class
     */     
    protected class ActionSentClaimAgain extends ItemAction {     
    
        public ActionSentClaimAgain()
        {
            this(null);
        }

        public ActionSentClaimAgain(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionSentClaimAgain.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSentClaimAgain.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSentClaimAgain.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceClaimRecordEditUI.this, "ActionSentClaimAgain", "actionSentClaimAgain_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.mkld.sapinterage.client", "ReceClaimRecordEditUI");
    }
    /**
     * output isBindWorkFlow method
     */
    public boolean isBindWorkFlow()
    {
        return true;
    }

    /**
     * output getEditUIName method
     */
    protected String getEditUIName()
    {
        return com.kingdee.eas.mkld.sapinterage.client.ReceClaimRecordEditUI.class.getName();
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
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.mkld.sapinterage.ReceClaimRecordInfo objectValue = new com.kingdee.eas.mkld.sapinterage.ReceClaimRecordInfo();
				if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Company")) != null && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Company")).getBoolean("isBizUnit"))
			objectValue.put("FICompany",com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Company")));
 
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }


    	protected String getTDFileName() {
    	return "/bim/mkld/sapinterage/ReceClaimRecord";
	}
    protected IMetaDataPK getTDQueryPK() {
    	return new MetaDataPK("com.kingdee.eas.mkld.sapinterage.app.ReceClaimRecordQuery");
	}
    

    /**
     * output getDetailTable method
     */
    protected KDTable getDetailTable() {        
        return null;
	}
    /**
     * output applyDefaultValue method
     */
    protected void applyDefaultValue(IObjectValue vo) {        
		vo.put("ClaimType","A");
vo.put("ClaimStatus","1");
vo.put("dmsSendStatus","1");
        
    }        
	protected void setFieldsNull(com.kingdee.bos.dao.AbstractObjectValue arg0) {
		super.setFieldsNull(arg0);
		arg0.put("number",null);
	}

}