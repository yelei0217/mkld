package com.kingdee.eas.fm.be.app.bankpay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.base.core.fm.ContextHelperFactory;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.fm.be.BankPayingBillStateEnum;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.NumericExceptionSubItem;

public class BankPayingResultSynUtil {
	
	public static void synPayMentBill(Context ctx,String billId) throws EASBizException{
		String sql="select CFFEEPAYNO,fnumber,FBankPayState,FBankReturnInfo,FCommitBeTime from T_CAS_PaymentBill where fid=?";
		try {
			IRowSet rs=DbUtil.executeQuery(ctx, sql,new Object[]{billId});
			if(rs!=null && rs.next()){
				int status=rs.getInt("FBankPayState");
				String payInfo= rs.getString("FBankReturnInfo");
				Date d=rs.getDate("FCommitBeTime");
				String ss=BankPayingBillStateEnum.getEnum(status).getName();
				String ds=null;
				if(d!=null){
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					ds=df.format(d);
				}
				invokMethod(ctx,billId,ss,payInfo,ds);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EASBizException(new NumericExceptionSubItem(e.getMessage(),e.getMessage()));
		}
	}
	
	/**
	   * 调用更新支付时间
	   * @param ctx
	   * @param id
	   * @param billId
	   * @param status
	   * @param payInfo
	   * @param payTime
	   */
	  public static void invokMethod(Context ctx,String billId,String status,String payInfo,String payTime){
		 //封装对象
		  
		  //调用请求接口
	  }
	  
	  private static String getSendData(Context ctx,String paymentId,String status,String errorMsg,String paymentDate,String payerBankAccountNum,String bankFirstCode, String cbsBatchNo,String paybankno){
			JSONObject mp = new JSONObject();
			mp.put("opEfsPaymentId", paymentId); //付款行ID
			mp.put("paymentResult", status);//付款结果
			mp.put("errorMsg", errorMsg);//付款失败原因
			mp.put("paymentDate", paymentDate);//付款日期
			mp.put("paymentModeCode", "ZF0001");//付款方式编码
			mp.put("paymentModeName", "网银转账");//支付方式名称
			mp.put("payerBankAccountNum", payerBankAccountNum);//付款银行账号
			mp.put("bankFirstCode", bankFirstCode);//付款银行主数据编号
			mp.put("groupCode", "CGR");//集团编码
			mp.put("cbsBatchNo", cbsBatchNo);
			mp.put("bankFlowNumber", paybankno);//银行流水号
			JSONArray arry = new JSONArray();
			arry.add(mp);
	 		return arry.toJSONString();
	  }
	  	  
		 private static String sendSuppMsg(Context ctx,String fid,String  paymentId,String data){
//			  String url = "http://172.24.30.154/sys/interface-internal/ipefspaymentPrj/receivePayResult";  //测试地址
		 		String url = "http://fssc.staff.xdf.cn/sys/interface-internal/ipefspaymentPrj/receivePayResult"; //生产地址
		 		String result = sendPost(ctx,url,fid,paymentId, data);
		 		//LogUtil.insertLog(ctx,DateBaseproecssType.Update,DateBaseType.FA_Cat_Clear, paymentId, paymentId,result,data);
		 	 return result ;
		 }
		 
		 /**
		     * 向指定 URL 发送POST方法的请求
		     * 
		     * @param url
		     *            发送请求的 URL
		     * @param param
		     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
		     * @param isproxy
		     *               是否使用代理模式
		     * @return 所代表远程资源的响应结果
		     */
		    public static String sendPost(Context ctx,String url,String fid,String paymentId, String param) {
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
		            conn.setRequestProperty("accept", "*/*");
		           // conn.setRequestProperty("connection", "Keep-Alive");
		          //  conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		            //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
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
		        	insertErrorMsg(ctx,fid,paymentId,param);
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
		            	insertErrorMsg(ctx,fid,paymentId,param);
		                ex.printStackTrace();
		            }
		        }
		        return result;
		    }    
		    
		    /**
		     * 记录错误日志
		     * @param ctx
		     * @param fid
		     * @param paymentId
		     * @param data
		     */
			private static void insertErrorMsg(Context ctx,String fid,String paymentId,String data) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				String version = String.valueOf(cal.getTimeInMillis());
				String userId = ContextHelperFactory.getLocalInstance(ctx).getCurrentUser().getId().toString();
				String ctrlId = ContextHelperFactory.getLocalInstance(ctx).getCurrentCtrlUnit().getId().toString();
				StringBuffer sbr = new StringBuffer("insert into CT_XDF_FACARDSHARELOG(fid,fcreatorid,fcreatetime,flastupdateuserid,flastupdatetime,fcontrolunitid,");
		        sbr.append("cfProecssType,cfDateBaseType,cfStatus,cfDelstatus,cfVersion,fnumber,fname_l2,fSimpleName,cfMessage)");
				sbr.append("values (newbosid('5A5D36D6'),'" + userId+ "',sysdate,'" + userId + "',sysdate,'" + ctrlId+ "',14,26,0,1,'"  
						+ version + "','"+ cal.getTimeInMillis() + "."  + fid + "','"   + paymentId + "','"  + fid + "','"  + data + "')");
				try {
					DbUtil.execute(ctx,sbr.toString());
				} catch (BOSException e) {
 					e.printStackTrace();
				}
		
			}

}
