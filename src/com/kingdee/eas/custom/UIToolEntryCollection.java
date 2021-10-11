package com.kingdee.eas.custom;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class UIToolEntryCollection extends AbstractObjectCollection 
{
    public UIToolEntryCollection()
    {
        super(UIToolEntryInfo.class);
    }
    public boolean add(UIToolEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(UIToolEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(UIToolEntryInfo item)
    {
        return removeObject(item);
    }
    public UIToolEntryInfo get(int index)
    {
        return(UIToolEntryInfo)getObject(index);
    }
    public UIToolEntryInfo get(Object key)
    {
        return(UIToolEntryInfo)getObject(key);
    }
    public void set(int index, UIToolEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(UIToolEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(UIToolEntryInfo item)
    {
        return super.indexOf(item);
    }
}