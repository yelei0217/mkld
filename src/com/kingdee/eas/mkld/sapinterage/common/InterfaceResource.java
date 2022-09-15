package com.kingdee.eas.mkld.sapinterage.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
	public static final String oa_inteface_path ="/api/EASToOA/accountworkflow/submitRequest";
	
	 /**OA 接口 路径  **/
	public static final String oa_appid ="AC41FDE9-5B3B-2CF9-FF02-84F0D1915A4F";
	
	 /**OA 接口 路径  银行付款单付款完成以后（调用oa接口）**/
	public static final String oa_bankpaying_path ="/api/EASToOA/accountworkflow/submitRequest";
	
	/** 接口 路径  调用中台系统同步客户 **/
	public static String db_customer_path = "https://48dc992f41254566bea81d1d4b02cbf8.apig.cn-east-3.huaweicloudapis.com/customer_master"; 

	/** 数据中台 app-key **/
	public static String db_center_app_key = "SAPMTU6MTY1OTQ0OTk1OTU1NQ=="; 
	
	/** 数据中台 app-secret **/
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
	

	public static String copyCtxUser ="virtualUser";  
	
	public static String  fileUrl = "/data/customer/";
	
	/**
	 *  银行账号 与事业部 对应关系
	 */
    public final static Map<String, String> Account_Business_Rale = new HashMap<String, String>() {
        {
        	put("36770188000135782",	"北区事业部");
        	put("310069192018800015245",	"南区事业部");
        	put("216120100100138853",	"餐饮事业部");
        	put(" 531902005810101",	"餐饮事业部");
        	put("453382354794",	"电商事业部");
        	put("03343210040038787",	"国际业务部");
        	put("158809983345",	"北区事业部");
        	put("22050147030000000697",	"北区事业部");
        }
    };
    
     
    /**
     * 收款单 获取 公司id集合
     */
    public final static HashSet<String> Rece_Company_Id_Sets = new HashSet<String>(){
    	{
    		 add("M2MAAAAABA3M567U");//1100		上海妙可蓝多食品科技股份有限公司
    		 add("M2MAAAAFzGHM567U");//1110		广泽乳业有限公司	  
    		 add("M2MAAAAFzJvM567U");//1220		海南新芝仕食品科技有限公司
    	}
    };
     	
}
