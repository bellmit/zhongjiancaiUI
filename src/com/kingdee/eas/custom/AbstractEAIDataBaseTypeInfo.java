package com.kingdee.eas.custom;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractEAIDataBaseTypeInfo extends com.kingdee.eas.framework.DataBaseInfo implements Serializable 
{
    public AbstractEAIDataBaseTypeInfo()
    {
        this("id");
    }
    protected AbstractEAIDataBaseTypeInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:数据库类型's 数据库驱动property 
     */
    public String getDriver()
    {
        return getString("driver");
    }
    public void setDriver(String item)
    {
        setString("driver", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("11FF827D");
    }
}