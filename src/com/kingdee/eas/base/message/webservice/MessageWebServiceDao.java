package com.kingdee.eas.base.message.webservice;

import com.kingdee.bos.*;
import com.kingdee.eas.base.message.*;

public interface MessageWebServiceDao
{
    boolean addMessage(WSMessage p0, Context p1);
    
    boolean removeMessage(String p0, Context p1);
    
    boolean updateMessage(String p0, Context p1);
    
    boolean updateMessages(String p0, MsgStatus p1, Context p2);
    
    public abstract boolean addInitiatorMessage(
			WfrProcMessage paramWfrProcMessage);

	public abstract boolean addCompletedMessage(
			WfrAssignMessage paramWfrAssignMessage);
	
	
	public abstract boolean addMessage(WSMessage paramWSMessage);

	public abstract boolean removeMessage(String paramString);

	public abstract boolean updateMessage(String paramString);

	public abstract boolean updateMessages(String paramString,MsgStatus paramMsgStatus);
 
}
