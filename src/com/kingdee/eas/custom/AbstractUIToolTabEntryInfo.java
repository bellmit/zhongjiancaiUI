package com.kingdee.eas.custom;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractUIToolTabEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractUIToolTabEntryInfo()
    {
        this("id");
    }
    protected AbstractUIToolTabEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 页签信息 's null property 
     */
    public com.kingdee.eas.custom.UIToolInfo getParent()
    {
        return (com.kingdee.eas.custom.UIToolInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.custom.UIToolInfo item)
    {
        put("parent", item);
    }
    /**
     * Object:页签信息's 页签名称property 
     */
    public String getTabName()
    {
        return getString("tabName");
    }
    public void setTabName(String item)
    {
        setString("tabName", item);
    }
    /**
     * Object:页签信息's 页签描述property 
     */
    public String getTabAlies()
    {
        return getString("tabAlies");
    }
    public void setTabAlies(String item)
    {
        setString("tabAlies", item);
    }
    /**
     * Object:页签信息's 分录属性property 
     */
    public String getEntryAtt()
    {
        return getString("entryAtt");
    }
    public void setEntryAtt(String item)
    {
        setString("entryAtt", item);
    }
    /**
     * Object:页签信息's 分录BOSTYPEproperty 
     */
    public String getEntryBostype()
    {
        return getString("entryBostype");
    }
    public void setEntryBostype(String item)
    {
        setString("entryBostype", item);
    }
    /**
     * Object:页签信息's 页签表格名称property 
     */
    public String getTabTableName()
    {
        return getString("tabTableName");
    }
    public void setTabTableName(String item)
    {
        setString("tabTableName", item);
    }
    /**
     * Object:页签信息's 手机端是否显示property 
     */
    public boolean isIsPhoneDisplay()
    {
        return getBoolean("isPhoneDisplay");
    }
    public void setIsPhoneDisplay(boolean item)
    {
        setBoolean("isPhoneDisplay", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("610095AE");
    }
}