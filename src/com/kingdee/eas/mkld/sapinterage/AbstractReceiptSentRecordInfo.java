package com.kingdee.eas.mkld.sapinterage;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractReceiptSentRecordInfo extends com.kingdee.eas.framework.DataBaseInfo implements Serializable 
{
    public AbstractReceiptSentRecordInfo()
    {
        this("id");
    }
    protected AbstractReceiptSentRecordInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:�ص����ͼ�¼'s ��Դ����property 
     */
    public com.kingdee.eas.mkld.sapinterage.app.ReceiptSourceTypeMenu getSourceType()
    {
        return com.kingdee.eas.mkld.sapinterage.app.ReceiptSourceTypeMenu.getEnum(getString("sourceType"));
    }
    public void setSourceType(com.kingdee.eas.mkld.sapinterage.app.ReceiptSourceTypeMenu item)
    {
		if (item != null) {
        setString("sourceType", item.getValue());
		}
    }
    /**
     * Object:�ص����ͼ�¼'s ����״̬property 
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
     * Object:�ص����ͼ�¼'s �������property 
     */
    public String getReceiptNo()
    {
        return getString("ReceiptNo");
    }
    public void setReceiptNo(String item)
    {
        setString("ReceiptNo", item);
    }
    /**
     * Object:�ص����ͼ�¼'s ��ˮ��property 
     */
    public String getTranPackageID()
    {
        return getString("TranPackageID");
    }
    public void setTranPackageID(String item)
    {
        setString("TranPackageID", item);
    }
    /**
     * Object: �ص����ͼ�¼ 's ��˾ property 
     */
    public com.kingdee.eas.basedata.org.CompanyOrgUnitInfo getCompany()
    {
        return (com.kingdee.eas.basedata.org.CompanyOrgUnitInfo)get("Company");
    }
    public void setCompany(com.kingdee.eas.basedata.org.CompanyOrgUnitInfo item)
    {
        put("Company", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("314BE638");
    }
}