package com.kingdee.eas.mkld.sapinterage;

import java.io.Serializable;

public class SAPInterfaceLogInfo extends AbstractSAPInterfaceLogInfo implements Serializable 
{
    public SAPInterfaceLogInfo()
    {
        super();
    }
    protected SAPInterfaceLogInfo(String pkField)
    {
        super(pkField);
    }
}