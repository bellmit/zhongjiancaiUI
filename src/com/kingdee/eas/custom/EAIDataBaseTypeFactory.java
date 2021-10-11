package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class EAIDataBaseTypeFactory
{
    private EAIDataBaseTypeFactory()
    {
    }
    public static com.kingdee.eas.custom.IEAIDataBaseType getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.IEAIDataBaseType)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("11FF827D") ,com.kingdee.eas.custom.IEAIDataBaseType.class);
    }
    
    public static com.kingdee.eas.custom.IEAIDataBaseType getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.IEAIDataBaseType)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("11FF827D") ,com.kingdee.eas.custom.IEAIDataBaseType.class, objectCtx);
    }
    public static com.kingdee.eas.custom.IEAIDataBaseType getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.IEAIDataBaseType)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("11FF827D"));
    }
    public static com.kingdee.eas.custom.IEAIDataBaseType getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.IEAIDataBaseType)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("11FF827D"));
    }
}