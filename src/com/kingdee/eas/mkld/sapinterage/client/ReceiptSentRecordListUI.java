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
    	btnG.setText("生成回单发送记录");// 设置按钮名称
    	btnG.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_time"));// 图标
		toolBar.add(btnG);// 添加到工具栏
		btnG.setVisible(true);// 设置可见
		btnG.setEnabled(true);// 设置可用
		btnG.addActionListener(new ActionListener() {// 添加点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				doGenReceiptRecord();
			}
		 });
		
		
	  	KDWorkButton btnSent = new KDWorkButton();
	  	btnSent.setText("发送电子回单");// 设置按钮名称
	  	btnSent.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_time"));// 图标
		toolBar.add(btnSent);// 添加到工具栏
		btnSent.setVisible(true);// 设置可见
		btnSent.setEnabled(true);// 设置可用
		btnSent.addActionListener(new ActionListener() {// 添加点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				doSentReceiptRecord();
			}
		 });
		
	  	KDWorkButton btnSent2DMS = new KDWorkButton();
	  	btnSent2DMS.setText("发送客户至DMS");// 设置按钮名称
	  	btnSent2DMS.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_time"));// 图标
		toolBar.add(btnSent2DMS);// 添加到工具栏
		btnSent2DMS.setVisible(true);// 设置可见
		btnSent2DMS.setEnabled(true);// 设置可用
		btnSent2DMS.addActionListener(new ActionListener() {// 添加点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				doSentCustomer2DMS();
			}
		 });
		
    }
    
    /**
     * 生成回单发送记录
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
     * 发送电子回单
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
     * 发送客至DMS
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