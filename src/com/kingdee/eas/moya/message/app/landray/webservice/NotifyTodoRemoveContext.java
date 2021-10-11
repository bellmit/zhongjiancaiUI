package com.kingdee.eas.moya.message.app.landray.webservice;

import java.io.*;
import javax.xml.namespace.*;
import org.apache.axis.description.*;
import org.apache.axis.encoding.*;
import org.apache.axis.encoding.ser.*;

public class NotifyTodoRemoveContext implements Serializable
{
    private String appName;
    private String key;
    private String modelId;
    private String modelName;
    private int optType;
    private String param1;
    private String param2;
    private String targets;
    private int type;
    private Object __equalsCalc;
    private boolean __hashCodeCalc;
    private static TypeDesc typeDesc;
    
    static {
        (NotifyTodoRemoveContext.typeDesc = new TypeDesc((Class)NotifyTodoRemoveContext.class, true)).setXmlType(new QName("http://webservice.notify.sys.kmss.landray.com/", "notifyTodoRemoveContext"));
        ElementDesc elemField = new ElementDesc();
        elemField.setFieldName("appName");
        elemField.setXmlName(new QName("", "appName"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoRemoveContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new QName("", "key"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoRemoveContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("modelId");
        elemField.setXmlName(new QName("", "modelId"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoRemoveContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("modelName");
        elemField.setXmlName(new QName("", "modelName"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoRemoveContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("optType");
        elemField.setXmlName(new QName("", "optType"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        NotifyTodoRemoveContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("param1");
        elemField.setXmlName(new QName("", "param1"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoRemoveContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("param2");
        elemField.setXmlName(new QName("", "param2"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoRemoveContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("targets");
        elemField.setXmlName(new QName("", "targets"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoRemoveContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new QName("", "type"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        NotifyTodoRemoveContext.typeDesc.addFieldDesc((FieldDesc)elemField);
    }
    
    public NotifyTodoRemoveContext() {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
    }
    
    public NotifyTodoRemoveContext(final String appName, final String key, final String modelId, final String modelName, final int optType, final String param1, final String param2, final String targets, final int type) {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
        this.appName = appName;
        this.key = key;
        this.modelId = modelId;
        this.modelName = modelName;
        this.optType = optType;
        this.param1 = param1;
        this.param2 = param2;
        this.targets = targets;
        this.type = type;
    }
    
    public String getAppName() {
        return this.appName;
    }
    
    public void setAppName(final String appName) {
        this.appName = appName;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public void setKey(final String key) {
        this.key = key;
    }
    
    public String getModelId() {
        return this.modelId;
    }
    
    public void setModelId(final String modelId) {
        this.modelId = modelId;
    }
    
    public String getModelName() {
        return this.modelName;
    }
    
    public void setModelName(final String modelName) {
        this.modelName = modelName;
    }
    
    public int getOptType() {
        return this.optType;
    }
    
    public void setOptType(final int optType) {
        this.optType = optType;
    }
    
    public String getParam1() {
        return this.param1;
    }
    
    public void setParam1(final String param1) {
        this.param1 = param1;
    }
    
    public String getParam2() {
        return this.param2;
    }
    
    public void setParam2(final String param2) {
        this.param2 = param2;
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
        if (!(obj instanceof NotifyTodoRemoveContext)) {
            return false;
        }
        final NotifyTodoRemoveContext other = (NotifyTodoRemoveContext)obj;
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
        final boolean _equals = ((this.appName == null && other.getAppName() == null) || (this.appName != null && this.appName.equals(other.getAppName()))) && ((this.key == null && other.getKey() == null) || (this.key != null && this.key.equals(other.getKey()))) && ((this.modelId == null && other.getModelId() == null) || (this.modelId != null && this.modelId.equals(other.getModelId()))) && ((this.modelName == null && other.getModelName() == null) || (this.modelName != null && this.modelName.equals(other.getModelName()))) && this.optType == other.getOptType() && ((this.param1 == null && other.getParam1() == null) || (this.param1 != null && this.param1.equals(other.getParam1()))) && ((this.param2 == null && other.getParam2() == null) || (this.param2 != null && this.param2.equals(other.getParam2()))) && ((this.targets == null && other.getTargets() == null) || (this.targets != null && this.targets.equals(other.getTargets()))) && this.type == other.getType();
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
        if (this.getAppName() != null) {
            _hashCode += this.getAppName().hashCode();
        }
        if (this.getKey() != null) {
            _hashCode += this.getKey().hashCode();
        }
        if (this.getModelId() != null) {
            _hashCode += this.getModelId().hashCode();
        }
        if (this.getModelName() != null) {
            _hashCode += this.getModelName().hashCode();
        }
        _hashCode += this.getOptType();
        if (this.getParam1() != null) {
            _hashCode += this.getParam1().hashCode();
        }
        if (this.getParam2() != null) {
            _hashCode += this.getParam2().hashCode();
        }
        if (this.getTargets() != null) {
            _hashCode += this.getTargets().hashCode();
        }
        _hashCode += this.getType();
        this.__hashCodeCalc = false;
        return _hashCode;
    }
    
    public static TypeDesc getTypeDesc() {
        return NotifyTodoRemoveContext.typeDesc;
    }
    
    public static Serializer getSerializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Serializer)new BeanSerializer(_javaType, _xmlType, NotifyTodoRemoveContext.typeDesc);
    }
    
    public static Deserializer getDeserializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Deserializer)new BeanDeserializer(_javaType, _xmlType, NotifyTodoRemoveContext.typeDesc);
    }
}
