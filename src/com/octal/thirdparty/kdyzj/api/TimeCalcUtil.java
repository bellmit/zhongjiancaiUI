package com.octal.thirdparty.kdyzj.api;

import org.slf4j.*;
import org.apache.commons.lang.*;
 

public class TimeCalcUtil
{
    private static final Logger logger;
    private static ThreadLocal<Long> startTime;
    private static ThreadLocal<String> url;
    private static ThreadLocal<String> reqType;
    
    static {
        logger = LoggerFactory.getLogger((Class)TimeCalcUtil.class);
        TimeCalcUtil.startTime = new ThreadLocal<Long>();
        TimeCalcUtil.url = new ThreadLocal<String>();
        TimeCalcUtil.reqType = new ThreadLocal<String>();
    }
    
    public static void setReqType(final String reqType) {
        TimeCalcUtil.reqType.set(reqType);
    }
    
    public static String getReqType() {
        return TimeCalcUtil.reqType.get();
    }
    
    public static void setStartTimeUrl(final long startTime, final String url) {
        if (StringUtils.isEmpty(url)) {
            TimeCalcUtil.logger.error("url is empty or null!");
            return;
        }
        TimeCalcUtil.startTime.set(startTime);
        TimeCalcUtil.url.set(url);
    }
    
    public static void logRunTime() {
        final Long start = TimeCalcUtil.startTime.get();
        if (start == null || 0L == start) {
            return;
        }
        final String thisUrl = TimeCalcUtil.url.get();
        if (StringUtils.isEmpty(thisUrl)) {
            TimeCalcUtil.logger.error("url is empty or null!");
        }
    }
}
