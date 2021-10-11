package com.kingdee.eas.moya.message.app.weaver.webservice;

import java.io.*;
import javax.xml.namespace.*;
import org.apache.axis.description.*;
import org.apache.axis.encoding.*;
import org.apache.axis.encoding.ser.*;

public class DeleteUserRequestInfoByJsonResponse implements Serializable
{
    private String out;
    private Object __equalsCalc;
    private boolean __hashCodeCalc;
    private static TypeDesc typeDesc;
    
    static {
        (DeleteUserRequestInfoByJsonResponse.typeDesc = new TypeDesc((Class)DeleteUserRequestInfoByJsonResponse.class, true)).setXmlType(new QName("webservices.ofs.weaver.com.cn", ">deleteUserRequestInfoByJsonResponse"));
        final ElementDesc elemField = new ElementDesc();
        elemField.setFieldName("out");
        elemField.setXmlName(new QName("webservices.ofs.weaver.com.cn", "out"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        DeleteUserRequestInfoByJsonResponse.typeDesc.addFieldDesc((FieldDesc)elemField);
    }
    
    public DeleteUserRequestInfoByJsonResponse() {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
    }
    
    public DeleteUserRequestInfoByJsonResponse(final String out) {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
        this.out = out;
    }
    
    public String getOut() {
        return this.out;
    }
    
    public void setOut(final String out) {
        this.out = out;
    }
    
    @Override
    public synchronized boolean equals(final Object obj) {
        if (!(obj instanceof DeleteUserRequestInfoByJsonResponse)) {
            return false;
        }
        final DeleteUserRequestInfoByJsonResponse other = (DeleteUserRequestInfoByJsonResponse)obj;
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
        final boolean _equals = (this.out == null && other.getOut() == null) || (this.out != null && this.out.equals(other.getOut()));
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
            _hashCode += this.getOut().hashCode();
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }
    
    public static TypeDesc getTypeDesc() {
        return DeleteUserRequestInfoByJsonResponse.typeDesc;
    }
    
    public static Serializer getSerializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Serializer)new BeanSerializer(_javaType, _xmlType, DeleteUserRequestInfoByJsonResponse.typeDesc);
    }
    
    public static Deserializer getDeserializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Deserializer)new BeanDeserializer(_javaType, _xmlType, DeleteUserRequestInfoByJsonResponse.typeDesc);
    }
}
