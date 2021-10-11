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
     * Object: Ò³Ç©ÐÅÏ¢ 's null property 
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
     * Object:Ò³Ç©ÐÅÏ¢'s Ò³Ç©Ãû³Æproperty 
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
     * Object:Ò³Ç©ÐÅÏ¢'s Ò³Ç©ÃèÊöproperty 
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
     * Object:Ò³Ç©ÐÅÏ¢'s ·ÖÂ¼ÊôÐÔproperty 
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
     * Object:Ò³Ç©ÐÅÏ¢'s ·ÖÂ¼BOSTYPEproperty 
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
     * Object:Ò³Ç©ÐÅÏ¢'s Ò³Ç©±í¸ñÃû³Æproperty 
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
     * Object:Ò³Ç©ÐÅÏ¢'s ÊÖ»ú¶ËÊÇ·ñÏÔÊ¾property 
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