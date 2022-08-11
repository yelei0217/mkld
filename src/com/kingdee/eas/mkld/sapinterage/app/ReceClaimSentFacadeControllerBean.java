package com.kingdee.eas.mkld.sapinterage.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.mkld.sapinterage.IReceClaimRecord;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordCollection;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordFactory;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordInfo;
import com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogFactory;
import com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogInfo;
import com.kingdee.eas.mkld.sapinterage.app.dto.ReceClaimDTO;
import com.kingdee.eas.mkld.sapinterage.app.util.ReceClaimRecordUtil;
import com.kingdee.eas.mkld.sapinterage.app.util.SAPInterfaceUtil;

public class ReceClaimSentFacadeControllerBean extends AbstractReceClaimSentFacadeControllerBean
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5623787730806306563L;
	private static Logger logger = Logger.getLogger("com.kingdee.eas.mkld.sapinterage.app.ReceClaimSentFacadeControllerBean");
	
	
	/**
	 * 
	 * �����տ�������
	 * @author Lei.Ye
	 */
	@Override
	protected String _sentReceClaim(Context ctx) throws BOSException {
//		ReceClaimRecordUtil.savaRecordBill(ctx, "0NSPa37+RFy5hP2g3xBY5/pE/Vs="); // �ͻ�
//		ReceClaimRecordUtil.savaRecordBill(ctx, "HMWXYdhdTCWiXxi9LcqJ8fpE/Vs="); //����
		
		//��ȡ�����죬һ��δ���͵ļ�¼����
        IReceClaimRecord ibiz = ReceClaimRecordFactory.getLocalInstance(ctx);
        EntityViewInfo viewInfo = new EntityViewInfo();
        FilterInfo filter = new FilterInfo();
        filter.getFilterItems().add(new FilterItemInfo("ClaimType",ClaimTypeMenu.CurrMonth,CompareType.EQUALS));//�������ͣ���������
        filter.getFilterItems().add(new FilterItemInfo("ClaimStatus",ClaimStatusMenu.Yes,CompareType.EQUALS));//����״̬��������
        filter.getFilterItems().add(new FilterItemInfo("FirstSentFlag",SendStatusMenu.UnSent,CompareType.EQUALS));//��һ�η���SAP״̬:δ����
        viewInfo.setFilter(filter);
        ReceClaimRecordCollection rcoll = ibiz.getReceClaimRecordCollection(viewInfo);
        List<ReceClaimDTO> lists = null ;
        if(rcoll !=null && rcoll.size() > 0){
        	Iterator it = rcoll.iterator();
        	lists = new ArrayList<ReceClaimDTO>();
        	Date currentDate = new Date();
        	while(it.hasNext()){
        		ReceClaimRecordInfo rInfo = (ReceClaimRecordInfo) it.next();
        		ReceClaimDTO dto = new  ReceClaimDTO();
        		dto.setEASID(rInfo.getPaymentId());//  �տID
        		dto.setZTYPE(ClaimTypeMenu.CURRMONTH_VALUE);//    ��������
        		dto.setBUKRS(rInfo.getFICompany().getNumber());//   ��˾����
        		dto.setBANKN(rInfo.getBankAccount());//   �տ������˺�
        		dto.setBUDAT(new SimpleDateFormat("yyyyMMdd").format(rInfo.getReceDate()));//   �տ�����
        		//dto.setBUDAT2();//  �ٴ���������
        		dto.setWAERS(rInfo.getCurrencyNo());
        		dto.setKUNNR(rInfo.getCustomerNo());//   �ͻ�����
        		dto.setDMBTR(rInfo.getReceAmount());//   �տ���
        		dto.setDMBTR_HK(rInfo.getLoans());//  ���л�����
        		dto.setDMBTR_BZJ(rInfo.getMargin());//  ���б�֤����
        		dto.setDMBTR_YJ(rInfo.getDeposit());//  ����Ѻ����
        		dto.setSGTXT(rInfo.getAbstract());//    ժҪ        		
        		lists.add(dto);
        	} 
        	
        //1�����ݷ�װ�� list ƴ�ӷ�����Ϣ
        Gson gson = new Gson();
        Map dataMp = new HashMap();
        dataMp.put("ITEMS", lists);
        String dataStr  = gson.toJson(dataMp);
        System.out.println(dataStr);
        System.out.println(dataMp.toString());
        dataStr = org.apache.commons.lang.StringEscapeUtils.escapeJava(dataStr);  //���ת���
        System.out.println(dataStr);

        String msgId = ReceClaimRecordUtil.getCurrentTimeStrS()+(int)(Math.random()*10000);
        
        //2������SAP����-- ������Ӧ��Ϣ
        
        String sendStr = SAPInterfaceUtil.createSendReqStr(msgId,"FICO_I012", dataStr);
 
        
        //4������������Ϣ�� �޸�ÿ����¼ ����״̬
    	
        
    	
        //5����¼���ͺ���Ӧ��־��Ϣ   	
        SAPInterfaceUtil.sendSapRequest(sendStr);
        SAPInterfaceLogInfo logInfo = new SAPInterfaceLogInfo();
        logInfo.setNumber(msgId);
        logInfo.setBizDate(currentDate);
        logInfo.setInterType(SAPInterTypeMenu.FICO_I012);
        logInfo.setClaimType(ClaimTypeMenu.CurrMonth);
        logInfo.setInterResult(InterResultMenu.SUCCESS);
        logInfo.setReqTime(currentDate);
        logInfo.setRequest(dataStr);
        logInfo.setRespond(dataStr);
	        try {
				SAPInterfaceLogFactory.getLocalInstance(ctx).addnew(logInfo);
			} catch (EASBizException e) {
	 			e.printStackTrace();
			}
        } 
        
		return super._sentReceClaim(ctx);
	}
	
	
	
	/**
	 * 
	 * ������ĩδ����
	 * @author Lei.Ye
	 */
	@Override
	protected String _sentReceNoClaim(Context ctx) throws BOSException {
		return super._sentReceNoClaim(ctx);
	
	}


	
}