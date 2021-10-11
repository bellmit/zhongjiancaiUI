package com.kingdee.eas.moya.message.app;

import com.kingdee.bos.*;
import com.kingdee.eas.base.message.webservice.*;
import com.kingdee.eas.moya.common.model.*;

public interface IMessageService
{
    void handleAddMessage(final Context p0, final WSMessage p1, final AssignInfo p2) throws Exception;
    
    void handleRemoveMessage(final Context p0, final String p1, final AssignInfo p2) throws Exception;
}
