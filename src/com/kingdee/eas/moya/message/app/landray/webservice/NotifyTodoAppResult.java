package com.kingdee.eas.moya.message.app.landray.webservice;

import java.io.*;
import javax.xml.namespace.*;
import org.apache.axis.description.*;
import org.apache.axis.encoding.*;
import org.apache.axis.encoding.ser.*;

public class NotifyTodoAppResult implements Serializable
{
    private String message;
    private int returnState;
    private Object __equalsCalc;
    private boolean __hashCodeCalc;
    private static TypeDesc typeDesc;
    
    static {
        (NotifyTodoAppResult.typeDesc = new TypeDesc((Class)NotifyTodoAppResult.class, true)).setXmlType(new QName("http://webservice.notify.sys.kmss.landray.com/", "notifyTodoAppResult"));
        ElementDesc elemField = new ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new QName("", "message"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoAppResult.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("returnState");
        elemField.setXmlName(new QName("", "returnState"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        NotifyTodoAppResult.typeDesc.addFieldDesc((FieldDesc)elemField);
    }
    
    public NotifyTodoAppResult() {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
    }
    
    public NotifyTodoAppResult(final String message, final int returnState) {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
        this.message = message;
        this.returnState = returnState;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public int getReturnState() {
        return this.returnState;
    }
    
    public void setReturnState(final int returnState) {
        this.returnState = returnState;
    }
    
    @Override
    public synchronized boolean equals(final Object obj) {
        if (!(obj instanceof NotifyTodoAppResult)) {
            return false;
        }
        final NotifyTodoAppResult other = (NotifyTodoAppResult)obj;
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
        final boolean _equals = ((this.message == null && other.getMessage() == null) || (this.message != null && this.message.equals(other.getMessage()))) && this.returnState == other.getReturnState();
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
        if (this.getMessage() != null) {
            _hashCode += this.getMessage().hashCode();
        }
        _hashCode += this.getReturnState();
        this.__hashCodeCalc = false;
        return _hashCode;
    }
    
    public static TypeDesc getTypeDesc() {
        return NotifyTodoAppResult.typeDesc;
    }
    
    public static Serializer getSerializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Serializer)new BeanSerializer(_javaType, _xmlType, NotifyTodoAppResult.typeDesc);
    }
    
    public static Deserializer getDeserializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Deserializer)new BeanDeserializer(_javaType, _xmlType, NotifyTodoAppResult.typeDesc);
    }
}
