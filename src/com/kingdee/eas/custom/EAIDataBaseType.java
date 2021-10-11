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

public class EAIDataBaseType extends DataBase implements IEAIDataBaseType
{
    public EAIDataBaseType()
    {
        super();
        registerInterface(IEAIDataBaseType.class, this);
    }
    public EAIDataBaseType(Context ctx)
    {
        super(ctx);
        registerInterface(IEAIDataBaseType.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("11FF827D");
    }
    private EAIDataBaseTypeController getController() throws BOSException
    {
        return (EAIDataBaseTypeController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public EAIDataBaseTypeInfo getEAIDataBaseTypeInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getEAIDataBaseTypeInfo(getContext(), pk);
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
    public EAIDataBaseTypeInfo getEAIDataBaseTypeInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getEAIDataBaseTypeInfo(getContext(), pk, selector);
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
    public EAIDataBaseTypeInfo getEAIDataBaseTypeInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getEAIDataBaseTypeInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public EAIDataBaseTypeCollection getEAIDataBaseTypeCollection() throws BOSException
    {
        try {
            return getController().getEAIDataBaseTypeCollection(getContext());
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
    public EAIDataBaseTypeCollection getEAIDataBaseTypeCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getEAIDataBaseTypeCollection(getContext(), view);
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
    public EAIDataBaseTypeCollection getEAIDataBaseTypeCollection(String oql) throws BOSException
    {
        try {
            return getController().getEAIDataBaseTypeCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}