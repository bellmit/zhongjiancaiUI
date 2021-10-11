package com.alibaba.dingtalk.openapi.demo.auth;

import com.alibaba.dingtalk.openapi.demo.Env;
import com.alibaba.dingtalk.openapi.demo.OApiException;
import com.alibaba.dingtalk.openapi.demo.utils.FileUtils;
import com.alibaba.dingtalk.openapi.demo.utils.HttpHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.oapi.lib.aes.DingTalkJsApiSingnature;
import com.dingtalk.open.client.ServiceFactory;
import com.dingtalk.open.client.api.model.corp.JsapiTicket;
import com.dingtalk.open.client.api.service.corp.CorpConnectionService;
import com.dingtalk.open.client.api.service.corp.JsapiService;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * AccessToken��jsticket�Ļ�ȡ��װ
 */
public class AuthHelper {

    /**
     * ������1Сʱ50����
     */
    public static final long cacheTime = 1000 * 60 * 55 * 2;

    /**
     * �ڴ˷����У�Ϊ�˱���Ƶ����ȡaccess_token��
     * �ھ�����һ�λ�ȡaccess_tokenʱ��������Сʱ֮�ڵ������
     * ��ֱ�Ӵӳ־û��洢�ж�ȡaccess_token
     *
     * ��Ϊaccess_token��jsapi_ticket�Ĺ���ʱ�䶼��7200��
     * �����ڻ�ȡaccess_token��ͬʱҲȥ��ȡ��jsapi_ticket
     * ע��jsapi_ticket����ǰ��ҳ��JSAPI��Ȩ����֤���õ�ʱ����Ҫʹ�õ�
     * ������Ϣ��鿴�������ĵ�--Ȩ����֤����
     */
    public static String getAccessToken() throws OApiException {
        long curTime = System.currentTimeMillis();
        System.out.println("*************************curTime="+curTime);
        JSONObject accessTokenValue = (JSONObject) FileUtils.getValue("accesstoken", Env.APP_KEY);
        System.out.println("*************************APP_KEY="+Env.APP_KEY);
        System.out.println("*************************APP_SECRET="+Env.APP_SECRET);
        String accToken = "";
        JSONObject jsontemp = new JSONObject();
        if (accessTokenValue == null || curTime - accessTokenValue.getLong("begin_time") >= cacheTime) {
            try {
                ServiceFactory serviceFactory = ServiceFactory.getInstance(); 
                CorpConnectionService corpConnectionService = serviceFactory.getOpenService(CorpConnectionService.class);
                accToken = corpConnectionService.getCorpToken(Env.APP_KEY, Env.APP_SECRET);
                // save accessToken
                JSONObject jsonAccess = new JSONObject();
                jsontemp.clear();
                jsontemp.put("access_token", accToken);
                jsontemp.put("begin_time", curTime);
                jsonAccess.put(Env.APP_KEY, jsontemp);
                //��ʵ��Ŀ����ñ��浽���ݿ���
                FileUtils.write2File(jsonAccess, "accesstoken");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return accessTokenValue.getString("access_token");
        }
        System.out.println("*************************accToken="+accToken);
        return accToken;
    }

    /**
     * ��ȡJSTicket, ����js��ǩ������
     * ����������£�jsapi_ticket����Ч��Ϊ7200�룬���Կ�������Ҫ��ĳ���ط����һ����ʱ��������ȥ����jsapi_ticket
     */
    public static String getJsapiTicket(String accessToken) throws OApiException {
        JSONObject jsTicketValue = (JSONObject) FileUtils.getValue("jsticket", Env.APP_KEY);
        long curTime = System.currentTimeMillis();
        String jsTicket = "";

        if (jsTicketValue == null || curTime -
                jsTicketValue.getLong("begin_time") >= cacheTime) {
            ServiceFactory serviceFactory;
            try {
                serviceFactory = ServiceFactory.getInstance();
                JsapiService jsapiService = serviceFactory.getOpenService(JsapiService.class);

                JsapiTicket JsapiTicket = jsapiService.getJsapiTicket(accessToken, "jsapi");
                jsTicket = JsapiTicket.getTicket();

                JSONObject jsonTicket = new JSONObject();
                JSONObject jsontemp = new JSONObject();
                jsontemp.clear();
                jsontemp.put("ticket", jsTicket);
                jsontemp.put("begin_time", curTime);
                jsonTicket.put(Env.APP_KEY, jsontemp);
                FileUtils.write2File(jsonTicket, "jsticket");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsTicket;
        } else {
            return jsTicketValue.getString("ticket");
        }
    }

    public static String sign(String ticket, String nonceStr, long timeStamp, String url) throws OApiException {
        try {
            return DingTalkJsApiSingnature.getJsApiSingnature(url, nonceStr, timeStamp, ticket);
        } catch (Exception ex) {
            throw new OApiException(0, ex.getMessage());
        }
    }

    /**
     * ���㵱ǰ�����jsapi��ǩ������<br/>
     * <p>
     * ���ǩ��������ͨ��ajax�첽����Ļ���ǩ�������е�url�����Ǹ��û�չʾҳ���url
     *
     * @param request
     * @return
     */
    public static String getConfig(HttpServletRequest request) {
        String urlString = request.getRequestURL().toString();
        String queryString = request.getQueryString();

        String queryStringEncode = null;
        String url;
        if (queryString != null) {
            queryStringEncode = URLDecoder.decode(queryString);
            url = urlString + "?" + queryStringEncode;
        } else {
            url = urlString;
        }
        /**
         * ȷ��url�����õ�Ӧ����ҳ��ַһ��
         */
        System.out.println(url);

        /**
         * ����ַ���
         */
        String nonceStr = "abcdefg";
        long timeStamp = System.currentTimeMillis() / 1000;
        String signedUrl = url;
        String accessToken = null;
        String ticket = null;
        String signature = null;

        try {
            accessToken = AuthHelper.getAccessToken();

            ticket = AuthHelper.getJsapiTicket(accessToken);
            signature = AuthHelper.sign(ticket, nonceStr, timeStamp, signedUrl);

        } catch (OApiException e) {
            e.printStackTrace();
        }

        Map<String, Object> configValue = new HashMap<String, Object>();
        configValue.put("jsticket", ticket);
        configValue.put("signature", signature);
        configValue.put("nonceStr", nonceStr);
        configValue.put("timeStamp", timeStamp);
        configValue.put("corpId", Env.CORP_ID);
        configValue.put("agentId", Env.AGENT_ID);

        String config = JSON.toJSONString(configValue);
        return config;
    }

    public static String getSsoToken() throws OApiException {
        String url = "https://oapi.dingtalk.com/sso/gettoken?corpid=" + Env.CORP_ID + "&corpsecret=" + Env.SSO_Secret;
        JSONObject response = HttpHelper.httpGet(url);
        String ssoToken;
        if (response.containsKey("access_token")) {
            ssoToken = response.getString("access_token");
        } else {
            throw new OApiException("Sso_token");
        }
        return ssoToken;

    }
}
