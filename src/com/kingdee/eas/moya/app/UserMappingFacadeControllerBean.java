package com.kingdee.eas.moya.app;

import org.apache.log4j.*;
import com.kingdee.eas.moya.message.app.wechat.comp.*;
import com.kingdee.eas.util.app.*;
import com.kingdee.bos.*;
import java.util.*;
import com.kingdee.jdbc.rowset.*;
import com.alibaba.fastjson.*;

public class UserMappingFacadeControllerBean extends AbstractUserMappingFacadeControllerBean
{
    private static Logger logger;
    
    static {
        UserMappingFacadeControllerBean.logger = Logger.getLogger("com.kingdee.eas.moya.app.UserMappingFacadeControllerBean");
    }
    
    @Override
    protected void _getUserMapping(final Context ctx) throws BOSException {
        try {
            final String token = Notifies.getToken();
            final Set<String> existIds = new HashSet<String>();
            final String sql = "select fuser_id from CT_BAS_USERMOBILE where ftype='qywx'";
            final IRowSet rowSet = DbUtil.executeQuery(ctx, sql);
            while (rowSet.next()) {
                final String user_id = rowSet.getString("fuser_id");
                existIds.add(user_id);
            }
            final JSONArray ja = Notifies.getAllUserInfo(token, existIds);
            for (int i = 0; i < ja.size(); ++i) {
                final JSONObject json = ja.getJSONObject(i);
                final String userid = new StringBuilder().append(json.get((Object)"userid")).toString();
                final String mobile = new StringBuilder().append(json.get((Object)"mobile")).toString();
                System.out.println("\u5fae\u4fe1\u7528\u6237\uff1a" + userid + "==" + mobile);
                final StringBuffer sqlInsert = new StringBuffer();
                sqlInsert.append("insert into CT_BAS_USERMOBILE(ftype,fuser_id, fmobile)");
                sqlInsert.append(" values(");
                sqlInsert.append("'qywx','" + userid + "','" + mobile + "'");
                sqlInsert.append(")");
                try {
                    DbUtil.execute(ctx, sqlInsert.toString());
                }
                catch (BOSException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e2) {
            throw new RuntimeException(e2.getMessage());
        }
    }
}
