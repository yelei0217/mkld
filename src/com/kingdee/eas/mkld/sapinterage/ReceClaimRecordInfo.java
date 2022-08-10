package com.kingdee.eas.mkld.sapinterage;

import java.io.Serializable;

public class ReceClaimRecordInfo extends AbstractReceClaimRecordInfo implements Serializable 
{
    public ReceClaimRecordInfo()
    {
        super();
    }
    protected ReceClaimRecordInfo(String pkField)
    {
        super(pkField);
    }
}