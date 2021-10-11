package com.kingdee.eas.octal;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.Context;
import com.kingdee.bos.util.BOSObjectType;

public class JcbMsgLogFactory
{
    public static IJcbMsgLog getRemoteInstance() throws BOSException {
        return (IJcbMsgLog)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("B64F3D40"), (Class)IJcbMsgLog.class);
    }
    
    public static IJcbMsgLog getRemoteInstanceWithObjectContext(final Context objectCtx) throws BOSException {
        return (IJcbMsgLog)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("B64F3D40"), (Class)IJcbMsgLog.class, objectCtx);
    }
    
    public static IJcbMsgLog getLocalInstance(final Context ctx) throws BOSException {
        return (IJcbMsgLog)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("B64F3D40"));
    }
    
    public static IJcbMsgLog getLocalInstance(final String sessionID) throws BOSException {
        return (IJcbMsgLog)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("B64F3D40"));
    }
}
