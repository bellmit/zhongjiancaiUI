package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class DateBaseInterfaceFacadeFactory
{
    private DateBaseInterfaceFacadeFactory()
    {
    }
    public static com.kingdee.eas.custom.IDateBaseInterfaceFacade getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.IDateBaseInterfaceFacade)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("8D8C65AF") ,com.kingdee.eas.custom.IDateBaseInterfaceFacade.class);
    }
    
    public static com.kingdee.eas.custom.IDateBaseInterfaceFacade getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.IDateBaseInterfaceFacade)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("8D8C65AF") ,com.kingdee.eas.custom.IDateBaseInterfaceFacade.class, objectCtx);
    }
    public static com.kingdee.eas.custom.IDateBaseInterfaceFacade getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.IDateBaseInterfaceFacade)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("8D8C65AF"));
    }
    public static com.kingdee.eas.custom.IDateBaseInterfaceFacade getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.IDateBaseInterfaceFacade)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("8D8C65AF"));
    }
}