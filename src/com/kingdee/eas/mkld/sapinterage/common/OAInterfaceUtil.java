package com.kingdee.eas.mkld.sapinterage.common;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
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
	  

		public static String sendBankPayMessageToOAPost(String param,int oper) {
			OutputStreamWriter out = null;
	        BufferedReader in = null;
	        String result = "";  
	        String  url = InterfaceResource.oa_base_path+InterfaceResource.oa_bankpaying_path;
	        if(url != null && !"".equals(url)){
	        	try {
	        		URL realUrl = new URL(url);
	   	            HttpURLConnection conn =  (HttpURLConnection) realUrl.openConnection();
	   	             // 打开和URL之间的连接
	   	            // 发送POST请求必须设置如下两行
	   	            conn.setDoOutput(true);
	   	            conn.setDoInput(true);
	   	            conn.setRequestMethod("POST");    // POST方法
	   	            // 设置通用的请求属性
	   	            //conn.setRequestProperty("accept", "*/*");
	   	            //conn.setRequestProperty("connection", "Keep-Alive");
	   	            //conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	   	          	conn.setRequestProperty("Content-Type", "application/json");
	   	          	conn.setRequestProperty("Charset", "UTF-8");
	   	          	conn.setRequestProperty("appid",InterfaceResource.oa_appid);
	   	            conn.connect();
	   	            // 获取URLConnection对象对应的输出流
	   	            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
	   	            // 发送请求参数
	   	            out.write(param);
	   	            System.out.println("param------------------------"+param);
	   	            // flush输出流的缓冲
	   	            out.flush();
	   	            // 定义BufferedReader输入流来读取URL的响应
	   	            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	   	            String line;
	   	            while ((line = in.readLine()) != null) {
	   	                result += line;
	   	            }
	   	        } catch (Exception e) {
	   	            System.out.println("发送 POST 请求出现异常！"+e);
	   	            e.printStackTrace();
	   	        }
	   	        //使用finally块来关闭输出流、输入流
	   	        finally{
	   	            try{
	   	                if(out!=null){
	   	                    out.close();
	   	                }
	   	                if(in!=null){
	   	                    in.close();
	   	                }
	   	            }
	   	            catch(IOException ex){
	   	                ex.printStackTrace();
	   	            }
	   	        }
	        } 
	        return result;
		}


		public static String sendMessageToBDGet(String param) {
			OutputStreamWriter out = null;
	        BufferedReader in = null;
	        String result = "";  
	        String dburl = InterfaceResource.db_customer_path;
	        if(dburl != null && !"".equals(dburl)){
	        	try {
	        		URL realUrl = new URL(dburl);
	   	            HttpURLConnection conn =  (HttpURLConnection) realUrl.openConnection();
	   	             // 打开和URL之间的连接
	   	            // 发送POST请求必须设置如下两行
	   	            conn.setDoOutput(true);
	   	            conn.setDoInput(true);
	   	            conn.setRequestMethod("GET");    // POST方法
	   	            // 设置通用的请求属性 
	   	          	conn.setRequestProperty("app-key", InterfaceResource.db_center_app_key);
	   	          	conn.setRequestProperty("app-secret",InterfaceResource.db_center_app_secret);
	   	            // conn.setRequestProperty("Params", "insertdt=2022-06-06 00:00:00&page_num=2&page_size=100");
	   	          	conn.setRequestProperty("Params", param);
	   	            conn.connect();
	   	            // 获取URLConnection对象对应的输出流
	   	            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
	   	            // 发送请求参数
	   	            out.write(param);
	   	            System.out.println("param------------------------"+param);
	   	            // flush输出流的缓冲
	   	            out.flush();
	   	            // 定义BufferedReader输入流来读取URL的响应
	   	            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	   	            String line;
	   	            while ((line = in.readLine()) != null) {
	   	                result += line;
	   	            }
	   	        } catch (Exception e) {
	   	            System.out.println("发送 POST 请求出现异常！"+e);
	   	            e.printStackTrace();
	   	        }
	   	        //使用finally块来关闭输出流、输入流
	   	        finally{
	   	            try{
	   	                if(out!=null){
	   	                    out.close();
	   	                }
	   	                if(in!=null){
	   	                    in.close();
	   	                }
	   	            }
	   	            catch(IOException ex){
	   	                ex.printStackTrace();
	   	            }
	   	        }
	        }
	       
	       return result;
	   }  

}