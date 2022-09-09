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
     * Object:回单发送记录's 来源类型property 
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
     * Object:回单发送记录's 发送状态property 
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
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("314BE638");
    }
}