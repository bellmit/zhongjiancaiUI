package com.kingdee.eas.moya.message.app.landray.webservice;

import java.io.*;
import javax.xml.namespace.*;
import org.apache.axis.description.*;
import org.apache.axis.encoding.*;
import org.apache.axis.encoding.ser.*;

public class NotifyTodoGetContext implements Serializable
{
    private String otherCond;
    private int pageNo;
    private int rowSize;
    private String targets;
    private int type;
    private Object __equalsCalc;
    private boolean __hashCodeCalc;
    private static TypeDesc typeDesc;
    
    static {
        (NotifyTodoGetContext.typeDesc = new TypeDesc((Class)NotifyTodoGetContext.class, true)).setXmlType(new QName("http://webservice.notify.sys.kmss.landray.com/", "notifyTodoGetContext"));
        ElementDesc elemField = new ElementDesc();
        elemField.setFieldName("otherCond");
        elemField.setXmlName(new QName("", "otherCond"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoGetContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("pageNo");
        elemField.setXmlName(new QName("", "pageNo"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        NotifyTodoGetContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("rowSize");
        elemField.setXmlName(new QName("", "rowSize"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        NotifyTodoGetContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("targets");
        elemField.setXmlName(new QName("", "targets"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoGetContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new QName("", "type"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        NotifyTodoGetContext.typeDesc.addFieldDesc((FieldDesc)elemField);
    }
    
    public NotifyTodoGetContext() {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
    }
    
    public NotifyTodoGetContext(final String otherCond, final int pageNo, final int rowSize, final String targets, final int type) {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
        this.otherCond = otherCond;
        this.pageNo = pageNo;
        this.rowSize = rowSize;
        this.targets = targets;
        this.type = type;
    }
    
    public String getOtherCond() {
        return this.otherCond;
    }
    
    public void setOtherCond(final String otherCond) {
        this.otherCond = otherCond;
    }
    
    public int getPageNo() {
        return this.pageNo;
    }
    
    public void setPageNo(final int pageNo) {
        this.pageNo = pageNo;
    }
    
    public int getRowSize() {
        return this.rowSize;
    }
    
    public void setRowSize(final int rowSize) {
        this.rowSize = rowSize;
    }
    
    public String getTargets() {
        return this.targets;
    }
    
    public void setTargets(final String targets) {
        this.targets = targets;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(final int type) {
        this.type = type;
    }
    
    @Override
    public synchronized boolean equals(final Object obj) {
        if (!(obj instanceof NotifyTodoGetContext)) {
            return false;
        }
        final NotifyTodoGetContext other = (NotifyTodoGetContext)obj;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.__equalsCalc != null) {
            return this.__equalsCalc == obj;
        }
        this.__equalsCalc = obj;
        final boolean _equals = ((this.otherCond == null && other.getOtherCond() == null) || (this.otherCond != null && this.otherCond.equals(other.getOtherCond()))) && this.pageNo == other.getPageNo() && this.rowSize == other.getRowSize() && ((this.targets == null && other.getTargets() == null) || (this.targets != null && this.targets.equals(other.getTargets()))) && this.type == other.getType();
        this.__equalsCalc = null;
        return _equals;
    }
    
    @Override
    public synchronized int hashCode() {
        if (this.__hashCodeCalc) {
            return 0;
        }
        this.__hashCodeCalc = true;
        int _hashCode = 1;
        if (this.getOtherCond() != null) {
            _hashCode += this.getOtherCond().hashCode();
        }
        _hashCode += this.getPageNo();
        _hashCode += this.getRowSize();
        if (this.getTargets() != null) {
            _hashCode += this.getTargets().hashCode();
        }
        _hashCode += this.getType();
        this.__hashCodeCalc = false;
        return _hashCode;
    }
    
    public static TypeDesc getTypeDesc() {
        return NotifyTodoGetContext.typeDesc;
    }
    
    public static Serializer getSerializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Serializer)new BeanSerializer(_javaType, _xmlType, NotifyTodoGetContext.typeDesc);
    }
    
    public static Deserializer getDeserializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Deserializer)new BeanDeserializer(_javaType, _xmlType, NotifyTodoGetContext.typeDesc);
    }
}
