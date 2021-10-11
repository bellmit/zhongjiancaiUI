package com.octal.thirdparty.kdyzj.api;

import org.apache.log4j.*;

public final class LogHelper
{
    private static Logger log;
    
    static {
        LogHelper.log = Logger.getLogger((Class)LogHelper.class);
    }
    
    public static void info(final Object o) {
        LogHelper.log.info(o);
    }
    
    public static void info(final Object o, final Throwable e) {
        LogHelper.log.info(o, e);
    }
    
    public static void error(final Object o) {
        LogHelper.log.error(o);
    }
    
    public static void error(final Object o, final Throwable e) {
        LogHelper.log.error(o, e);
    }
    
    public static void debug(final Object o) {
        LogHelper.log.debug(o);
    }
    
    public static void debug(final Object o, final Throwable e) {
        LogHelper.log.debug(o, e);
    }
}
