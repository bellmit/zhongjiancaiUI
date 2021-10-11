package com.kingdee.eas.custom;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class UIToolTabTableEntryCollection extends AbstractObjectCollection 
{
    public UIToolTabTableEntryCollection()
    {
        super(UIToolTabTableEntryInfo.class);
    }
    public boolean add(UIToolTabTableEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(UIToolTabTableEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(UIToolTabTableEntryInfo item)
    {
        return removeObject(item);
    }
    public UIToolTabTableEntryInfo get(int index)
    {
        return(UIToolTabTableEntryInfo)getObject(index);
    }
    public UIToolTabTableEntryInfo get(Object key)
    {
        return(UIToolTabTableEntryInfo)getObject(key);
    }
    public void set(int index, UIToolTabTableEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(UIToolTabTableEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(UIToolTabTableEntryInfo item)
    {
        return super.indexOf(item);
    }
}