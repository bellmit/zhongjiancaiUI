package com.kingdee.eas.custom.app;

import com.kingdee.bos.framework.ejb.*;
import com.kingdee.bos.util.*;
import java.util.*;
import com.kingdee.bos.metadata.*;
import com.kingdee.bos.*;
import com.kingdee.bos.service.*;

public abstract class AbstractDesignatePerformerFacadeControllerBean extends AbstractBizControllerBean implements DesignatePerformerFacadeController
{
    protected BOSObjectType getBOSType() {
        return new BOSObjectType("9DEF9A99");
    }
    
    public void setNextPerson(final Context ctx, final ArrayList actInstList, final String[] actInstIds, final ArrayList list) throws BOSException {
        try {
            final ServiceContext svcCtx = this.createServiceContext(new MetaDataPK("2415f28c-467d-4747-ad74-ccb7ae03f30b"), new Object[] { ctx, actInstList, actInstIds, list });
            this.invokeServiceBefore(svcCtx);
            if (!svcCtx.invokeBreak()) {
                this._setNextPerson(ctx, actInstList, actInstIds, list);
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
    
    protected void _setNextPerson(final Context ctx, final ArrayList actInstList, final String[] actInstIds, final ArrayList list) throws BOSException {
    }
    
    public void autoTask(final Context ctx) throws BOSException {
        try {
            final ServiceContext svcCtx = this.createServiceContext(new MetaDataPK("41e8ba91-6a30-48ad-8092-4b54aea87cea"), new Object[] { ctx });
            this.invokeServiceBefore(svcCtx);
            if (!svcCtx.invokeBreak()) {
                this._autoTask(ctx);
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
    
    protected void _autoTask(final Context ctx) throws BOSException {
    }
}
