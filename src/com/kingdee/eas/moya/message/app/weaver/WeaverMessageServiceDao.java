package com.kingdee.eas.moya.message.app.weaver;

import com.kingdee.eas.moya.message.app.*;
import com.kingdee.bos.*;
import com.kingdee.eas.base.message.MsgStatus;
import com.kingdee.eas.base.message.webservice.*;
import com.kingdee.eas.moya.common.model.*;

public class WeaverMessageServiceDao extends BaseMessageServiceDao
{
    @Override
    public void handleAddMessage(final Context ctx, final WSMessage message, final AssignInfo assignInfo) throws Exception {
        Notifies.sendTodo(ctx, message, assignInfo, message.getSourceID(), message.getTitle(), message.getBody(), this.getUrl(message, ctx), message.getRecieveNumber(), assignInfo.getSender(), assignInfo.getReceiverID(), message.getSendTime(), message.getReceiveTime(), "EAS");
    }
    
    @Override
    public void handleRemoveMessage(final Context ctx, final String assignID, final AssignInfo assignInfo) throws Exception {
        Notifies.setTodoDone(ctx, assignID, assignInfo, "EAS");
    }

	@Override
	public boolean addCompletedMessage(WfrAssignMessage paramWfrAssignMessage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addInitiatorMessage(WfrProcMessage paramWfrProcMessage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addMessage(WSMessage paramWSMessage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeMessage(String paramString) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateMessage(String paramString) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateMessages(String paramString, MsgStatus paramMsgStatus) {
		// TODO Auto-generated method stub
		return false;
	}
}
