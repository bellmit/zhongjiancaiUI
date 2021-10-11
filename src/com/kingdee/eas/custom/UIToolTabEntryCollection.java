package com.kingdee.eas.custom;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class UIToolTabEntryCollection extends AbstractObjectCollection 
{
    public UIToolTabEntryCollection()
    {
        super(UIToolTabEntryInfo.class);
    }
    public boolean add(UIToolTabEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(UIToolTabEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(UIToolTabEntryInfo item)
    {
        return removeObject(item);
    }
    public UIToolTabEntryInfo get(int index)
    {
        return(UIToolTabEntryInfo)getObject(index);
    }
    public UIToolTabEntryInfo get(Object key)
    {
        return(UIToolTabEntryInfo)getObject(key);
    }
    public void set(int index, UIToolTabEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(UIToolTabEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(UIToolTabEntryInfo item)
    {
        return super.indexOf(item);
    }
}