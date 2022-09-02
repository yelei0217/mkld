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
		String result ="ͬ���ɹ�";
 		String toekn = loginGetToken();
		if(toekn != null && !"".equals(toekn)){
			result= sendPost(InterfaceResource.dms_base_path+"/api/teapi/dy-biz/1332196536108585058/1338657770815230044/",body,toekn);
		}
		return result;
	}
	  /* ��ָ�� URL ����POST����������
	     * 
	     * @param url
	     *            ��������� URL
	     * @param param
	     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
	     * @param isproxy
	     *               �Ƿ�ʹ�ô���ģʽ
	     * @return ������Զ����Դ����Ӧ���
	     */
	    private static String sendPost(String url, String param,String token) {
	        OutputStreamWriter out = null;
	        BufferedReader in = null;
	        String result = "";
	        try {
	            URL realUrl = new URL(url);
	            HttpURLConnection conn =  (HttpURLConnection) realUrl.openConnection();
	             // �򿪺�URL֮�������
	 
	            // ����POST�������������������
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setRequestMethod("POST");    // POST����
	 
	            // ����ͨ�õ���������
	            conn.setRequestProperty("Accept", "*/*");
	            conn.setRequestProperty("Connection", "keep-alive");
	         //   conn.setRequestProperty("User-Agent", "PostmanRuntime/7.26.3");
	            conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
	            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
	            
	            if(token!=null && !"".equals(token)){
	                conn.setRequestProperty("token", token);
	            }
	            
	            conn.connect();
	            // ��ȡURLConnection�����Ӧ�������
	            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
	            // �����������
	            out.write(param);
	            // flush������Ļ���
	            out.flush();
	            // ����BufferedReader����������ȡURL����Ӧ
	            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	        	result = "���� POST ��������쳣��"+e;
	            System.out.println("���� POST ��������쳣��"+e);
	            e.printStackTrace();
	        }
	        //ʹ��finally�����ر��������������
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
