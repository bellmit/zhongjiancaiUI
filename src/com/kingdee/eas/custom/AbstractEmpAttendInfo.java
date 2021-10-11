package com.kingdee.eas.custom;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractEmpAttendInfo extends com.kingdee.eas.framework.DataBaseInfo implements Serializable 
{
    public AbstractEmpAttendInfo()
    {
        this("id");
    }
    protected AbstractEmpAttendInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:Ա��������Ϣ's Ա������property 
     */
    public String getEmpNumber()
    {
        return getString("empNumber");
    }
    public void setEmpNumber(String item)
    {
        setString("empNumber", item);
    }
    /**
     * Object:Ա��������Ϣ's Ա������property 
     */
    public String getEmpName()
    {
        return getString("empName");
    }
    public void setEmpName(String item)
    {
        setString("empName", item);
    }
    /**
     * Object:Ա��������Ϣ's �ڼ�property 
     */
    public String getPeriod()
    {
        return getString("period");
    }
    public void setPeriod(String item)
    {
        setString("period", item);
    }
    /**
     * Object:Ա��������Ϣ's ����property 
     */
    public java.math.BigDecimal getBingjia()
    {
        return getBigDecimal("bingjia");
    }
    public void setBingjia(java.math.BigDecimal item)
    {
        setBigDecimal("bingjia", item);
    }
    /**
     * Object:Ա��������Ϣ's �¼�property 
     */
    public java.math.BigDecimal getShijia()
    {
        return getBigDecimal("shijia");
    }
    public void setShijia(java.math.BigDecimal item)
    {
        setBigDecimal("shijia", item);
    }
    /**
     * Object:Ա��������Ϣ's ����property 
     */
    public java.math.BigDecimal getChanjia()
    {
        return getBigDecimal("chanjia");
    }
    public void setChanjia(java.math.BigDecimal item)
    {
        setBigDecimal("chanjia", item);
    }
    /**
     * Object:Ա��������Ϣ's ����property 
     */
    public java.math.BigDecimal getKuanggong()
    {
        return getBigDecimal("kuanggong");
    }
    public void setKuanggong(java.math.BigDecimal item)
    {
        setBigDecimal("kuanggong", item);
    }
    /**
     * Object:Ա��������Ϣ's ���ٿۿ�property 
     */
    public java.math.BigDecimal getBingjiakoukuan()
    {
        return getBigDecimal("bingjiakoukuan");
    }
    public void setBingjiakoukuan(java.math.BigDecimal item)
    {
        setBigDecimal("bingjiakoukuan", item);
    }
    /**
     * Object:Ա��������Ϣ's ���ڿۿ�property 
     */
    public java.math.BigDecimal getKaoqinkoukuan()
    {
        return getBigDecimal("kaoqinkoukuan");
    }
    public void setKaoqinkoukuan(java.math.BigDecimal item)
    {
        setBigDecimal("kaoqinkoukuan", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("A362E0DD");
    }
}