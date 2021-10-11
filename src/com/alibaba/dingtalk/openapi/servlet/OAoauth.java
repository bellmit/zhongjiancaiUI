package com.alibaba.dingtalk.openapi.servlet;

import javax.servlet.http.*;
import com.alibaba.dingtalk.openapi.demo.auth.*;
import com.alibaba.dingtalk.openapi.demo.user.*;
import com.alibaba.dingtalk.openapi.demo.*;
import com.alibaba.fastjson.*;
import java.io.*;
import javax.servlet.*;

public class OAoauth extends HttpServlet
{
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String code = request.getParameter("code");
        if (code != null) {
            try {
                final String ssoToken = AuthHelper.getSsoToken();
                response.getWriter().append(ssoToken);
                final JSONObject js = UserHelper.getAgentUserInfo(ssoToken, code);
                response.getWriter().append(js.toString());
            }
            catch (OApiException e) {
                e.printStackTrace();
            }
        }
        else {
            final String reurl = "https://oa.dingtalk.com/omp/api/micro_app/admin/landing?corpid=" + Env.CORP_ID + "&redirect_url=";
            response.addHeader("location", reurl);
            response.setStatus(302);
        }
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
