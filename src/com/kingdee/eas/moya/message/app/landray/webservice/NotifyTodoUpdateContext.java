package com.kingdee.eas.moya.message.app.landray.webservice;

import java.io.*;
import javax.xml.namespace.*;
import org.apache.axis.description.*;
import org.apache.axis.encoding.*;
import org.apache.axis.encoding.ser.*;

public class NotifyTodoUpdateContext implements Serializable
{
    private String appName;
    private String createTime;
    private String docCreator;
    private String extendContent;
    private String key;
    private Integer level;
    private String link;
    private String modelId;
    private String modelName;
    private String others;
    private String param1;
    private String param2;
    private String subject;
    private int type;
    private Object __equalsCalc;
    private boolean __hashCodeCalc;
    private static TypeDesc typeDesc;
    
    static {
        (NotifyTodoUpdateContext.typeDesc = new TypeDesc((Class)NotifyTodoUpdateContext.class, true)).setXmlType(new QName("http://webservice.notify.sys.kmss.landray.com/", "notifyTodoUpdateContext"));
        ElementDesc elemField = new ElementDesc();
        elemField.setFieldName("appName");
        elemField.setXmlName(new QName("", "appName"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoUpdateContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("createTime");
        elemField.setXmlName(new QName("", "createTime"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoUpdateContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("docCreator");
        elemField.setXmlName(new QName("", "docCreator"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoUpdateContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("extendContent");
        elemField.setXmlName(new QName("", "extendContent"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoUpdateContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new QName("", "key"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoUpdateContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("level");
        elemField.setXmlName(new QName("", "level"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoUpdateContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("link");
        elemField.setXmlName(new QName("", "link"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoUpdateContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("modelId");
        elemField.setXmlName(new QName("", "modelId"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoUpdateContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("modelName");
        elemField.setXmlName(new QName("", "modelName"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoUpdateContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("others");
        elemField.setXmlName(new QName("", "others"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoUpdateContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("param1");
        elemField.setXmlName(new QName("", "param1"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoUpdateContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("param2");
        elemField.setXmlName(new QName("", "param2"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoUpdateContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("subject");
        elemField.setXmlName(new QName("", "subject"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        NotifyTodoUpdateContext.typeDesc.addFieldDesc((FieldDesc)elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new QName("", "type"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        NotifyTodoUpdateContext.typeDesc.addFieldDesc((FieldDesc)elemField);
    }
    
    public NotifyTodoUpdateContext() {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
    }
    
    public NotifyTodoUpdateContext(final String appName, final String createTime, final String docCreator, final String extendContent, final String key, final Integer level, final String link, final String modelId, final String modelName, final String others, final String param1, final String param2, final String subject, final int type) {
        this.__equalsCalc = null;
        this.__hashCodeCalc = false;
        this.appName = appName;
        this.createTime = createTime;
        this.docCreator = docCreator;
        this.extendContent = extendContent;
        this.key = key;
        this.level = level;
        this.link = link;
        this.modelId = modelId;
        this.modelName = modelName;
        this.others = others;
        this.param1 = param1;
        this.param2 = param2;
        this.subject = subject;
        this.type = type;
    }
    
    public String getAppName() {
        return this.appName;
    }
    
    public void setAppName(final String appName) {
        this.appName = appName;
    }
    
    public String getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(final String createTime) {
        this.createTime = createTime;
    }
    
    public String getDocCreator() {
        return this.docCreator;
    }
    
    public void setDocCreator(final String docCreator) {
        this.docCreator = docCreator;
    }
    
    public String getExtendContent() {
        return this.extendContent;
    }
    
    public void setExtendContent(final String extendContent) {
        this.extendContent = extendContent;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public void setKey(final String key) {
        this.key = key;
    }
    
    public Integer getLevel() {
        return this.level;
    }
    
    public void setLevel(final Integer level) {
        this.level = level;
    }
    
    public String getLink() {
        return this.link;
    }
    
    public void setLink(final String link) {
        this.link = link;
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
    
    public String getOthers() {
        return this.others;
    }
    
    public void setOthers(final String others) {
        this.others = others;
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
    
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(final String subject) {
        this.subject = subject;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(final int type) {
        this.type = type;
    }
    
    @Override
    public synchronized boolean equals(final Object obj) {
        if (!(obj instanceof NotifyTodoUpdateContext)) {
            return false;
        }
        final NotifyTodoUpdateContext other = (NotifyTodoUpdateContext)obj;
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
        final boolean _equals = ((this.appName == null && other.getAppName() == null) || (this.appName != null && this.appName.equals(other.getAppName()))) && ((this.createTime == null && other.getCreateTime() == null) || (this.createTime != null && this.createTime.equals(other.getCreateTime()))) && ((this.docCreator == null && other.getDocCreator() == null) || (this.docCreator != null && this.docCreator.equals(other.getDocCreator()))) && ((this.extendContent == null && other.getExtendContent() == null) || (this.extendContent != null && this.extendContent.equals(other.getExtendContent()))) && ((this.key == null && other.getKey() == null) || (this.key != null && this.key.equals(other.getKey()))) && ((this.level == null && other.getLevel() == null) || (this.level != null && this.level.equals(other.getLevel()))) && ((this.link == null && other.getLink() == null) || (this.link != null && this.link.equals(other.getLink()))) && ((this.modelId == null && other.getModelId() == null) || (this.modelId != null && this.modelId.equals(other.getModelId()))) && ((this.modelName == null && other.getModelName() == null) || (this.modelName != null && this.modelName.equals(other.getModelName()))) && ((this.others == null && other.getOthers() == null) || (this.others != null && this.others.equals(other.getOthers()))) && ((this.param1 == null && other.getParam1() == null) || (this.param1 != null && this.param1.equals(other.getParam1()))) && ((this.param2 == null && other.getParam2() == null) || (this.param2 != null && this.param2.equals(other.getParam2()))) && ((this.subject == null && other.getSubject() == null) || (this.subject != null && this.subject.equals(other.getSubject()))) && this.type == other.getType();
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
        if (this.getCreateTime() != null) {
            _hashCode += this.getCreateTime().hashCode();
        }
        if (this.getDocCreator() != null) {
            _hashCode += this.getDocCreator().hashCode();
        }
        if (this.getExtendContent() != null) {
            _hashCode += this.getExtendContent().hashCode();
        }
        if (this.getKey() != null) {
            _hashCode += this.getKey().hashCode();
        }
        if (this.getLevel() != null) {
            _hashCode += this.getLevel().hashCode();
        }
        if (this.getLink() != null) {
            _hashCode += this.getLink().hashCode();
        }
        if (this.getModelId() != null) {
            _hashCode += this.getModelId().hashCode();
        }
        if (this.getModelName() != null) {
            _hashCode += this.getModelName().hashCode();
        }
        if (this.getOthers() != null) {
            _hashCode += this.getOthers().hashCode();
        }
        if (this.getParam1() != null) {
            _hashCode += this.getParam1().hashCode();
        }
        if (this.getParam2() != null) {
            _hashCode += this.getParam2().hashCode();
        }
        if (this.getSubject() != null) {
            _hashCode += this.getSubject().hashCode();
        }
        _hashCode += this.getType();
        this.__hashCodeCalc = false;
        return _hashCode;
    }
    
    public static TypeDesc getTypeDesc() {
        return NotifyTodoUpdateContext.typeDesc;
    }
    
    public static Serializer getSerializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Serializer)new BeanSerializer(_javaType, _xmlType, NotifyTodoUpdateContext.typeDesc);
    }
    
    public static Deserializer getDeserializer(final String mechType, final Class _javaType, final QName _xmlType) {
        return (Deserializer)new BeanDeserializer(_javaType, _xmlType, NotifyTodoUpdateContext.typeDesc);
    }
}
