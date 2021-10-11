package com.kingdee.eas.custom;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class TaskCollection extends AbstractObjectCollection 
{
    public TaskCollection()
    {
        super(TaskInfo.class);
    }
    public boolean add(TaskInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(TaskCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(TaskInfo item)
    {
        return removeObject(item);
    }
    public TaskInfo get(int index)
    {
        return(TaskInfo)getObject(index);
    }
    public TaskInfo get(Object key)
    {
        return(TaskInfo)getObject(key);
    }
    public void set(int index, TaskInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(TaskInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(TaskInfo item)
    {
        return super.indexOf(item);
    }
}