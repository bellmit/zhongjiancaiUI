package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class TaskFactory
{
    private TaskFactory()
    {
    }
    public static com.kingdee.eas.custom.ITask getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.ITask)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("4499254A") ,com.kingdee.eas.custom.ITask.class);
    }
    
    public static com.kingdee.eas.custom.ITask getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.ITask)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("4499254A") ,com.kingdee.eas.custom.ITask.class, objectCtx);
    }
    public static com.kingdee.eas.custom.ITask getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.ITask)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("4499254A"));
    }
    public static com.kingdee.eas.custom.ITask getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.ITask)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("4499254A"));
    }
}