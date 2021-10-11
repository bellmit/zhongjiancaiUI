package com.kingdee.eas.moya.message.app.weaver.webservice;

import java.rmi.*;

public interface OfsTodoDataWebServicePortType extends Remote
{
    AnyType2AnyTypeMapEntry[] receiveRequestInfoByMap(final AnyType2AnyTypeMapEntry[] p0) throws RemoteException;
    
    AnyType2AnyTypeMapEntry[] processOverRequestByMap(final AnyType2AnyTypeMapEntry[] p0) throws RemoteException;
    
    String receiveRequestInfoByXml(final String p0) throws RemoteException;
    
    String processDoneRequestByJson(final String p0) throws RemoteException;
    
    AnyType2AnyTypeMapEntry[] deleteUserRequestInfoByMap(final AnyType2AnyTypeMapEntry[] p0) throws RemoteException;
    
    String deleteUserRequestInfoByXML(final String p0) throws RemoteException;
    
    String processDoneRequestByXml(final String p0) throws RemoteException;
    
    AnyType2AnyTypeMapEntry[] deleteRequestInfoByMap(final AnyType2AnyTypeMapEntry[] p0) throws RemoteException;
    
    String processOverRequestByXml(final String p0) throws RemoteException;
    
    String receiveRequestInfoByJson(final String p0) throws RemoteException;
    
    String receiveTodoRequestByJson(final String p0) throws RemoteException;
    
    String deleteRequestInfoByXML(final String p0) throws RemoteException;
    
    String receiveTodoRequestByXml(final String p0) throws RemoteException;
    
    String deleteUserRequestInfoByJson(final String p0) throws RemoteException;
    
    AnyType2AnyTypeMapEntry[] processDoneRequestByMap(final AnyType2AnyTypeMapEntry[] p0) throws RemoteException;
    
    String deleteRequestInfoByJson(final String p0) throws RemoteException;
    
    String processOverRequestByJson(final String p0) throws RemoteException;
    
    AnyType2AnyTypeMapEntry[] receiveTodoRequestByMap(final AnyType2AnyTypeMapEntry[] p0) throws RemoteException;
}
