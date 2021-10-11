package com.kingdee.eas.custom;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.framework.CoreBillBase;
import java.lang.String;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.framework.ICoreBillBase;
import com.kingdee.bos.Context;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.custom.app.*;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;

public class UITool extends CoreBillBase implements IUITool
{
    public UITool()
    {
        super();
        registerInterface(IUITool.class, this);
    }
    public UITool(Context ctx)
    {
        super(ctx);
        registerInterface(IUITool.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("8339A4B1");
    }
    private UIToolController getController() throws BOSException
    {
        return (UIToolController)getBizController();
    }
    /**
     *ȡ����-System defined method
     *@return
     */
    public UIToolCollection getUIToolCollection() throws BOSException
    {
        try {
            return getController().getUIToolCollection(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ����-System defined method
     *@param view ȡ����
     *@return
     */
    public UIToolCollection getUIToolCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getUIToolCollection(getContext(), view);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ����-System defined method
     *@param oql ȡ����
     *@return
     */
    public UIToolCollection getUIToolCollection(String oql) throws BOSException
    {
        try {
            return getController().getUIToolCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡֵ-System defined method
     *@param pk ȡֵ
     *@return
     */
    public UIToolInfo getUIToolInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getUIToolInfo(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡֵ-System defined method
     *@param pk ȡֵ
     *@param selector ȡֵ
     *@return
     */
    public UIToolInfo getUIToolInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getUIToolInfo(getContext(), pk, selector);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡֵ-System defined method
     *@param oql ȡֵ
     *@return
     */
    public UIToolInfo getUIToolInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getUIToolInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�������ɽ���-User defined method
     */
    public void resutPage() throws BOSException
    {
        try {
            getController().resutPage(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *���ں�̨���񣬻��涤������ҵ΢�ŵ�userid-User defined method
     */
    public void getUserMapping() throws BOSException
    {
        try {
            getController().getUserMapping(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}