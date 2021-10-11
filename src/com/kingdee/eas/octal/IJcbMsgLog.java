package com.kingdee.eas.octal;

import com.kingdee.bos.framework.*;
import com.kingdee.eas.moya.common.model.*;
import com.kingdee.bos.*;
import com.kingdee.eas.common.*;

public interface IJcbMsgLog extends IBizCtrl
{
    String insertNotifyLog(final MsgLogInfo p0) throws BOSException, EASBizException;
}
