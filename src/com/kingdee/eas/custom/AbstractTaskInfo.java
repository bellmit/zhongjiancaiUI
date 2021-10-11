package com.kingdee.eas.custom;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractTaskInfo extends com.kingdee.eas.framework.DataBaseInfo implements Serializable 
{
    public AbstractTaskInfo()
    {
        this("id");
    }
    protected AbstractTaskInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:消息's 流程节点property 
     */
    public String getAssignid()
    {
        return getString("assignid");
    }
    public void setAssignid(String item)
    {
        setString("assignid", item);
    }
    /**
     * Object:消息's 消息idproperty 
     */
    public String getRecordid()
    {
        return getString("recordid");
    }
    public void setRecordid(String item)
    {
        setString("recordid", item);
    }
    /**
     * Object:消息's ddidproperty 
     */
    public String getDdid()
    {
        return getString("ddid");
    }
    public void setDdid(String item)
    {
        setString("ddid", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("4499254A");
    }
}