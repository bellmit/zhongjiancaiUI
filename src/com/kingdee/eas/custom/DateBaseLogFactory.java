package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class DateBaseLogFactory
{
    private DateBaseLogFactory()
    {
    }
    public static com.kingdee.eas.custom.IDateBaseLog getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.IDateBaseLog)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("C5F167C0") ,com.kingdee.eas.custom.IDateBaseLog.class);
    }
    
    public static com.kingdee.eas.custom.IDateBaseLog getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.IDateBaseLog)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("C5F167C0") ,com.kingdee.eas.custom.IDateBaseLog.class, objectCtx);
    }
    public static com.kingdee.eas.custom.IDateBaseLog getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.IDateBaseLog)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("C5F167C0"));
    }
    public static com.kingdee.eas.custom.IDateBaseLog getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.IDateBaseLog)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("C5F167C0"));
    }
}