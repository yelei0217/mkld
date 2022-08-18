package com.kingdee.eas.mkld.sapinterage.common;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.HttpClient;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kingdee.eas.mkld.sapinterage.app.dto.SapReceReqDTO;
import com.kingdee.eas.mkld.sapinterage.app.dto.SapReceRspDTO;
import com.kingdee.eas.mkld.sapinterage.app.util.ReceClaimRecordUtil;
import com.kingdee.eas.mkld.sapinterage.rest.HTTPSClientUtil;
import com.kingdee.eas.mkld.sapinterage.rest.HTTPSTrustClient;

public class SAPInterfaceUtil {
	
	  public static String getBasicAuth()
	  {
	    byte[] encodedAuth = (byte[])null;
	    String userName = InterfaceResource.sap_userid;
	    String password = InterfaceResource.sap_password;
	    String auth = null;
	    try
	    {
	      auth = userName + ":" + password;
	      encodedAuth = Base64.encodeBase64(auth.getBytes("UTF-8"));
	    }
	    catch (UnsupportedEncodingException e)
	    {
	      e.printStackTrace();
	    }
	    String basicAuth = "Basic " + new String(encodedAuth);
	    return basicAuth;
	  }
	  
	  public static String createSendReqStr(String sentId ,String senType,String jsonData){
		  SapReceReqDTO rq = new SapReceReqDTO();
		  rq.setIF_TYPE(senType);
		  rq.setIF_TRANSACTION_ID(sentId);
		  rq.setSEQUENCE("1");
		  rq.setIF_FINISHED_FLAG("true");
		  rq.setSYS_SRC("EAS");
		  rq.setSYS_TAR("SAP");
		  rq.setTIMESTAMP_SRC(ReceClaimRecordUtil.getCurrentTimeStr());
		  rq.setIF_JSON_DATA(jsonData);
		  Gson gson=new Gson();  //����תJson
		 return gson.toJson(rq);
	 }
	  
	  public static boolean judgeInteSuccess(String result){
		 boolean flag = false;
		 if(result!= null && !"".equals(result) && result.contains("OUTPUT")){
			 JsonObject returnData = new JsonParser().parse(result).getAsJsonObject();
			 if(returnData.get("OUTPUT")!=null ){
				 Gson gson=new Gson();
				 String outputStr = returnData.get("OUTPUT").getAsString();
		     	 SapReceRspDTO m = (SapReceRspDTO) gson.fromJson(outputStr,SapReceRspDTO.class);
		     	 if(m !=null && m.getFLAG() !=null && "S".equals(m.getFLAG()))
		     		flag = true;
			 }
		 }
		 return flag ;
	 }
	 
	  public static String sendSapRequest(String strJson){
		  String result = null;
		  try {
			HttpClient httpClient = new HTTPSTrustClient().init();
		//	String strJson = createSendReqStr(sentId,senType,jsonData);
		    result = HTTPSClientUtil.doPostJson(httpClient, InterfaceResource.sap_base_url, strJson);
		} catch (Exception e) {
 			e.printStackTrace();
		}
		  return result ;
	 }
	  
	 
}
