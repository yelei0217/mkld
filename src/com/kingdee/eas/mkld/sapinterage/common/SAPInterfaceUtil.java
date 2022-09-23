package com.kingdee.eas.mkld.sapinterage.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

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
		  Gson gson=new Gson();  //对象转Json
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
//		    result = sendPost( InterfaceResource.sap_base_url,strJson,getBasicAuth());
		} catch (Exception e) {
 			e.printStackTrace();
		}
		  return result ;
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
	            conn.setRequestProperty("Authorization", token);
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
