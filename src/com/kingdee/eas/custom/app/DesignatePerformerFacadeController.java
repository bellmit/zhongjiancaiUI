package com.kingdee.eas.custom.app;

import com.kingdee.bos.framework.ejb.*;
import java.util.*;
import com.kingdee.bos.*;
import java.rmi.*;

public interface DesignatePerformerFacadeController extends BizController
{
    void setNextPerson(final Context p0, final ArrayList p1, final String[] p2, final ArrayList p3) throws BOSException, RemoteException;
    
    void autoTask(final Context p0) throws BOSException, RemoteException;
}
