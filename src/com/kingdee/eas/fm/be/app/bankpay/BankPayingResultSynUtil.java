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
	   * ���ø���֧��ʱ��
	   * @param ctx
	   * @param id
	   * @param billId
	   * @param status
	   * @param payInfo
	   * @param payTime
	   */
	  public static void invokMethod(Context ctx,String billId,String status,String payInfo,String payTime){
		 //��װ����
		  
		  //��������ӿ�
	  }
	  
	  private static String getSendData(Context ctx,String paymentId,String status,String errorMsg,String paymentDate,String payerBankAccountNum,String bankFirstCode, String cbsBatchNo,String paybankno){
			JSONObject mp = new JSONObject();
			mp.put("opEfsPaymentId", paymentId); //������ID
			mp.put("paymentResult", status);//������
			mp.put("errorMsg", errorMsg);//����ʧ��ԭ��
			mp.put("paymentDate", paymentDate);//��������
			mp.put("paymentModeCode", "ZF0001");//���ʽ����
			mp.put("paymentModeName", "����ת��");//֧����ʽ����
			mp.put("payerBankAccountNum", payerBankAccountNum);//���������˺�
			mp.put("bankFirstCode", bankFirstCode);//�������������ݱ��
			mp.put("groupCode", "CGR");//���ű���
			mp.put("cbsBatchNo", cbsBatchNo);
			mp.put("bankFlowNumber", paybankno);//������ˮ��
			JSONArray arry = new JSONArray();
			arry.add(mp);
	 		return arry.toJSONString();
	  }
	  	  
		 private static String sendSuppMsg(Context ctx,String fid,String  paymentId,String data){
//			  String url = "http://172.24.30.154/sys/interface-internal/ipefspaymentPrj/receivePayResult";  //���Ե�ַ
		 		String url = "http://fssc.staff.xdf.cn/sys/interface-internal/ipefspaymentPrj/receivePayResult"; //������ַ
		 		String result = sendPost(ctx,url,fid,paymentId, data);
		 		//LogUtil.insertLog(ctx,DateBaseproecssType.Update,DateBaseType.FA_Cat_Clear, paymentId, paymentId,result,data);
		 	 return result ;
		 }
		 
		 /**
		     * ��ָ�� URL ����POST����������
		     * 
		     * @param url
		     *            ��������� URL
		     * @param param
		     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
		     * @param isproxy
		     *               �Ƿ�ʹ�ô���ģʽ
		     * @return ������Զ����Դ����Ӧ���
		     */
		    public static String sendPost(Context ctx,String url,String fid,String paymentId, String param) {
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
		            conn.setRequestProperty("accept", "*/*");
		           // conn.setRequestProperty("connection", "Keep-Alive");
		          //  conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		            //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
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
		        	insertErrorMsg(ctx,fid,paymentId,param);
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
		            	insertErrorMsg(ctx,fid,paymentId,param);
		                ex.printStackTrace();
		            }
		        }
		        return result;
		    }    
		    
		    /**
		     * ��¼������־
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
