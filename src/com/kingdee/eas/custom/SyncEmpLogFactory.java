package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class SyncEmpLogFactory
{
    private SyncEmpLogFactory()
    {
    }
    public static com.kingdee.eas.custom.ISyncEmpLog getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.ISyncEmpLog)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("FE723BDC") ,com.kingdee.eas.custom.ISyncEmpLog.class);
    }
    
    public static com.kingdee.eas.custom.ISyncEmpLog getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.ISyncEmpLog)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("FE723BDC") ,com.kingdee.eas.custom.ISyncEmpLog.class, objectCtx);
    }
    public static com.kingdee.eas.custom.ISyncEmpLog getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.ISyncEmpLog)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("FE723BDC"));
    }
    public static com.kingdee.eas.custom.ISyncEmpLog getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.ISyncEmpLog)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("FE723BDC"));
    }
}