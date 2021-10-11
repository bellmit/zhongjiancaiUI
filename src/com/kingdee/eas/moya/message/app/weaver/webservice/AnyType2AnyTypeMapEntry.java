package com.kingdee.eas.moya.message.app.weaver.webservice;

import java.io.*;
import javax.xml.namespace.*;
import org.apache.axis.description.*;
import org.apache.axis.encoding.*;
import org.apache.axis.encoding.ser.*;

public class AnyType2AnyTypeMapEntry implements Serializable
{
    private Object key;
    private Object value;
    private Object __equalsCalc;
    private boolean __hashCodeCalc;
    private static TypeDesc typeDesc;
    
    static {
        (AnyType2AnyTypeMapEntry.typeDesc = new TypeDesc((Class)AnyType2AnyTypeMapEntry.class, true)).setXmlType(new QName("webservices.ofs.weaver.com.cn", ">anyType2anyTypeMap>entry"));
        ElementDesc elemField = new ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new QName("webservices.ofs.weaver.com.cn", "key"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        AnyType2AnyTypeMapEntry.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new QName("webservices.ofs.weaver.com.cn", "value"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        AnyType2AnyTypeMapEntry.typeDesc.addFieldDesc((FieldDesc)elemField);
    }
    
    public AnyType2AnyTypeMapEntry() {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
    }
    
    public AnyType2AnyTypeMapEntry(final Object key, final Object value) {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
        this.key = key;
        this.value = value;
    }
    
    public Object getKey() {
        return this.key;
    }
    
    public void setKey(final Object key) {
        this.key = key;
    }
    
    public Object getValue() {
        return this.value;
    }
    
    public void setValue(final Object value) {
        this.value = value;
    }
    
    @Override
    public synchronized boolean equals(final Object obj) {
        if (!(obj instanceof AnyType2AnyTypeMapEntry)) {
            return false;
        }
        final AnyType2AnyTypeMapEntry other = (AnyType2AnyTypeMapEntry)obj;
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
        final boolean _equals = ((this.key == null && other.getKey() == null) || (this.key != null && this.key.equals(other.getKey()))) && ((this.value == null && other.getValue() == null) || (this.value != null && this.value.equals(other.getValue())));
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
        if (this.getKey() != null) {
            _hashCode += this.getKey().hashCode();
        }
        if (this.getValue() != null) {
            _hashCode += this.getValue().hashCode();
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }
    
    public static TypeDesc getTypeDesc() {
        return AnyType2AnyTypeMapEntry.typeDesc;
    }
    
    public static Serializer getSerializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Serializer)new BeanSerializer(_javaType, _xmlType, AnyType2AnyTypeMapEntry.typeDesc);
    }
    
    public static Deserializer getDeserializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Deserializer)new BeanDeserializer(_javaType, _xmlType, AnyType2AnyTypeMapEntry.typeDesc);
    }
}
