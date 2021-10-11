package com.kingdee.eas.custom;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class WebAuditLogCollection extends AbstractObjectCollection 
{
    public WebAuditLogCollection()
    {
        super(WebAuditLogInfo.class);
    }
    public boolean add(WebAuditLogInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(WebAuditLogCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(WebAuditLogInfo item)
    {
        return removeObject(item);
    }
    public WebAuditLogInfo get(int index)
    {
        return(WebAuditLogInfo)getObject(index);
    }
    public WebAuditLogInfo get(Object key)
    {
        return(WebAuditLogInfo)getObject(key);
    }
    public void set(int index, WebAuditLogInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(WebAuditLogInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(WebAuditLogInfo item)
    {
        return super.indexOf(item);
    }
}