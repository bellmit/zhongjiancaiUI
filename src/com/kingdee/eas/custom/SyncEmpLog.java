package com.kingdee.eas.custom;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.dao.IObjectPK;
import java.lang.String;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.Context;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.custom.app.*;
import com.kingdee.eas.framework.DataBase;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.IDataBase;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;

public class SyncEmpLog extends DataBase implements ISyncEmpLog
{
    public SyncEmpLog()
    {
        super();
        registerInterface(ISyncEmpLog.class, this);
    }
    public SyncEmpLog(Context ctx)
    {
        super(ctx);
        registerInterface(ISyncEmpLog.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("FE723BDC");
    }
    private SyncEmpLogController getController() throws BOSException
    {
        return (SyncEmpLogController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public SyncEmpLogInfo getSyncEmpLogInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getSyncEmpLogInfo(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@param selector 取值
     *@return
     */
    public SyncEmpLogInfo getSyncEmpLogInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getSyncEmpLogInfo(getContext(), pk, selector);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param oql 取值
     *@return
     */
    public SyncEmpLogInfo getSyncEmpLogInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getSyncEmpLogInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public SyncEmpLogCollection getSyncEmpLogCollection() throws BOSException
    {
        try {
            return getController().getSyncEmpLogCollection(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@param view 取集合
     *@return
     */
    public SyncEmpLogCollection getSyncEmpLogCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getSyncEmpLogCollection(getContext(), view);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@param oql 取集合
     *@return
     */
    public SyncEmpLogCollection getSyncEmpLogCollection(String oql) throws BOSException
    {
        try {
            return getController().getSyncEmpLogCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}