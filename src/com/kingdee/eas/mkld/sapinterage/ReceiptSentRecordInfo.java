package com.kingdee.eas.mkld.sapinterage;

import java.io.Serializable;

public class ReceiptSentRecordInfo extends AbstractReceiptSentRecordInfo implements Serializable 
{
    public ReceiptSentRecordInfo()
    {
        super();
    }
    protected ReceiptSentRecordInfo(String pkField)
    {
        super(pkField);
    }
}