package com.kingdee.eas.moya.common.sso.util;

import javax.servlet.http.*;
import java.util.*;
import com.kingdee.eas.moya.common.*;
import com.kingdee.util.*;
import com.kingdee.eas.cp.eip.sso.ltpa.*;
import com.kingdee.eas.moya.common.sso.user.*;
//import com.moya.api.config.*;

public class SSOUtil
{
    private static final String EMPTY = "";
    
    public static String generateUrl(final HttpServletRequest request) {
        return generateUrl(request, null, false);
    }
    
    public static String generateUrl(final HttpServletRequest request, final boolean supportWorkflow) {
        return generateUrl(request, null, supportWorkflow);
    }
    
    public static String generateUrl(final HttpServletRequest request, final String serverUrl, final boolean supportWorkflow) {
        return generateUrl(request, new HashMap<String, Object>(), serverUrl, supportWorkflow);
    }
    
    public static String testUrl(final HttpServletRequest request, final String serverUrl, final String username, final boolean supportWorkflow) {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("username", username);
        return generateUrl(request, parameters, serverUrl, supportWorkflow);
    }
    
    public static String generateUrl(final HttpServletRequest request, final Map<String, Object> parameters, final String serverUrl, final boolean supportWorkflow) {
        assembleLoginParameters(request, parameters, supportWorkflow);
        if (parameters.get("username") == null) {
            return "";
        }
        final String loginUrl = getLoginUrl(request, serverUrl);
        return UrlUtil.assembleUrl(loginUrl, parameters);
    }
    
    public static String getLoginUrl(final HttpServletRequest request, String serverUrl) {
        if (serverUrl == null) {
            serverUrl = PropUtil.getParameterByName("server.url");
        }
        final String redirectTo = request.getParameter("redirectTo");
        if (!StringUtils.isEmpty(redirectTo) && redirectTo.indexOf("easweb") != -1) {
            return String.valueOf(serverUrl) + PropUtil.getParameterByName("server.eas.path");
        }
        final String target = request.getParameter("target");
        if (!StringUtils.isEmpty(target) && "eas".equalsIgnoreCase(target)) {
            return String.valueOf(serverUrl) + PropUtil.getParameterByName("server.eas.path");
        }
        return String.valueOf(serverUrl) + PropUtil.getParameterByName("server.path");
    }
    
    public static Map<String, Object> assembleLoginParameters(final HttpServletRequest request, Map<String, Object> parameters, final boolean supportWorkflow) {
        if (parameters == null) {
            parameters = new HashMap<String, Object>();
        }
        String username = request.getParameter("username");
        if (username == null) {
            username = getUserName(request);
        }
        parameters.put("username", username);
        if (username != null) {
            String password = null;
            if (!EASUtil.is20()) {
                password = LtpaTokenManager.generate(username, LtpaTokenManager.getDefaultLtpaConfig()).toString();
            }
            parameters.put("password", password);
        }
        final String redirectTo = request.getParameter("redirectTo");
        System.out.println("redirectTo=\t" + redirectTo);
        System.out.println("supportWorkflow=\t" + supportWorkflow);
        if (redirectTo != null && redirectTo.trim() != null) {
            if (supportWorkflow) {
                parameters.put("redirectTo", WorkflowUtil.getUrl(request));
            }
            else {
                parameters.put("redirectTo", redirectTo);
            }
        }
        else if (EASUtil.is82()) {
            parameters.put("redirectTo", PropUtil.getParameterByName("server.eas.redirectTo"));
        }
        final String callback = request.getParameter("callback");
        if (callback != null && callback.trim() != null) {
            parameters.put("callback", callback);
        }
        final String assignmentId = request.getParameter("assignmentid");
        if (assignmentId != null && assignmentId.trim() != null) {
            parameters.put("assignmentid", assignmentId);
        }
        final String dataCenter = request.getParameter("dataCenter");
        if (!StringUtils.isEmpty(dataCenter)) {
            parameters.put("dataCenter", dataCenter);
        }
        return parameters;
    }
    
    private static String getUserName(final HttpServletRequest request) {
        final String userNameBuilder = PropUtil.getParameterByName("userNameBuilder");
        if (userNameBuilder == null) {
            return null;
        }
        try {
            final Class clazz = Class.forName(userNameBuilder);
            final Object obj = clazz.newInstance();
            if (obj instanceof IUserNameBuilder) {
                return ((IUserNameBuilder)obj).getUserName(request);
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
        catch (InstantiationException e3) {
            e3.printStackTrace();
        }
        return null;
    }
    
    public static String getServerUrl(final HttpServletRequest request) {
    	return "http://kd.i-leasing.cn:16888";
        //return ApiConfigParameters.getParameterByName("server.url");
    }
}
