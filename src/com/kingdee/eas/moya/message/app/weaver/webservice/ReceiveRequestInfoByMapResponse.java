package com.kingdee.eas.moya.message.app.weaver.webservice;

import java.io.*;
import javax.xml.namespace.*;
import org.apache.axis.description.*;
import java.util.*;
import java.lang.reflect.*;
import org.apache.axis.encoding.*;
import org.apache.axis.encoding.ser.*;

public class ReceiveRequestInfoByMapResponse implements Serializable
{
    private AnyType2AnyTypeMapEntry[] out;
    private Object __equalsCalc;
    private boolean __hashCodeCalc;
    private static TypeDesc typeDesc;
    
    static {
        (ReceiveRequestInfoByMapResponse.typeDesc = new TypeDesc((Class)ReceiveRequestInfoByMapResponse.class, true)).setXmlType(new QName("webservices.ofs.weaver.com.cn", ">receiveRequestInfoByMapResponse"));
        final ElementDesc elemField = new ElementDesc();
        elemField.setFieldName("out");
        elemField.setXmlName(new QName("webservices.ofs.weaver.com.cn", "out"));
        elemField.setXmlType(new QName("webservices.ofs.weaver.com.cn", ">anyType2anyTypeMap>entry"));
        elemField.setNillable(true);
        elemField.setItemQName(new QName("webservices.ofs.weaver.com.cn", "entry"));
        ReceiveRequestInfoByMapResponse.typeDesc.addFieldDesc((FieldDesc)elemField);
    }
    
    public ReceiveRequestInfoByMapResponse() {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
    }
    
    public ReceiveRequestInfoByMapResponse(final AnyType2AnyTypeMapEntry[] out) {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
        this.out = out;
    }
    
    public AnyType2AnyTypeMapEntry[] getOut() {
        return this.out;
    }
    
    public void setOut(final AnyType2AnyTypeMapEntry[] out) {
        this.out = out;
    }
    
    @Override
    public synchronized boolean equals(final Object obj) {
        if (!(obj instanceof ReceiveRequestInfoByMapResponse)) {
            return false;
        }
        final ReceiveRequestInfoByMapResponse other = (ReceiveRequestInfoByMapResponse)obj;
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
        final boolean _equals = (this.out == null && other.getOut() == null) || (this.out != null && Arrays.equals(this.out, other.getOut()));
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
        if (this.getOut() != null) {
            for (int i = 0; i < Array.getLength(this.getOut()); ++i) {
                final Object obj = Array.get(this.getOut(), i);
                if (obj != null && !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }
    
    public static TypeDesc getTypeDesc() {
        return ReceiveRequestInfoByMapResponse.typeDesc;
    }
    
    public static Serializer getSerializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Serializer)new BeanSerializer(_javaType, _xmlType, ReceiveRequestInfoByMapResponse.typeDesc);
    }
    
    public static Deserializer getDeserializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Deserializer)new BeanDeserializer(_javaType, _xmlType, ReceiveRequestInfoByMapResponse.typeDesc);
    }
}
