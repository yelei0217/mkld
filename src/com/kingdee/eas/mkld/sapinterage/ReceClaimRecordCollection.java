package com.kingdee.eas.mkld.sapinterage;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class ReceClaimRecordCollection extends AbstractObjectCollection 
{
    public ReceClaimRecordCollection()
    {
        super(ReceClaimRecordInfo.class);
    }
    public boolean add(ReceClaimRecordInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(ReceClaimRecordCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(ReceClaimRecordInfo item)
    {
        return removeObject(item);
    }
    public ReceClaimRecordInfo get(int index)
    {
        return(ReceClaimRecordInfo)getObject(index);
    }
    public ReceClaimRecordInfo get(Object key)
    {
        return(ReceClaimRecordInfo)getObject(key);
    }
    public void set(int index, ReceClaimRecordInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(ReceClaimRecordInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(ReceClaimRecordInfo item)
    {
        return super.indexOf(item);
    }
}