package com.kingdee.eas.moya.common.sso.util;

import javax.servlet.http.*;

public final class CookiesUtil
{
    public static String getCookieValueByName(final HttpServletRequest request, final String cookieName) {
        final Cookie[] cookies = request.getCookies();
        if (cookies != null && cookieName != null) {
            for (int i = 0; i < cookies.length; ++i) {
                if (cookieName.equals(cookies[i].getName())) {
                    return cookies[i].getValue();
                }
            }
        }
        return null;
    }
}
