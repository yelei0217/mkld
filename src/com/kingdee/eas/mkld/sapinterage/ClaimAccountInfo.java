package com.kingdee.eas.mkld.sapinterage;

import java.io.Serializable;

public class ClaimAccountInfo extends AbstractClaimAccountInfo implements Serializable 
{
    public ClaimAccountInfo()
    {
        super();
    }
    protected ClaimAccountInfo(String pkField)
    {
        super(pkField);
    }
}