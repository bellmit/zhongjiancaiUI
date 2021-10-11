package com.kingdee.eas.moya.message.app.wechat.comp;

import com.kingdee.eas.moya.common.*;
import org.apache.http.entity.*;
import java.net.*;
import org.apache.http.util.*;
import org.apache.http.impl.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.*;
import com.alibaba.fastjson.*;
import java.util.*;
import java.io.*;

public final class Notifies
{
    public static String getToken() {
        final String corpid = PropUtil.getParameterByName("wx.comp.corpid");
        final String corpsecret = PropUtil.getParameterByName("wx.comp.corpsecret");
        final String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret;
        final String content = HttpUtil.myPost(url, null);
        final JSONObject obj = JSONObject.parseObject(content);
        if (obj.getInteger("errcode") == 0) {
            return obj.getString("access_token");
        }
        return null;
    }
    
    public static String getUserInfo(final String userid, final String token) throws Exception {
        final String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=" + token + "&userid=" + userid;
        final HttpGet httpGet = new HttpGet();
        httpGet.setURI(new URI(url.toString()));
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final CloseableHttpResponse res = httpClient.execute((HttpUriRequest)httpGet);
        final HttpEntity resEntity = res.getEntity();
        final String content = EntityUtils.toString(resEntity, "utf-8");
        return content;
    }
    
    public static JSONArray getAllUserInfo(final String token, final Set<String> existIds) throws Exception {
        final Set<String> departments = getAllDepartment(token);
        final Iterator<String> it = departments.iterator();
        final JSONArray retArr = new JSONArray();
        while (it.hasNext()) {
            final String department = it.next();
            final String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=" + token + "&department_id=" + department;
            final String depData = HttpUtil.myPost(url, null);
            final JSONObject obj = JSONObject.parseObject(depData);
            if (obj.getInteger("errcode") != 0) {
                throw new RuntimeException(obj.getString("errmsg"));
            }
            final JSONArray userList = obj.getJSONArray("userlist");
            for (int i = 0; i < userList.size(); ++i) {
                final JSONObject userObj = userList.getJSONObject(i);
                final String userid = userObj.getString("userid");
                if (existIds == null || !existIds.contains(userid)) {
                    final String userInfo = getUserInfo(userid, token);
                    retArr.add((Object)JSONObject.parseObject(userInfo));
                }
            }
        }
        return retArr;
    }
    
    public static Set<String> getAllDepartment(final String token) {
        final Set<String> set = new HashSet<String>();
        final String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=" + token;
        final String depData = HttpUtil.myPost(url, null);
        final JSONObject obj = JSONObject.parseObject(depData);
        if (obj.getInteger("errcode") == 0) {
            final JSONArray departments = obj.getJSONArray("department");
            for (int i = 0; i < departments.size(); ++i) {
                final JSONObject department = departments.getJSONObject(i);
                final String id = department.getString("id");
                set.add(id);
            }
            return set;
        }
        throw new RuntimeException(obj.getString("errmsg"));
    }
    
    public static String getUserId(final String userName, final String token) {
        final String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=" + token + "&department_id=1";
        final String depData = HttpUtil.myPost(url, null);
        final JSONObject obj = JSONObject.parseObject(depData);
        if (obj.getInteger("errcode") == 0) {
            final JSONArray arr = obj.getJSONArray("userlist");
            for (int i = 0; i < arr.size(); ++i) {
                final JSONObject userObj = arr.getJSONObject(i);
                if (userName.equals(userObj.getString("name"))) {
                    return userObj.getString("userid");
                }
            }
        }
        return null;
    }
    
    public static String getUserId(final String userName) {
        final String token = getToken();
        return getUserId(userName, token);
    }
    
    public static JSONObject sendMsg(final String token, final JSONObject entity) throws UnsupportedEncodingException {
        throw new Error("\u65e0\u6cd5\u89e3\u6790\u7684\u7f16\u8bd1\u95ee\u9898\uff1a\n\t\u6784\u9020\u51fd\u6570 StringEntity\uff08String, Charset\uff09\u672a\u5b9a\u4e49\n");
    }
    
    public static JSONObject sendMsg(final String userid, final String title, final String description, final String url, final String btntxt) throws Exception {
        final String token = getToken();
        final JSONObject entity = initTextCard(userid, title, description, url, btntxt);
        return sendMsg(token, entity);
    }
    
    public static JSONObject initTextCard(final String userid, final String title, final String description, final String url, final String btntxt) {
        final String agentid = PropUtil.getParameterByName("wx.comp.agentid");
        final JSONObject gObj = new JSONObject();
        gObj.put("touser", (Object)userid);
        gObj.put("msgtype", (Object)"textcard");
        gObj.put("agentid", (Object)agentid);
        final JSONObject obj = new JSONObject();
        obj.put("title", (Object)title);
        obj.put("description", (Object)description);
        obj.put("url", (Object)url);
        obj.put("btntxt", (Object)btntxt);
        gObj.put("textcard", (Object)obj);
        return gObj;
    }
    
    public static String getUserTicket(final String code, final String token) throws Exception {
        final StringBuffer url = new StringBuffer("https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo");
        url.append("?access_token=").append(token);
        url.append("&code=").append(code);
        final HttpGet httpGet = new HttpGet();
        httpGet.setURI(new URI(url.toString()));
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final CloseableHttpResponse res = httpClient.execute((HttpUriRequest)httpGet);
        final HttpEntity resEntity = res.getEntity();
        final String content = EntityUtils.toString(resEntity, "utf-8");
        return content;
    }
    
    public static String getUserPhone(final String code, final String token) throws Exception {
        final String ticketRetStr = getUserTicket(code, token);
        final JSONObject ticketretObj = JSONObject.parseObject(ticketRetStr);
        if (ticketretObj.getInteger("errcode") != 0) {
            throw new RuntimeException(ticketretObj.getString("errmsg"));
        }
        final String ticket = ticketretObj.getString("user_ticket");
        final String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail?access_token=" + token;
        final JSONObject entityObj = new JSONObject();
        entityObj.put("user_ticket", (Object)ticket);
        final String retStr = HttpUtil.myPost(url, new StringEntity(entityObj.toJSONString()));
        final JSONObject retObj = JSONObject.parseObject(retStr);
        if (retObj.getInteger("errcode") != 0) {
            throw new RuntimeException(retObj.getString("errmsg"));
        }
        return retObj.getString("mobile");
    }
}
