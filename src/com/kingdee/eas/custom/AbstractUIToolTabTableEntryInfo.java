package com.kingdee.eas.custom;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractUIToolTabTableEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractUIToolTabTableEntryInfo()
    {
        this("id");
    }
    protected AbstractUIToolTabTableEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 页签表格信息 's null property 
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
     * Object:页签表格信息's 显示名称property 
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
     * Object:页签表格信息's 实体属性property 
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
     * Object:页签表格信息's 数据库字段名property 
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
     * Object:页签表格信息's 字段类型property 
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
     * Object:页签表格信息's 所属行号property 
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
     * Object:页签表格信息's 跨列数property 
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
     * Object:页签表格信息's 是否能修改property 
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
     * Object:页签表格信息's 页签表格名称property 
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
     * Object:页签表格信息's 是否手机端收起property 
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
     * Object:页签表格信息's 修改状态property 
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
     * Object:页签表格信息's 列宽property 
     */
    public int getColWidth()
    {
        return getInt("colWidth");
    }
    public void setColWidth(int item)
    {
        setInt("colWidth", item);
    }
    /**
     * Object:页签表格信息's 显示节点property 
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
     * Object:页签表格信息's 编辑节点property 
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
     * Object:页签表格信息's 初始值property 
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
     * Object:页签表格信息's 标签属性property 
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
     * Object:页签表格信息's 必填节点property 
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
     * Object:页签表格信息's 针对隐藏节点property 
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
        return new BOSObjectType("C9786588");
    }
}