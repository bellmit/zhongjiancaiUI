package com.kingdee.eas.custom;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractUIToolInfo extends com.kingdee.eas.framework.CoreBillBaseInfo implements Serializable 
{
    public AbstractUIToolInfo()
    {
        this("id");
    }
    protected AbstractUIToolInfo(String pkField)
    {
        super(pkField);
        put("TabTableEntry", new com.kingdee.eas.custom.UIToolTabTableEntryCollection());
        put("TabEntry", new com.kingdee.eas.custom.UIToolTabEntryCollection());
        put("entrys", new com.kingdee.eas.custom.UIToolEntryCollection());
    }
    /**
     * Object: UI���� 's ��¼ property 
     */
    public com.kingdee.eas.custom.UIToolEntryCollection getEntrys()
    {
        return (com.kingdee.eas.custom.UIToolEntryCollection)get("entrys");
    }
    /**
     * Object:UI����'s �Ƿ�����ƾ֤property 
     */
    public boolean isFivouchered()
    {
        return getBoolean("Fivouchered");
    }
    public void setFivouchered(boolean item)
    {
        setBoolean("Fivouchered", item);
    }
    /**
     * Object:UI����'s ��������property 
     */
    public String getAuditTitle()
    {
        return getString("auditTitle");
    }
    public void setAuditTitle(String item)
    {
        setString("auditTitle", item);
    }
    /**
     * Object:UI����'s BOSTYPEproperty 
     */
    public String getBostype()
    {
        return getString("bostype");
    }
    public void setBostype(String item)
    {
        setString("bostype", item);
    }
    /**
     * Object:UI����'s URLproperty 
     */
    public String getUrl()
    {
        return getString("url");
    }
    public void setUrl(String item)
    {
        setString("url", item);
    }
    /**
     * Object: UI���� 's ҳǩ��Ϣ property 
     */
    public com.kingdee.eas.custom.UIToolTabEntryCollection getTabEntry()
    {
        return (com.kingdee.eas.custom.UIToolTabEntryCollection)get("TabEntry");
    }
    /**
     * Object: UI���� 's ҳǩ�����Ϣ property 
     */
    public com.kingdee.eas.custom.UIToolTabTableEntryCollection getTabTableEntry()
    {
        return (com.kingdee.eas.custom.UIToolTabTableEntryCollection)get("TabTableEntry");
    }
    /**
     * Object:UI����'s ����property 
     */
    public String getTableName()
    {
        return getString("tableName");
    }
    public void setTableName(String item)
    {
        setString("tableName", item);
    }
    /**
     * Object:UI����'s ������ʾ�ڵ�property 
     */
    public String getFileViewNode()
    {
        return getString("fileViewNode");
    }
    public void setFileViewNode(String item)
    {
        setString("fileViewNode", item);
    }
    /**
     * Object:UI����'s �����ϴ��ڵ�property 
     */
    public String getFileUpNode()
    {
        return getString("fileUpNode");
    }
    public void setFileUpNode(String item)
    {
        setString("fileUpNode", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("8339A4B1");
    }
}