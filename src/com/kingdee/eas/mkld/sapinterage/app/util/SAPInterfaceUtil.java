package com.kingdee.eas.mkld.sapinterage.app.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.apache.commons.codec.binary.Base64;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.google.gson.Gson;
import com.kingdee.eas.mkld.sapinterage.app.dto.SapReceRspDTO;

public class SAPInterfaceUtil {
	
	  private static String getBasicAuth()
	  {
		  
	    byte[] encodedAuth = (byte[])null;
	    String userName = SapInterfaceResource.userid;
	    String password = SapInterfaceResource.password;
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
	  
	  public static String sendSapRequest(String receStr)
	  {
	    String responseString = null;
	    OkHttpClient client = new OkHttpClient.Builder()
	      .connectTimeout(15L, TimeUnit.SECONDS)
	      .readTimeout(600L, TimeUnit.SECONDS)
	      .build();
	    MediaType mediaType = MediaType.parse("text/xml");
	    RequestBody body = RequestBody.create(mediaType, receStr);
	    Request request = new Request.Builder()
	      .url(SapInterfaceResource.base_url+ SapInterfaceResource.rece_claim_uri)
	      .get()
	      .put(body)
	      .addHeader("Content-Type", "application/x-www-form-urlencoded")
	      .addHeader("Authorization", getBasicAuth())
	      .addHeader("cache-control", "no-cache")
	      .addHeader("Postman-Token", "d5109dba-3f51-42e2-b9da-18ad87525936")
	      .build();
	    try
	    {
	      Response response = client.newCall(request).execute();
	      responseString = response.body().string();
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	    return responseString;
	  }
	  
	  public static String createSendReqStr(String sentId ,String senType,String items){
		 StringBuffer sbr = new StringBuffer();
			  sbr.append(" <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mil=\"http://www.milkground.cn\"> ");
			  sbr.append("    <soapenv:Header/>																			 ");
			  sbr.append("    <soapenv:Body>																			 ");
			  sbr.append("       <mil:MT_ALL2ERP_DATA_SEND>			 													 ");
			  sbr.append("          <IF_TYPE>"+senType+"</IF_TYPE>			 											 ");
			  sbr.append("          <IF_TRANSACTION_ID>"+sentId+"</IF_TRANSACTION_ID>									 ");
			  sbr.append("          <SEQUENCE>1</SEQUENCE>																 ");
			  sbr.append("          <IF_FINISHED_FLAG>true</IF_FINISHED_FLAG>											 ");
			  sbr.append("          <SYS_SRC>EAS</SYS_SRC>																 ");
			  sbr.append("          <TIMESTAMP_SRC>"+ReceClaimRecordUtil.getCurrentTimeStr()+"</TIMESTAMP_SRC>			 ");
			  sbr.append("          <SYS_TAR>SAP</SYS_TAR>																 ");
			  sbr.append("          <TIMESTAMP_PO></TIMESTAMP_PO>														 ");
			  sbr.append("          <IF_JSON_DATA>"+items+"</IF_JSON_DATA>												 ");
			  sbr.append("       </mil:MT_ALL2ERP_DATA_SEND>															 ");
			  sbr.append("    </soapenv:Body>			 																 ");
			  sbr.append(" </soapenv:Envelope>																			 ");
			  return sbr.toString();
	  }


		public static SapReceRspDTO parseSapReceRespond(String respond){
			SapReceRspDTO rsqdto = null;
			try {
				Document document = DocumentHelper.parseText(respond);
				Element root = document.getRootElement();
				Element levelOne =null;
			    Element levelTwo = null;
			    Element levelThree = null;
			    Element levelFour = null;
				Iterator itChild = root.elementIterator();
				Iterator itLevelTwo = null;
			    Iterator itLevelThree = null;
			    Iterator itLevelFour = null;
				String outputStr = null ;
				while(itChild.hasNext()){
					 levelOne = (Element)itChild.next();
					if("Body".equals(levelOne.getName())){
						 itLevelTwo = levelOne.elementIterator();
						 while (itLevelTwo.hasNext()) {
							 levelTwo = (Element)itLevelTwo.next();
							 if ("MT_ALL2ERP_DATA_RECV".equals(levelTwo.getName())){
								  	  itLevelThree = levelTwo.elementIterator();
						              while (itLevelThree.hasNext())
						              {
						            	  levelThree = (Element)itLevelThree.next();
						            	  if ("OUTPUT".equals(levelThree.getName()))
						                  {
						            		  outputStr = levelThree.getStringValue();
						                  }
						              }
					            }
				          }
					}
				}
				if(outputStr!=null && !"".equals(outputStr))
					rsqdto =  new Gson().fromJson(outputStr, SapReceRspDTO.class);
			} catch (DocumentException e) {
	 			e.printStackTrace();
			}
			
			return rsqdto;
		}	  
	  
}
