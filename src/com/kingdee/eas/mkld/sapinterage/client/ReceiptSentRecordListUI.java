/**
 * output package name
 */
package com.kingdee.eas.mkld.sapinterage.client;

import java.awt.event.*;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ctrl.swing.KDWorkButton;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.framework.*;
import com.kingdee.eas.mkld.sapinterage.ReceClaimSentFacadeFactory;

/**
 * output class name
 */
public class ReceiptSentRecordListUI extends AbstractReceiptSentRecordListUI
{
    private static final Logger logger = CoreUIObject.getLogger(ReceiptSentRecordListUI.class);
    
    /**
     * output class constructor
     */
    public ReceiptSentRecordListUI() throws Exception
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
    public void onLoad() throws Exception {
     	super.onLoad();
     	
    	KDWorkButton btnG = new KDWorkButton();
    	btnG.setText("���ɻص����ͼ�¼");// ���ð�ť����
    	btnG.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_time"));// ͼ��
		toolBar.add(btnG);// ��ӵ�������
		btnG.setVisible(true);// ���ÿɼ�
		btnG.setEnabled(true);// ���ÿ���
		btnG.addActionListener(new ActionListener() {// ��ӵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				doGenReceiptRecord();
			}
		 });
		
		
	  	KDWorkButton btnSent = new KDWorkButton();
	  	btnSent.setText("���͵��ӻص�");// ���ð�ť����
	  	btnSent.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_time"));// ͼ��
		toolBar.add(btnSent);// ��ӵ�������
		btnSent.setVisible(true);// ���ÿɼ�
		btnSent.setEnabled(true);// ���ÿ���
		btnSent.addActionListener(new ActionListener() {// ��ӵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				doSentReceiptRecord();
			}
		 });
		
	  	KDWorkButton btnSent2DMS = new KDWorkButton();
	  	btnSent2DMS.setText("���Ϳͻ���DMS");// ���ð�ť����
	  	btnSent2DMS.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_time"));// ͼ��
		toolBar.add(btnSent2DMS);// ��ӵ�������
		btnSent2DMS.setVisible(true);// ���ÿɼ�
		btnSent2DMS.setEnabled(true);// ���ÿ���
		btnSent2DMS.addActionListener(new ActionListener() {// ��ӵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				doSentCustomer2DMS();
			}
		 });
		
    }
    
    /**
     * ���ɻص����ͼ�¼
     * 
     */
    private void doGenReceiptRecord()
    {
    	try {
			ReceClaimSentFacadeFactory.getRemoteInstance().genReceiptRecord();
		} catch (BOSException e) {
 			e.printStackTrace();
		}
    }
    
    /**
     * 
     * ���͵��ӻص�
     */
    private void doSentReceiptRecord()
    {
     	try {
			ReceClaimSentFacadeFactory.getRemoteInstance().SentReceiptRecord();
		} catch (BOSException e) {
 			e.printStackTrace();
		}
    }
    
    /**
     * 
     * ���Ϳ���DMS
     */
    private void doSentCustomer2DMS()
    {
     	try {
			ReceClaimSentFacadeFactory.getRemoteInstance().sentCustomer2DMS();
		} catch (BOSException e) {
 			e.printStackTrace();
		}
    }
    
    

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.mkld.sapinterage.ReceiptSentRecordFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected com.kingdee.bos.dao.IObjectValue createNewData()
    {
        com.kingdee.eas.mkld.sapinterage.ReceiptSentRecordInfo objectValue = new com.kingdee.eas.mkld.sapinterage.ReceiptSentRecordInfo();
		
        return objectValue;
    }

}