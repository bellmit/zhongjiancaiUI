package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class UIToolFactory
{
    private UIToolFactory()
    {
    }
    public static com.kingdee.eas.custom.IUITool getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.IUITool)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("8339A4B1") ,com.kingdee.eas.custom.IUITool.class);
    }
    
    public static com.kingdee.eas.custom.IUITool getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.IUITool)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("8339A4B1") ,com.kingdee.eas.custom.IUITool.class, objectCtx);
    }
    public static com.kingdee.eas.custom.IUITool getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.IUITool)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("8339A4B1"));
    }
    public static com.kingdee.eas.custom.IUITool getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.IUITool)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("8339A4B1"));
    }
}