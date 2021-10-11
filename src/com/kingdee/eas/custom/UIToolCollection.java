package com.kingdee.eas.custom;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class UIToolCollection extends AbstractObjectCollection 
{
    public UIToolCollection()
    {
        super(UIToolInfo.class);
    }
    public boolean add(UIToolInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(UIToolCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(UIToolInfo item)
    {
        return removeObject(item);
    }
    public UIToolInfo get(int index)
    {
        return(UIToolInfo)getObject(index);
    }
    public UIToolInfo get(Object key)
    {
        return(UIToolInfo)getObject(key);
    }
    public void set(int index, UIToolInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(UIToolInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(UIToolInfo item)
    {
        return super.indexOf(item);
    }
}