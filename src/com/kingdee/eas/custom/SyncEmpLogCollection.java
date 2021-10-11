package com.kingdee.eas.custom;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class SyncEmpLogCollection extends AbstractObjectCollection 
{
    public SyncEmpLogCollection()
    {
        super(SyncEmpLogInfo.class);
    }
    public boolean add(SyncEmpLogInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(SyncEmpLogCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(SyncEmpLogInfo item)
    {
        return removeObject(item);
    }
    public SyncEmpLogInfo get(int index)
    {
        return(SyncEmpLogInfo)getObject(index);
    }
    public SyncEmpLogInfo get(Object key)
    {
        return(SyncEmpLogInfo)getObject(key);
    }
    public void set(int index, SyncEmpLogInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(SyncEmpLogInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(SyncEmpLogInfo item)
    {
        return super.indexOf(item);
    }
}