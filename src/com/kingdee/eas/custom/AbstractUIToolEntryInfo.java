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
     * Object: ��¼ 's ����ͷ property 
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
     * Object:��¼'s ��ʾ����property 
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
     * Object:��¼'s ʵ������property 
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
     * Object:��¼'s ���ݿ��ֶ���property 
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
     * Object:��¼'s �ֶ�����property 
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
     * Object:��¼'s ������property 
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
     * Object:��¼'s �����к�property 
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
     * Object:��¼'s �Ƿ����޸�property 
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
     * Object:��¼'s �Ƿ��ֻ�����ʾproperty 
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
     * Object:��¼'s �޸�״̬property 
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
     * Object:��¼'s ��ʾ�ڵ�property 
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
     * Object:��¼'s �༭�ڵ�property 
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
     * Object:��¼'s ��ʼֵproperty 
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
     * Object:��¼'s ��ǩ����property 
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
     * Object:��¼'s ����ڵ�property 
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
     * Object:��¼'s ������ؽڵ�property 
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