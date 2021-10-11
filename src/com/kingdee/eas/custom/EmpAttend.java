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

public class EmpAttend extends DataBase implements IEmpAttend
{
    public EmpAttend()
    {
        super();
        registerInterface(IEmpAttend.class, this);
    }
    public EmpAttend(Context ctx)
    {
        super(ctx);
        registerInterface(IEmpAttend.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("A362E0DD");
    }
    private EmpAttendController getController() throws BOSException
    {
        return (EmpAttendController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public EmpAttendInfo getEmpAttendInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getEmpAttendInfo(getContext(), pk);
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
    public EmpAttendInfo getEmpAttendInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getEmpAttendInfo(getContext(), pk, selector);
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
    public EmpAttendInfo getEmpAttendInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getEmpAttendInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public EmpAttendCollection getEmpAttendCollection() throws BOSException
    {
        try {
            return getController().getEmpAttendCollection(getContext());
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
    public EmpAttendCollection getEmpAttendCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getEmpAttendCollection(getContext(), view);
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
    public EmpAttendCollection getEmpAttendCollection(String oql) throws BOSException
    {
        try {
            return getController().getEmpAttendCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}