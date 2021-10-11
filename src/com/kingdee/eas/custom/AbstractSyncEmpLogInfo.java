package com.kingdee.eas.custom;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractSyncEmpLogInfo extends com.kingdee.eas.framework.DataBaseInfo implements Serializable 
{
    public AbstractSyncEmpLogInfo()
    {
        this("id");
    }
    protected AbstractSyncEmpLogInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:��Աͬ����־'s Ա������property 
     */
    public String getEmpNumber()
    {
        return getString("empNumber");
    }
    public void setEmpNumber(String item)
    {
        setString("empNumber", item);
    }
    /**
     * Object:��Աͬ����־'s ��Ϣproperty 
     */
    public String getMsg()
    {
        return getString("msg");
    }
    public void setMsg(String item)
    {
        setString("msg", item);
    }
    /**
     * Object:��Աͬ����־'s ����property 
     */
    public java.util.Date getDate()
    {
        return getDate("date");
    }
    public void setDate(java.util.Date item)
    {
        setDate("date", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("FE723BDC");
    }
}