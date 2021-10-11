package com.kingdee.eas.custom;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class EAIDataBaseCollection extends AbstractObjectCollection 
{
    public EAIDataBaseCollection()
    {
        super(EAIDataBaseInfo.class);
    }
    public boolean add(EAIDataBaseInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(EAIDataBaseCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(EAIDataBaseInfo item)
    {
        return removeObject(item);
    }
    public EAIDataBaseInfo get(int index)
    {
        return(EAIDataBaseInfo)getObject(index);
    }
    public EAIDataBaseInfo get(Object key)
    {
        return(EAIDataBaseInfo)getObject(key);
    }
    public void set(int index, EAIDataBaseInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(EAIDataBaseInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(EAIDataBaseInfo item)
    {
        return super.indexOf(item);
    }
}