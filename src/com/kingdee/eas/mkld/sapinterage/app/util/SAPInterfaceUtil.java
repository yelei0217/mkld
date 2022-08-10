package com.kingdee.eas.mkld.sapinterage.app.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.apache.commons.codec.binary.Base64;

public class SAPInterfaceUtil {

	
	  private String getBasicAuth()
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
	  
	  public String sendSapRequest(String receStr)
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
}
