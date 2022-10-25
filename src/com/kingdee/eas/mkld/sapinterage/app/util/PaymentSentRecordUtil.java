package com.kingdee.eas.mkld.sapinterage.app.util;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.derby.tools.sysinfo;

import com.alibaba.fastjson.JSONObject;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogFactory;
import com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogInfo;
import com.kingdee.eas.mkld.sapinterage.app.InterResultMenu;
import com.kingdee.eas.mkld.sapinterage.app.SAPInterTypeMenu;
import com.kingdee.eas.mkld.sapinterage.common.OAInterfaceUtil;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet; 
public class PaymentSentRecordUtil {

	/**
	 * 
	 * 生成 数据
	 * @param ctx
	 * @throws BOSException
	 */
	public static void doGenRecord(Context ctx) throws BOSException {
		//删除 7天之前的 数据
		String delSql = "/*dialect*/ delete from CT_SIG_PaymentSentRecord where FCREATETIME < to_date(to_char(sysdate-7,'yyyy/mm/dd'),'yyyy/mm/dd') ";
		DbUtil.execute(ctx, delSql);
		
		StringBuffer sbr = new StringBuffer();
  		sbr.append(" /*dialect*/insert into CT_SIG_PaymentSentRecord ").append("\r\n");
  		sbr.append("(FID,FNumber,FName_l2,CFSENTFLAG,CFCOMPANYID,CFZBUDAT1,CFPAYAMOUNT,CFOAID,FSIMPLENAME,CFZBANKN1,FDESCRIPTION_L2,CFRecBillNum,FCREATETIME) ").append("\r\n");
		sbr.append(" select newbosid('080ABC86'),p.FID, p.fnumber ZEASNUM,0,p.FCOMPANYID , to_char( ep.FSUBMITTIME,'yyyy-mm-dd')  ZBUDAT1,ep.FPAYAMOUNT , ").append("\r\n");
		sbr.append(" p.CFOaBillID OAID ,bank.fname_l2 BANKNAME,accou.FBANKACCOUNTNUMBER ZBANKN1, ep.FBANKRETURNINFO RETURNMSG,p.CFRecBillNum,sysdate ").append("\r\n");
		sbr.append(" from T_CAS_PaymentBill p ").append("\r\n");
		sbr.append(" inner join T_BE_BankPayingBill ep on ep.FSOURCEBILLID  = p.FID ").append("\r\n");
		sbr.append(" inner join  T_BD_AccountBanks  accou on  accou.fid = p.FPAYERACCOUNTBANKID ").append("\r\n");
		sbr.append(" inner join  T_BD_Bank  bank on  bank.fid = accou.FBANK ").append("\r\n");
		sbr.append(" where ep.FSTATE = 6 and p.CFSourceSystem ='OA' ").append("\r\n");
		sbr.append(" and to_char(ep.FSUBMITTIME,'yyyy-MM-dd') = to_char(SYSDATE,'yyyy-MM-dd') ").append("\r\n");
		sbr.append(" and not exists (select * from CT_SIG_PaymentSentRecord r where r.FNUMBER = p.FID ) ");
		DbUtil.execute(ctx, sbr.toString());
	}
	
	/**
	 * 
	 * 发送 数据
	 * @param ctx
	 * @throws BOSException
	 */
	public static void doSentRecord(Context ctx) throws BOSException {
		String sql ="select FID,FNumber,FName_l2,CFSENTFLAG,CFCOMPANYID,CFZBUDAT1,CFPAYAMOUNT,CFOAID,FSIMPLENAME,CFZBANKN1,FDESCRIPTION_L2,CFRecBillNum,FCREATETIME from CT_SIG_PaymentSentRecord WHERE CFSENTFLAG != 1 ";
		IRowSet rs = DbUtil.executeQuery(ctx, sql);
		if(rs !=null && rs.size() >0 ){ 
			// 获取token
			try {
 				while(rs.next()){
					if(rs.getObject("CFZBUDAT1") !=null && !"".equals(rs.getObject("CFZBUDAT1").toString()) && 
							rs.getObject("FName_l2") !=null && !"".equals(rs.getObject("FName_l2").toString()) && 
						//	rs.getObject("CFPAYAMOUNT") !=null && !"".equals(rs.getObject("CFPAYAMOUNT").toString()) && 
							rs.getObject("CFOAID") !=null && !"".equals(rs.getObject("CFOAID").toString()) && 
							rs.getObject("FSIMPLENAME") !=null && !"".equals(rs.getObject("FSIMPLENAME").toString()) && 
							rs.getObject("CFZBANKN1") !=null && !"".equals(rs.getObject("CFZBANKN1").toString()) && 
							rs.getObject("FDESCRIPTION_L2") !=null && !"".equals(rs.getObject("FDESCRIPTION_L2").toString())  
					){
						HashMap<String,Object> eMps = new HashMap<String,Object>();
						eMps.put("payStatus", "0");
						eMps.put("requestid", rs.getObject("CFOAID").toString()); 
						eMps.put("remark", rs.getObject("FDESCRIPTION_L2").toString()); 
						eMps.put("BANKNAME", rs.getObject("FSIMPLENAME").toString());
						eMps.put("ZBANKN1", rs.getObject("CFZBANKN1").toString()); 
						eMps.put("ZBUDAT1", rs.getObject("CFZBUDAT1").toString());
						eMps.put("ZEASNUM", rs.getObject("FName_l2").toString());
						String sendData = JSONObject.toJSONString(eMps);
 						System.out.println("########  sendData ########"+sendData);
						SAPInterfaceLogInfo logInfo= new SAPInterfaceLogInfo();
						logInfo.setReqTime(new Date());  
						logInfo.setRequest(sendData);
						String result = OAInterfaceUtil.sendBankPayMessageToOAPost(sendData);
 						System.out.println("########  result ########"+result);
 						int sendFlag = 0 ;
						if(null!=result && !"".equals(result)){
							JSONObject jo = JSONObject.parseObject(result);
							if(null!=jo.get("resultCode") && "0".equals(jo.get("resultCode"))){
								logInfo.setInterResult(InterResultMenu.SUCCESS);
								logInfo.setRespond(result);
								sendFlag = 1 ;
								//修改收款認領單 狀態
								if(rs.getObject("CFRecBillNum") !=null && !"".equals(rs.getObject("CFRecBillNum").toString())){
									ReceClaimRecordUtil.doUpdateClaimStaByNumber(ctx, rs.getObject("CFRecBillNum").toString());
								}
								
							}else{
								logInfo.setInterResult(InterResultMenu.FAIL);
								logInfo.setRespond(result);
								sendFlag = 2 ;
							}
						}else{
							logInfo.setRespond("返回值为空，请查看");
						}
						logInfo.setInterType(SAPInterTypeMenu.BANKPAY);
						SAPInterfaceLogFactory.getLocalInstance(ctx).save(logInfo);
						
						String updateSql = "update  CT_SIG_PaymentSentRecord set  CFSENTFLAG = '"+sendFlag+"' where FID = '"+rs.getString("FID")+"'";
						DbUtil.execute(ctx, updateSql);
					}
 				
				}
			} catch (SQLException e) {
 				e.printStackTrace();
			} catch (EASBizException e) {
 				e.printStackTrace();
			}
			
			
		}
		 
	}
}
