package com.kingdee.eas.custom;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class DateBaseLogCollection extends AbstractObjectCollection 
{
    public DateBaseLogCollection()
    {
        super(DateBaseLogInfo.class);
    }
    public boolean add(DateBaseLogInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(DateBaseLogCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(DateBaseLogInfo item)
    {
        return removeObject(item);
    }
    public DateBaseLogInfo get(int index)
    {
        return(DateBaseLogInfo)getObject(index);
    }
    public DateBaseLogInfo get(Object key)
    {
        return(DateBaseLogInfo)getObject(key);
    }
    public void set(int index, DateBaseLogInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(DateBaseLogInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(DateBaseLogInfo item)
    {
        return super.indexOf(item);
    }
}