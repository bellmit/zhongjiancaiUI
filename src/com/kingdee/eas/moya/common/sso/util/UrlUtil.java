package com.kingdee.eas.moya.common.sso.util;

import java.net.*;
import java.io.*;
import java.util.*;

public class UrlUtil
{
    private static final String EQUALS = "=";
    
    public static String assembleUrl(final String url, final Map<String, Object> params) {
        final StringBuilder redirectUrl = new StringBuilder(url);
        if (redirectUrl.indexOf("?") == -1) {
            redirectUrl.append('?');
        }
        else {
            redirectUrl.append('&');
        }
        String key = null;
        String value = null;
        int index = 0;
        final Iterator<String> paramIterator = params.keySet().iterator();
        while (paramIterator.hasNext()) {
            key = paramIterator.next();
            if (index != 0) {
                redirectUrl.append('&');
            }
            redirectUrl.append(key);
            redirectUrl.append("=");
            value = params.get(key).toString();
            try {
                redirectUrl.append(URLEncoder.encode(value, "UTF-8"));
            }
            catch (UnsupportedEncodingException e) {
                redirectUrl.append(URLEncoder.encode(value));
            }
            ++index;
        }
        return redirectUrl.toString();
    }
}
