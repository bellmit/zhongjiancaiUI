package com.kingdee.eas.moya.message.app.wechat.comp;

import com.kingdee.eas.moya.message.app.*;
import com.kingdee.bos.*;
import com.kingdee.eas.base.message.MsgStatus;
import com.kingdee.eas.base.message.webservice.*;
import com.kingdee.eas.moya.common.model.*;
import com.kingdee.eas.util.app.*;
import com.kingdee.jdbc.rowset.*;
import com.alibaba.fastjson.*;

public class CompWxMessageServiceDao extends BaseMessageServiceDao
{
    @Override
    public void handleAddMessage(final Context ctx, final WSMessage message, final AssignInfo assignInfo) throws Exception {
        System.out.println("\u4f01\u4e1a\u5fae\u4fe1\u53d1\u9001\u5f85\u529e\u5f00\u59cb");
        final String mobile = assignInfo.getReceiver().getCell();
        final String sql = "select fuser_id from CT_BAS_USERMOBILE where ftype='qywx' and fmobile='" + mobile + "'";
        final IRowSet rowSet = DbUtil.executeQuery(ctx, sql);
        if (rowSet.next()) {
            final String userid = rowSet.getString("fuser_id");
            final JSONObject resObj = Notifies.sendMsg(userid, message.getTitle(), message.getBody(), message.getUrl(), "\u67e5\u770b");
            if (resObj.getInteger("errcode") != 0) {
                throw new RuntimeException(resObj.getString("errmsg"));
            }
        }
        System.out.println("\u4f01\u4e1a\u5fae\u4fe1\u53d1\u9001\u5f85\u529e\u7ed3\u675f");
    }
    
    @Override
    public void handleRemoveMessage(final Context ctx, final String assignID, final AssignInfo assignInfo) throws Exception {
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
