package com.octal.thirdparty.kdyzj.api;

public class StringUtils
{
    public static boolean isEmpty(final String str) {
        return str == null || str.trim().length() == 0;
    }
    
    public static String getProperty(final String key) {
        return PropertiesUtils.getInstance().getProperty(key);
    }
}
