package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class WebAuditLogFactory
{
    private WebAuditLogFactory()
    {
    }
    public static com.kingdee.eas.custom.IWebAuditLog getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.IWebAuditLog)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("2A0C9178") ,com.kingdee.eas.custom.IWebAuditLog.class);
    }
    
    public static com.kingdee.eas.custom.IWebAuditLog getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.IWebAuditLog)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("2A0C9178") ,com.kingdee.eas.custom.IWebAuditLog.class, objectCtx);
    }
    public static com.kingdee.eas.custom.IWebAuditLog getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.IWebAuditLog)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("2A0C9178"));
    }
    public static com.kingdee.eas.custom.IWebAuditLog getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.IWebAuditLog)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("2A0C9178"));
    }
}