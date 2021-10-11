package com.kingdee.eas.moya.message.app.yzj;

import org.apache.http.*;
import org.apache.http.message.*;
import com.kingdee.eas.moya.common.*;
import java.util.*;
 
import com.octal.thirdparty.kdyzj.api.*;
import com.alibaba.fastjson.*;

public final class Notifies
{
    public static void main(final String[] args) {
        try {
            getToken();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getToken() throws Exception {
        final long time = new Date().getTime();
        final String url = "https://www.yunzhijia.com/gateway/oauth2/token/getAccessToken";
        final List<NameValuePair> params = new LinkedList<NameValuePair>();
        params.add((NameValuePair)new BasicNameValuePair("appId", PropUtil.getParameterByName("yzj.appid")));
        params.add((NameValuePair)new BasicNameValuePair("secret", PropUtil.getParameterByName("yzj.secret")));
        params.add((NameValuePair)new BasicNameValuePair("timestamp", new StringBuilder(String.valueOf(time)).toString()));
        params.add((NameValuePair)new BasicNameValuePair("scope", PropUtil.getParameterByName("yzj.scope")));
        final String content = HttpUtil.myPost(url, params, 1);
        final JSONObject obj = JSONObject.parseObject(content);
        final Boolean isSuccess = obj.getBoolean("success");
        if (isSuccess) {
            final String dataStr = obj.getString("data");
            final JSONObject data = JSONObject.parseObject(dataStr);
            return data.getString("accessToken");
        }
        return null;
    }
    
    public static String getOpenIdByPhone(final String phone, final String eid) {
        String openId = "";
        final JSONObject data = new JSONObject();
        data.put("eid", (Object)eid);
        data.put("type", (Object)0);
        data.put("array", (Object)new String[] { phone });
        final YzjDealData yzjDeal = new YzjDealData();
        final YzjBackData result = yzjDeal.SendYzj(eid, data, "https://www.yunzhijia.com/openaccess/input/person/get", "AddPnPersonInfo");
        if (result.getSuccess().equals("false")) {
            throw new RuntimeException(String.valueOf(result.getErrorCode()) + ":" + result.getError());
        }
        final JSONArray jsonArray = JSONObject.parseArray(result.getData());
        JSONObject jsono = null;
        if (jsonArray == null || jsonArray.size() == 0) {
            throw new RuntimeException("\ufffd\u04b2\ufffd\ufffd\ufffdopenid");
        }
        for (int i = 0; i < jsonArray.size(); ++i) {
            jsono = jsonArray.getJSONObject(i);
            openId = jsono.getString("openId");
            final String phoneTemp = jsono.getString("phone");
            if (phone.equals(phoneTemp)) {
                return openId;
            }
        }
        throw new RuntimeException("\ufffd\u04b2\ufffd\ufffd\ufffdopenid");
    }
    
    public static JSONObject sendWorkRecord(final String phone, final String title, final String url, final String content, final String billid) throws Exception {
        throw new Error("\u65e0\u6cd5\u89e3\u6790\u7684\u7f16\u8bd1\u95ee\u9898\uff1a\n\t\u6784\u9020\u51fd\u6570 StringEntity\uff08String, Charset\uff09\u672a\u5b9a\u4e49\n");
    }
    
    public static JSONObject updateWordRecord(final String phone, final String billid) throws Exception {
        throw new Error("\u65e0\u6cd5\u89e3\u6790\u7684\u7f16\u8bd1\u95ee\u9898\uff1a\n\t\u6784\u9020\u51fd\u6570 StringEntity\uff08String, Charset\uff09\u672a\u5b9a\u4e49\n");
    }
}
