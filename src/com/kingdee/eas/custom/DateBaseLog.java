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

public class DateBaseLog extends DataBase implements IDateBaseLog
{
    public DateBaseLog()
    {
        super();
        registerInterface(IDateBaseLog.class, this);
    }
    public DateBaseLog(Context ctx)
    {
        super(ctx);
        registerInterface(IDateBaseLog.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("C5F167C0");
    }
    private DateBaseLogController getController() throws BOSException
    {
        return (DateBaseLogController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public DateBaseLogInfo getDateBaseLogInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getDateBaseLogInfo(getContext(), pk);
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
    public DateBaseLogInfo getDateBaseLogInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getDateBaseLogInfo(getContext(), pk, selector);
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
    public DateBaseLogInfo getDateBaseLogInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getDateBaseLogInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public DateBaseLogCollection getDateBaseLogCollection() throws BOSException
    {
        try {
            return getController().getDateBaseLogCollection(getContext());
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
    public DateBaseLogCollection getDateBaseLogCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getDateBaseLogCollection(getContext(), view);
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
    public DateBaseLogCollection getDateBaseLogCollection(String oql) throws BOSException
    {
        try {
            return getController().getDateBaseLogCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}