package com.kingdee.eas.custom;

public class MYTimeLogUtil
{
    private static boolean isEnable;
    
    static {
        MYTimeLogUtil.isEnable = true;
    }
    
    public static void printTime(final long startTime, final String desc) {
        if (MYTimeLogUtil.isEnable) {
            final long end = System.currentTimeMillis();
            System.out.println(String.valueOf(desc) + ":" + (end - startTime) / 1000L + "s");
        }
    }
}
