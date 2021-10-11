package com.kingdee.eas.moya.app;

import org.apache.log4j.*;
import com.kingdee.eas.moya.common.model.*;
import com.kingdee.util.*;
import com.kingdee.eas.util.app.*;
import com.kingdee.bos.*;
import com.kingdee.eas.common.*;

public class JcbMsgLogControllerBean extends AbstractJcbMsgLogControllerBean
{
    private static final long serialVersionUID = -1212274360380185200L;
    private static Logger logger;
    
    static {
        JcbMsgLogControllerBean.logger = Logger.getLogger("com.kingdee.eas.moya.app.JcbMsgLogControllerBean");
    }
    
    @Override
    protected String _insertNotifyLog(final Context ctx, final MsgLogInfo msgLogInfo) throws BOSException, EASBizException {
        String result = msgLogInfo.getResult();
        if (!StringUtils.isEmpty(result) && result.length() >= 500) {
            result = result.substring(0, 500);
        }
        final StringBuffer sql = new StringBuffer();
        sql.append("insert into CT_BAS_TaskLog(fresult,fopt, FMsgType,FSender,FReceivers,FASSIGNID,FCreatetime,fid)");
        sql.append(" values(");
        sql.append("'" + result + "','" + msgLogInfo.getOpt() + "'," + msgLogInfo.getBiztype() + ", '" + msgLogInfo.getSender() + "', '" + msgLogInfo.getReceiver() + "'" + ",'" + msgLogInfo.getAssignID() + "',now(),newbosid('730BE0ED')");
        sql.append(")");
        try {
            DbUtil.execute(ctx, sql.toString());
        }
        catch (BOSException e) {
            JcbMsgLogControllerBean.logger.error((Object)("-----------jcb\u96c6\u6210\u5b9d\u5199\u65e5\u5fd7\u51fa\u73b0\u5f02\u5e38------\n" + e.getLocalizedMessage()));
        }
        return "success";
    }
}
