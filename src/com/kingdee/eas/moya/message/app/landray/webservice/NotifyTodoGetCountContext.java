package com.kingdee.eas.moya.message.app.landray.webservice;

import java.io.*;
import javax.xml.namespace.*;
import org.apache.axis.description.*;
import org.apache.axis.encoding.*;
import org.apache.axis.encoding.ser.*;

public class NotifyTodoGetCountContext implements Serializable
{
    private String target;
    private String types;
    private Object __equalsCalc;
    private boolean __hashCodeCalc;
    private static TypeDesc typeDesc;
    
    static {
        (NotifyTodoGetCountContext.typeDesc = new TypeDesc((Class)NotifyTodoGetCountContext.class, true)).setXmlType(new QName("http://webservice.notify.sys.kmss.landray.com/", "notifyTodoGetCountContext"));
        ElementDesc elemField = new ElementDesc();
        elemField.setFieldName("target");
        elemField.setXmlName(new QName("", "target"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoGetCountContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("types");
        elemField.setXmlName(new QName("", "types"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoGetCountContext.typeDesc.addFieldDesc((FieldDesc)elemField);
    }
    
    public NotifyTodoGetCountContext() {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
    }
    
    public NotifyTodoGetCountContext(final String target, final String types) {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
        this.target = target;
        this.types = types;
    }
    
    public String getTarget() {
        return this.target;
    }
    
    public void setTarget(final String target) {
        this.target = target;
    }
    
    public String getTypes() {
        return this.types;
    }
    
    public void setTypes(final String types) {
        this.types = types;
    }
    
    @Override
    public synchronized boolean equals(final Object obj) {
        if (!(obj instanceof NotifyTodoGetCountContext)) {
            return false;
        }
        final NotifyTodoGetCountContext other = (NotifyTodoGetCountContext)obj;
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
        final boolean _equals = ((this.target == null && other.getTarget() == null) || (this.target != null && this.target.equals(other.getTarget()))) && ((this.types == null && other.getTypes() == null) || (this.types != null && this.types.equals(other.getTypes())));
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
        if (this.getTarget() != null) {
            _hashCode += this.getTarget().hashCode();
        }
        if (this.getTypes() != null) {
            _hashCode += this.getTypes().hashCode();
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }
    
    public static TypeDesc getTypeDesc() {
        return NotifyTodoGetCountContext.typeDesc;
    }
    
    public static Serializer getSerializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Serializer)new BeanSerializer(_javaType, _xmlType, NotifyTodoGetCountContext.typeDesc);
    }
    
    public static Deserializer getDeserializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Deserializer)new BeanDeserializer(_javaType, _xmlType, NotifyTodoGetCountContext.typeDesc);
    }
}
