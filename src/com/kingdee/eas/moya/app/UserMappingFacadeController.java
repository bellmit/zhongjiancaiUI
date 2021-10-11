package com.kingdee.eas.moya.app;

import com.kingdee.bos.framework.ejb.*;
import com.kingdee.bos.*;
import java.rmi.*;

public interface UserMappingFacadeController extends BizController
{
    void getUserMapping(final Context p0) throws BOSException, RemoteException;
}
