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
     * Object: UI工具 's 分录 property 
     */
    public com.kingdee.eas.custom.UIToolEntryCollection getEntrys()
    {
        return (com.kingdee.eas.custom.UIToolEntryCollection)get("entrys");
    }
    /**
     * Object:UI工具's 是否生成凭证property 
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
     * Object:UI工具's 审批名称property 
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
     * Object:UI工具's BOSTYPEproperty 
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
     * Object:UI工具's URLproperty 
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
     * Object: UI工具 's 页签信息 property 
     */
    public com.kingdee.eas.custom.UIToolTabEntryCollection getTabEntry()
    {
        return (com.kingdee.eas.custom.UIToolTabEntryCollection)get("TabEntry");
    }
    /**
     * Object: UI工具 's 页签表格信息 property 
     */
    public com.kingdee.eas.custom.UIToolTabTableEntryCollection getTabTableEntry()
    {
        return (com.kingdee.eas.custom.UIToolTabTableEntryCollection)get("TabTableEntry");
    }
    /**
     * Object:UI工具's 表名property 
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
     * Object:UI工具's 附件显示节点property 
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
     * Object:UI工具's 附件上传节点property 
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