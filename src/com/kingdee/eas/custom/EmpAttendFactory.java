package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class EmpAttendFactory
{
    private EmpAttendFactory()
    {
    }
    public static com.kingdee.eas.custom.IEmpAttend getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.IEmpAttend)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("A362E0DD") ,com.kingdee.eas.custom.IEmpAttend.class);
    }
    
    public static com.kingdee.eas.custom.IEmpAttend getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.IEmpAttend)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("A362E0DD") ,com.kingdee.eas.custom.IEmpAttend.class, objectCtx);
    }
    public static com.kingdee.eas.custom.IEmpAttend getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.IEmpAttend)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("A362E0DD"));
    }
    public static com.kingdee.eas.custom.IEmpAttend getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.IEmpAttend)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("A362E0DD"));
    }
}