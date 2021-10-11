package com.kingdee.eas.custom;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractWebAuditLogInfo extends com.kingdee.eas.framework.DataBaseInfo implements Serializable 
{
    public AbstractWebAuditLogInfo()
    {
        this("id");
    }
    protected AbstractWebAuditLogInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:web审批日志's 日志类型property 
     */
    public com.kingdee.eas.custom.LogTypeEnum getLogType()
    {
        return com.kingdee.eas.custom.LogTypeEnum.getEnum(getInt("logType"));
    }
    public void setLogType(com.kingdee.eas.custom.LogTypeEnum item)
    {
		if (item != null) {
        setInt("logType", item.getValue());
		}
    }
    /**
     * Object:web审批日志's 参数property 
     */
    public String getParameter()
    {
        return getString("parameter");
    }
    public void setParameter(String item)
    {
        setString("parameter", item);
    }
    /**
     * Object:web审批日志's 返回值property 
     */
    public String getReturnValue()
    {
        return getString("returnValue");
    }
    public void setReturnValue(String item)
    {
        setString("returnValue", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("2A0C9178");
    }
}