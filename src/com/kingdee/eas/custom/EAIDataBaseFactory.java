package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class EAIDataBaseFactory
{
    private EAIDataBaseFactory()
    {
    }
    public static com.kingdee.eas.custom.IEAIDataBase getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.IEAIDataBase)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("0D6288A3") ,com.kingdee.eas.custom.IEAIDataBase.class);
    }
    
    public static com.kingdee.eas.custom.IEAIDataBase getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.IEAIDataBase)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("0D6288A3") ,com.kingdee.eas.custom.IEAIDataBase.class, objectCtx);
    }
    public static com.kingdee.eas.custom.IEAIDataBase getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.IEAIDataBase)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("0D6288A3"));
    }
    public static com.kingdee.eas.custom.IEAIDataBase getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.IEAIDataBase)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("0D6288A3"));
    }
}