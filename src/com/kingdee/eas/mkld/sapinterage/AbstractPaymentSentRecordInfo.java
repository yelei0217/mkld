package com.kingdee.eas.mkld.sapinterage;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractPaymentSentRecordInfo extends com.kingdee.eas.framework.DataBaseInfo implements Serializable 
{
    public AbstractPaymentSentRecordInfo()
    {
        this("id");
    }
    protected AbstractPaymentSentRecordInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:付款单发送记录's 发送状态property 
     */
    public com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu getSentFlag()
    {
        return com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu.getEnum(getString("SentFlag"));
    }
    public void setSentFlag(com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu item)
    {
		if (item != null) {
        setString("SentFlag", item.getValue());
		}
    }
    /**
     * Object: 付款单发送记录 's 公司 property 
     */
    public com.kingdee.eas.basedata.org.CompanyOrgUnitInfo getCompany()
    {
        return (com.kingdee.eas.basedata.org.CompanyOrgUnitInfo)get("Company");
    }
    public void setCompany(com.kingdee.eas.basedata.org.CompanyOrgUnitInfo item)
    {
        put("Company", item);
    }
    /**
     * Object:付款单发送记录's 付款时间property 
     */
    public String getZBUDAT1()
    {
        return getString("ZBUDAT1");
    }
    public void setZBUDAT1(String item)
    {
        setString("ZBUDAT1", item);
    }
    /**
     * Object:付款单发送记录's 付款金额property 
     */
    public java.math.BigDecimal getPAYAMOUNT()
    {
        return getBigDecimal("PAYAMOUNT");
    }
    public void setPAYAMOUNT(java.math.BigDecimal item)
    {
        setBigDecimal("PAYAMOUNT", item);
    }
    /**
     * Object:付款单发送记录's OAIDproperty 
     */
    public String getOAID()
    {
        return getString("OAID");
    }
    public void setOAID(String item)
    {
        setString("OAID", item);
    }
    /**
     * Object:付款单发送记录's 付款账号property 
     */
    public String getZBANKN1()
    {
        return getString("ZBANKN1");
    }
    public void setZBANKN1(String item)
    {
        setString("ZBANKN1", item);
    }
    /**
     * Object:付款单发送记录's 收款单号property 
     */
    public String getRecBillNum()
    {
        return getString("RecBillNum");
    }
    public void setRecBillNum(String item)
    {
        setString("RecBillNum", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("080ABC86");
    }
}