package com.kingdee.eas.mkld.sapinterage;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class ClaimAccountCollection extends AbstractObjectCollection 
{
    public ClaimAccountCollection()
    {
        super(ClaimAccountInfo.class);
    }
    public boolean add(ClaimAccountInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(ClaimAccountCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(ClaimAccountInfo item)
    {
        return removeObject(item);
    }
    public ClaimAccountInfo get(int index)
    {
        return(ClaimAccountInfo)getObject(index);
    }
    public ClaimAccountInfo get(Object key)
    {
        return(ClaimAccountInfo)getObject(key);
    }
    public void set(int index, ClaimAccountInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(ClaimAccountInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(ClaimAccountInfo item)
    {
        return super.indexOf(item);
    }
}