package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class SyncDDDateFactory
{
    private SyncDDDateFactory()
    {
    }
    public static com.kingdee.eas.custom.ISyncDDDate getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.ISyncDDDate)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("FA67EEAE") ,com.kingdee.eas.custom.ISyncDDDate.class);
    }
    
    public static com.kingdee.eas.custom.ISyncDDDate getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.ISyncDDDate)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("FA67EEAE") ,com.kingdee.eas.custom.ISyncDDDate.class, objectCtx);
    }
    public static com.kingdee.eas.custom.ISyncDDDate getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.ISyncDDDate)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("FA67EEAE"));
    }
    public static com.kingdee.eas.custom.ISyncDDDate getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.ISyncDDDate)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("FA67EEAE"));
    }
}