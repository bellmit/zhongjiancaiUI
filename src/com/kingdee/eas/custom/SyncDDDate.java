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

public class SyncDDDate extends AbstractBizCtrl implements ISyncDDDate
{
    public SyncDDDate()
    {
        super();
        registerInterface(ISyncDDDate.class, this);
    }
    public SyncDDDate(Context ctx)
    {
        super(ctx);
        registerInterface(ISyncDDDate.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("FA67EEAE");
    }
    private SyncDDDateController getController() throws BOSException
    {
        return (SyncDDDateController)getBizController();
    }
    /**
     *同步员工钉钉信息-User defined method
     *@param data data
     */
    public void SyncDDEmpDate(String data) throws BOSException
    {
        try {
            getController().SyncDDEmpDate(getContext(), data);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}