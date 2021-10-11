package com.kingdee.eas.custom;

import com.kingdee.bos.util.*;
import com.kingdee.bos.*;

public class DesignatePerformerFacadeFactory
{
    public static IDesignatePerformerFacade getRemoteInstance() throws BOSException {
        return (IDesignatePerformerFacade)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("9DEF9A99"), (Class)IDesignatePerformerFacade.class);
    }
    
    public static IDesignatePerformerFacade getRemoteInstanceWithObjectContext(final Context objectCtx) throws BOSException {
        return (IDesignatePerformerFacade)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("9DEF9A99"), (Class)IDesignatePerformerFacade.class, objectCtx);
    }
    
    public static IDesignatePerformerFacade getLocalInstance(final Context ctx) throws BOSException {
        return (IDesignatePerformerFacade)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("9DEF9A99"));
    }
    
    public static IDesignatePerformerFacade getLocalInstance(final String sessionID) throws BOSException {
        return (IDesignatePerformerFacade)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("9DEF9A99"));
    }
}
