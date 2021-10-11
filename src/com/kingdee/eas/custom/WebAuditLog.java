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

public class WebAuditLog extends DataBase implements IWebAuditLog
{
    public WebAuditLog()
    {
        super();
        registerInterface(IWebAuditLog.class, this);
    }
    public WebAuditLog(Context ctx)
    {
        super(ctx);
        registerInterface(IWebAuditLog.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("2A0C9178");
    }
    private WebAuditLogController getController() throws BOSException
    {
        return (WebAuditLogController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public WebAuditLogInfo getWebAuditLogInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getWebAuditLogInfo(getContext(), pk);
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
    public WebAuditLogInfo getWebAuditLogInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getWebAuditLogInfo(getContext(), pk, selector);
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
    public WebAuditLogInfo getWebAuditLogInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getWebAuditLogInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public WebAuditLogCollection getWebAuditLogCollection() throws BOSException
    {
        try {
            return getController().getWebAuditLogCollection(getContext());
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
    public WebAuditLogCollection getWebAuditLogCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getWebAuditLogCollection(getContext(), view);
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
    public WebAuditLogCollection getWebAuditLogCollection(String oql) throws BOSException
    {
        try {
            return getController().getWebAuditLogCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}