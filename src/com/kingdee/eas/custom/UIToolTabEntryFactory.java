package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class UIToolTabEntryFactory
{
    private UIToolTabEntryFactory()
    {
    }
    public static com.kingdee.eas.custom.IUIToolTabEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.IUIToolTabEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("610095AE") ,com.kingdee.eas.custom.IUIToolTabEntry.class);
    }
    
    public static com.kingdee.eas.custom.IUIToolTabEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.IUIToolTabEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("610095AE") ,com.kingdee.eas.custom.IUIToolTabEntry.class, objectCtx);
    }
    public static com.kingdee.eas.custom.IUIToolTabEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.IUIToolTabEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("610095AE"));
    }
    public static com.kingdee.eas.custom.IUIToolTabEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.IUIToolTabEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("610095AE"));
    }
}