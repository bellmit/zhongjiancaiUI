package com.kingdee.eas.custom;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class EAIDataBaseTypeCollection extends AbstractObjectCollection 
{
    public EAIDataBaseTypeCollection()
    {
        super(EAIDataBaseTypeInfo.class);
    }
    public boolean add(EAIDataBaseTypeInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(EAIDataBaseTypeCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(EAIDataBaseTypeInfo item)
    {
        return removeObject(item);
    }
    public EAIDataBaseTypeInfo get(int index)
    {
        return(EAIDataBaseTypeInfo)getObject(index);
    }
    public EAIDataBaseTypeInfo get(Object key)
    {
        return(EAIDataBaseTypeInfo)getObject(key);
    }
    public void set(int index, EAIDataBaseTypeInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(EAIDataBaseTypeInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(EAIDataBaseTypeInfo item)
    {
        return super.indexOf(item);
    }
}