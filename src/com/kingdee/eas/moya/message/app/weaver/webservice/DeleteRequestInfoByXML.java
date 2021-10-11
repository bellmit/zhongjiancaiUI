package com.kingdee.eas.moya.message.app.weaver.webservice;

import java.io.*;
import javax.xml.namespace.*;
import org.apache.axis.description.*;
import org.apache.axis.encoding.*;
import org.apache.axis.encoding.ser.*;

public class DeleteRequestInfoByXML implements Serializable
{
    private String in0;
    private Object __equalsCalc;
    private boolean __hashCodeCalc;
    private static TypeDesc typeDesc;
    
    static {
        (DeleteRequestInfoByXML.typeDesc = new TypeDesc((Class)DeleteRequestInfoByXML.class, true)).setXmlType(new QName("webservices.ofs.weaver.com.cn", ">deleteRequestInfoByXML"));
        final ElementDesc elemField = new ElementDesc();
        elemField.setFieldName("in0");
        elemField.setXmlName(new QName("webservices.ofs.weaver.com.cn", "in0"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        DeleteRequestInfoByXML.typeDesc.addFieldDesc((FieldDesc)elemField);
    }
    
    public DeleteRequestInfoByXML() {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
    }
    
    public DeleteRequestInfoByXML(final String in0) {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
        this.in0 = in0;
    }
    
    public String getIn0() {
        return this.in0;
    }
    
    public void setIn0(final String in0) {
        this.in0 = in0;
    }
    
    @Override
    public synchronized boolean equals(final Object obj) {
        if (!(obj instanceof DeleteRequestInfoByXML)) {
            return false;
        }
        final DeleteRequestInfoByXML other = (DeleteRequestInfoByXML)obj;
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
        final boolean _equals = (this.in0 == null && other.getIn0() == null) || (this.in0 != null && this.in0.equals(other.getIn0()));
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
            _hashCode += this.getIn0().hashCode();
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }
    
    public static TypeDesc getTypeDesc() {
        return DeleteRequestInfoByXML.typeDesc;
    }
    
    public static Serializer getSerializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Serializer)new BeanSerializer(_javaType, _xmlType, DeleteRequestInfoByXML.typeDesc);
    }
    
    public static Deserializer getDeserializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Deserializer)new BeanDeserializer(_javaType, _xmlType, DeleteRequestInfoByXML.typeDesc);
    }
}
