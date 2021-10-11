package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class DataFacadeFactory
{
    private DataFacadeFactory()
    {
    }
    public static com.kingdee.eas.custom.IDataFacade getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.IDataFacade)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("CE3AECE9") ,com.kingdee.eas.custom.IDataFacade.class);
    }
    
    public static com.kingdee.eas.custom.IDataFacade getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.IDataFacade)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("CE3AECE9") ,com.kingdee.eas.custom.IDataFacade.class, objectCtx);
    }
    public static com.kingdee.eas.custom.IDataFacade getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.IDataFacade)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("CE3AECE9"));
    }
    public static com.kingdee.eas.custom.IDataFacade getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.IDataFacade)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("CE3AECE9"));
    }
}