package com.kingdee.eas.moya.message.app.landray;

import com.kingdee.eas.moya.message.app.*;
import com.kingdee.bos.*;
import com.kingdee.eas.base.message.MsgStatus;
import com.kingdee.eas.base.message.webservice.*;
import com.kingdee.eas.moya.common.model.*;

public class LandrayMessageServiceDao extends BaseMessageServiceDao
{
    @Override
    public void handleAddMessage(final Context ctx, final WSMessage message, final AssignInfo assignInfo) throws Exception {
        throw new Error("\u65e0\u6cd5\u89e3\u6790\u7684\u7f16\u8bd1\u95ee\u9898\uff1a\n\t\u5b57\u7b26\u4e32\u6587\u5b57\u672a\u7528\u53cc\u5f15\u53f7\u6b63\u786e\u5730\u5f15\u8d77\u6765\n");
    }
    
    @Override
    protected String getApproveUrl(final WSMessage message) {
        final StringBuffer msgUrl = new StringBuffer(message.getUrl());
        msgUrl.append("&redirectTo=");
        final String url = "/easweb/webviews/workflow/approve.jsp?assignmentId=" + this.getAssignID(message) + "&waf2skin=easbase";
        msgUrl.append(url);
        String result = msgUrl.toString();
        result = result.replaceAll("&", "&amp;");
        return result;
    }
    
    @Override
    public void handleRemoveMessage(final Context ctx, final String assignID, final AssignInfo assignInfo) throws Exception {
        Notifies.setTodoDone(assignID, "EAS");
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
