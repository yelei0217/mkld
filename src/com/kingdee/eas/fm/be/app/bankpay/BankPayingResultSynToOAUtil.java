package com.kingdee.eas.fm.be.app.bankpay;

import java.util.Date;
import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.kingdee.bos.Context;
import com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogFactory;
import com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogInfo;
import com.kingdee.eas.mkld.sapinterage.app.InterResultMenu;
import com.kingdee.eas.mkld.sapinterage.app.SAPInterTypeMenu;
import com.kingdee.eas.mkld.sapinterage.common.OAInterfaceUtil;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;

public class BankPayingResultSynToOAUtil {

	public static void synPayMentBill(Context ctx,String billId) {
		//billId = "+22aLXK6RN6Dtl7tn6kgKkAoToE=";
		/** 
		 序号	字段名称	字段类型  	字段大小	字段说明	是否需要必填	默认值
			1	requestid	String		OA流程唯一标识	必填	   505549
			2	remark	text		签字意见	必填	资金平台付款成功，流程自动提交
			3	payStatus	Integer		付款成功状态	必填	0  0-成功 /  1-失败
			4	BANKNAME	String		付款银行名称	必填	
			5	ZBANKN1	String		付款银行账号	必填	
			6	ZBUDAT1	DATE	YYYY-MM-DD	付款日期	必填	
			7	ZEASNUM	String		资金平台单据号	必填	 
		 */
		String sql= "  select  bill.fnumber NUMBER,bill.FBankPayState STATE ,bill.FBankReturnInfo RETURNMSG,bill.FCommitBeTime ,  " +
		"   to_char( bill.FPayDate,'yyyy-mm-dd') ZBUDAT1  , bank.fname_l2 BANKNAME  , bill.CFOaBillID OAID  , bill.CFSourceSystem  TYPE , " +   //bill.CFOaBillID OAID  , bill.CFSourceSystem  TYPE , , 
		" accou.FBANKACCOUNTNUMBER ZBANKN1 ,bill.fnumber ZEASNUM " +
		"  from T_CAS_PaymentBill  bill " +
		" left  join  T_BD_AccountBanks  accou on  accou.fid = bill.FPAYERACCOUNTBANKID  " +
		" left  join  T_BD_Bank  bank on  bank.fid = accou.FBANK  where bill.fid=?";
		try {
			IRowSet rs=DbUtil.executeQuery(ctx, sql,new Object[]{billId});
			if(rs!=null && rs.next()){
				
				if( null!= rs.getObject("TYPE")  && "OA".equals(rs.getString("TYPE"))){  
				//if( true ){  
					int status = rs.getInt("STATE");
					
					SAPInterfaceLogInfo sAPInterfaceLogInfo= new SAPInterfaceLogInfo();
					HashMap<String,Object> eMps = new HashMap<String,Object>();
					if( 6 == status ){
						eMps.put("payStatus", "0");
					}else{
						eMps.put("payStatus", "1");
					}
					String oaid = rs.getString("OAID");
					String returnMsg = rs.getString("RETURNMSG");
					
					String BANKNAME = rs.getString("BANKNAME");
					String ZBANKN1 = rs.getString("ZBANKN1"); 
					String ZEASNUM = rs.getString("ZEASNUM");
					String ZBUDAT1 = rs.getString("ZBUDAT1");
					 
					eMps.put("requestid", oaid);
					//eMps.put("requestid", "505549");
					eMps.put("remark", returnMsg); 
					
					eMps.put("BANKNAME", BANKNAME);
					eMps.put("ZBANKN1", ZBANKN1); 
					eMps.put("ZBUDAT1", ZBUDAT1);
					eMps.put("ZEASNUM", ZEASNUM);
					
					
					System.out.println("########  body ########"+JSONObject.toJSONString(eMps));
					sAPInterfaceLogInfo.setReqTime(new Date()); 
					sAPInterfaceLogInfo.setRequest(JSONObject.toJSONString(eMps));
					String result =  OAInterfaceUtil.sendBankPayMessageToOAPost(JSONObject.toJSONString(eMps),1);
					System.out.println("########  result ########"+result);
					if(null!=result && !"".equals(result)){
						JSONObject jo = JSONObject.parseObject(result);
						if(null!=jo.get("resultCode") && "0".equals(jo.get("resultCode"))){
							sAPInterfaceLogInfo.setInterResult(InterResultMenu.SUCCESS);
							sAPInterfaceLogInfo.setRespond(result);
						}else{
							sAPInterfaceLogInfo.setInterResult(InterResultMenu.FAIL);
							sAPInterfaceLogInfo.setRespond(result);
						}
						
					}else{
						sAPInterfaceLogInfo.setRespond("返回值为空，请查看");
					}
					
					sAPInterfaceLogInfo.setInterType(SAPInterTypeMenu.BANKPAY);
					SAPInterfaceLogFactory.getLocalInstance(ctx).save(sAPInterfaceLogInfo);
				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	  
	
}
