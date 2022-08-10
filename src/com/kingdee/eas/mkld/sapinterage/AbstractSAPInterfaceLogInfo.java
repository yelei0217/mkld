package com.kingdee.eas.mkld.sapinterage;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractSAPInterfaceLogInfo extends com.kingdee.eas.framework.CoreBillBaseInfo implements Serializable 
{
    public AbstractSAPInterfaceLogInfo()
    {
        this("id");
    }
    protected AbstractSAPInterfaceLogInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:SAP接口调用日志's 接口类型property 
     */
    public com.kingdee.eas.mkld.sapinterage.app.SAPInterTypeMenu getInterType()
    {
        return com.kingdee.eas.mkld.sapinterage.app.SAPInterTypeMenu.getEnum(getString("InterType"));
    }
    public void setInterType(com.kingdee.eas.mkld.sapinterage.app.SAPInterTypeMenu item)
    {
		if (item != null) {
        setString("InterType", item.getValue());
		}
    }
    /**
     * Object:SAP接口调用日志's 认领类型property 
     */
    public com.kingdee.eas.mkld.sapinterage.app.ClaimTypeMenu getClaimType()
    {
        return com.kingdee.eas.mkld.sapinterage.app.ClaimTypeMenu.getEnum(getString("ClaimType"));
    }
    public void setClaimType(com.kingdee.eas.mkld.sapinterage.app.ClaimTypeMenu item)
    {
		if (item != null) {
        setString("ClaimType", item.getValue());
		}
    }
    /**
     * Object:SAP接口调用日志's 接口结果property 
     */
    public com.kingdee.eas.mkld.sapinterage.app.InterResultMenu getInterResult()
    {
        return com.kingdee.eas.mkld.sapinterage.app.InterResultMenu.getEnum(getString("InterResult"));
    }
    public void setInterResult(com.kingdee.eas.mkld.sapinterage.app.InterResultMenu item)
    {
		if (item != null) {
        setString("InterResult", item.getValue());
		}
    }
    /**
     * Object:SAP接口调用日志's 请求时间property 
     */
    public java.util.Date getReqTime()
    {
        return getDate("reqTime");
    }
    public void setReqTime(java.util.Date item)
    {
        setDate("reqTime", item);
    }
    /**
     * Object:SAP接口调用日志's 请求参数property 
     */
    public String getRequest()
    {
        return getString("request");
    }
    public void setRequest(String item)
    {
        setString("request", item);
    }
    /**
     * Object:SAP接口调用日志's 响应信息property 
     */
    public String getRespond()
    {
        return getString("respond");
    }
    public void setRespond(String item)
    {
        setString("respond", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("D380C1E4");
    }
}