package com.kingdee.eas.moya.app;

import com.kingdee.bos.framework.ejb.*;
import com.kingdee.eas.moya.common.model.*;
import com.kingdee.bos.*;
import com.kingdee.eas.common.*;
import java.rmi.*;

public interface JcbMsgLogController extends BizController
{
    String insertNotifyLog(final Context p0, final MsgLogInfo p1) throws BOSException, EASBizException, RemoteException;
}
