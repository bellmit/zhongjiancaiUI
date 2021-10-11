package com.kingdee.eas.custom;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractUIToolEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractUIToolEntryInfo()
    {
        this("id");
    }
    protected AbstractUIToolEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 分录 's 单据头 property 
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
     * Object:分录's 显示名称property 
     */
    public String getDisplayName()
    {
        return getString("displayName");
    }
    public void setDisplayName(String item)
    {
        setString("displayName", item);
    }
    /**
     * Object:分录's 实体属性property 
     */
    public String getEntityAttributes()
    {
        return getString("entityAttributes");
    }
    public void setEntityAttributes(String item)
    {
        setString("entityAttributes", item);
    }
    /**
     * Object:分录's 数据库字段名property 
     */
    public String getDbName()
    {
        return getString("dbName");
    }
    public void setDbName(String item)
    {
        setString("dbName", item);
    }
    /**
     * Object:分录's 字段类型property 
     */
    public String getFieldType()
    {
        return getString("fieldType");
    }
    public void setFieldType(String item)
    {
        setString("fieldType", item);
    }
    /**
     * Object:分录's 跨列数property 
     */
    public int getCrossColumn()
    {
        return getInt("crossColumn");
    }
    public void setCrossColumn(int item)
    {
        setInt("crossColumn", item);
    }
    /**
     * Object:分录's 所属行号property 
     */
    public int getBelongToRow()
    {
        return getInt("belongToRow");
    }
    public void setBelongToRow(int item)
    {
        setInt("belongToRow", item);
    }
    /**
     * Object:分录's 是否能修改property 
     */
    public boolean isIsEdit()
    {
        return getBoolean("isEdit");
    }
    public void setIsEdit(boolean item)
    {
        setBoolean("isEdit", item);
    }
    /**
     * Object:分录's 是否手机端显示property 
     */
    public boolean isIsPhone()
    {
        return getBoolean("isPhone");
    }
    public void setIsPhone(boolean item)
    {
        setBoolean("isPhone", item);
    }
    /**
     * Object:分录's 修改状态property 
     */
    public String getEditState()
    {
        return getString("editState");
    }
    public void setEditState(String item)
    {
        setString("editState", item);
    }
    /**
     * Object:分录's 显示节点property 
     */
    public String getShowNode()
    {
        return getString("showNode");
    }
    public void setShowNode(String item)
    {
        setString("showNode", item);
    }
    /**
     * Object:分录's 编辑节点property 
     */
    public String getEditNode()
    {
        return getString("editNode");
    }
    public void setEditNode(String item)
    {
        setString("editNode", item);
    }
    /**
     * Object:分录's 初始值property 
     */
    public String getInitValue()
    {
        return getString("initValue");
    }
    public void setInitValue(String item)
    {
        setString("initValue", item);
    }
    /**
     * Object:分录's 标签属性property 
     */
    public String getLablePro()
    {
        return getString("lablePro");
    }
    public void setLablePro(String item)
    {
        setString("lablePro", item);
    }
    /**
     * Object:分录's 必填节点property 
     */
    public String getRequiredNode()
    {
        return getString("requiredNode");
    }
    public void setRequiredNode(String item)
    {
        setString("requiredNode", item);
    }
    /**
     * Object:分录's 针对隐藏节点property 
     */
    public String getToHideNode()
    {
        return getString("toHideNode");
    }
    public void setToHideNode(String item)
    {
        setString("toHideNode", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("A536BA41");
    }
}