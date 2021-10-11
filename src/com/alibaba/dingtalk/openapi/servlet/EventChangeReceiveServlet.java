package com.alibaba.dingtalk.openapi.servlet;

import javax.servlet.http.*;
import com.alibaba.fastjson.*;
import com.alibaba.dingtalk.openapi.demo.*;
import com.alibaba.dingtalk.openapi.demo.utils.aes.*;
import java.util.*;
import javax.servlet.*;
import java.io.*;

public class EventChangeReceiveServlet extends HttpServlet
{
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String msgSignature = request.getParameter("signature");
        final String timeStamp = request.getParameter("timestamp");
        final String nonce = request.getParameter("nonce");
        final ServletInputStream sis = request.getInputStream();
        final BufferedReader br = new BufferedReader(new InputStreamReader((InputStream)sis));
        String line = null;
        final StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        final JSONObject jsonEncrypt = JSONObject.parseObject(sb.toString());
        final String encrypt = jsonEncrypt.getString("encrypt");
        DingTalkEncryptor dingTalkEncryptor = null;
        String plainText = null;
        try {
            dingTalkEncryptor = new DingTalkEncryptor("", "", Env.CORP_ID);
            plainText = dingTalkEncryptor.getDecryptMsg(msgSignature, timeStamp, nonce, encrypt);
        }
        catch (DingTalkEncryptException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        final JSONObject plainTextJson = JSONObject.parseObject(plainText);
        final String eventType = plainTextJson.getString("EventType");
        final long timeStampLong = Long.parseLong(timeStamp);
        Map<String, String> jsonMap = null;
        try {
            jsonMap = dingTalkEncryptor.getEncryptedMap("success", timeStampLong, nonce);
        }
        catch (DingTalkEncryptException e2) {
            System.out.println(e2.getMessage());
            e2.printStackTrace();
        }
        final JSONObject json = new JSONObject();
        json.putAll((Map)jsonMap);
        response.getWriter().append(json.toString());
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
