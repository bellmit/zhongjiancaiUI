package com.kingdee.eas.moya.message.app.landray;

import com.kingdee.eas.moya.common.*;
import com.kingdee.eas.moya.message.app.landray.webservice.*;
import java.text.*;
import java.util.*;

public final class Notifies
{
    public static String getAddress() {
        return PropUtil.getParameterByName("notify.address");
    }
    
    public static NotifyTodoAppResult sendTodo(final String assignID, final String subject, final String link, final String recieveNumber, final String modelName) {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final StringBuffer sb = new StringBuffer();
        sb.append("<createTime>" + format.format(new Date()) + "</createTime>");
        sb.append("<link>" + link + "</link>");
        sb.append("<subject>" + subject + "</subject>");
        sb.append("<targets>[{\"LoginName\":\"" + recieveNumber + "\"}]</targets>");
        sb.append("<type>1</type>");
        sb.append("<modelId>" + assignID + "</modelId>");
        sb.append("<modelName>" + modelName + "</modelName>");
        sb.append("<appName>" + modelName + "</appName>");
        System.out.println("\u7481\u5757\u68f6\u9428\u5248rl:" + link);
        return sendTodo(sb.toString());
    }
    
    private static NotifyTodoAppResult sendTodo(final String xml) {
        throw new Error("\u65e0\u6cd5\u89e3\u6790\u7684\u7f16\u8bd1\u95ee\u9898\uff1a\n\t\u5b57\u7b26\u4e32\u6587\u5b57\u672a\u7528\u53cc\u5f15\u53f7\u6b63\u786e\u5730\u5f15\u8d77\u6765\n");
    }
    
    public static NotifyTodoAppResult setTodoDone(final String assignID, final String modelName) {
        final StringBuffer sb = new StringBuffer();
        sb.append("<appName>" + modelName + "</appName>");
        sb.append("<optType>1</optType>");
        sb.append("<modelId>" + assignID + "</modelId>");
        sb.append("<modelName>" + modelName + "</modelName>");
        return setTodoDone(sb.toString());
    }
    
    private static NotifyTodoAppResult setTodoDone(final String xml) {
        throw new Error("\u65e0\u6cd5\u89e3\u6790\u7684\u7f16\u8bd1\u95ee\u9898\uff1a\n\t\u5b57\u7b26\u4e32\u6587\u5b57\u672a\u7528\u53cc\u5f15\u53f7\u6b63\u786e\u5730\u5f15\u8d77\u6765\n");
    }
}
