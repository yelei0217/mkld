package com.kingdee.eas.mkld.sapinterage.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.kingdee.eas.mkld.sapinterage.app.dto.SapReceRspDTO;
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
		ReceClaimRecordUtil.savaRecordBill(ctx, "0NSPa37+RFy5hP2g3xBY5/pE/Vs="); // �ͻ�
		ReceClaimRecordUtil.savaRecordBill(ctx, "HMWXYdhdTCWiXxi9LcqJ8fpE/Vs="); //����
		
		//��ȡ�����죬һ��δ���͵ļ�¼����
        IReceClaimRecord ibiz = ReceClaimRecordFactory.getLocalInstance(ctx);
        EntityViewInfo viewInfo = new EntityViewInfo();
        FilterInfo filter = new FilterInfo();
        filter.getFilterItems().add(new FilterItemInfo("ClaimType",ClaimTypeMenu.CurrMonth,CompareType.EQUALS));//�������ͣ���������
        filter.getFilterItems().add(new FilterItemInfo("ClaimStatus",ClaimStatusMenu.Yes,CompareType.EQUALS));//����״̬��������
        filter.getFilterItems().add(new FilterItemInfo("FirstSentFlag",SendStatusMenu.UnSent,CompareType.EQUALS));//��һ�η���SAP״̬:δ����
        filter.getFilterItems().add(new FilterItemInfo("FirstSentFlag",SendStatusMenu.SentF,CompareType.EQUALS));//��һ�η���SAP״̬:δ����
        filter.setMaskString("#0 and #1 and (#2 or #3)");
        viewInfo.setFilter(filter);
        ReceClaimRecordCollection rcoll = ibiz.getReceClaimRecordCollection(viewInfo);
        List<ReceClaimDTO> lists = null ;
        if(rcoll !=null && rcoll.size() > 0){
        	Iterator it = rcoll.iterator();
        	lists = new ArrayList<ReceClaimDTO>();
        	Date currentDate = new Date();
        	Set rIds = new HashSet();
        	while(it.hasNext()){
        		ReceClaimRecordInfo rInfo = (ReceClaimRecordInfo) it.next();
        		ReceClaimDTO dto = new  ReceClaimDTO();
        		dto.setEASID(rInfo.getPaymentId());//  �տID
        		dto.setZTYPE(ClaimTypeMenu.CURRMONTH_VALUE);//    ��������
        		dto.setBUKRS(rInfo.getCompanyNumber());//   ��˾����
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
        		rIds.add(rInfo.getId().toString());
        		lists.add(dto);
        	} 
        	
        //1�����ݷ�װ�� list ƴ�ӷ�����Ϣ
        Gson gson = new Gson();
        Map dataMp = new HashMap();
        dataMp.put("ITEMS", lists);
        String dataStr  = gson.toJson(dataMp);
        System.out.println(dataStr);
        System.out.println(dataMp.toString());
      //  dataStr = org.apache.commons.lang.StringEscapeUtils.escapeJava(dataStr);  //���ת���
      //  System.out.println(dataStr);

        String msgId = ReceClaimRecordUtil.getCurrentTimeStrS()+(int)(Math.random()*10000);
        
        //2������SAP����-- ������Ӧ��Ϣ
        
        String sapReceReq = SAPInterfaceUtil.createSendReqStr(msgId,"FICO_I012", dataStr);
        System.out.println("sapReceReq:"+sapReceReq);
        
        //4������������Ϣ�� �޸�ÿ����¼ ����״̬
    	
        //5����¼���ͺ���Ӧ��־��Ϣ   	
//       String sapReceRsp = "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
//	    "   <SOAP:Header/>"+
//	    "   <SOAP:Body xmlns:mil=\"http://www.milkground.cn\">"+
//	    "      <ns0:MT_ALL2ERP_DATA_RECV xmlns:ns0=\"http://www.milkground.cn\">"+
//	    "         <OUTPUT>{\"TRANSACTION_ID\":\"20220812173650123\",\"SAP_DOC_ID\":\"\",\"DATE\":\"2022-08-16\",\"TIME\":\"10:31:30\",\"FLAG\":\"F\",\"MESSAGE\":\"�ٴ��������ڱ���\"}</OUTPUT>"+
//	    "      </ns0:MT_ALL2ERP_DATA_RECV>"+
//	    "   </SOAP:Body>"+
//	    "</SOAP:Envelope>";
    	   
       String sapReceRsp = SAPInterfaceUtil.sendSapRequest(sapReceReq);
       	
       System.out.println("sapReceRsp:"+sapReceRsp);
       InterResultMenu resultemnu = InterResultMenu.FAIL;
       SapReceRspDTO rsqdto = SAPInterfaceUtil.parseSapReceRespond(sapReceRsp);
       if(rsqdto != null && rsqdto.getFLAG() !=null && "S".equals(rsqdto.getFLAG() ))
    	   resultemnu = InterResultMenu.SUCCESS;
        SAPInterfaceLogInfo logInfo = new SAPInterfaceLogInfo();
        logInfo.setNumber(msgId);
        logInfo.setBizDate(currentDate);
        logInfo.setInterType(SAPInterTypeMenu.FICO_I012);
        logInfo.setClaimType(ClaimTypeMenu.CurrMonth);
        logInfo.setInterResult(resultemnu);
        logInfo.setReqTime(currentDate);
        logInfo.setRequest(dataStr);
        logInfo.setRespond(sapReceRsp);
	        try {
				SAPInterfaceLogFactory.getLocalInstance(ctx).addnew(logInfo);
			} catch (EASBizException e) {
				e.printStackTrace();
			 }
			
		     if(rIds !=null && rIds.size() >0 && InterResultMenu.SUCCESS_VALUE.equals(resultemnu.getValue())) 
		    	 ReceClaimRecordUtil.updateRecordSendSta(ctx, rIds,InterResultMenu.SUCCESS_VALUE);
		     else
		    	 ReceClaimRecordUtil.updateRecordSendSta(ctx, rIds,InterResultMenu.FAIL_VALUE);
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