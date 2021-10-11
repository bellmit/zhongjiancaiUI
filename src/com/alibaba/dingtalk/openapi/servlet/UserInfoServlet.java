package com.alibaba.dingtalk.openapi.servlet;

import javax.servlet.http.*;
import com.alibaba.dingtalk.openapi.demo.auth.*;
import com.alibaba.dingtalk.openapi.demo.user.*;
import com.alibaba.fastjson.*;
import com.dingtalk.open.client.api.model.corp.*;
import java.io.*;

public class UserInfoServlet extends HttpServlet
{
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String code = request.getParameter("code");
        final String corpId = request.getParameter("corpid");
        System.out.println("authCode:" + code + " corpid:" + corpId);
        try {
            response.setContentType("text/html; charset=utf-8");
            final String accessToken = AuthHelper.getAccessToken();
            System.out.println("access token:" + accessToken);
            final CorpUserDetail user = UserHelper.getUser(accessToken, UserHelper.getUserInfo(accessToken, code).getUserid());
            final String userJson = JSON.toJSONString((Object)user);
            response.getWriter().append(userJson);
            System.out.println("userjson:" + userJson);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.getWriter().append(e.getMessage());
        }
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        this.doGet(request, response);
    }
}
