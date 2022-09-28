package com.kingdee.eas.mkld.sapinterage.app.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.ormapping.ObjectStringPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.core.fm.ContextHelperFactory;
import com.kingdee.eas.base.permission.IUser;
import com.kingdee.eas.base.permission.UserFactory;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.framework.app.ParallelSqlExecutor;
import com.kingdee.eas.basedata.master.cssp.CSSPGroupCollection;
import com.kingdee.eas.basedata.master.cssp.CSSPGroupFactory;
import com.kingdee.eas.basedata.master.cssp.CSSPGroupInfo;
import com.kingdee.eas.basedata.master.cssp.CSSPGroupStandardFactory;
import com.kingdee.eas.basedata.master.cssp.CSSPGroupStandardInfo;
import com.kingdee.eas.basedata.master.cssp.CustomerFactory;
import com.kingdee.eas.basedata.master.cssp.CustomerGroupDetailInfo;
import com.kingdee.eas.basedata.master.cssp.CustomerInfo;
import com.kingdee.eas.basedata.master.cssp.ICustomer;
import com.kingdee.eas.basedata.master.cssp.UsedStatusEnum;
import com.kingdee.eas.basedata.org.AdminOrgUnitFactory;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.basedata.org.CompanyOrgUnitFactory;
import com.kingdee.eas.basedata.org.CompanyOrgUnitInfo;
import com.kingdee.eas.basedata.org.CostCenterOrgUnitFactory;
import com.kingdee.eas.basedata.org.CostCenterOrgUnitInfo;
import com.kingdee.eas.basedata.org.CtrlUnitInfo;
import com.kingdee.eas.basedata.org.FullOrgUnitFactory;
import com.kingdee.eas.basedata.org.FullOrgUnitInfo;
import com.kingdee.eas.basedata.org.HROrgUnitFactory;
import com.kingdee.eas.basedata.org.HROrgUnitInfo;
import com.kingdee.eas.basedata.org.OrgType;
import com.kingdee.eas.basedata.org.ProfitCenterOrgUnitFactory;
import com.kingdee.eas.basedata.org.ProfitCenterOrgUnitInfo;
import com.kingdee.eas.basedata.org.PurchaseOrgUnitFactory;
import com.kingdee.eas.basedata.org.PurchaseOrgUnitInfo;
import com.kingdee.eas.basedata.org.QualityOrgUnitFactory;
import com.kingdee.eas.basedata.org.QualityOrgUnitInfo;
import com.kingdee.eas.basedata.org.SaleOrgUnitFactory;
import com.kingdee.eas.basedata.org.SaleOrgUnitInfo;
import com.kingdee.eas.basedata.org.StorageOrgUnitFactory;
import com.kingdee.eas.basedata.org.StorageOrgUnitInfo;
import com.kingdee.eas.basedata.org.TransportOrgUnitFactory;
import com.kingdee.eas.basedata.org.TransportOrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.framework.DeletedStatusEnum;
import com.kingdee.eas.framework.EffectedStatusEnum;
import com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogFactory;
import com.kingdee.eas.mkld.sapinterage.SAPInterfaceLogInfo;
import com.kingdee.eas.mkld.sapinterage.app.InterResultMenu;
import com.kingdee.eas.mkld.sapinterage.app.SAPInterTypeMenu;
import com.kingdee.eas.mkld.sapinterage.common.InterfaceResource;
import com.kingdee.eas.mkld.sapinterage.common.OAInterfaceUtil;
import com.kingdee.eas.mkld.sapinterage.rest.HTTPSClientUtil;
import com.kingdee.eas.mkld.sapinterage.rest.HTTPSTrustClient;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;

public class CustmerUtil {
	
	 
	private static void clearFiles(String workspaceRootPath){
    	File file = new File(workspaceRootPath);
    	if(file.exists()){ 
    		file.delete();
    	} 
    }
	public static void writerText(String path, String content,String dateStr) { 
		
		System.out.println("########  writerText ########"+path); 
        File dirFile = new File(path); 
         
        System.out.println("########  !dirFile.exists() ########"+(!dirFile.exists())); 
        if (!dirFile.exists()) {//判断目录是否存在，不存在创建
            dirFile.mkdir();
        } 
        
        dirFile.setWritable(true, false);    //设置写权限，windows下不用此语句
        dirFile.setReadable(true);//设置可读权限  
        try {
            //new FileWriter(path + "config.log", true)  设置true 在不覆盖以前文件的基础上继续写
            BufferedWriter writer = new BufferedWriter(new FileWriter(path  +""+dateStr+".txt", true));
            writer.write(content+"\r\n");//写入文件
            writer.flush();//清空缓冲区数据
            writer.close();//关闭读写流 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public   String DoCustomer(Context ctx,String param) {
		 
		String dateStr = "";
		if(param.length() <=2 ){
			Date today = new Date(); 
			Calendar c = Calendar.getInstance();
			c.setTime(today);
			c.add(Calendar.DAY_OF_MONTH, -1);
			Date yesterday = c.getTime();//这是昨天
		     
			SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
			dateStr = forma.format(yesterday)+" 00:00:00"; 
		}else{
			dateStr = param+" 00:00:00";
		}
		
		
		
		Context oldCTX = copyContext(ctx) ;
		Context tempCTX = new Context() ;
		try {
			tempCTX =context(oldCTX, InterfaceResource.copyCtxUser);
		} catch (Exception e1) {
 			e1.printStackTrace();
		} 
		
		int num = 100;
		int i = 1;
		System.out.println("########  dateStr ########"+dateStr); 
		
		Date date = new Date(); // this object contains the current date value 
	  	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	  	String dateStrNow = formatter.format(date);
	    clearFiles( InterfaceResource.fileUrl +""+dateStrNow+".txt");
	    
	    System.out.println("########  dateStrNow ########"+dateStrNow); 
		SAPInterfaceLogInfo sAPInterfaceLogInfo= new SAPInterfaceLogInfo();
		
		HashMap<String, String> cssMap   = new HashMap<String, String>();
		try {
			CSSPGroupCollection csspColl = CSSPGroupFactory.getLocalInstance(ctx).getCSSPGroupCollection(" where number != '1' ");
			for(int a = 0; a < csspColl.size() ; a++){
				CSSPGroupInfo cssinfo = csspColl.get(a);
				cssMap.put(cssinfo.getNumber(), cssinfo.getId().toString() );
			}
			
		} catch (BOSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		while( num == 100){ 
			
			String dataStr = "insertdt="+dateStr+"&page_num="+i+"&page_size=100";
			 
			//String result =  OAInterfaceUtil.sendMessageToBDGet(dataStr);
			
			Map<String, String> paramHeader = new HashMap<String, String>();
			paramHeader.put("app-key", InterfaceResource.db_center_app_key);
			paramHeader.put("app-secret",InterfaceResource.db_center_app_secret); 
			paramHeader.put("Params", dataStr);
			
			Map<String, String> paramBody= new HashMap<String, String>(); 
			paramBody.put("Params", dataStr);
			
			sAPInterfaceLogInfo.setReqTime(new Date()); 
			sAPInterfaceLogInfo.setRequest(JSONObject.toJSONString(paramBody)); 
			HttpClient httpClient;
			String result = "";
			try { 
//				httpClient = new HTTPSTrustClient().init();
//				result = HTTPSClientUtil.doGet(  httpClient, InterfaceResource.db_customer_path,  paramHeader,  paramBody) ;
//				
				//result = syncCustomerFormMDB(dataStr);
				System.out.println(dataStr);
				result = sendGet(dataStr);
				//result =  OAInterfaceUtil.sendMessageToBDGet(dataStr);
				
				//result="{\"errCode\": 0, \"errMsg\": \"\", \"requestId\": \"\", \"value\": [{\"HEADER\": {\"PARTNER\": \"800045\", \"NAME_ORG1\": \"\\u5317\\u4eac\\u4eac\\u6e2f\\u76db\\u5143\\u8d38\\u6613\\u6709\\u9650\\u516c\\u53f8\", \"BU_SORT1\": null, \"CREATION_GROUP\": \"Z300\", \"CREATION_NM\": \"\\u96c6\\u56e2\\u5916\\u6709\\u4e1a\\u52a1\\u5f80\\u6765\\u5ba2\\u6237\\u4e3b\\u6570\\u636e\", \"COUNTRY\": \"CN\", \"ZPROVINCE\": \"110000\", \"ZPROVINCE_NM\": \"\\u5317\\u4eac\\u5e02\", \"STR_SUPPL2\": \"\\u5317\\u4eac\\u5e02\\u987a\\u4e49\\u533a\\u8d75\\u5168\\u8425\\u9547\\u767d\\u5e99\\u6751\\u59d4\\u4f1a\\u4e1c50\\u7c73\", \"NAME_CO\": null, \"TEL_NUMBER\": null, \"SMTP_ADDR\": null, \"TAXNUMXL\": \"91110113699648109N\", \"STREET\": \"\\u5317\\u4eac\\u5e02\\u987a\\u4e49\\u533a\\u8d75\\u5168\\u8425\\u9547\\u767d\\u5e99\\u6751\\u59d4\\u4f1a\\u4e1c50\\u7c73\", \"BKDSC\": \"\\u5317\\u4eac\\u94f6\\u884c\\u987a\\u4e49\\u652f\\u884c\", \"ACCNAME\": \"01090338840120105080045\", \"BKEXT\": \"010-85913361\", \"KATR1\": \"Z1\", \"KATR1_NM\": \"\\u589e\\u7968\", \"ADRNR\": null, \"INSERTDT\": \"2022-08-20 14:00:19\"}, \"BUSINESS\": [], \"ADDRESS\": []}]}";
				if(result!=null ){
					
					sAPInterfaceLogInfo.setInterResult(InterResultMenu.SUCCESS); 
					sAPInterfaceLogInfo.setInterType(SAPInterTypeMenu.CUSTOMER);
					SAPInterfaceLogFactory.getLocalInstance(ctx).save(sAPInterfaceLogInfo);
					
					writerText(InterfaceResource.fileUrl, result,dateStrNow);
					
					JSONObject jo = JSONObject.parseObject(result);
					if(null != jo.get("errCode") && "0".equals(jo.get("errCode").toString())){
						JSONArray  arr = jo.getJSONArray("value");
						if(arr.size() < 100){
							num = arr.size();
						}
						i++;
						for(int x= 0 ; x < arr.size() ; x++){
							JSONObject j = arr.getJSONObject(x);
							
							JSONArray address = j.getJSONArray("ADDRESS");
							JSONObject header = j.getJSONObject("HEADER");
							JSONArray business = j.getJSONArray("BUSINESS"); 
							/*System.out.println("########  address ########"+address);
							System.out.println("########  header ########"+header);
							System.out.println("########  business ########"+business); */
							
							/*String cusnumber = header.getString("PARTNER");
							String cusname = header.getString("NAME_ORG1");
							String cusclassNum = header.getString("CREATION_GROUP");
							String cusclassName = header.getString("CREATION_NM");*/
							HashMap<String, String> retMap = syncCustomers(ctx,header ,cssMap,business.size() , tempCTX);
							if(retMap!=null && null != retMap.get("code") && "success".equals(retMap.get("code"))){
								
								String cusid = retMap.get("cusid");
								if(null != retMap.get("type") && "1".equals(retMap.get("type"))){//新增 
									String numS= "";
									if(business.size()>0){
										for(int w = 0 ; w < business.size(); w++ ){
											JSONObject bussJSOb =  business.getJSONObject(w); 
											/*if( null != bussJSOb.get("LOEVM")  && "1".equals(bussJSOb.getString("LOEVM"))){
												
											}else{
												
											}*/
											
											if( null != bussJSOb.get("VKORG")  && !"".equals(bussJSOb.getString("VKORG")) ){
												String  conNumber = bussJSOb.getString("VKORG"); 
												numS = numS+"'"+conNumber+"',";
											}
										} 
									}else{
										numS = "'1100',";
									}
									doDataBaseDAssign(ctx, cusid, numS); 
									
								}else if(null != retMap.get("type") && "2".equals(retMap.get("type"))){  //修改
									 
									
									
								}else{//不正常数据
									
								}
							}else{
								
							}
						}
					}
				}else{
					sAPInterfaceLogInfo.setInterResult(InterResultMenu.FAIL);
					sAPInterfaceLogInfo.setRespond("返回值为空");
					sAPInterfaceLogInfo.setInterType(SAPInterTypeMenu.CUSTOMER);
					SAPInterfaceLogFactory.getLocalInstance(ctx).save(sAPInterfaceLogInfo);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				num = 0;
				
				sAPInterfaceLogInfo.setInterResult(InterResultMenu.FAIL);
				sAPInterfaceLogInfo.setRespond(e.getMessage());
				sAPInterfaceLogInfo.setInterType(SAPInterTypeMenu.CUSTOMER);
				try {
					SAPInterfaceLogFactory.getLocalInstance(ctx).save(sAPInterfaceLogInfo);
				} catch (EASBizException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BOSException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} 
		}
		
		
		return "";
	}
	
	public HashMap<String, String> syncCustomers(Context ctx, JSONObject headerjson , HashMap<String, String> cssMap ,int sizeBus ,Context  tempCTX)	 {
		HashMap<String, String>  map = new HashMap<String, String>();
		
		map.put("code", "error");
		String msg = "";
		
		try { 
			 
			if (null== headerjson.get("PARTNER") || "".equals(headerjson.get("PARTNER")) ) {
				msg =  "客户编码不能为空" ;
				map.put("msg", msg);
				return map;
			}
			String cusnumber = headerjson.getString("PARTNER");
			if (null== headerjson.get("NAME_ORG1") || "".equals(headerjson.get("NAME_ORG1")) ) {
				msg =  cusnumber+"的客户名称不能为空" ;
				map.put("msg", msg);
				return map;
			}
			String cusname = headerjson.getString("NAME_ORG1");
			
			String cusclass = ""; 
			
			if (null== headerjson.get("CREATION_GROUP") || "".equals(headerjson.get("CREATION_GROUP")) ) {
//				msg =  cusnumber+"的客户分组不能为空" ;
//				map.put("msg", msg);
//				return map;
				
				cusclass = "999"; 
			}else{
				cusclass = headerjson.getString("CREATION_GROUP");
			}
			 
			if( "".equals(cusclass)){ 
				cusclass = "999";
			}
				
			
			CustomerInfo customerInfo = new CustomerInfo();
			ICustomer iCustomer = CustomerFactory.getLocalInstance(ctx);
			// 判断是否已存在
			if (!iCustomer.exists("where number='" + cusnumber+ "'  ")) {
	
				// 基本信息补充
				customerInfo.setNumber(cusnumber);
				customerInfo.setName(cusname);
				if(null!= headerjson.get("BU_SORT1") && !"".equals(headerjson.get("BU_SORT1"))){
					customerInfo.setDescription(headerjson.getString("BU_SORT1"));
				}
				 
				customerInfo.setIsSwitch(false);
				customerInfo.setIsCredited(false);
				customerInfo.setTaxRate(BigDecimal.ZERO);
				customerInfo.setVersion(1);
				customerInfo.setUsedStatus(UsedStatusEnum.APPROVED);
				customerInfo.setEffectedStatus(EffectedStatusEnum.TEMPSTORE);
	
	//			//财务组织
	//			CompanyOrgUnitInfo companyInfo = new CompanyOrgUnitInfo();
	//			companyInfo.setId(BOSUuid.read("Bd1JPB3CSpmUVz0eDuXVJMznrtQ="));
	//			// 管理单元
	//			CtrlUnitInfo ctrlUnitInfo = new CtrlUnitInfo();
	//			ctrlUnitInfo.setId(BOSUuid.read("Bd1JPB3CSpmUVz0eDuXVJMznrtQ="));
	//			
				// xwO2Ku/tTvWqrmWYsKde3sznrtQ= 前途  - 10
				//财务组织
				CompanyOrgUnitInfo companyInfo = new CompanyOrgUnitInfo();
				companyInfo.setId(BOSUuid.read("00000000-0000-0000-0000-000000000000CCE7AED4"));
				// 管理单元
				CtrlUnitInfo ctrlUnitInfo = new CtrlUnitInfo();
				ctrlUnitInfo.setId(BOSUuid.read("00000000-0000-0000-0000-000000000000CCE7AED4"));
				
				customerInfo.setAdminCU(ctrlUnitInfo);
				// 控制单元
				customerInfo.setCU(ctrlUnitInfo);
				// 根据category是101还是102采取不同逻辑
				CSSPGroupInfo cGroupInfo = new CSSPGroupInfo(); 
				if (  CSSPGroupFactory.getLocalInstance(ctx).exists("where number='"+cusclass+"' ") ) { 
					//cGroupInfo.setId(BOSUuid.read(cssMap.get(cusclass))  ); 
					cGroupInfo =  CSSPGroupFactory.getLocalInstance(ctx).getCSSPGroupInfo("where number='"+cusclass+"' ");
					customerInfo.setBrowseGroup(cGroupInfo);
				} else{
				 
					String number = cusclass;  
					if (null== headerjson.get("CREATION_NM") || "".equals(headerjson.get("CREATION_NM")) ) {
						msg =  cusnumber+"的客户分组名称不能为空" ;
						map.put("msg", msg);
						return map; 
					}
					String name = headerjson.getString("CREATION_NM");
					cGroupInfo = new CSSPGroupInfo();
					//CSSPGroupInfo parentCSSGroup = CSSPGroupFactory.getLocalInstance(ctx).getCSSPGroupInfo("where number='101' ");
					//cGroupInfo.setParent(parentCSSGroup); 
					cGroupInfo.setName(name);
					cGroupInfo.setNumber(number);
					cGroupInfo.setLevel(1);

					cGroupInfo.setDeletedStatus(DeletedStatusEnum.NORMAL);
					cGroupInfo.setIsLeaf(true);
					//cGroupInfo.setLongNumber("101!" + s);
					// 分类标准
					CSSPGroupStandardInfo groupStandardInfo = CSSPGroupStandardFactory.getLocalInstance(ctx).getCSSPGroupStandardInfo( new ObjectStringPK("00000000-0000-0000-0000-000000000002BC122A7F"));
					
					cGroupInfo.setGroupStandard(groupStandardInfo);
					CSSPGroupFactory.getLocalInstance(ctx).save(cGroupInfo);
					customerInfo.setBrowseGroup(cGroupInfo);
					
				}
	
				// 客户分组明细
				CustomerGroupDetailInfo customerGroupDetailInfo = new CustomerGroupDetailInfo();
				customerGroupDetailInfo.setCustomer(customerInfo);
				customerGroupDetailInfo.setCustomerGroup(customerInfo.getBrowseGroup());
				customerGroupDetailInfo.setCustomerGroupStandard(customerInfo.getBrowseGroup().getGroupStandard());
				customerInfo.getCustomerGroupDetails().add(customerGroupDetailInfo);
				 
				// 新增客户
				IObjectPK pk = iCustomer.save(customerInfo);
				
				/*CustomerCompanyInfoInfo cusCompanyInfo = new CustomerCompanyInfoInfo(); 
				cusCompanyInfo.setCustomer(customerInfo); 
				cusCompanyInfo.setCU(ctrlUnitInfo);
				cusCompanyInfo.setCompanyOrgUnit(companyInfo); 
				String id = CustomerCompanyInfoFactory.getLocalInstance(ctx).addnew(cusCompanyInfo).toString();*/
				
				//doDataBaseDAssign(ctx, pk.toString(), "cus"); 
				
				
				
				//修改认领记录单
			    if(pk!=null && !"".equals(pk.toString())){
			    	ReceClaimRecordUtil.doUpdateClaimStaByCusName(ctx, cusnumber, cusname);
			    }
			    
			    
				map.put("cusid", pk.toString());
				map.put("msg", msg);
				map.put("type", "1");
				map.put("code", "success");
				
				return map;
			} else { 
				//ICustomer iCustomer = CustomerFactory.getLocalInstance(ctx);
				
				CustomerInfo cusInfo = iCustomer.getCustomerCollection(" where  number ='"+cusnumber+"'  ").get(0);
				
				System.out.println("########  address ########cusnumber"+cusnumber);
				String cusInfoname = cusInfo.getName();
				if(!cusname.equals(cusInfoname)){
					cusInfo.setName(cusname); 
					if (cusInfo.getUsedStatus() == UsedStatusEnum.APPROVED) {   //核准
						DbUtil.execute(ctx,"update T_BD_CUSTOMER set FUsedStatus = 0 where  fnumber ='"+cusnumber+"' ");  
						try{
							CustomerFactory.getLocalInstance(ctx).update(new ObjectUuidPK(cusInfo.getId()), cusInfo);
						}catch (Exception e) {//为核准
							// TODO: handle exception 
							try{
								DbUtil.execute(ctx,"update T_BD_CUSTOMER set FUsedStatus = 1 where  fnumber ='"+cusnumber+"' "); 
								CustomerFactory.getLocalInstance(tempCTX).update(new ObjectUuidPK(cusInfo.getId()), cusInfo);
							}catch (Exception e3) {//为核准
								// TODO: handle exception 
								e3.printStackTrace();
								DbUtil.execute(ctx,"update T_BD_CUSTOMER set fname_l2 = '"+cusname+"' where  fnumber ='"+cusnumber+"' "); 
							}  
						} 
						DbUtil.execute(ctx,"update T_BD_CUSTOMER set FUsedStatus = 1 where  fnumber ='"+cusnumber+"' ");  
					} else if (cusInfo.getUsedStatus() == UsedStatusEnum.FREEZED) {    //禁用
						DbUtil.execute(ctx,"update T_BD_CUSTOMER set FUsedStatus = 0 where  fnumber ='"+cusnumber+"' "); 
						try{
							CustomerFactory.getLocalInstance(ctx).update(new ObjectUuidPK(cusInfo.getId()), cusInfo);
						}catch (Exception e) {
							// TODO: handle exception 
							try{
								System.out.println("########CustomerFactory UsedStatusEnum.FREEZED CustomerFactory ########"+cusInfo.getId());
								CustomerFactory.getLocalInstance(tempCTX).update(new ObjectUuidPK(cusInfo.getId()), cusInfo);
							}catch (Exception e3) {//为核准
								// TODO: handle exception 
								e3.printStackTrace();
								DbUtil.execute(ctx,"update T_BD_CUSTOMER set fname_l2 = '"+cusname+"' where  fnumber ='"+cusnumber+"' "); 
							}  
						}  
						DbUtil.execute(ctx,"update T_BD_CUSTOMER set FUsedStatus = 2 where  fnumber ='"+cusnumber+"' "); 
						
					}else  if (cusInfo.getUsedStatus() == UsedStatusEnum.DELETED) {    //删除
						DbUtil.execute(ctx,"update T_BD_CUSTOMER set FUsedStatus = 0 where  fnumber ='"+cusnumber+"' "); 
						try{
							CustomerFactory.getLocalInstance(ctx).update(new ObjectUuidPK(cusInfo.getId()), cusInfo);
						}catch (Exception e) {
							// TODO: handle exception 
							try{
								System.out.println("########CustomerFactory UsedStatusEnum.DELETED CustomerFactory ########"+cusInfo.getId());
								CustomerFactory.getLocalInstance(tempCTX).update(new ObjectUuidPK(cusInfo.getId()), cusInfo);
							}catch (Exception e3) {//为核准
								// TODO: handle exception 
								e3.printStackTrace();
								DbUtil.execute(ctx,"update T_BD_CUSTOMER set fname_l2 = '"+cusname+"' where  fnumber ='"+cusnumber+"' "); 
							}  
						} 
						DbUtil.execute(ctx,"update T_BD_CUSTOMER set FUsedStatus = 3 where  fnumber ='"+cusnumber+"' "); 
					} 
				}  
			/*try {
					iCustomer.update(new ObjectUuidPK(cusInfo.getId()), cusInfo);
				} catch (EASBizException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("########  eeee ########cusnumber"+e.getMessage());
					try {
						ICustomer iCustomerTemp = CustomerFactory.getLocalInstance(tempCTX);
						iCustomerTemp.update(new ObjectUuidPK(cusInfo.getId()), cusInfo);
					} catch (EASBizException e23) {
						// TODO Auto-generated catch block
						e23.printStackTrace();  
					}
					 
				} */
				map.put("code", "success");
				
				msg =  "客户编码为"+cusnumber+"的客户名称修改为"+cusname+";" ;
				map.put("msg", msg);
				map.put("type", "2");
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
			
		}
		return map;
	}


	public  static String doDataBaseDAssign(Context ctx ,String dataBaseID,String numS){
		String msg = "";
		String userId = ContextHelperFactory.getLocalInstance(ctx).getCurrentUser().getId().toString(); 
		ExecutorService pool = Executors.newFixedThreadPool(6);
	    ParallelSqlExecutor pe = new ParallelSqlExecutor(pool); 
	    List<String> companys = getAssignCompanys(ctx,numS);
	    if(companys!=null && companys.size()>0){
	    	String bosType = "BF0C040E"; 
	    	
			for(String company :companys){
				String sql  = "insert into T_BD_DataBaseDAssign(fid,fcreatorid,fcreatetime,flastupdateuserid,flastupdatetime,fcontrolunitid,fdatabasedid,fassigncuid,fbosobjecttype,fstatus)"
					+ " values (newbosid('37C67DFC'),'"+userId+"',sysdate,'"+userId+"',sysdate,'"+company+"','"+dataBaseID+"','"+company+"','"+bosType+"',0)";  
		    	pe.getSqlList().add(sql);
		    		
		    	String cusonterinfoid  = BOSUuid.create("7751B8D7").toString();
	    		sql  = "insert into T_BD_CUSTOMERCOMPANYINFO (FID,FCreatorID,FCreateTime,FLastUpdateUserID,FLastUpdateTime,FControlUnitID,FComOrgID,"+
	     		 " FSettlementTypeID,FSettlementCurrencyID,FPaymentTypeID,FAccountClassID,FEffectedStatus" +
	     		 " ,FFreezeOrgUnitID,FCUSTOMERID,FContactPerson,FContactPersonPost,FPhone,FMobile,FFax,FPostalcode,FEMail)  "+
		    	 " values('"+cusonterinfoid+"','"+userId+"',sysdate,'"+userId+"',sysdate,'"+company+"','"+company+"','' ,'dfd38d11-00fd-1000-e000-1ebdc0a8100dDEB58FDC' ,"+
	    		 " '','',2,'','"+dataBaseID+"','','' , '' , '' , '' , '' , '' )"; 
	    		pe.getSqlList().add(sql); 
	    		
	    		
	    		/*sql = "insert into T_BD_CustomerSaleInfo (FID,FCreatorID,FCreateTime,FLastUpdateUserID,FLastUpdateTime,FControlUnitID,FPurchaseOrgID,"+
	    		" FIsFreezeOrder,FIsFreezeDelivery,FEffectedStatus,FCustomerID,FSettlementOrgUnitID,FBillingOrgUnitID,FDeliverOrgUnitID,FUsingStatus )"+
	    		" values(newbosid('26C00F09'),'"+userId+"',sysdate,'"+userId+"',sysdate,'"+company+"','"+company+"',0,0,2,"+
	    		" '"+dataBaseID+"','"+dataBaseID+"',null,'"+dataBaseID+"',0)"; 
	    		pe.getSqlList().add(sql); */
			} 
		 
	    } 
	    if(pe.getSqlList().size()>0){
	    	try {
				pe.executeUpdate(ctx); 
				pool.shutdown(); 
				return msg;
			} catch (EASBizException e) { 
				e.printStackTrace();
				pool.shutdown(); 
				return e.getMessage();
			} catch (BOSException e) { 
				e.printStackTrace();
				pool.shutdown(); 
				return e.getMessage();
			}finally{
				pool.shutdown(); 
			}	 
	    }
	
	    return msg;
	}
		
	private static List<String> getAssignCompanys(Context ctx,String nums){
		nums = nums + "'MKLD'";
		List<String>  list = new ArrayList<String>(); 
 			try {
 				String sbr = "select FID from T_ORG_COMPANY where FNUMBER  in ("+nums+") and FISASSISTANTORG =0";
 				IRowSet rs =DbUtil.executeQuery(ctx,sbr.toString()); 
				while(rs.next()){
					if(rs.getObject("FID")!=null &&!"".equals(rs.getObject("FID").toString())){
						list.add(rs.getObject("FID").toString());
					}
	        	}  
			} catch (BOSException e) {
				e.printStackTrace();
			} catch (SQLException e) {
 				e.printStackTrace();
			}
	 
		return list;
	}	


	public static synchronized Context copyContext(Context ctx) {
		Context newCtx = new Context();
		newCtx.setAIS(ctx.getAIS());
		newCtx.setCaller(ctx.getCaller());
		newCtx.setClientHostIP(ctx.getClientHostIP());
		newCtx.setClientHostName(ctx.getClientHostName());
		newCtx.setLocale(ctx.getLocale());
		newCtx.setReadAIS(ctx.getReadAIS());
		newCtx.setSolution(ctx.getSolution());
		newCtx.setUserName(ctx.getUserName());
		newCtx.put(OrgType.Admin, ctx.get(OrgType.Admin));
		CompanyOrgUnitInfo company = (CompanyOrgUnitInfo) ctx
				.get(OrgType.Company);
		newCtx.put(OrgType.Company, company);
		newCtx.put("CompanyInfo", company);
		newCtx.put("CurCompanyId", ctx.get(OrgType.Transport));
		newCtx.put(OrgType.CostCenter, ctx.get(OrgType.Transport));
		newCtx.put(OrgType.HRO, ctx.get(OrgType.Transport));
		newCtx.put(OrgType.ProfitCenter, ctx.get(OrgType.Transport));
		newCtx.put(OrgType.Purchase, ctx.get(OrgType.Transport));
		newCtx.put(OrgType.Quality, ctx.get(OrgType.Transport));
		newCtx.put(OrgType.Sale, ctx.get(OrgType.Transport));
		newCtx.put(OrgType.Storage, ctx.get(OrgType.Transport));
		newCtx.put(OrgType.Transport, ctx.get(OrgType.Transport));
		UserInfo user = null;
		try {
			user = UserFactory.getLocalInstance(ctx).getUserInfo(
					ctx.getCaller());
			newCtx.put("UserInfo", user);
			newCtx.put("Password", user.getPassword());
		} catch (EASBizException e) {
			e.printStackTrace();
		} catch (BOSException e) {
			e.printStackTrace();
		}
		newCtx.put("SwitchToNewLoginFlow", "true");
		newCtx.put("ClientName", ctx.get("ClientName"));
		newCtx.put("ClientIP", ctx.get("ClientIP"));
		newCtx.put("dbTypeCode", Integer.valueOf(0));
		newCtx.put("CurCompanyId", ctx.get("CurCompanyId"));
		newCtx.put("CurOU", ctx.get("CurOU"));
		newCtx.put(OrgType.NONE, null);
		newCtx.put("hint", "no");
		newCtx.put("USBKEY_LOGIN", ctx.get("USBKEY_LOGIN"));
		return newCtx;
	}
	

	public static synchronized Context context(Context ctx, String number)
			throws EASBizException, BOSException {
		IUser iUser = UserFactory.getLocalInstance(ctx);
		FilterInfo filter = new FilterInfo();
		filter.getFilterItems().add(new FilterItemInfo("number", number));
		boolean flag = iUser.exists(filter);
		UserInfo user = null;
		IObjectPK userPk = null;
		if (flag) {
			user = iUser.getUserInfo("where number = '" + number + "'");
			userPk = new ObjectUuidPK(user.getId());
		} else {
			userPk = ctx.getCaller();
		}

		SelectorItemCollection selectors = new SelectorItemCollection();
		selectors.add(new SelectorItemInfo("*"));
		selectors.add(new SelectorItemInfo("defOrgUnit.*"));
		user = UserFactory.getLocalInstance(ctx).getUserInfo(userPk, selectors);
		ctx.setCaller(userPk);
		ctx.setUserName(user.getName());

		FullOrgUnitInfo orgUnit = user.getDefOrgUnit();
		String orgUnitId = orgUnit.getId().toString();//"00000000-0000-0000-0000-000000000000CCE7AED4";//orgUnit.getId().toString();
		IObjectPK orgUnitPk = new ObjectStringPK(orgUnitId);
		orgUnit = FullOrgUnitFactory.getLocalInstance(ctx).getFullOrgUnitInfo(orgUnitPk);

		ctx.put("UserInfo", user);
		ctx.put("SwitchToNewLoginFlow", "true");
		ctx.put("ClientName", "127.0.0.1");
		ctx.put("Password", user.getPassword());
		ctx.put("ClientIP", "127.0.0.1");
		ctx.put("dbTypeCode", Integer.valueOf(0));
		ctx.put("CurCompanyId", null);
		ctx.put("CurOU", orgUnit);
		ctx.put("CompanyInfo", null);
		ctx.put(OrgType.NONE, null);
		ctx.put("hint", "no");
		ctx.put("USBKEY_LOGIN", null);
		ctx = handleCurrentOrg(ctx, orgUnit);

		return ctx;
	}


	protected static synchronized Context handleCurrentOrg(Context ctx,
			FullOrgUnitInfo orgUnit) throws EASBizException, BOSException {
		String orgUnitId = orgUnit.getId().toString();
		IObjectPK orgUnitPk = new ObjectStringPK(orgUnitId);
		if (orgUnit.isIsAdminOrgUnit()) {
			AdminOrgUnitInfo adminOrg = AdminOrgUnitFactory.getLocalInstance(
					ctx).getAdminOrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.Admin, adminOrg);
		} else {
			ctx.put(OrgType.Admin, null);
		}
		if (orgUnit.isIsCompanyOrgUnit()) {
			CompanyOrgUnitInfo company = CompanyOrgUnitFactory
					.getLocalInstance(ctx).getCompanyOrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.Company, company);
			ctx.put("CompanyInfo", company);
			ctx.put("CurCompanyId", orgUnitId);
		} else {
			ctx.put(OrgType.Company, null);
		}
		if (orgUnit.isIsCostOrgUnit()) {
			CostCenterOrgUnitInfo costCenter = CostCenterOrgUnitFactory
					.getLocalInstance(ctx).getCostCenterOrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.CostCenter, costCenter);
		} else {
			ctx.put(OrgType.CostCenter, null);
		}
		orgUnit.isIsCU();

		if (orgUnit.isIsHROrgUnit()) {
			HROrgUnitInfo hrOrg = HROrgUnitFactory.getLocalInstance(ctx)
					.getHROrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.HRO, hrOrg);
		} else {
			ctx.put(OrgType.HRO, null);
		}
		if (orgUnit.isIsProfitOrgUnit()) {
			ProfitCenterOrgUnitInfo profitCenter = ProfitCenterOrgUnitFactory
					.getLocalInstance(ctx)
					.getProfitCenterOrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.ProfitCenter, profitCenter);
		} else {
			ctx.put(OrgType.ProfitCenter, null);
		}
		if (orgUnit.isIsPurchaseOrgUnit()) {
			PurchaseOrgUnitInfo purchaseOrg = PurchaseOrgUnitFactory
					.getLocalInstance(ctx).getPurchaseOrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.Purchase, purchaseOrg);
		} else {
			ctx.put(OrgType.Purchase, null);
		}
		if (orgUnit.isIsQualityOrgUnit()) {
			QualityOrgUnitInfo qualityOrg = QualityOrgUnitFactory
					.getLocalInstance(ctx).getQualityOrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.Quality, qualityOrg);
		} else {
			ctx.put(OrgType.Quality, null);
		}
		if (orgUnit.isIsSaleOrgUnit()) {
			SaleOrgUnitInfo saleOrg = SaleOrgUnitFactory.getLocalInstance(ctx)
					.getSaleOrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.Sale, saleOrg);
		} else {
			ctx.put(OrgType.Sale, null);
		}
		if (orgUnit.isIsStorageOrgUnit()) {
			StorageOrgUnitInfo storageOrg = StorageOrgUnitFactory
					.getLocalInstance(ctx).getStorageOrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.Storage, storageOrg);
		} else {
			ctx.put(OrgType.Storage, null);
		}
		if (orgUnit.isIsTransportOrgUnit()) {
			TransportOrgUnitInfo transportOrg = TransportOrgUnitFactory
					.getLocalInstance(ctx).getTransportOrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.Transport, transportOrg);
		} else {
			ctx.put(OrgType.Transport, null);
		}

		return ctx;
	}
	
	
	/**
	 * 
	 * @param sendStr
	 * @return
	 */
	  private static String syncCustomerFormMDB(String param)
	  {
	    String responseString = null;
	    OkHttpClient client = new OkHttpClient.Builder()
	      .connectTimeout(15L, TimeUnit.SECONDS)
	      .readTimeout(600L, TimeUnit.SECONDS)
	      .build();
	   // MediaType mediaType = MediaType.parse("application/json");
	   // RequestBody body = RequestBody.create(mediaType, sendStr);
	    RequestBody body =RequestBody.create(null, new byte[0]);
	    Request request = new Request.Builder()
	      .url(InterfaceResource.db_customer_path)
	      .get()
	      .put(body)
	      .addHeader("app-key", InterfaceResource.db_center_app_key)
	      .addHeader("app-secret", InterfaceResource.db_center_app_secret)
	      .addHeader("Params", param)
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
	   
	  private String sendGet(String param){
		    String result = "";
	        CloseableHttpClient client = HttpClients.createDefault();
	        HttpGet httpGet = new HttpGet(InterfaceResource.db_customer_path);
	        httpGet.setHeader("app-key", InterfaceResource.db_center_app_key);
	        httpGet.setHeader("app-secret",  InterfaceResource.db_center_app_secret);
	        httpGet.setHeader("Params", param);
	        try {
				CloseableHttpResponse Response = client.execute(httpGet);
				result = EntityUtils.toString(Response.getEntity());
			} catch (ClientProtocolException e) {
 				e.printStackTrace();
			} catch (IOException e) {
 				e.printStackTrace();
			} 
		    return result;
		}
	  
	  
}
