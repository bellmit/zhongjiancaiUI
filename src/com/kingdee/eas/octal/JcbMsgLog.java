package com.kingdee.eas.octal;

import java.rmi.RemoteException;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.moya.app.JcbMsgLogController;
import com.kingdee.eas.moya.common.model.MsgLogInfo;

public class JcbMsgLog extends AbstractBizCtrl implements IJcbMsgLog
{
    public JcbMsgLog() {
        this.registerInterface((Class)IJcbMsgLog.class, (Object)this);
    }
    
    public JcbMsgLog(final Context ctx) {
        super(ctx);
        this.registerInterface((Class)IJcbMsgLog.class, (Object)this);
    }
    
    public BOSObjectType getType() {
        return new BOSObjectType("B64F3D40");
    }
    
    private JcbMsgLogController getController() throws BOSException {
        return (JcbMsgLogController)this.getBizController();
    }
    
    public String insertNotifyLog(final MsgLogInfo msgLogInfo) throws BOSException, EASBizException {
        try {
            return this.getController().insertNotifyLog(this.getContext(), msgLogInfo);
        }
        catch (RemoteException err) {
            throw new EJBRemoteException((Throwable)err);
        }
    }
}
