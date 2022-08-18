package com.kingdee.eas.mkld.sapinterage.common;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OAInterfaceUtil {

	  public static String sendSapRequest(String sendStr)
	  {
	    String responseString = null;
	    OkHttpClient client = new OkHttpClient.Builder()
	      .connectTimeout(15L, TimeUnit.SECONDS)
	      .readTimeout(600L, TimeUnit.SECONDS)
	      .build();
	    MediaType mediaType = MediaType.parse("application/json");
	    RequestBody body = RequestBody.create(mediaType, sendStr);
	    Request request = new Request.Builder()
	      .url(InterfaceResource.oa_base_path+InterfaceResource.oa_inteface_path)
	      .get()
	      .put(body)
	      .addHeader("Content-Type", "application/json")
	      .addHeader("cache-control", "no-cache")
	      .addHeader("appid", InterfaceResource.oa_appid)
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
