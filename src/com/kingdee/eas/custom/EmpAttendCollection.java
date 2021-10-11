package com.kingdee.eas.custom;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class EmpAttendCollection extends AbstractObjectCollection 
{
    public EmpAttendCollection()
    {
        super(EmpAttendInfo.class);
    }
    public boolean add(EmpAttendInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(EmpAttendCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(EmpAttendInfo item)
    {
        return removeObject(item);
    }
    public EmpAttendInfo get(int index)
    {
        return(EmpAttendInfo)getObject(index);
    }
    public EmpAttendInfo get(Object key)
    {
        return(EmpAttendInfo)getObject(key);
    }
    public void set(int index, EmpAttendInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(EmpAttendInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(EmpAttendInfo item)
    {
        return super.indexOf(item);
    }
}