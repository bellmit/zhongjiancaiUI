package com.kingdee.eas.moya.message.app.yzj;

import com.kingdee.eas.moya.message.app.*;
import com.kingdee.bos.*;
import com.kingdee.eas.base.message.MsgStatus;
import com.kingdee.eas.base.message.webservice.*;
import com.kingdee.eas.moya.common.model.*;

public class YZJMessageServiceDao extends BaseMessageServiceDao
{
    @Override
    public void handleAddMessage(final Context ctx, final WSMessage message, final AssignInfo assignInfo) throws Exception {
        Notifies.sendWorkRecord(assignInfo.getReceiver().getCell(), message.getTitle(), message.getUrl(), message.getBody(), assignInfo.getAssignID());
    }
    
    @Override
    public void handleRemoveMessage(final Context ctx, final String assignID, final AssignInfo assignInfo) throws Exception {
        Notifies.updateWordRecord(assignInfo.getReceiver().getCell(), assignID);
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
