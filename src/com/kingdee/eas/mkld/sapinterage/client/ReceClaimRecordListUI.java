/**
 * output package name
 */
package com.kingdee.eas.mkld.sapinterage.client;

import java.awt.event.*;

import org.apache.log4j.Logger;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.UIException;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.bos.ctrl.swing.KDWorkButton;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.framework.*;
import com.kingdee.eas.mkld.sapinterage.ReceClaimSentFacadeFactory;
import com.kingdee.eas.util.client.MsgBox;

/**
 * output class name
 */
public class ReceClaimRecordListUI extends AbstractReceClaimRecordListUI
{
    private static final Logger logger = CoreUIObject.getLogger(ReceClaimRecordListUI.class);
    
    @Override
    public void onLoad() throws Exception { 
    	super.onLoad();
//     	this.btnAddNew.setVisible(false);
//     	//this.btnEdit.setVisible(false);
//     	this.btnRemove.setVisible(false);
//     	this.btnCreateTo.setVisible(false);
//
//     	this.menuItemAddNew.setVisible(false);
//     	//this.menuItemEdit.setVisible(false);
//     	this.menuItemRemove.setVisible(false);
//     	this.menuItemCreateTo.setVisible(false);
     	
    	KDWorkButton btnSend2OA = new KDWorkButton();
		btnSend2OA.setText("�������OA����");// ���ð�ť����
		btnSend2OA.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_time"));// ͼ��
		toolBar.add(btnSend2OA);// ��ӵ�������
		btnSend2OA.setVisible(true);// ���ÿɼ�
		btnSend2OA.setEnabled(true);// ���ÿ���
		btnSend2OA.addActionListener(new ActionListener() {// ��ӵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowPayMentSendWind();
			}
		 });
		
		
		KDWorkButton btnSend2DMS = new KDWorkButton();
		btnSend2DMS.setText("�ͻ�����DMS����");// ���ð�ť����
		btnSend2DMS.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_time"));// ͼ��
		toolBar.add(btnSend2DMS);// ��ӵ�������
		btnSend2DMS.setVisible(true);// ���ÿɼ�
		btnSend2DMS.setEnabled(true);// ���ÿ���
		btnSend2DMS.addActionListener(new ActionListener() {// ��ӵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowDMSSendWind();
			}
		 });
		
    }
    
    private void ShowPayMentSendWind()
    {
        UIContext context = new UIContext(this);
        try
        {
          String path = "com.kingdee.eas.mkld.sapinterage.client.PaymentSendUI";
          IUIWindow window = UIFactory.createUIFactory("com.kingdee.eas.base.uiframe.client.UIModelDialogFactory").create(path, context, null, OprtState.VIEW);
          window.show();
          refreshList();
        }
        catch (UIException e)
        {
          e.printStackTrace();
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
    
    }
    
    
    private void ShowDMSSendWind()
    {
        UIContext context = new UIContext(this);
        try
        {
          String path = "com.kingdee.eas.mkld.sapinterage.client.DmsSendUI";
          IUIWindow window = UIFactory.createUIFactory("com.kingdee.eas.base.uiframe.client.UIModelDialogFactory").create(path, context, null, OprtState.VIEW);
          window.show();
          refreshList();
        }
        catch (UIException e)
        {
          e.printStackTrace();
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
    
    }
    
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