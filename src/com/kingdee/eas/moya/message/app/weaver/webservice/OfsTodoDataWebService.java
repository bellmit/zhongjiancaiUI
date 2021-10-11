package com.kingdee.eas.moya.message.app.weaver.webservice;

import javax.xml.rpc.*;
import java.net.*;

public interface OfsTodoDataWebService extends Service
{
    String getOfsTodoDataWebServiceHttpPortAddress();
    
    OfsTodoDataWebServicePortType getOfsTodoDataWebServiceHttpPort() throws ServiceException;
    
    OfsTodoDataWebServicePortType getOfsTodoDataWebServiceHttpPort(final URL p0) throws ServiceException;
}
