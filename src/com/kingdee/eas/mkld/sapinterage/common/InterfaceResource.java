package com.kingdee.eas.mkld.sapinterage.common;

import java.util.HashMap;
import java.util.Map;

public class InterfaceResource {
	
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
	public static String db_customer_path = "https://48dc992f41254566bea81d1d4b02cbf8.apig.cn-east-3.huaweicloudapis.com/customer_master"; 

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
	

    public final static Map<String, String> Account_Business_Rale = new HashMap<String, String>() {
        {
            put("ORGUNIT","1");
            put("POSITION","2");
        }
    };
	
}
