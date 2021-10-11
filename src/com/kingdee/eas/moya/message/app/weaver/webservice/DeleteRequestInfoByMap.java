package com.kingdee.eas.moya.message.app.weaver.webservice;

import java.io.*;
import javax.xml.namespace.*;
import org.apache.axis.description.*;
import java.util.*;
import java.lang.reflect.*;
import org.apache.axis.encoding.*;
import org.apache.axis.encoding.ser.*;

public class DeleteRequestInfoByMap implements Serializable
{
    private AnyType2AnyTypeMapEntry[] in0;
    private Object __equalsCalc;
    private boolean __hashCodeCalc;
    private static TypeDesc typeDesc;
    
    static {
        (DeleteRequestInfoByMap.typeDesc = new TypeDesc((Class)DeleteRequestInfoByMap.class, true)).setXmlType(new QName("webservices.ofs.weaver.com.cn", ">deleteRequestInfoByMap"));
        final ElementDesc elemField = new ElementDesc();
        elemField.setFieldName("in0");
        elemField.setXmlName(new QName("webservices.ofs.weaver.com.cn", "in0"));
        elemField.setXmlType(new QName("webservices.ofs.weaver.com.cn", ">anyType2anyTypeMap>entry"));
        elemField.setNillable(true);
        elemField.setItemQName(new QName("webservices.ofs.weaver.com.cn", "entry"));
        DeleteRequestInfoByMap.typeDesc.addFieldDesc((FieldDesc)elemField);
    }
    
    public DeleteRequestInfoByMap() {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
    }
    
    public DeleteRequestInfoByMap(final AnyType2AnyTypeMapEntry[] in0) {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
        this.in0 = in0;
    }
    
    public AnyType2AnyTypeMapEntry[] getIn0() {
        return this.in0;
    }
    
    public void setIn0(final AnyType2AnyTypeMapEntry[] in0) {
        this.in0 = in0;
    }
    
    @Override
    public synchronized boolean equals(final Object obj) {
        if (!(obj instanceof DeleteRequestInfoByMap)) {
            return false;
        }
        final DeleteRequestInfoByMap other = (DeleteRequestInfoByMap)obj;
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
        final boolean _equals = (this.in0 == null && other.getIn0() == null) || (this.in0 != null && Arrays.equals(this.in0, other.getIn0()));
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
        if (this.getIn0() != null) {
            for (int i = 0; i < Array.getLength(this.getIn0()); ++i) {
                final Object obj = Array.get(this.getIn0(), i);
                if (obj != null && !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }
    
    public static TypeDesc getTypeDesc() {
        return DeleteRequestInfoByMap.typeDesc;
    }
    
    public static Serializer getSerializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Serializer)new BeanSerializer(_javaType, _xmlType, DeleteRequestInfoByMap.typeDesc);
    }
    
    public static Deserializer getDeserializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Deserializer)new BeanDeserializer(_javaType, _xmlType, DeleteRequestInfoByMap.typeDesc);
    }
}
