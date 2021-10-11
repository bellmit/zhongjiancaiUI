package com.kingdee.eas.octal;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.Context;
import com.kingdee.bos.util.BOSObjectType;

public class UserMappingFacadeFactory
{
    public static IUserMappingFacade getRemoteInstance() throws BOSException {
        return (IUserMappingFacade)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("79A31E91"), (Class)IUserMappingFacade.class);
    }
    
    public static IUserMappingFacade getRemoteInstanceWithObjectContext(final Context objectCtx) throws BOSException {
        return (IUserMappingFacade)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("79A31E91"), (Class)IUserMappingFacade.class, objectCtx);
    }
    
    public static IUserMappingFacade getLocalInstance(final Context ctx) throws BOSException {
        return (IUserMappingFacade)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("79A31E91"));
    }
    
    public static IUserMappingFacade getLocalInstance(final String sessionID) throws BOSException {
        return (IUserMappingFacade)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("79A31E91"));
    }
}
