package com.kingdee.eas.mkld.sapinterage;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class SAPInterfaceLogCollection extends AbstractObjectCollection 
{
    public SAPInterfaceLogCollection()
    {
        super(SAPInterfaceLogInfo.class);
    }
    public boolean add(SAPInterfaceLogInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(SAPInterfaceLogCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(SAPInterfaceLogInfo item)
    {
        return removeObject(item);
    }
    public SAPInterfaceLogInfo get(int index)
    {
        return(SAPInterfaceLogInfo)getObject(index);
    }
    public SAPInterfaceLogInfo get(Object key)
    {
        return(SAPInterfaceLogInfo)getObject(key);
    }
    public void set(int index, SAPInterfaceLogInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(SAPInterfaceLogInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(SAPInterfaceLogInfo item)
    {
        return super.indexOf(item);
    }
}