package com.kingdee.eas.octal;

import java.rmi.RemoteException;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.eas.moya.app.UserMappingFacadeController;

public class UserMappingFacade extends AbstractBizCtrl implements IUserMappingFacade
{
    public UserMappingFacade() {
        this.registerInterface((Class)IUserMappingFacade.class, (Object)this);
    }
    
    public UserMappingFacade(final Context ctx) {
        super(ctx);
        this.registerInterface((Class)IUserMappingFacade.class, (Object)this);
    }
    
    public BOSObjectType getType() {
        return new BOSObjectType("79A31E91");
    }
    
    private UserMappingFacadeController getController() throws BOSException {
        return (UserMappingFacadeController)this.getBizController();
    }
    
    public void getUserMapping() throws BOSException {
        try {
            this.getController().getUserMapping(this.getContext());
        }
        catch (RemoteException err) {
            throw new EJBRemoteException((Throwable)err);
        }
    }
}
