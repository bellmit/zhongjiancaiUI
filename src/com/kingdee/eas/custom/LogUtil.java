package com.kingdee.eas.custom;

import java.text.*;
import java.util.*;
import com.kingdee.eas.basedata.org.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.dao.ormapping.*;
import com.kingdee.bos.dao.*;
import com.kingdee.eas.framework.*;
import com.kingdee.eas.common.*;
import com.kingdee.bos.*;

public class LogUtil
{
    public static void insertLog(final Context ctx, final LogTypeEnum logType, final String parameter, final String returnValue) {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            final String number = sdf.format(new Date());
            final WebAuditLogInfo logInfo = new WebAuditLogInfo();
            final CtrlUnitInfo cui = new CtrlUnitInfo();
            cui.setId(BOSUuid.read("00000000-0000-0000-0000-000000000000CCE7AED4"));
            logInfo.setCU(cui);
            ctx.setCaller((IObjectPK)new ObjectUuidPK("256c221a-0106-1000-e000-10d7c0a813f413B7DE7F"));
            logInfo.setNumber(String.valueOf(number) + System.currentTimeMillis());
            logInfo.setParameter(parameter);
            logInfo.setReturnValue(returnValue);
            logInfo.setLogType(logType);
            IWebAuditLog factory = null;
            if (ctx == null) {
                factory = WebAuditLogFactory.getRemoteInstance();
            }
            else {
                factory = WebAuditLogFactory.getLocalInstance(ctx);
            }
            factory.addnew((CoreBaseInfo)logInfo);
        }
        catch (EASBizException e) {
            e.printStackTrace();
        }
        catch (BOSException e2) {
            e2.printStackTrace();
        }
    }
}
