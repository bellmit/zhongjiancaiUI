package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class UIToolEntryFactory
{
    private UIToolEntryFactory()
    {
    }
    public static com.kingdee.eas.custom.IUIToolEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.IUIToolEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("A536BA41") ,com.kingdee.eas.custom.IUIToolEntry.class);
    }
    
    public static com.kingdee.eas.custom.IUIToolEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.IUIToolEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("A536BA41") ,com.kingdee.eas.custom.IUIToolEntry.class, objectCtx);
    }
    public static com.kingdee.eas.custom.IUIToolEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.IUIToolEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("A536BA41"));
    }
    public static com.kingdee.eas.custom.IUIToolEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.IUIToolEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("A536BA41"));
    }
}