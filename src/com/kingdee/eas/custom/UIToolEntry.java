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
import com.kingdee.eas.framework.ICoreBillEntryBase;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.custom.app.*;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.CoreBillEntryBase;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;

public class UIToolEntry extends CoreBillEntryBase implements IUIToolEntry
{
    public UIToolEntry()
    {
        super();
        registerInterface(IUIToolEntry.class, this);
    }
    public UIToolEntry(Context ctx)
    {
        super(ctx);
        registerInterface(IUIToolEntry.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("A536BA41");
    }
    private UIToolEntryController getController() throws BOSException
    {
        return (UIToolEntryController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public UIToolEntryInfo getUIToolEntryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getUIToolEntryInfo(getContext(), pk);
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
    public UIToolEntryInfo getUIToolEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getUIToolEntryInfo(getContext(), pk, selector);
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
    public UIToolEntryInfo getUIToolEntryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getUIToolEntryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public UIToolEntryCollection getUIToolEntryCollection() throws BOSException
    {
        try {
            return getController().getUIToolEntryCollection(getContext());
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
    public UIToolEntryCollection getUIToolEntryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getUIToolEntryCollection(getContext(), view);
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
    public UIToolEntryCollection getUIToolEntryCollection(String oql) throws BOSException
    {
        try {
            return getController().getUIToolEntryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}