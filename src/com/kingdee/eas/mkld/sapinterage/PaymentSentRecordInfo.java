package com.kingdee.eas.mkld.sapinterage;

import java.io.Serializable;

public class PaymentSentRecordInfo extends AbstractPaymentSentRecordInfo implements Serializable 
{
    public PaymentSentRecordInfo()
    {
        super();
    }
    protected PaymentSentRecordInfo(String pkField)
    {
        super(pkField);
    }
}