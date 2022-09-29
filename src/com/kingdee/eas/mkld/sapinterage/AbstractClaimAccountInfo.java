package com.kingdee.eas.mkld.sapinterage;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractClaimAccountInfo extends com.kingdee.eas.framework.DataBaseInfo implements Serializable 
{
    public AbstractClaimAccountInfo()
    {
        this("id");
    }
    protected AbstractClaimAccountInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:���������˺�'s �Ƿ��տ��˺�property 
     */
    public boolean isIsCalim()
    {
        return getBoolean("isCalim");
    }
    public void setIsCalim(boolean item)
    {
        setBoolean("isCalim", item);
    }
    /**
     * Object:���������˺�'s �Ƿ���DMSproperty 
     */
    public boolean isIsSendDms()
    {
        return getBoolean("isSendDms");
    }
    public void setIsSendDms(boolean item)
    {
        setBoolean("isSendDms", item);
    }
    /**
     * Object:���������˺�'s ��ҵ��property 
     */
    public String getBusDivision()
    {
        return getString("busDivision");
    }
    public void setBusDivision(String item)
    {
        setString("busDivision", item);
    }
    /**
     * Object:���������˺�'s ״̬property 
     */
    public com.kingdee.eas.basedata.framework.DataStateEnum getDataState()
    {
        return com.kingdee.eas.basedata.framework.DataStateEnum.getEnum(getInt("dataState"));
    }
    public void setDataState(com.kingdee.eas.basedata.framework.DataStateEnum item)
    {
		if (item != null) {
        setInt("dataState", item.getValue());
		}
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("738C4C1A");
    }
}