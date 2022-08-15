package com.kingdee.eas.mkld.sapinterage.app.util;

public class SapInterfaceResource {
	
	 /**SAP 接口登录用户 **/
	public static final String userid = "po_consumer_eas";
	/**SAP 接口登录用户 密码**/
	public static final String password = "Passeas@0802#";
	 /**SAP 接口基础URL **/
	public static final String base_url ="http://42.123.65.139:50001/XISOAPAdapter/";
	
	 /**SAP 收款单认领接口URI **/
	public static final String rece_claim_uri = "MessageServlet?senderParty=&senderService=BS_EAS&receiverParty=&receiverService=&interface=SI_ALL2ERP_DATA_OUT&interfaceNamespace=http://www.milkground.cn";
	

}
