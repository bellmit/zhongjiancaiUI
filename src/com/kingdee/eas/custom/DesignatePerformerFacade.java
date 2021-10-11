package com.kingdee.eas.custom;

import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.eas.custom.app.*;
import com.kingdee.bos.*;
import java.util.*;
import com.kingdee.bos.framework.ejb.*;
import java.rmi.*;

public class DesignatePerformerFacade extends AbstractBizCtrl implements IDesignatePerformerFacade
{
    public DesignatePerformerFacade() {
        this.registerInterface((Class)IDesignatePerformerFacade.class, (Object)this);
    }
    
    public DesignatePerformerFacade(final Context ctx) {
        super(ctx);
        this.registerInterface((Class)IDesignatePerformerFacade.class, (Object)this);
    }
    
    public BOSObjectType getType() {
        return new BOSObjectType("9DEF9A99");
    }
    
    private DesignatePerformerFacadeController getController() throws BOSException {
        return (DesignatePerformerFacadeController)this.getBizController();
    }
    
    public void setNextPerson(final ArrayList actInstList, final String[] actInstIds, final ArrayList list) throws BOSException {
        try {
            this.getController().setNextPerson(this.getContext(), actInstList, actInstIds, list);
        }
        catch (RemoteException err) {
            throw new EJBRemoteException((Throwable)err);
        }
    }
    
    public void autoTask() throws BOSException {
        try {
            this.getController().autoTask(this.getContext());
        }
        catch (RemoteException err) {
            throw new EJBRemoteException((Throwable)err);
        }
    }
}
