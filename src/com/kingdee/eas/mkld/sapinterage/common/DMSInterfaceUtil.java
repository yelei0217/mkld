package com.kingdee.eas.mkld.sapinterage.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DMSInterfaceUtil {

	public static String loginGetToken(){
  		String token ="";
		String url = InterfaceResource.dms_base_path+"/api/auth/openlogin";
		JSONObject mp = new JSONObject();
		mp.put("accountinfocode", InterfaceResource.dms_account_info_code);
		mp.put("opentypecode", InterfaceResource.dms_open_ytpe_code);
		mp.put("opentypesecret", InterfaceResource.dms_open_ytpe_secret);
		mp.put("clienttypecode", InterfaceResource.dms_client_ytpe_code);
		String result = sendPost(url, mp.toJSONString(),"");
		if(result!=null && !"".equals(result)){
			 JsonObject returnData = new JsonParser().parse(result).getAsJsonObject();
			 if(returnData.get("resp_data")!=null && returnData.get("resp_data").getAsJsonObject().get("token") !=null){
				 token = returnData.get("resp_data").getAsJsonObject().get("token").getAsString();
			 }
		}
	 return token ;
	}

	public static String sendCustomer2dms( String body){
		String result ="同步成功";
 		String toekn = loginGetToken();
		if(toekn != null && !"".equals(toekn)){
			result= sendPost(InterfaceResource.dms_base_path+"/api/teapi/dy-biz/1332196536108585058/1338657770815230044/",body,toekn);
		}
		return result;
	}
	  /* 向指定 URL 发送POST方法的请求
	     * 
	     * @param url
	     *            发送请求的 URL
	     * @param param
	     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	     * @param isproxy
	     *               是否使用代理模式
	     * @return 所代表远程资源的响应结果
	     */
	    private static String sendPost(String url, String param,String token) {
	        OutputStreamWriter out = null;
	        BufferedReader in = null;
	        String result = "";
	        try {
	            URL realUrl = new URL(url);
	            HttpURLConnection conn =  (HttpURLConnection) realUrl.openConnection();
	             // 打开和URL之间的连接
	 
	            // 发送POST请求必须设置如下两行
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setRequestMethod("POST");    // POST方法
	 
	            // 设置通用的请求属性
	            conn.setRequestProperty("Accept", "*/*");
	            conn.setRequestProperty("Connection", "keep-alive");
	         //   conn.setRequestProperty("User-Agent", "PostmanRuntime/7.26.3");
	            conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
	            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
	            
	            if(token!=null && !"".equals(token)){
	                conn.setRequestProperty("token", token);
	            }
	            
	            conn.connect();
	            // 获取URLConnection对象对应的输出流
	            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
	            // 发送请求参数
	            out.write(param);
	            // flush输出流的缓冲
	            out.flush();
	            // 定义BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	        	result = "发送 POST 请求出现异常！"+e;
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
	        return result;
	    }    
}
