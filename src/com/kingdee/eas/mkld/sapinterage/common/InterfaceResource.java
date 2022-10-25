package com.kingdee.eas.mkld.sapinterage.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.eas.mkld.sapinterage.ClaimAccountCollection;
import com.kingdee.eas.mkld.sapinterage.ClaimAccountFactory;
import com.kingdee.eas.mkld.sapinterage.ClaimAccountInfo;
import com.kingdee.eas.mkld.sapinterage.app.ClaimStatusMenu;
import com.kingdee.eas.mkld.sapinterage.app.ClaimTypeMenu;
import com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu;

public class InterfaceResource {
//	  ����ϵͳ���� 
	
	 /** Ĭ�Ͽͻ� **/
	public static final String DEFAULT_CUSTOMER_NO = "9000011";
	 /**��������ID **/
	public static final String MKLD_DB_ID = "mkldTest";
	
	 /**SAP �ӿڵ�¼�û� **/
	public static final String sap_userid = "po_consumer_eas";
	
	/**SAP �ӿڵ�¼�û� ����**/
	public static final String sap_password = "Passeas@0802#";
	
	 /**SAP �ӿڻ���URL **/
 	public static final String sap_base_url ="https://vhimngodci.hec.mengniu.cn:50001/RESTAdapter/EAS2ERP/DATA";

 	 /**SAP SSL ֤��·��  **/
	public static final String sap_Certificate_path = "/software/xxx.keystore";
	
	 /**SAP SSL ֤��·��  **/
	public static final String sap_Certificate_pwd="123456";
	
	 /**OA ���� ·��  **/
	public static final String oa_base_path ="http://180.169.95.237:9080";
	
	 /**OA �ӿ� ·��  **/
	public static final String oa_inteface_path ="/api/EASToOA/accountworkflow/submitRequest";
	
	 /**OA �ӿ� ·��  **/
	public static final String oa_appid ="AC41FDE9-5B3B-2CF9-FF02-84F0D1915A4F";
	
	 /**OA �ӿ� ·��  ���и����������Ժ󣨵���oa�ӿڣ�**/
	public static final String oa_bankpaying_path ="/api/EASToOA/accountworkflow/submitRequest";
	
	/** �ӿ� ·��  ������̨ϵͳͬ���ͻ� **/
	public static String db_customer_path = "http://10.52.127.122:8087/customer_master/"; 

	/** ������̨ app-key **/
	public static String db_center_app_key = "SAPMTU6MTY1OTQ0OTk1OTU1NQ=="; 
	
	/** ������̨ app-secret **/
	public static String db_center_app_secret = "sap27b3b5b3e365343f64d154abb0b64563"; 
	
	/** DMS accountinfocode **/
	public static String dms_account_info_code = "1457892711934857216"; 
	
	/** DMS opentypecode **/
	public static String dms_open_ytpe_code = "milkground-data-server"; 

	/** DMS opentypesecret **/
	public static String dms_open_ytpe_secret = "123456";
	
	/** DMS clienttypecode **/
	public static String dms_client_ytpe_code = "6";
	
	/** DMS dms_base_path **/
	public static String dms_base_path ="http://116.63.82.155:7000";
	

	public static String copyCtxUser ="user";  
	
	public static String  fileUrl = "/data/customer/";
	
//	/**
//	 *  �����˺� ����ҵ�� ��Ӧ��ϵ
//	 */
//   public final static Map<String, String> Account_2MDS_Rale = new HashMap<String, String>() {
//       {
//       	put("36770188000135700",	"������ҵ��");
//       	put("453382354794",	"������ҵ��");
//       	put("216120100100138000",	"������ҵ��");
//       	put("531902005810101",	"������ҵ��");
//       	put("3343210040038780",	"����������ҵ��ԭ������");
//       	put("310069192018800000000",	"������ҵ��");
//       	put("22050147030000000697",	"������ҵ��");
//       }
//   };
//   
//   
//   /**
//    * �տ ��ȡ ��˾id����
//    */
//   public final static HashSet<String> Rece_Account_Id_Sets = new HashSet<String>(){
//   	{
//   		add("36770188000135700");	//110001	������౱����������	�й�������йɷ����޹�˾�Ϻ���ɽ֧��
//   		add("453382354794");	//110002	������������������	�й������Ϻ��з���֧��
//   		add("216120100100138000");	//110003	������������������	��ҵ�����Ϻ�¬��֧��
//   		add("531902005810101");	//110003		���������Ϻ�����
//   		add("3343210040038780");	//110004	����������ҵ��ԭ������	�й�ũҵ�����Ϻ��н���֧��
//   		add("310069192018800000000");	//110005	�������������������	��ͨ�����Ϻ�����֧��
//   		add("221000603011907090204");	//111001	������ҵҺ����������	��ͨ���йɷ����޹�˾����ʡ����Ӫҵ��
//   		add("22050147030000000697");	//111002	������ҵ�����������ģ��ڲ����ף�	�й��������йɷ����޹�˾������·֧��
//   		add("265036446929");	//122000	������֥�˱�����������	�й������Ϻ��з���֧��
//   	}
//   };
//   
//  // public final static String Rece_Account_Ids = "'36770188000135700','453382354794','216120100100138000','531902005810101','3343210040038780','310069192018800000000','221000603011907090204','22050147030000000697','265036446929'";
//   
//   /**
//    * �տ ��ȡ ��˾id����
//    */
//   public final static HashSet<String> Rece_Company_Id_Sets = new HashSet<String>(){
//   	{
//   		 add("M2MAAAAABA3M567U");//1100		�Ϻ��������ʳƷ�Ƽ��ɷ����޹�˾
//   		 add("M2MAAAAFzGHM567U");//1110		������ҵ���޹�˾	  
//   		 add("M2MAAAAFzJvM567U");//1220		������֥��ʳƷ�Ƽ����޹�˾
//   	}
//   };
//    
	
//	 // ����ϵͳ����
//	 /** Ĭ�Ͽͻ� **/
//	public static final String DEFAULT_CUSTOMER_NO = "9000000";
//	
//	 /**��������ID **/
//	public static final String MKLD_DB_ID = "MKLD_EAS";
//	
//	 /**SAP �ӿڵ�¼�û� **/
//	public static final String sap_userid = "pop_consumer_eas";
//	
//	/**SAP �ӿڵ�¼�û� ����**/
//	public static final String sap_password = "Poeas@1005#";
//	
//	 /**SAP �ӿڻ���URL **/
// 	public static final String sap_base_url ="http://10.200.7.101:50000/RESTAdapter/EAS2ERP/DATA";
//
// 	 /**SAP SSL ֤��·��  **/
//	public static final String sap_Certificate_path = "/software/xxx.keystore";
//	
//	 /**SAP SSL ֤��·��  **/
//	public static final String sap_Certificate_pwd="123456";
//	
//	 /**OA ���� ·��  **/
//	public static final String oa_base_path ="http://10.52.127.10:9080";
//	
//	 /**OA �ӿ� ·��  **/
//	public static final String oa_inteface_path ="/api/EASToOA/accountworkflow/submitRequest";
//	
//	 /**OA �ӿ� ·��  **/
//	public static final String oa_appid ="BE6FEC0B-734E-4A28-8F67-AD4D141197AE";
//	
//	 /**OA �ӿ� ·��  ���и����������Ժ󣨵���oa�ӿڣ�**/
//	public static final String oa_bankpaying_path ="/api/EASToOA/accountworkflow/submitRequest";
//	
//	/** �ӿ� ·��  ������̨ϵͳͬ���ͻ� **/
//	public static String db_customer_path = "http://10.52.127.122:8087/customer_master/"; 
//
//	/** ������̨ app-key **/
//	public static String db_center_app_key = "SAPMTU6MTY1OTQ0OTk1OTU1NQ=="; 
//	
//	/** ������̨ app-secret **/
//	public static String db_center_app_secret = "sap27b3b5b3e365343f64d154abb0b64563"; 
//	
//	/** DMS accountinfocode **/
//	public static String dms_account_info_code = "1273563847394988032"; 
//	
//	/** DMS opentypecode **/
//	public static String dms_open_ytpe_code = "milkground-data-server"; 
//
//	/** DMS opentypesecret **/
//	public static String dms_open_ytpe_secret = "123456";
//	
//	/** DMS clienttypecode **/
//	public static String dms_client_ytpe_code = "6";
//	
//	/** DMS dms_base_path **/
//	public static String dms_base_path ="http://47.102.138.30:7000";
//	
//	public static String copyCtxUser ="user";  
//	
//	public static String  fileUrl = "/data/customer/";
//	
//	
//	/**
//	 *  �����˺� ����ҵ�� ��Ӧ��ϵ
//	 */
//  public final static Map<String, String> Account_2MDS_Rale = new HashMap<String, String>() {
//      {
//      	put("36770188000135782",	"������ҵ��");
//      	put("453382354794",	"������ҵ��");
//      	put("216120100100138853",	"������ҵ��");
//      	put("531902005810101",	"������ҵ��");
//      	put("03343210040038787",	"����������ҵ��ԭ������");
//      	put("310069192018800015245",	"������ҵ��");
//      	put("22050147030000000697",	"������ҵ��");
//      }
//  };
  
  
  public final static Map<String, String> getAccount2MDSRale(Context ctx){
	  Map<String, String> mp = new HashMap<String, String>();
 	  EntityViewInfo viewInfo = new EntityViewInfo();
      FilterInfo filter = new FilterInfo();
      filter.getFilterItems().add(new FilterItemInfo("isSendDms",true,CompareType.EQUALS));//�������ͣ���������
      filter.getFilterItems().add(new FilterItemInfo("dataState",com.kingdee.eas.basedata.framework.DataStateEnum.ENABLE,CompareType.EQUALS));//����״̬��������
      viewInfo.setFilter(filter);
      try {
		  ClaimAccountCollection coll = ClaimAccountFactory.getLocalInstance(ctx).getClaimAccountCollection(viewInfo);
		  if(coll !=null && coll.size() >0 ){
			  Iterator it = coll.iterator();
			  while(it.hasNext()){
				  ClaimAccountInfo info = (ClaimAccountInfo) it.next();
				  if(info.getNumber() !=null && !"".equals(info.getNumber()) &&
					 info.getBusDivision() !=null && !"".equals(info.getBusDivision())) 
					  mp.put(info.getNumber(), info.getBusDivision()); 
			  }		   
		  }
	} catch (BOSException e) {
 		e.printStackTrace();
	} 
	  return mp;
  }
  
  
//  
//  /**
//   * �տ ��ȡ ��˾id����
//   */
//  public final static HashSet<String> Rece_Account_Id_Sets = new HashSet<String>(){
//  	{
//  		add("36770188000135782");	//110001	������౱����������	�й�������йɷ����޹�˾�Ϻ���ɽ֧��
//  		add("453382354794");	//110002	������������������	�й������Ϻ��з���֧��
//  		add("216120100100138853");	//110003	������������������	��ҵ�����Ϻ�¬��֧��
//  		add("531902005810101");	//110003		���������Ϻ�����
//  		add("03343210040038787");	//110004	����������ҵ��ԭ������	�й�ũҵ�����Ϻ��н���֧��
//  		add("310069192018800015245");	//110005	�������������������	��ͨ�����Ϻ�����֧��
//  		add("221000603011907090204");	//111001	������ҵҺ����������	��ͨ���йɷ����޹�˾����ʡ����Ӫҵ��
//  		add("22050147030000000697");	//111002	������ҵ�����������ģ��ڲ����ף�	�й��������йɷ����޹�˾������·֧��
//  		add("265036446929");	//122000	������֥�˱�����������	�й������Ϻ��з���֧��
//  	}
//  };
  
  public final static HashSet<String> getReceAccountIdSets(Context ctx){
	  HashSet<String> sets = new HashSet<String> ();
	  EntityViewInfo viewInfo = new EntityViewInfo();
      FilterInfo filter = new FilterInfo();
      filter.getFilterItems().add(new FilterItemInfo("isCalim",true,CompareType.EQUALS));//�������ͣ���������
      filter.getFilterItems().add(new FilterItemInfo("dataState",com.kingdee.eas.basedata.framework.DataStateEnum.ENABLE,CompareType.EQUALS));//����״̬��������
      viewInfo.setFilter(filter);
	  try {
		  ClaimAccountCollection coll = ClaimAccountFactory.getLocalInstance(ctx).getClaimAccountCollection(viewInfo);
		  if(coll !=null && coll.size() >0 ){
			  Iterator it = coll.iterator();
			  while(it.hasNext()){
				  ClaimAccountInfo info = (ClaimAccountInfo) it.next();
				  sets.add(info.getNumber());
			  }		   
		  }
	} catch (BOSException e) {
 		e.printStackTrace();
	} 
	  return sets;
  }
	  
	  
//  public final static String Rece_Account_Ids = " '36770188000135782','453382354794','216120100100138853','531902005810101','03343210040038787','310069192018800015245','221000603011907090204','22050147030000000697','265036446929' ";
  
  
//  /**
//   * �տ ��ȡ ��˾id����
//   */
//  public final static HashSet<String> Rece_Company_Id_Sets = new HashSet<String>(){
//  	{
//  		 add("iEYAAAAABBXM567U");//1100		�Ϻ��������ʳƷ�Ƽ��ɷ����޹�˾
//  		 add("iEYAAAALxK/M567U");//1110		������ҵ���޹�˾	  
//  		 add("iEYAAAAFc7fM567U");//1220		������֥��ʳƷ�Ƽ����޹�˾
//  	}
//  };
    
  
  public final static HashSet<String> getReceCompanyIdSets(Context ctx){
	  HashSet<String> sets = new HashSet<String> ();
	  EntityViewInfo viewInfo = new EntityViewInfo();
      FilterInfo filter = new FilterInfo();
      filter.getFilterItems().add(new FilterItemInfo("isCalim",true,CompareType.EQUALS));//�������ͣ���������
      filter.getFilterItems().add(new FilterItemInfo("dataState",com.kingdee.eas.basedata.framework.DataStateEnum.ENABLE,CompareType.EQUALS));//����״̬��������
      viewInfo.setFilter(filter);
	  try {
		  ClaimAccountCollection coll = ClaimAccountFactory.getLocalInstance(ctx).getClaimAccountCollection(viewInfo);
		  if(coll !=null && coll.size() >0 ){
			  Iterator it = coll.iterator();
			  while(it.hasNext()){
				  ClaimAccountInfo info = (ClaimAccountInfo) it.next();
				  if(info.getCompany() !=null && info.getCompany().getId() !=null && !"".equals(info.getCompany().getId().toString()))
				  sets.add(info.getCompany().getId().toString());
			  }		   
		  }
	} catch (BOSException e) {
 		e.printStackTrace();
	} 
	  return sets;
  }
  
  
     	
}
