package com.kingdee.eas.custom;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractEAIDataBaseInfo extends com.kingdee.eas.framework.DataBaseInfo implements Serializable 
{
    public AbstractEAIDataBaseInfo()
    {
        this("id");
    }
    protected AbstractEAIDataBaseInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:�м��'s ����Դ��ַproperty 
     */
    public String getAddress()
    {
        return getString("address");
    }
    public void setAddress(String item)
    {
        setString("address", item);
    }
    /**
     * Object:�м��'s �û���property 
     */
    public String getDataSourceUser()
    {
        return getString("dataSourceUser");
    }
    public void setDataSourceUser(String item)
    {
        setString("dataSourceUser", item);
    }
    /**
     * Object:�м��'s ����property 
     */
    public String getDataSourcePassword()
    {
        return getString("dataSourcePassword");
    }
    public void setDataSourcePassword(String item)
    {
        setString("dataSourcePassword", item);
    }
    /**
     * Object: �м�� 's ���ݿ�����  property 
     */
    public com.kingdee.eas.custom.EAIDataBaseTypeInfo getDataBaseType()
    {
        return (com.kingdee.eas.custom.EAIDataBaseTypeInfo)get("dataBaseType");
    }
    public void setDataBaseType(com.kingdee.eas.custom.EAIDataBaseTypeInfo item)
    {
        put("dataBaseType", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("0D6288A3");
    }
}