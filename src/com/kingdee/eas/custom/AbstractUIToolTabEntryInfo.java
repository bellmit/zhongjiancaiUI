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
     * Object: ҳǩ��Ϣ 's null property 
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
     * Object:ҳǩ��Ϣ's ҳǩ����property 
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
     * Object:ҳǩ��Ϣ's ҳǩ����property 
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
     * Object:ҳǩ��Ϣ's ��¼����property 
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
     * Object:ҳǩ��Ϣ's ��¼BOSTYPEproperty 
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
     * Object:ҳǩ��Ϣ's ҳǩ�������property 
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
     * Object:ҳǩ��Ϣ's �ֻ����Ƿ���ʾproperty 
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