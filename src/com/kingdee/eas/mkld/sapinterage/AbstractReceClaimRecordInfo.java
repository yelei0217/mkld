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
     * Object:�տ������¼'s �Ƿ�����ƾ֤property 
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
     * Object: �տ������¼ 's ��˾ property 
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
     * Object:�տ������¼'s �տ��IDproperty 
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
     * Object:�տ������¼'s �տ���property 
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
     * Object:�տ������¼'s ��ˮ��property 
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
     * Object:�տ������¼'s ���property 
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
     * Object:�տ������¼'s �·�property 
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
     * Object:�տ������¼'s �տ������˺�property 
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
     * Object:�տ������¼'s �տ�����property 
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
     * Object:�տ������¼'s �տ���property 
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
     * Object:�տ������¼'s ����property 
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
     * Object:�տ������¼'s ��֤��property 
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
     * Object:�տ������¼'s Ѻ��property 
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
     * Object:�տ������¼'s ժҪproperty 
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
     * Object:�տ������¼'s �ٴ���������property 
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
     * Object:�տ������¼'s �ͻ�����property 
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
     * Object:�տ������¼'s ��������property 
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
     * Object:�տ������¼'s ����״̬property 
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
     * Object:�տ������¼'s ����������property 
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
     * Object:�տ������¼'s ���η���״̬property 
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
     * Object:�տ������¼'s һ�η���״̬property 
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
     * Object:�տ������¼'s ���ֱ���property 
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
     * Object:�տ������¼'s ��˾����property 
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