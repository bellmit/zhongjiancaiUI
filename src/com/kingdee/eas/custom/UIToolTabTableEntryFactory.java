package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class UIToolTabTableEntryFactory
{
    private UIToolTabTableEntryFactory()
    {
    }
    public static com.kingdee.eas.custom.IUIToolTabTableEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.IUIToolTabTableEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("C9786588") ,com.kingdee.eas.custom.IUIToolTabTableEntry.class);
    }
    
    public static com.kingdee.eas.custom.IUIToolTabTableEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.IUIToolTabTableEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("C9786588") ,com.kingdee.eas.custom.IUIToolTabTableEntry.class, objectCtx);
    }
    public static com.kingdee.eas.custom.IUIToolTabTableEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.IUIToolTabTableEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("C9786588"));
    }
    public static com.kingdee.eas.custom.IUIToolTabTableEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.IUIToolTabTableEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("C9786588"));
    }
}