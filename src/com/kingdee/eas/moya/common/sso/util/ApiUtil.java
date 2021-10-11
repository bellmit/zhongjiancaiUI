package com.kingdee.eas.moya.common.sso.util;

import javax.servlet.http.*;

public class ApiUtil
{
    public static boolean isMobileDevice(final HttpServletRequest request) {
        final String ua = request.getParameter("ua");
        if (!Strs.isEmpty(ua)) {
            return "ios".equals(ua) || "android".equals(ua);
        }
        final String userAgent = request.getHeader("User-Agent");
        return userAgent.matches(".*Android.*") || userAgent.matches(".*iPhone.*") || userAgent.matches(".*iPad.*");
    }
}
