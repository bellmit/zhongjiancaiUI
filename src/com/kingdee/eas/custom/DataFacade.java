package com.kingdee.eas.custom;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.custom.app.*;
import java.lang.String;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;

public class DataFacade extends AbstractBizCtrl implements IDataFacade
{
    public DataFacade()
    {
        super();
        registerInterface(IDataFacade.class, this);
    }
    public DataFacade(Context ctx)
    {
        super(ctx);
        registerInterface(IDataFacade.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("CE3AECE9");
    }
    private DataFacadeController getController() throws BOSException
    {
        return (DataFacadeController)getBizController();
    }
    /**
     *getAndPutDate-User defined method
     *@param data data
     */
    public void getAndPutDate(String data) throws BOSException
    {
        try {
            getController().getAndPutDate(getContext(), data);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}