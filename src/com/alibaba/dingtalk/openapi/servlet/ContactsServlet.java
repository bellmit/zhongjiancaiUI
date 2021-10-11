package com.alibaba.dingtalk.openapi.servlet;

import javax.servlet.http.*;
import com.alibaba.dingtalk.openapi.demo.auth.*;
import com.alibaba.dingtalk.openapi.demo.department.*;
import com.alibaba.dingtalk.openapi.demo.user.*;
import com.alibaba.fastjson.*;
import com.dingtalk.open.client.api.model.corp.*;
import java.util.*;
import java.io.*;

public class ContactsServlet extends HttpServlet
{
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        try {
            response.setContentType("text/html; charset=utf-8");
            final String accessToken = AuthHelper.getAccessToken();
            List<Department> departments = new ArrayList<Department>();
            departments = DepartmentHelper.listDepartments(accessToken, "1");
            final JSONObject json = new JSONObject();
            final JSONArray usersArray = new JSONArray();
            System.out.println("depart num:" + departments.size());
            for (int i = 0; i < departments.size(); ++i) {
                final JSONObject usersJSON = new JSONObject();
                final JSONArray userArray = new JSONArray();
                long offset = 0L;
                final int size = 5;
                CorpUserList corpUserList = new CorpUserList();
                while (true) {
                    corpUserList = UserHelper.getDepartmentUser(accessToken, Long.valueOf(departments.get(i).getId()), offset, size, null);
                    System.out.println(JSON.toJSONString((Object)corpUserList));
                    if (!Boolean.TRUE.equals(corpUserList.isHasMore())) {
                        break;
                    }
                    offset += size;
                }
                if (corpUserList.getUserlist().size() != 0) {
                    for (int j = 0; j < corpUserList.getUserlist().size(); ++j) {
                        final String user = JSON.toJSONString(corpUserList.getUserlist().get(j));
                        userArray.add(JSONObject.parseObject(user, (Class)CorpUserDetail.class));
                    }
                    System.out.println("user:" + userArray.toString());
                    usersJSON.put("name", (Object)departments.get(i).getName());
                    usersJSON.put("member", (Object)userArray);
                    usersArray.add((Object)usersJSON);
                }
            }
            json.put("department", (Object)usersArray);
            System.out.println("depart:" + json.toJSONString());
            response.getWriter().append(json.toJSONString());
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
