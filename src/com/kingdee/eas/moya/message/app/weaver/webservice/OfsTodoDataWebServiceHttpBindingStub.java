package com.kingdee.eas.moya.message.app.weaver.webservice;


import java.net.URL;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.xml.namespace.QName;
import org.apache.axis.AxisFault;
import org.apache.axis.NoEndPointException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;
import org.apache.axis.constants.Style;
import org.apache.axis.constants.Use;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.DeserializerFactory;
import org.apache.axis.encoding.ser.ArrayDeserializerFactory;
import org.apache.axis.encoding.ser.ArraySerializerFactory;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.encoding.ser.EnumDeserializerFactory;
import org.apache.axis.encoding.ser.EnumSerializerFactory;
import org.apache.axis.encoding.ser.SimpleDeserializerFactory;
import org.apache.axis.encoding.ser.SimpleListDeserializerFactory;
import org.apache.axis.encoding.ser.SimpleListSerializerFactory;
import org.apache.axis.encoding.ser.SimpleSerializerFactory;
import org.apache.axis.soap.SOAPConstants;
import org.apache.axis.utils.JavaUtils;

public class OfsTodoDataWebServiceHttpBindingStub extends Stub implements
		OfsTodoDataWebServicePortType {
	private Vector cachedSerClasses;
	private Vector cachedSerQNames;
	private Vector cachedSerFactories;
	private Vector cachedDeserFactories;
	static OperationDesc[] _operations = new OperationDesc[18];

	static {
		_initOperationDesc1();
		_initOperationDesc2();
	}

	private static void _initOperationDesc1() {
    OperationDesc oper = new OperationDesc();
    oper.setName("receiveRequestInfoByMap");
    ParameterDesc param = new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte) 1, new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"), com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class, false, false);
    param.setItemQName(new QName("webservices.ofs.weaver.com.cn", "entry"));
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"));
    oper.setReturnClass(com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class);
    oper.setReturnQName(new QName("webservices.ofs.weaver.com.cn", "out"));
    param = oper.getReturnParamDesc();
    param.setItemQName(new QName("webservices.ofs.weaver.com.cn", "entry"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[0] = oper;
    oper = new OperationDesc();
    oper.setName("processOverRequestByMap");
    param = new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"), com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class, false, false);
    param.setItemQName(new QName("webservices.ofs.weaver.com.cn", "entry"));
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"));
    oper.setReturnClass(com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class);
    oper.setReturnQName(new QName("webservices.ofs.weaver.com.cn", "out"));
    param = oper.getReturnParamDesc();
    param.setItemQName(new QName("webservices.ofs.weaver.com.cn", "entry"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[1] = oper;
    oper = new OperationDesc();
    oper.setName("receiveRequestInfoByXml");
    param = new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
    oper.setReturnClass(String.class);
    oper.setReturnQName(new QName("webservices.ofs.weaver.com.cn", "out"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[2] = oper;
    oper = new OperationDesc();
    oper.setName("processDoneRequestByJson");
    param = new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte) 1, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
    oper.setReturnClass(String.class);
    oper.setReturnQName(new QName("webservices.ofs.weaver.com.cn", "out"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[3] = oper;
    oper = new OperationDesc();
    oper.setName("deleteUserRequestInfoByMap");
    param = new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"), com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class, false, false);
    param.setItemQName(new QName("webservices.ofs.weaver.com.cn", "entry"));
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"));
    oper.setReturnClass(com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class);
    oper.setReturnQName(new QName("webservices.ofs.weaver.com.cn", "out"));
    param = oper.getReturnParamDesc();
    param.setItemQName(new QName("webservices.ofs.weaver.com.cn", "entry"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[4] = oper;
    oper = new OperationDesc();
    oper.setName("deleteUserRequestInfoByXML");
    param = new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
    oper.setReturnClass(String.class);
    oper.setReturnQName(new QName("webservices.ofs.weaver.com.cn", "out"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[5] = oper;
    oper = new OperationDesc();
    oper.setName("processDoneRequestByXml");
    param = new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
    oper.setReturnClass(String.class);
    oper.setReturnQName(new QName("webservices.ofs.weaver.com.cn", "out"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[6] = oper;
    oper = new OperationDesc();
    oper.setName("deleteRequestInfoByMap");
    param = new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"), com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class, false, false);
    param.setItemQName(new QName("webservices.ofs.weaver.com.cn", "entry"));
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"));
    oper.setReturnClass(com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class);
    oper.setReturnQName(new QName("webservices.ofs.weaver.com.cn", "out"));
    param = oper.getReturnParamDesc();
    param.setItemQName(new QName("webservices.ofs.weaver.com.cn", "entry"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[7] = oper;
    oper = new OperationDesc();
    oper.setName("processOverRequestByXml");
    param = new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
    oper.setReturnClass(String.class);
    oper.setReturnQName(new QName("webservices.ofs.weaver.com.cn", "out"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[8] = oper;
    oper = new OperationDesc();
    oper.setName("receiveRequestInfoByJson");
    param = new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
    oper.setReturnClass(String.class);
    oper.setReturnQName(new QName("webservices.ofs.weaver.com.cn", "out"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[9] = oper;
  }

	private static void _initOperationDesc2() {
    OperationDesc oper = new OperationDesc();
    oper.setName("receiveTodoRequestByJson");
    ParameterDesc param = new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
    oper.setReturnClass(String.class);
    oper.setReturnQName(new QName("webservices.ofs.weaver.com.cn", "out"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[10] = oper;
    oper = new OperationDesc();
    oper.setName("deleteRequestInfoByXML");
    param = new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
    oper.setReturnClass(String.class);
    oper.setReturnQName(new QName("webservices.ofs.weaver.com.cn", "out"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[11] = oper;
    oper = new OperationDesc();
    oper.setName("receiveTodoRequestByXml");
    param = new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
    oper.setReturnClass(String.class);
    oper.setReturnQName(new QName("webservices.ofs.weaver.com.cn", "out"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[12] = oper;
    oper = new OperationDesc();
    oper.setName("deleteUserRequestInfoByJson");
    param = new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
    oper.setReturnClass(String.class);
    oper.setReturnQName(new QName("webservices.ofs.weaver.com.cn", "out"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[13] = oper;
    oper = new OperationDesc();
    oper.setName("processDoneRequestByMap");
    param = new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"), com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class, false, false);
    param.setItemQName(new QName("webservices.ofs.weaver.com.cn", "entry"));
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"));
    oper.setReturnClass(com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class);
    oper.setReturnQName(new QName("webservices.ofs.weaver.com.cn", "out"));
    param = oper.getReturnParamDesc();
    param.setItemQName(new QName("webservices.ofs.weaver.com.cn", "entry"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[14] = oper;
    oper = new OperationDesc();
    oper.setName("deleteRequestInfoByJson");
    param = new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
    oper.setReturnClass(String.class);
    oper.setReturnQName(new QName("webservices.ofs.weaver.com.cn", "out"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[15] = oper;
    oper = new OperationDesc();
    oper.setName("processOverRequestByJson");
    param = new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
    oper.setReturnClass(String.class);
    oper.setReturnQName(new QName("webservices.ofs.weaver.com.cn", "out"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[16] = oper;
    oper = new OperationDesc();
    oper.setName("receiveTodoRequestByMap");
    param = new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"), com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class, false, false);
    param.setItemQName(new QName("webservices.ofs.weaver.com.cn", "entry"));
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"));
    oper.setReturnClass(com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class);
    oper.setReturnQName(new QName("webservices.ofs.weaver.com.cn", "out"));
    param = oper.getReturnParamDesc();
    param.setItemQName(new QName("webservices.ofs.weaver.com.cn", "entry"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[17] = oper;
  }

	public OfsTodoDataWebServiceHttpBindingStub() throws AxisFault {
		this(null);
	}

	public OfsTodoDataWebServiceHttpBindingStub(URL endpointURL,
			javax.xml.rpc.Service service) throws AxisFault {
		this(service);
		this.cachedEndpoint = endpointURL;
	}

	public OfsTodoDataWebServiceHttpBindingStub(javax.xml.rpc.Service service) throws AxisFault {
    this.cachedSerClasses = new Vector();
    this.cachedSerQNames = new Vector();
    this.cachedSerFactories = new Vector();
    this.cachedDeserFactories = new Vector();
    if (service == null) {
      this.service = new org.apache.axis.client.Service();
    }
    else {
      this.service = service;
    }
    ((org.apache.axis.client.Service)this.service).setTypeMappingVersion("1.2");
    Class beansf = BeanSerializerFactory.class;
    Class beandf = BeanDeserializerFactory.class;
    Class enumsf = EnumSerializerFactory.class;
    Class enumdf = EnumDeserializerFactory.class;
    Class arraysf = ArraySerializerFactory.class;
    Class arraydf = ArrayDeserializerFactory.class;
    Class simplesf = SimpleSerializerFactory.class;
    Class simpledf = SimpleDeserializerFactory.class;
    Class simplelistsf = SimpleListSerializerFactory.class;
    Class simplelistdf = SimpleListDeserializerFactory.class;
    QName qName = new QName("webservices.ofs.weaver.com.cn", ">anyType2anyTypeMap>entry");
    this.cachedSerQNames.add(qName);
    Class cls = AnyType2AnyTypeMapEntry.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">deleteRequestInfoByJson");
    this.cachedSerQNames.add(qName);
    cls = DeleteRequestInfoByJson.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">deleteRequestInfoByJsonResponse");
    this.cachedSerQNames.add(qName);
    cls = DeleteRequestInfoByJsonResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">deleteRequestInfoByMap");
    this.cachedSerQNames.add(qName);
    cls = DeleteRequestInfoByMap.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">deleteRequestInfoByMapResponse");
    this.cachedSerQNames.add(qName);
    cls = DeleteRequestInfoByMapResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">deleteRequestInfoByXML");
    this.cachedSerQNames.add(qName);
    cls = DeleteRequestInfoByXML.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">deleteRequestInfoByXMLResponse");
    this.cachedSerQNames.add(qName);
    cls = DeleteRequestInfoByXMLResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">deleteUserRequestInfoByJson");
    this.cachedSerQNames.add(qName);
    cls = DeleteUserRequestInfoByJson.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">deleteUserRequestInfoByJsonResponse");
    this.cachedSerQNames.add(qName);
    cls = DeleteUserRequestInfoByJsonResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">deleteUserRequestInfoByMap");
    this.cachedSerQNames.add(qName);
    cls = DeleteUserRequestInfoByMap.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">deleteUserRequestInfoByMapResponse");
    this.cachedSerQNames.add(qName);
    cls = DeleteUserRequestInfoByMapResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">deleteUserRequestInfoByXML");
    this.cachedSerQNames.add(qName);
    cls = DeleteUserRequestInfoByXML.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">deleteUserRequestInfoByXMLResponse");
    this.cachedSerQNames.add(qName);
    cls = DeleteUserRequestInfoByXMLResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">processDoneRequestByJson");
    this.cachedSerQNames.add(qName);
    cls = ProcessDoneRequestByJson.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">processDoneRequestByJsonResponse");
    this.cachedSerQNames.add(qName);
    cls = ProcessDoneRequestByJsonResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">processDoneRequestByMap");
    this.cachedSerQNames.add(qName);
    cls = ProcessDoneRequestByMap.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">processDoneRequestByMapResponse");
    this.cachedSerQNames.add(qName);
    cls = ProcessDoneRequestByMapResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">processDoneRequestByXml");
    this.cachedSerQNames.add(qName);
    cls = ProcessDoneRequestByXml.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">processDoneRequestByXmlResponse");
    this.cachedSerQNames.add(qName);
    cls = ProcessDoneRequestByXmlResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">processOverRequestByJson");
    this.cachedSerQNames.add(qName);
    cls = ProcessOverRequestByJson.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">processOverRequestByJsonResponse");
    this.cachedSerQNames.add(qName);
    cls = ProcessOverRequestByJsonResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">processOverRequestByMap");
    this.cachedSerQNames.add(qName);
    cls = ProcessOverRequestByMap.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">processOverRequestByMapResponse");
    this.cachedSerQNames.add(qName);
    cls = ProcessOverRequestByMapResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">processOverRequestByXml");
    this.cachedSerQNames.add(qName);
    cls = ProcessOverRequestByXml.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">processOverRequestByXmlResponse");
    this.cachedSerQNames.add(qName);
    cls = ProcessOverRequestByXmlResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">receiveRequestInfoByJson");
    this.cachedSerQNames.add(qName);
    cls = ReceiveRequestInfoByJson.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">receiveRequestInfoByJsonResponse");
    this.cachedSerQNames.add(qName);
    cls = ReceiveRequestInfoByJsonResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">receiveRequestInfoByMap");
    this.cachedSerQNames.add(qName);
    cls = ReceiveRequestInfoByMap.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">receiveRequestInfoByMapResponse");
    this.cachedSerQNames.add(qName);
    cls = ReceiveRequestInfoByMapResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">receiveRequestInfoByXml");
    this.cachedSerQNames.add(qName);
    cls = ReceiveRequestInfoByXml.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">receiveRequestInfoByXmlResponse");
    this.cachedSerQNames.add(qName);
    cls = ReceiveRequestInfoByXmlResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">receiveTodoRequestByJson");
    this.cachedSerQNames.add(qName);
    cls = ReceiveTodoRequestByJson.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">receiveTodoRequestByJsonResponse");
    this.cachedSerQNames.add(qName);
    cls = ReceiveTodoRequestByJsonResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">receiveTodoRequestByMap");
    this.cachedSerQNames.add(qName);
    cls = ReceiveTodoRequestByMap.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">receiveTodoRequestByMapResponse");
    this.cachedSerQNames.add(qName);
    cls = ReceiveTodoRequestByMapResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">receiveTodoRequestByXml");
    this.cachedSerQNames.add(qName);
    cls = ReceiveTodoRequestByXml.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", ">receiveTodoRequestByXmlResponse");
    this.cachedSerQNames.add(qName);
    cls = ReceiveTodoRequestByXmlResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    qName = new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap");
    this.cachedSerQNames.add(qName);
    cls = com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class;
    this.cachedSerClasses.add(cls);
    qName = new QName("webservices.ofs.weaver.com.cn", ">anyType2anyTypeMap>entry");
    QName qName2 = new QName("webservices.ofs.weaver.com.cn", "entry");
    this.cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
    this.cachedDeserFactories.add(new ArrayDeserializerFactory());
  }

	protected Call createCall() throws RemoteException {
		try {
			Call _call = super._createCall();
			if (this.maintainSessionSet) {
				_call.setMaintainSession(this.maintainSession);
			}
			if (this.cachedUsername != null) {
				_call.setUsername(this.cachedUsername);
			}
			if (this.cachedPassword != null) {
				_call.setPassword(this.cachedPassword);
			}
			if (this.cachedEndpoint != null) {
				_call.setTargetEndpointAddress(this.cachedEndpoint);
			}
			if (this.cachedTimeout != null) {
				_call.setTimeout(this.cachedTimeout);
			}
			if (this.cachedPortName != null) {
				_call.setPortName(this.cachedPortName);
			}
			Enumeration keys = this.cachedProperties.keys();
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				_call.setProperty(key, this.cachedProperties.get(key));
			}
			synchronized (this) {
				if (firstCall()) {
					_call.setEncodingStyle(null);
					for (int i = 0; i < this.cachedSerFactories.size(); ++i) {
						Class cls = (Class) this.cachedSerClasses.get(i);
						QName qName = (QName) this.cachedSerQNames.get(i);
						Object x = this.cachedSerFactories.get(i);
						if (x instanceof Class) {
							Class sf = (Class) this.cachedSerFactories.get(i);
							Class df = (Class) this.cachedDeserFactories.get(i);
							_call
									.registerTypeMapping(cls, qName, sf, df,
											false);
						} else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
							org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory) this.cachedSerFactories
									.get(i);
							DeserializerFactory df = (DeserializerFactory) this.cachedDeserFactories
									.get(i);
							_call
									.registerTypeMapping(cls, qName, sf, df,
											false);
						}
					}
				}
			}
			return _call;
		} catch (Throwable _t) {
			throw new AxisFault("Failure trying to get the Call object", _t);
		}
	}

	public AnyType2AnyTypeMapEntry[] receiveRequestInfoByMap(AnyType2AnyTypeMapEntry[] in0) throws RemoteException {
    if (this.cachedEndpoint == null) {
      throw new NoEndPointException();
    }
    Call _call = createCall();
    _call.setOperation(_operations[0]);
    _call.setUseSOAPAction(true);
    _call.setSOAPActionURI("");
    _call.setEncodingStyle(null);
    _call.setProperty("sendXsiTypes", Boolean.FALSE);
    _call.setProperty("sendMultiRefs", Boolean.FALSE);
    _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
    _call.setOperationName(new QName("webservices.ofs.weaver.com.cn", "receiveRequestInfoByMap"));
    setRequestHeaders(_call);
    setAttachments(_call);
    try {
      Object _resp = _call.invoke(new Object[] { in0 });
      if (_resp instanceof RemoteException) {
        throw ((RemoteException)_resp);
      }
      extractAttachments(_call);
      try {
        return ((AnyType2AnyTypeMapEntry[])_resp);
      }
      catch (Exception _exception) {
        return ((AnyType2AnyTypeMapEntry[])JavaUtils.convert(_resp, com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class));
      }
    }
    catch (AxisFault axisFaultException) {
      throw axisFaultException;
    }
  }

	public AnyType2AnyTypeMapEntry[] processOverRequestByMap(AnyType2AnyTypeMapEntry[] in0) throws RemoteException {
    if (this.cachedEndpoint == null) {
      throw new NoEndPointException();
    }
    Call _call = createCall();
    _call.setOperation(_operations[1]);
    _call.setUseSOAPAction(true);
    _call.setSOAPActionURI("");
    _call.setEncodingStyle(null);
    _call.setProperty("sendXsiTypes", Boolean.FALSE);
    _call.setProperty("sendMultiRefs", Boolean.FALSE);
    _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
    _call.setOperationName(new QName("webservices.ofs.weaver.com.cn", "processOverRequestByMap"));
    setRequestHeaders(_call);
    setAttachments(_call);
    try {
      Object _resp = _call.invoke(new Object[] { in0 });
      if (_resp instanceof RemoteException) {
        throw ((RemoteException)_resp);
      }
      extractAttachments(_call);
      try {
        return ((AnyType2AnyTypeMapEntry[])_resp);
      }
      catch (Exception _exception) {
        return ((AnyType2AnyTypeMapEntry[])JavaUtils.convert(_resp, com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class));
      }
    }
    catch (AxisFault axisFaultException) {
      throw axisFaultException;
    }
  }

	public String receiveRequestInfoByXml(String in0) throws RemoteException {
		if (this.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[2]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty("sendXsiTypes", Boolean.FALSE);
		_call.setProperty("sendMultiRefs", Boolean.FALSE);
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("webservices.ofs.weaver.com.cn",
				"receiveRequestInfoByXml"));
		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { in0 });
			if (_resp instanceof RemoteException) {
				throw ((RemoteException) _resp);
			}
			extractAttachments(_call);
			try {
				return ((String) _resp);
			} catch (Exception _exception) {
				return ((String) JavaUtils.convert(_resp, String.class));
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String processDoneRequestByJson(String in0) throws RemoteException {
		if (this.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[3]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty("sendXsiTypes", Boolean.FALSE);
		_call.setProperty("sendMultiRefs", Boolean.FALSE);
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("webservices.ofs.weaver.com.cn",
				"processDoneRequestByJson"));
		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { in0 });
			if (_resp instanceof RemoteException) {
				throw ((RemoteException) _resp);
			}
			extractAttachments(_call);
			try {
				return ((String) _resp);
			} catch (Exception _exception) {
				return ((String) JavaUtils.convert(_resp, String.class));
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public AnyType2AnyTypeMapEntry[] deleteUserRequestInfoByMap(AnyType2AnyTypeMapEntry[] in0) throws RemoteException {
    if (this.cachedEndpoint == null) {
      throw new NoEndPointException();
    }
    Call _call = createCall();
    _call.setOperation(_operations[4]);
    _call.setUseSOAPAction(true);
    _call.setSOAPActionURI("");
    _call.setEncodingStyle(null);
    _call.setProperty("sendXsiTypes", Boolean.FALSE);
    _call.setProperty("sendMultiRefs", Boolean.FALSE);
    _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
    _call.setOperationName(new QName("webservices.ofs.weaver.com.cn", "deleteUserRequestInfoByMap"));
    setRequestHeaders(_call);
    setAttachments(_call);
    try {
      Object _resp = _call.invoke(new Object[] { in0 });
      if (_resp instanceof RemoteException) {
        throw ((RemoteException)_resp);
      }
      extractAttachments(_call);
      try {
        return ((AnyType2AnyTypeMapEntry[])_resp);
      }
      catch (Exception _exception) {
        return ((AnyType2AnyTypeMapEntry[])JavaUtils.convert(_resp, com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class));
      }
    }
    catch (AxisFault axisFaultException) {
      throw axisFaultException;
    }
  }

	public String deleteUserRequestInfoByXML(String in0) throws RemoteException {
		if (this.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[5]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty("sendXsiTypes", Boolean.FALSE);
		_call.setProperty("sendMultiRefs", Boolean.FALSE);
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("webservices.ofs.weaver.com.cn",
				"deleteUserRequestInfoByXML"));
		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { in0 });
			if (_resp instanceof RemoteException) {
				throw ((RemoteException) _resp);
			}
			extractAttachments(_call);
			try {
				return ((String) _resp);
			} catch (Exception _exception) {
				return ((String) JavaUtils.convert(_resp, String.class));
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String processDoneRequestByXml(String in0) throws RemoteException {
		if (this.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[6]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty("sendXsiTypes", Boolean.FALSE);
		_call.setProperty("sendMultiRefs", Boolean.FALSE);
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("webservices.ofs.weaver.com.cn",
				"processDoneRequestByXml"));
		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { in0 });
			if (_resp instanceof RemoteException) {
				throw ((RemoteException) _resp);
			}
			extractAttachments(_call);
			try {
				return ((String) _resp);
			} catch (Exception _exception) {
				return ((String) JavaUtils.convert(_resp, String.class));
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public AnyType2AnyTypeMapEntry[] deleteRequestInfoByMap(AnyType2AnyTypeMapEntry[] in0) throws RemoteException {
    if (this.cachedEndpoint == null) {
      throw new NoEndPointException();
    }
    Call _call = createCall();
    _call.setOperation(_operations[7]);
    _call.setUseSOAPAction(true);
    _call.setSOAPActionURI("");
    _call.setEncodingStyle(null);
    _call.setProperty("sendXsiTypes", Boolean.FALSE);
    _call.setProperty("sendMultiRefs", Boolean.FALSE);
    _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
    _call.setOperationName(new QName("webservices.ofs.weaver.com.cn", "deleteRequestInfoByMap"));
    setRequestHeaders(_call);
    setAttachments(_call);
    try {
      Object _resp = _call.invoke(new Object[] { in0 });
      if (_resp instanceof RemoteException) {
        throw ((RemoteException)_resp);
      }
      extractAttachments(_call);
      try {
        return ((AnyType2AnyTypeMapEntry[])_resp);
      }
      catch (Exception _exception) {
        return ((AnyType2AnyTypeMapEntry[])JavaUtils.convert(_resp, com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class));
      }
    }
    catch (AxisFault axisFaultException) {
      throw axisFaultException;
    }
  }

	public String processOverRequestByXml(String in0) throws RemoteException {
		if (this.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[8]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty("sendXsiTypes", Boolean.FALSE);
		_call.setProperty("sendMultiRefs", Boolean.FALSE);
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("webservices.ofs.weaver.com.cn",
				"processOverRequestByXml"));
		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { in0 });
			if (_resp instanceof RemoteException) {
				throw ((RemoteException) _resp);
			}
			extractAttachments(_call);
			try {
				return ((String) _resp);
			} catch (Exception _exception) {
				return ((String) JavaUtils.convert(_resp, String.class));
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String receiveRequestInfoByJson(String in0) throws RemoteException {
		if (this.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[9]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty("sendXsiTypes", Boolean.FALSE);
		_call.setProperty("sendMultiRefs", Boolean.FALSE);
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("webservices.ofs.weaver.com.cn",
				"receiveRequestInfoByJson"));
		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { in0 });
			if (_resp instanceof RemoteException) {
				throw ((RemoteException) _resp);
			}
			extractAttachments(_call);
			try {
				return ((String) _resp);
			} catch (Exception _exception) {
				return ((String) JavaUtils.convert(_resp, String.class));
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String receiveTodoRequestByJson(String in0) throws RemoteException {
		if (this.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[10]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty("sendXsiTypes", Boolean.FALSE);
		_call.setProperty("sendMultiRefs", Boolean.FALSE);
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("webservices.ofs.weaver.com.cn",
				"receiveTodoRequestByJson"));
		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { in0 });
			if (_resp instanceof RemoteException) {
				throw ((RemoteException) _resp);
			}
			extractAttachments(_call);
			try {
				return ((String) _resp);
			} catch (Exception _exception) {
				return ((String) JavaUtils.convert(_resp, String.class));
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String deleteRequestInfoByXML(String in0) throws RemoteException {
		if (this.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[11]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty("sendXsiTypes", Boolean.FALSE);
		_call.setProperty("sendMultiRefs", Boolean.FALSE);
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("webservices.ofs.weaver.com.cn",
				"deleteRequestInfoByXML"));
		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { in0 });
			if (_resp instanceof RemoteException) {
				throw ((RemoteException) _resp);
			}
			extractAttachments(_call);
			try {
				return ((String) _resp);
			} catch (Exception _exception) {
				return ((String) JavaUtils.convert(_resp, String.class));
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String receiveTodoRequestByXml(String in0) throws RemoteException {
		if (this.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[12]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty("sendXsiTypes", Boolean.FALSE);
		_call.setProperty("sendMultiRefs", Boolean.FALSE);
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("webservices.ofs.weaver.com.cn",
				"receiveTodoRequestByXml"));
		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { in0 });
			if (_resp instanceof RemoteException) {
				throw ((RemoteException) _resp);
			}
			extractAttachments(_call);
			try {
				return ((String) _resp);
			} catch (Exception _exception) {
				return ((String) JavaUtils.convert(_resp, String.class));
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String deleteUserRequestInfoByJson(String in0)
			throws RemoteException {
		if (this.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[13]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty("sendXsiTypes", Boolean.FALSE);
		_call.setProperty("sendMultiRefs", Boolean.FALSE);
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("webservices.ofs.weaver.com.cn",
				"deleteUserRequestInfoByJson"));
		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { in0 });
			if (_resp instanceof RemoteException) {
				throw ((RemoteException) _resp);
			}
			extractAttachments(_call);
			try {
				return ((String) _resp);
			} catch (Exception _exception) {
				return ((String) JavaUtils.convert(_resp, String.class));
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public AnyType2AnyTypeMapEntry[] processDoneRequestByMap(AnyType2AnyTypeMapEntry[] in0) throws RemoteException {
    if (this.cachedEndpoint == null) {
      throw new NoEndPointException();
    }
    Call _call = createCall();
    _call.setOperation(_operations[14]);
    _call.setUseSOAPAction(true);
    _call.setSOAPActionURI("");
    _call.setEncodingStyle(null);
    _call.setProperty("sendXsiTypes", Boolean.FALSE);
    _call.setProperty("sendMultiRefs", Boolean.FALSE);
    _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
    _call.setOperationName(new QName("webservices.ofs.weaver.com.cn", "processDoneRequestByMap"));
    setRequestHeaders(_call);
    setAttachments(_call);
    try {
      Object _resp = _call.invoke(new Object[] { in0 });
      if (_resp instanceof RemoteException) {
        throw ((RemoteException)_resp);
      }
      extractAttachments(_call);
      try {
        return ((AnyType2AnyTypeMapEntry[])_resp);
      }
      catch (Exception _exception) {
        return ((AnyType2AnyTypeMapEntry[])JavaUtils.convert(_resp, com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class));
      }
    }
    catch (AxisFault axisFaultException) {
      throw axisFaultException;
    }
  }

	public String deleteRequestInfoByJson(String in0) throws RemoteException {
		if (this.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[15]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty("sendXsiTypes", Boolean.FALSE);
		_call.setProperty("sendMultiRefs", Boolean.FALSE);
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("webservices.ofs.weaver.com.cn",
				"deleteRequestInfoByJson"));
		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { in0 });
			if (_resp instanceof RemoteException) {
				throw ((RemoteException) _resp);
			}
			extractAttachments(_call);
			try {
				return ((String) _resp);
			} catch (Exception _exception) {
				return ((String) JavaUtils.convert(_resp, String.class));
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String processOverRequestByJson(String in0) throws RemoteException {
		if (this.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[16]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty("sendXsiTypes", Boolean.FALSE);
		_call.setProperty("sendMultiRefs", Boolean.FALSE);
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("webservices.ofs.weaver.com.cn",
				"processOverRequestByJson"));
		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { in0 });
			if (_resp instanceof RemoteException) {
				throw ((RemoteException) _resp);
			}
			extractAttachments(_call);
			try {
				return ((String) _resp);
			} catch (Exception _exception) {
				return ((String) JavaUtils.convert(_resp, String.class));
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public AnyType2AnyTypeMapEntry[] receiveTodoRequestByMap(AnyType2AnyTypeMapEntry[] in0) throws RemoteException {
    if (this.cachedEndpoint == null) {
      throw new NoEndPointException();
    }
    Call _call = createCall();
    _call.setOperation(_operations[17]);
    _call.setUseSOAPAction(true);
    _call.setSOAPActionURI("");
    _call.setEncodingStyle(null);
    _call.setProperty("sendXsiTypes", Boolean.FALSE);
    _call.setProperty("sendMultiRefs", Boolean.FALSE);
    _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
    _call.setOperationName(new QName("webservices.ofs.weaver.com.cn", "receiveTodoRequestByMap"));
    setRequestHeaders(_call);
    setAttachments(_call);
    try {
      Object _resp = _call.invoke(new Object[] { in0 });
      if (_resp instanceof RemoteException) {
        throw ((RemoteException)_resp);
      }
      extractAttachments(_call);
      try {
        return ((AnyType2AnyTypeMapEntry[])_resp);
      }
      catch (Exception _exception) {
        return ((AnyType2AnyTypeMapEntry[])JavaUtils.convert(_resp, com.kingdee.eas.moya.message.app.weaver.webservice.AnyType2AnyTypeMapEntry.class));
      }
    }
    catch (AxisFault axisFaultException) {
      throw axisFaultException;
    }
  }
}