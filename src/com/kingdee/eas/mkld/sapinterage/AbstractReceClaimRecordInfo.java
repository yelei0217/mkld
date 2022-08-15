package com.kingdee.eas.mkld.sapinterage;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractReceClaimRecordInfo extends com.kingdee.eas.framework.CoreBillBaseInfo implements Serializable 
{
    public AbstractReceClaimRecordInfo()
    {
        this("id");
    }
    protected AbstractReceClaimRecordInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:收款认领记录's 是否生成凭证property 
     */
    public boolean isFivouchered()
    {
        return getBoolean("Fivouchered");
    }
    public void setFivouchered(boolean item)
    {
        setBoolean("Fivouchered", item);
    }
    /**
     * Object: 收款认领记录 's 公司 property 
     */
    public com.kingdee.eas.basedata.org.CompanyOrgUnitInfo getFICompany()
    {
        return (com.kingdee.eas.basedata.org.CompanyOrgUnitInfo)get("FICompany");
    }
    public void setFICompany(com.kingdee.eas.basedata.org.CompanyOrgUnitInfo item)
    {
        put("FICompany", item);
    }
    /**
     * Object:收款认领记录's 收款单据IDproperty 
     */
    public String getPaymentId()
    {
        return getString("PaymentId");
    }
    public void setPaymentId(String item)
    {
        setString("PaymentId", item);
    }
    /**
     * Object:收款认领记录's 收款单编号property 
     */
    public String getPaymentNo()
    {
        return getString("paymentNo");
    }
    public void setPaymentNo(String item)
    {
        setString("paymentNo", item);
    }
    /**
     * Object:收款认领记录's 流水号property 
     */
    public String getTrsreq()
    {
        return getString("trsreq");
    }
    public void setTrsreq(String item)
    {
        setString("trsreq", item);
    }
    /**
     * Object:收款认领记录's 年度property 
     */
    public int getYear()
    {
        return getInt("year");
    }
    public void setYear(int item)
    {
        setInt("year", item);
    }
    /**
     * Object:收款认领记录's 月份property 
     */
    public int getMonth()
    {
        return getInt("month");
    }
    public void setMonth(int item)
    {
        setInt("month", item);
    }
    /**
     * Object:收款认领记录's 收款银行账号property 
     */
    public String getBankAccount()
    {
        return getString("BankAccount");
    }
    public void setBankAccount(String item)
    {
        setString("BankAccount", item);
    }
    /**
     * Object:收款认领记录's 收款日期property 
     */
    public java.util.Date getReceDate()
    {
        return getDate("ReceDate");
    }
    public void setReceDate(java.util.Date item)
    {
        setDate("ReceDate", item);
    }
    /**
     * Object:收款认领记录's 收款金额property 
     */
    public java.math.BigDecimal getReceAmount()
    {
        return getBigDecimal("ReceAmount");
    }
    public void setReceAmount(java.math.BigDecimal item)
    {
        setBigDecimal("ReceAmount", item);
    }
    /**
     * Object:收款认领记录's 货款property 
     */
    public java.math.BigDecimal getLoans()
    {
        return getBigDecimal("Loans");
    }
    public void setLoans(java.math.BigDecimal item)
    {
        setBigDecimal("Loans", item);
    }
    /**
     * Object:收款认领记录's 保证金property 
     */
    public java.math.BigDecimal getMargin()
    {
        return getBigDecimal("Margin");
    }
    public void setMargin(java.math.BigDecimal item)
    {
        setBigDecimal("Margin", item);
    }
    /**
     * Object:收款认领记录's 押金property 
     */
    public java.math.BigDecimal getDeposit()
    {
        return getBigDecimal("Deposit");
    }
    public void setDeposit(java.math.BigDecimal item)
    {
        setBigDecimal("Deposit", item);
    }
    /**
     * Object:收款认领记录's 摘要property 
     */
    public String getAbstract()
    {
        return getString("Abstract");
    }
    public void setAbstract(String item)
    {
        setString("Abstract", item);
    }
    /**
     * Object:收款认领记录's 再次认领日期property 
     */
    public java.util.Date getAgainClaimDate()
    {
        return getDate("AgainClaimDate");
    }
    public void setAgainClaimDate(java.util.Date item)
    {
        setDate("AgainClaimDate", item);
    }
    /**
     * Object:收款认领记录's 客户编码property 
     */
    public String getCustomerNo()
    {
        return getString("CustomerNo");
    }
    public void setCustomerNo(String item)
    {
        setString("CustomerNo", item);
    }
    /**
     * Object:收款认领记录's 认领类型property 
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
     * Object:收款认领记录's 认领状态property 
     */
    public com.kingdee.eas.mkld.sapinterage.app.ClaimStatusMenu getClaimStatus()
    {
        return com.kingdee.eas.mkld.sapinterage.app.ClaimStatusMenu.getEnum(getString("ClaimStatus"));
    }
    public void setClaimStatus(com.kingdee.eas.mkld.sapinterage.app.ClaimStatusMenu item)
    {
		if (item != null) {
        setString("ClaimStatus", item.getValue());
		}
    }
    /**
     * Object:收款认领记录's 往来户姓名property 
     */
    public String getPayerName()
    {
        return getString("PayerName");
    }
    public void setPayerName(String item)
    {
        setString("PayerName", item);
    }
    /**
     * Object:收款认领记录's 二次发送状态property 
     */
    public com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu getSendSentFlag()
    {
        return com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu.getEnum(getString("SendSentFlag"));
    }
    public void setSendSentFlag(com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu item)
    {
		if (item != null) {
        setString("SendSentFlag", item.getValue());
		}
    }
    /**
     * Object:收款认领记录's 一次发送状态property 
     */
    public com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu getFirstSentFlag()
    {
        return com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu.getEnum(getString("FirstSentFlag"));
    }
    public void setFirstSentFlag(com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu item)
    {
		if (item != null) {
        setString("FirstSentFlag", item.getValue());
		}
    }
    /**
     * Object:收款认领记录's 币种编码property 
     */
    public String getCurrencyNo()
    {
        return getString("CurrencyNo");
    }
    public void setCurrencyNo(String item)
    {
        setString("CurrencyNo", item);
    }
    /**
     * Object:收款认领记录's 公司编码property 
     */
    public String getCompanyNumber()
    {
        return getString("companyNumber");
    }
    public void setCompanyNumber(String item)
    {
        setString("companyNumber", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("841A1C2F");
    }
}