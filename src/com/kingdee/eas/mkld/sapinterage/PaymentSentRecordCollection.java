package com.kingdee.eas.mkld.sapinterage;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class PaymentSentRecordCollection extends AbstractObjectCollection 
{
    public PaymentSentRecordCollection()
    {
        super(PaymentSentRecordInfo.class);
    }
    public boolean add(PaymentSentRecordInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(PaymentSentRecordCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(PaymentSentRecordInfo item)
    {
        return removeObject(item);
    }
    public PaymentSentRecordInfo get(int index)
    {
        return(PaymentSentRecordInfo)getObject(index);
    }
    public PaymentSentRecordInfo get(Object key)
    {
        return(PaymentSentRecordInfo)getObject(key);
    }
    public void set(int index, PaymentSentRecordInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(PaymentSentRecordInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(PaymentSentRecordInfo item)
    {
        return super.indexOf(item);
    }
}