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
     * Object: ҳǩ�����Ϣ 's null property 
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
     * Object:ҳǩ�����Ϣ's ��ʾ����property 
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
     * Object:ҳǩ�����Ϣ's ʵ������property 
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
     * Object:ҳǩ�����Ϣ's ���ݿ��ֶ���property 
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
     * Object:ҳǩ�����Ϣ's �ֶ�����property 
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
     * Object:ҳǩ�����Ϣ's �����к�property 
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
     * Object:ҳǩ�����Ϣ's ������property 
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
     * Object:ҳǩ�����Ϣ's �Ƿ����޸�property 
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
     * Object:ҳǩ�����Ϣ's ҳǩ�������property 
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
     * Object:ҳǩ�����Ϣ's �Ƿ��ֻ�������property 
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
     * Object:ҳǩ�����Ϣ's �޸�״̬property 
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
     * Object:ҳǩ�����Ϣ's �п�property 
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
     * Object:ҳǩ�����Ϣ's ��ʾ�ڵ�property 
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
     * Object:ҳǩ�����Ϣ's �༭�ڵ�property 
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
     * Object:ҳǩ�����Ϣ's ��ʼֵproperty 
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
     * Object:ҳǩ�����Ϣ's ��ǩ����property 
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
     * Object:ҳǩ�����Ϣ's ����ڵ�property 
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
     * Object:ҳǩ�����Ϣ's ������ؽڵ�property 
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