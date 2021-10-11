package com.kingdee.eas.moya.app;

import com.kingdee.bos.framework.ejb.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.*;
import com.kingdee.bos.*;
import com.kingdee.bos.service.*;

public abstract class AbstractUserMappingFacadeControllerBean extends AbstractBizControllerBean implements UserMappingFacadeController
{
    protected BOSObjectType getBOSType() {
        return new BOSObjectType("79A31E91");
    }
    
    public void getUserMapping(final Context ctx) throws BOSException {
        try {
            final ServiceContext svcCtx = this.createServiceContext(new MetaDataPK("dfdce268-900b-459b-b6fa-c6d611694051"), new Object[] { ctx });
            this.invokeServiceBefore(svcCtx);
            if (!svcCtx.invokeBreak()) {
                this._getUserMapping(ctx);
            }
            this.invokeServiceAfter(svcCtx);
        }
        catch (BOSException ex) {
            throw ex;
        }
        finally {
            super.cleanUpServiceState();
        }
        super.cleanUpServiceState();
    }
    
    protected void _getUserMapping(final Context ctx) throws BOSException {
    }
}
