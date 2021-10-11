package com.kingdee.eas.moya.message.app.weaver.webservice;

import org.apache.axis.wsdl.*;
import javax.xml.namespace.*;
import org.apache.axis.description.*;
import java.util.*;
import java.rmi.*;

public class OfsTodoDataWebServiceHttpBindingSkeleton implements OfsTodoDataWebServicePortType, Skeleton
{
    private OfsTodoDataWebServicePortType impl;
    private static Map _myOperations;
    private static Collection _myOperationsList;
    
    static {
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperations = new Hashtable();
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList = new ArrayList();
        ParameterDesc[] _params = { new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"), (Class)AnyType2AnyTypeMapEntry[].class, false, false) };
        OperationDesc _oper = new OperationDesc("receiveRequestInfoByMap", _params, new QName("webservices.ofs.weaver.com.cn", "out"));
        _oper.setReturnType(new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"));
        _oper.setElementQName(new QName("webservices.ofs.weaver.com.cn", "receiveRequestInfoByMap"));
        _oper.setSoapAction("");
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList.add(_oper);
        if (OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("receiveRequestInfoByMap") == null) {
            OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.put("receiveRequestInfoByMap", new ArrayList());
        }
        ((Collection) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("receiveRequestInfoByMap")).add(_oper);
        _params = new ParameterDesc[] { new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"), (Class)AnyType2AnyTypeMapEntry[].class, false, false) };
        _oper = new OperationDesc("processOverRequestByMap", _params, new QName("webservices.ofs.weaver.com.cn", "out"));
        _oper.setReturnType(new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"));
        _oper.setElementQName(new QName("webservices.ofs.weaver.com.cn", "processOverRequestByMap"));
        _oper.setSoapAction("");
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList.add(_oper);
        if (OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("processOverRequestByMap") == null) {
            OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.put("processOverRequestByMap", new ArrayList());
        }
        ((Collection) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("processOverRequestByMap")).add(_oper);
        _params = new ParameterDesc[] { new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), (Class)String.class, false, false) };
        _oper = new OperationDesc("receiveRequestInfoByXml", _params, new QName("webservices.ofs.weaver.com.cn", "out"));
        _oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new QName("webservices.ofs.weaver.com.cn", "receiveRequestInfoByXml"));
        _oper.setSoapAction("");
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList.add(_oper);
        if (OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("receiveRequestInfoByXml") == null) {
            OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.put("receiveRequestInfoByXml", new ArrayList());
        }
        ((Collection) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("receiveRequestInfoByXml")).add(_oper);
        _params = new ParameterDesc[] { new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), (Class)String.class, false, false) };
        _oper = new OperationDesc("processDoneRequestByJson", _params, new QName("webservices.ofs.weaver.com.cn", "out"));
        _oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new QName("webservices.ofs.weaver.com.cn", "processDoneRequestByJson"));
        _oper.setSoapAction("");
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList.add(_oper);
        if (OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("processDoneRequestByJson") == null) {
            OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.put("processDoneRequestByJson", new ArrayList());
        }
        ((Collection) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("processDoneRequestByJson")).add(_oper);
        _params = new ParameterDesc[] { new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"), (Class)AnyType2AnyTypeMapEntry[].class, false, false) };
        _oper = new OperationDesc("deleteUserRequestInfoByMap", _params, new QName("webservices.ofs.weaver.com.cn", "out"));
        _oper.setReturnType(new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"));
        _oper.setElementQName(new QName("webservices.ofs.weaver.com.cn", "deleteUserRequestInfoByMap"));
        _oper.setSoapAction("");
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList.add(_oper);
        if (OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("deleteUserRequestInfoByMap") == null) {
            OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.put("deleteUserRequestInfoByMap", new ArrayList());
        }
        ((Collection) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("deleteUserRequestInfoByMap")).add(_oper);
        _params = new ParameterDesc[] { new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), (Class)String.class, false, false) };
        _oper = new OperationDesc("deleteUserRequestInfoByXML", _params, new QName("webservices.ofs.weaver.com.cn", "out"));
        _oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new QName("webservices.ofs.weaver.com.cn", "deleteUserRequestInfoByXML"));
        _oper.setSoapAction("");
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList.add(_oper);
        if (OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("deleteUserRequestInfoByXML") == null) {
            OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.put("deleteUserRequestInfoByXML", new ArrayList());
        }
        ((Collection) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("deleteUserRequestInfoByXML")).add(_oper);
        _params = new ParameterDesc[] { new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), (Class)String.class, false, false) };
        _oper = new OperationDesc("processDoneRequestByXml", _params, new QName("webservices.ofs.weaver.com.cn", "out"));
        _oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new QName("webservices.ofs.weaver.com.cn", "processDoneRequestByXml"));
        _oper.setSoapAction("");
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList.add(_oper);
        if (OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("processDoneRequestByXml") == null) {
            OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.put("processDoneRequestByXml", new ArrayList());
        }
        ((Collection) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("processDoneRequestByXml")).add(_oper);
        _params = new ParameterDesc[] { new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"), (Class)AnyType2AnyTypeMapEntry[].class, false, false) };
        _oper = new OperationDesc("deleteRequestInfoByMap", _params, new QName("webservices.ofs.weaver.com.cn", "out"));
        _oper.setReturnType(new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"));
        _oper.setElementQName(new QName("webservices.ofs.weaver.com.cn", "deleteRequestInfoByMap"));
        _oper.setSoapAction("");
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList.add(_oper);
        if (OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("deleteRequestInfoByMap") == null) {
            OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.put("deleteRequestInfoByMap", new ArrayList());
        }
        ((Collection) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("deleteRequestInfoByMap")).add(_oper);
        _params = new ParameterDesc[] { new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), (Class)String.class, false, false) };
        _oper = new OperationDesc("processOverRequestByXml", _params, new QName("webservices.ofs.weaver.com.cn", "out"));
        _oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new QName("webservices.ofs.weaver.com.cn", "processOverRequestByXml"));
        _oper.setSoapAction("");
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList.add(_oper);
        if (OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("processOverRequestByXml") == null) {
            OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.put("processOverRequestByXml", new ArrayList());
        }
        ((Collection) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("processOverRequestByXml")).add(_oper);
        _params = new ParameterDesc[] { new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), (Class)String.class, false, false) };
        _oper = new OperationDesc("receiveRequestInfoByJson", _params, new QName("webservices.ofs.weaver.com.cn", "out"));
        _oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new QName("webservices.ofs.weaver.com.cn", "receiveRequestInfoByJson"));
        _oper.setSoapAction("");
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList.add(_oper);
        if (OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("receiveRequestInfoByJson") == null) {
            OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.put("receiveRequestInfoByJson", new ArrayList());
        }
        ((Collection) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("receiveRequestInfoByJson")).add(_oper);
        _params = new ParameterDesc[] { new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), (Class)String.class, false, false) };
        _oper = new OperationDesc("receiveTodoRequestByJson", _params, new QName("webservices.ofs.weaver.com.cn", "out"));
        _oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new QName("webservices.ofs.weaver.com.cn", "receiveTodoRequestByJson"));
        _oper.setSoapAction("");
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList.add(_oper);
        if (OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("receiveTodoRequestByJson") == null) {
            OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.put("receiveTodoRequestByJson", new ArrayList());
        }
        ((Collection) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("receiveTodoRequestByJson")).add(_oper);
        _params = new ParameterDesc[] { new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), (Class)String.class, false, false) };
        _oper = new OperationDesc("deleteRequestInfoByXML", _params, new QName("webservices.ofs.weaver.com.cn", "out"));
        _oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new QName("webservices.ofs.weaver.com.cn", "deleteRequestInfoByXML"));
        _oper.setSoapAction("");
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList.add(_oper);
        if (OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("deleteRequestInfoByXML") == null) {
            OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.put("deleteRequestInfoByXML", new ArrayList());
        }
        ((Collection) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("deleteRequestInfoByXML")).add(_oper);
        _params = new ParameterDesc[] { new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), (Class)String.class, false, false) };
        _oper = new OperationDesc("receiveTodoRequestByXml", _params, new QName("webservices.ofs.weaver.com.cn", "out"));
        _oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new QName("webservices.ofs.weaver.com.cn", "receiveTodoRequestByXml"));
        _oper.setSoapAction("");
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList.add(_oper);
        if (OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("receiveTodoRequestByXml") == null) {
            OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.put("receiveTodoRequestByXml", new ArrayList());
        }
        ((Collection) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("receiveTodoRequestByXml")).add(_oper);
        _params = new ParameterDesc[] { new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), (Class)String.class, false, false) };
        _oper = new OperationDesc("deleteUserRequestInfoByJson", _params, new QName("webservices.ofs.weaver.com.cn", "out"));
        _oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new QName("webservices.ofs.weaver.com.cn", "deleteUserRequestInfoByJson"));
        _oper.setSoapAction("");
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList.add(_oper);
        if (OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("deleteUserRequestInfoByJson") == null) {
            OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.put("deleteUserRequestInfoByJson", new ArrayList());
        }
        ((Collection) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("deleteUserRequestInfoByJson")).add(_oper);
        _params = new ParameterDesc[] { new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"), (Class)AnyType2AnyTypeMapEntry[].class, false, false) };
        _oper = new OperationDesc("processDoneRequestByMap", _params, new QName("webservices.ofs.weaver.com.cn", "out"));
        _oper.setReturnType(new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"));
        _oper.setElementQName(new QName("webservices.ofs.weaver.com.cn", "processDoneRequestByMap"));
        _oper.setSoapAction("");
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList.add(_oper);
        if (OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("processDoneRequestByMap") == null) {
            OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.put("processDoneRequestByMap", new ArrayList());
        }
        ((Collection) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("processDoneRequestByMap")).add(_oper);
        _params = new ParameterDesc[] { new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), (Class)String.class, false, false) };
        _oper = new OperationDesc("deleteRequestInfoByJson", _params, new QName("webservices.ofs.weaver.com.cn", "out"));
        _oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new QName("webservices.ofs.weaver.com.cn", "deleteRequestInfoByJson"));
        _oper.setSoapAction("");
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList.add(_oper);
        if (OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("deleteRequestInfoByJson") == null) {
            OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.put("deleteRequestInfoByJson", new ArrayList());
        }
        ((Collection) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("deleteRequestInfoByJson")).add(_oper);
        _params = new ParameterDesc[] { new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), (Class)String.class, false, false) };
        _oper = new OperationDesc("processOverRequestByJson", _params, new QName("webservices.ofs.weaver.com.cn", "out"));
        _oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new QName("webservices.ofs.weaver.com.cn", "processOverRequestByJson"));
        _oper.setSoapAction("");
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList.add(_oper);
        if (OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("processOverRequestByJson") == null) {
            OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.put("processOverRequestByJson", new ArrayList());
        }
        ((Collection) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("processOverRequestByJson")).add(_oper);
        _params = new ParameterDesc[] { new ParameterDesc(new QName("webservices.ofs.weaver.com.cn", "in0"), (byte)1, new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"), (Class)AnyType2AnyTypeMapEntry[].class, false, false) };
        _oper = new OperationDesc("receiveTodoRequestByMap", _params, new QName("webservices.ofs.weaver.com.cn", "out"));
        _oper.setReturnType(new QName("webservices.ofs.weaver.com.cn", "anyType2anyTypeMap"));
        _oper.setElementQName(new QName("webservices.ofs.weaver.com.cn", "receiveTodoRequestByMap"));
        _oper.setSoapAction("");
        OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList.add(_oper);
        if (OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("receiveTodoRequestByMap") == null) {
            OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.put("receiveTodoRequestByMap", new ArrayList());
        }
        ((Collection) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get("receiveTodoRequestByMap")).add(_oper);
    }
    
    public static List getOperationDescByName(final String methodName) {
        return (List) OfsTodoDataWebServiceHttpBindingSkeleton._myOperations.get(methodName);
    }
    
    public static Collection getOperationDescs() {
        return OfsTodoDataWebServiceHttpBindingSkeleton._myOperationsList;
    }
    
    public OfsTodoDataWebServiceHttpBindingSkeleton() {
        this.impl = new OfsTodoDataWebServiceHttpBindingImpl();
    }
    
    public OfsTodoDataWebServiceHttpBindingSkeleton(final OfsTodoDataWebServicePortType impl) {
        this.impl = impl;
    }
    
    @Override
    public AnyType2AnyTypeMapEntry[] receiveRequestInfoByMap(final AnyType2AnyTypeMapEntry[] in0) throws RemoteException {
        final AnyType2AnyTypeMapEntry[] ret = this.impl.receiveRequestInfoByMap(in0);
        return ret;
    }
    
    @Override
    public AnyType2AnyTypeMapEntry[] processOverRequestByMap(final AnyType2AnyTypeMapEntry[] in0) throws RemoteException {
        final AnyType2AnyTypeMapEntry[] ret = this.impl.processOverRequestByMap(in0);
        return ret;
    }
    
    @Override
    public String receiveRequestInfoByXml(final String in0) throws RemoteException {
        final String ret = this.impl.receiveRequestInfoByXml(in0);
        return ret;
    }
    
    @Override
    public String processDoneRequestByJson(final String in0) throws RemoteException {
        final String ret = this.impl.processDoneRequestByJson(in0);
        return ret;
    }
    
    @Override
    public AnyType2AnyTypeMapEntry[] deleteUserRequestInfoByMap(final AnyType2AnyTypeMapEntry[] in0) throws RemoteException {
        final AnyType2AnyTypeMapEntry[] ret = this.impl.deleteUserRequestInfoByMap(in0);
        return ret;
    }
    
    @Override
    public String deleteUserRequestInfoByXML(final String in0) throws RemoteException {
        final String ret = this.impl.deleteUserRequestInfoByXML(in0);
        return ret;
    }
    
    @Override
    public String processDoneRequestByXml(final String in0) throws RemoteException {
        final String ret = this.impl.processDoneRequestByXml(in0);
        return ret;
    }
    
    @Override
    public AnyType2AnyTypeMapEntry[] deleteRequestInfoByMap(final AnyType2AnyTypeMapEntry[] in0) throws RemoteException {
        final AnyType2AnyTypeMapEntry[] ret = this.impl.deleteRequestInfoByMap(in0);
        return ret;
    }
    
    @Override
    public String processOverRequestByXml(final String in0) throws RemoteException {
        final String ret = this.impl.processOverRequestByXml(in0);
        return ret;
    }
    
    @Override
    public String receiveRequestInfoByJson(final String in0) throws RemoteException {
        final String ret = this.impl.receiveRequestInfoByJson(in0);
        return ret;
    }
    
    @Override
    public String receiveTodoRequestByJson(final String in0) throws RemoteException {
        final String ret = this.impl.receiveTodoRequestByJson(in0);
        return ret;
    }
    
    @Override
    public String deleteRequestInfoByXML(final String in0) throws RemoteException {
        final String ret = this.impl.deleteRequestInfoByXML(in0);
        return ret;
    }
    
    @Override
    public String receiveTodoRequestByXml(final String in0) throws RemoteException {
        final String ret = this.impl.receiveTodoRequestByXml(in0);
        return ret;
    }
    
    @Override
    public String deleteUserRequestInfoByJson(final String in0) throws RemoteException {
        final String ret = this.impl.deleteUserRequestInfoByJson(in0);
        return ret;
    }
    
    @Override
    public AnyType2AnyTypeMapEntry[] processDoneRequestByMap(final AnyType2AnyTypeMapEntry[] in0) throws RemoteException {
        final AnyType2AnyTypeMapEntry[] ret = this.impl.processDoneRequestByMap(in0);
        return ret;
    }
    
    @Override
    public String deleteRequestInfoByJson(final String in0) throws RemoteException {
        final String ret = this.impl.deleteRequestInfoByJson(in0);
        return ret;
    }
    
    @Override
    public String processOverRequestByJson(final String in0) throws RemoteException {
        final String ret = this.impl.processOverRequestByJson(in0);
        return ret;
    }
    
    @Override
    public AnyType2AnyTypeMapEntry[] receiveTodoRequestByMap(final AnyType2AnyTypeMapEntry[] in0) throws RemoteException {
        final AnyType2AnyTypeMapEntry[] ret = this.impl.receiveTodoRequestByMap(in0);
        return ret;
    }
}
