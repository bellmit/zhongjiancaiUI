package com.octal.thirdparty.kdyzj.api;

public class Constants
{
    public static final int HTTP_CLIENT_SOTIMEOUT = 120000;
    public static final int HTTP_CLIENT_CONNECTIONTIMEOUT = 120000;
    public static final int HTTP_CLIENT_MAXTOTAL = 1000;
    public static final int HTTP_CLIENT_MAXPERROTE = 40;
    
    public static long getImageExpires() {
        return System.currentTimeMillis() + 604800000L;
    }
}
