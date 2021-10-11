package com.kingdee.eas.moya.message.app.weaver.webservice;

//import com.moya.api.config.*;
import javax.xml.namespace.*;
import javax.xml.rpc.*;
import java.net.*;
import org.apache.axis.*;
import org.apache.axis.client.Service;
import java.rmi.*;
import org.apache.axis.client.*;
import org.apache.axis.client.Stub;

import java.util.*;

public class OfsTodoDataWebServiceLocator extends Service implements OfsTodoDataWebService
{
    private String oa_adress;
    private String OfsTodoDataWebServiceHttpPort_address;
    private String OfsTodoDataWebServiceHttpPortWSDDServiceName;
    private HashSet ports;
    
    public OfsTodoDataWebServiceLocator() {
        this.oa_adress = "";
        //this.OfsTodoDataWebServiceHttpPort_address = String.valueOf(ApiConfigParameters.getParameterByName("weaver.address")) + "/services/OfsTodoDataWebService";
        this.OfsTodoDataWebServiceHttpPort_address =  "/services/OfsTodoDataWebService";
        this.OfsTodoDataWebServiceHttpPortWSDDServiceName = "OfsTodoDataWebServiceHttpPort";
        this.ports = null;
    }
    
    public OfsTodoDataWebServiceLocator(final EngineConfiguration config) {
        super(config);
        this.oa_adress = "";
        //this.OfsTodoDataWebServiceHttpPort_address = String.valueOf(ApiConfigParameters.getParameterByName("weaver.address")) + "/services/OfsTodoDataWebService";
        this.OfsTodoDataWebServiceHttpPort_address =  "/services/OfsTodoDataWebService";
        this.OfsTodoDataWebServiceHttpPortWSDDServiceName = "OfsTodoDataWebServiceHttpPort";
        this.ports = null;
    }
    
    public OfsTodoDataWebServiceLocator(final String wsdlLoc, final QName sName) throws ServiceException {
        super(wsdlLoc, sName);
        this.oa_adress = "";
        //this.OfsTodoDataWebServiceHttpPort_address = String.valueOf(ApiConfigParameters.getParameterByName("weaver.address")) + "/services/OfsTodoDataWebService";
        this.OfsTodoDataWebServiceHttpPort_address =  "/services/OfsTodoDataWebService";
        this.OfsTodoDataWebServiceHttpPortWSDDServiceName = "OfsTodoDataWebServiceHttpPort";
        this.ports = null;
    }
    
    public String getOfsTodoDataWebServiceHttpPortAddress() {
        return this.OfsTodoDataWebServiceHttpPort_address;
    }
    
    public String getOfsTodoDataWebServiceHttpPortWSDDServiceName() {
        return this.OfsTodoDataWebServiceHttpPortWSDDServiceName;
    }
    
    public void setOfsTodoDataWebServiceHttpPortWSDDServiceName(final String name) {
        this.OfsTodoDataWebServiceHttpPortWSDDServiceName = name;
    }
    
    public OfsTodoDataWebServicePortType getOfsTodoDataWebServiceHttpPort() throws ServiceException {
        URL endpoint;
        try {
            endpoint = new URL(this.OfsTodoDataWebServiceHttpPort_address);
        }
        catch (MalformedURLException e) {
            throw new ServiceException((Throwable)e);
        }
        return this.getOfsTodoDataWebServiceHttpPort(endpoint);
    }
    
    public OfsTodoDataWebServicePortType getOfsTodoDataWebServiceHttpPort(final URL portAddress) throws ServiceException {
        try {
            final OfsTodoDataWebServiceHttpBindingStub _stub = new OfsTodoDataWebServiceHttpBindingStub(portAddress, (javax.xml.rpc.Service)this);
            _stub.setPortName(this.getOfsTodoDataWebServiceHttpPortWSDDServiceName());
            return _stub;
        }
        catch (AxisFault e) {
            return null;
        }
    }
    
    public void setOfsTodoDataWebServiceHttpPortEndpointAddress(final String address) {
        this.OfsTodoDataWebServiceHttpPort_address = address;
    }
    
    public Remote getPort(final Class serviceEndpointInterface) throws ServiceException {
        try {
            if (OfsTodoDataWebServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                final OfsTodoDataWebServiceHttpBindingStub _stub = new OfsTodoDataWebServiceHttpBindingStub(new URL(this.OfsTodoDataWebServiceHttpPort_address), (javax.xml.rpc.Service)this);
                _stub.setPortName(this.getOfsTodoDataWebServiceHttpPortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new ServiceException(t);
        }
        throw new ServiceException("There is no stub implementation for the interface:  " + ((serviceEndpointInterface == null) ? "null" : serviceEndpointInterface.getName()));
    }
    
    public Remote getPort(final QName portName, final Class serviceEndpointInterface) throws ServiceException {
        if (portName == null) {
            return this.getPort(serviceEndpointInterface);
        }
        final String inputPortName = portName.getLocalPart();
        if ("OfsTodoDataWebServiceHttpPort".equals(inputPortName)) {
            return this.getOfsTodoDataWebServiceHttpPort();
        }
        final Remote _stub = this.getPort(serviceEndpointInterface);
        ((Stub)_stub).setPortName(portName);
        return _stub;
    }
    
    public QName getServiceName() {
        return new QName("webservices.ofs.weaver.com.cn", "OfsTodoDataWebService");
    }
    
    public Iterator getPorts() {
        if (this.ports == null) {
            (this.ports = new HashSet()).add(new QName("webservices.ofs.weaver.com.cn", "OfsTodoDataWebServiceHttpPort"));
        }
        return this.ports.iterator();
    }
    
    public void setEndpointAddress(final String portName, final String address) throws ServiceException {
        if ("OfsTodoDataWebServiceHttpPort".equals(portName)) {
            this.setOfsTodoDataWebServiceHttpPortEndpointAddress(address);
            return;
        }
        throw new ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
    }
    
    public void setEndpointAddress(final QName portName, final String address) throws ServiceException {
        this.setEndpointAddress(portName.getLocalPart(), address);
    }
}
