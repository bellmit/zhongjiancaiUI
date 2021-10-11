package com.kingdee.eas.moya.app;

import com.kingdee.bos.framework.ejb.*;
import com.kingdee.bos.util.*;
import com.kingdee.eas.moya.common.model.*;
import com.kingdee.bos.metadata.*;
import com.kingdee.bos.*;
import com.kingdee.eas.common.*;
import com.kingdee.bos.service.*;

public abstract class AbstractJcbMsgLogControllerBean extends AbstractBizControllerBean implements JcbMsgLogController
{
    protected BOSObjectType getBOSType() {
        return new BOSObjectType("B64F3D40");
    }
    
    public String insertNotifyLog(final Context ctx, final MsgLogInfo msgLogInfo) throws BOSException, EASBizException {
        try {
            final ServiceContext svcCtx = this.createServiceContext(new MetaDataPK("d3293215-7b9d-45ee-8be0-db1f4ea85a27"), new Object[] { ctx, msgLogInfo });
            this.invokeServiceBefore(svcCtx);
            if (!svcCtx.invokeBreak()) {
                final String retValue = this._insertNotifyLog(ctx, msgLogInfo);
                svcCtx.setMethodReturnValue((Object)retValue);
            }
            this.invokeServiceAfter(svcCtx);
            return (String)svcCtx.getMethodReturnValue();
        }
        catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        }
        catch (EASBizException ex2) {
            this.setRollbackOnly();
            throw ex2;
        }
        finally {
            super.cleanUpServiceState();
        }
    }
    
    protected String _insertNotifyLog(final Context ctx, final MsgLogInfo msgLogInfo) throws BOSException, EASBizException {
        return null;
    }
}
