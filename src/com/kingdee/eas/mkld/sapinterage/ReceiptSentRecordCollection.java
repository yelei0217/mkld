package com.kingdee.eas.mkld.sapinterage;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class ReceiptSentRecordCollection extends AbstractObjectCollection 
{
    public ReceiptSentRecordCollection()
    {
        super(ReceiptSentRecordInfo.class);
    }
    public boolean add(ReceiptSentRecordInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(ReceiptSentRecordCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(ReceiptSentRecordInfo item)
    {
        return removeObject(item);
    }
    public ReceiptSentRecordInfo get(int index)
    {
        return(ReceiptSentRecordInfo)getObject(index);
    }
    public ReceiptSentRecordInfo get(Object key)
    {
        return(ReceiptSentRecordInfo)getObject(key);
    }
    public void set(int index, ReceiptSentRecordInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(ReceiptSentRecordInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(ReceiptSentRecordInfo item)
    {
        return super.indexOf(item);
    }
}