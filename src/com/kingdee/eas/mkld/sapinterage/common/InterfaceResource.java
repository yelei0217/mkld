package com.kingdee.eas.mkld.sapinterage.common;

public class InterfaceResource {
	
	 /**数据中心ID **/
	public static final String MKLD_DB_ID = "mkldTest";
	
	 /**SAP 接口登录用户 **/
	public static final String sap_userid = "po_consumer_eas";
	
	/**SAP 接口登录用户 密码**/
	public static final String sap_password = "Passeas@0802#";
	
	 /**SAP 接口基础URL **/
 	public static final String sap_base_url ="https://vhimngodci.hec.mengniu.cn:50001/RESTAdapter/EAS2ERP/DATA";

 	 /**SAP SSL 证书路径  **/
	public static final String sap_Certificate_path = "/software/xxx.keystore";
	
	 /**SAP SSL 证书路径  **/
	public static final String sap_Certificate_pwd="123456";
	
	 /**OA 基础 路径  **/
	public static final String oa_base_path ="http://180.169.95.237:9080";
	
	 /**OA 接口 路径  **/
	public static final String oa_inteface_path ="/api/mkld/workflow/submitRequest";
	
	 /**OA 接口 路径  **/
	public static final String oa_appid ="AC41FDE9-5B3B-2CF9-FF02-84F0D1915A4F";
	
	 /**OA 接口 路径  银行付款单付款完成以后（调用oa接口）**/
	public static final String oa_bankpaying_path ="/api/EASToOA/accountworkflow/submitRequest";
	
	/** 接口 路径  调用中台西系统同步客户 **/
	public static String db_customer_path = "https://48dc992f41254566bea81d1d4b02cbf8.apig.cn-east-3.huaweicloudapis.com/customer_master"; 

	/** 数据中台 app-key **/
	public static String db_center_app_key = "SAPMTU6MTY1OTQ0OTk1OTU1NQ=="; 
	
	/** 数据中台 app-secret **/
	public static String db_center_app_secret = "sap27b3b5b3e365343f64d154abb0b64563"; 

	
}
