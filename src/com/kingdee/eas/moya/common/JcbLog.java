package com.kingdee.eas.moya.common;

import java.util.*;
import java.text.*;
import java.io.*;

public class JcbLog
{
    public static final int LOG_LEVEL_DUMP = 1;
    public static final int LOG_LEVEL_TRACE = 2;
    public static final int LOG_LEVEL_NORMAL = 3;
    public static final int LOG_LEVEL_ERROR = 4;
    public static final int LOG_LEVEL_NONE = 5;
    protected static boolean log2console;
    protected static boolean log2file;
    protected static String path;
    protected static int level;
    
    static {
        JcbLog.log2console = false;
        JcbLog.log2file = true;
        JcbLog.path = String.valueOf(System.getProperty("user.dir")) + "\\jcblog.txt";
        JcbLog.level = 5;
    }
    
    public static void setLevel(final int l) {
        if (l == 2 || l == 3 || l == 5 || l == 1 || l == 4) {
            JcbLog.level = l;
        }
        else {
            JcbLog.level = 3;
        }
    }
    
    public static void setPath(final String s) {
        JcbLog.path = s;
    }
    
    public static void setFile(final boolean b) {
        JcbLog.log2file = b;
    }
    
    public static void setConsole(final boolean b) {
        JcbLog.log2console = b;
    }
    
    public static int getLevel() {
        return JcbLog.level;
    }
    
    public static String getPath() {
        return JcbLog.path;
    }
    
    public static boolean getConsole() {
        return JcbLog.log2console;
    }
    
    public static boolean getFile() {
        return JcbLog.log2file;
    }
    
    public static void logException(final String event, final Exception e) {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final PrintStream ps = new PrintStream(bos);
        e.printStackTrace(ps);
        ps.close();
        log(4, String.valueOf(event) + e + ":" + bos);
        try {
            bos.close();
        }
        catch (IOException ex) {}
    }
    
    public static synchronized void log(final int level, final String event) {
        if (level == 5) {
            return;
        }
        final Date dt = new Date();
        final SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ssss");
        String log = "[" + bartDateFormat.format(dt) + "] [";
        switch (level) {
            case 2: {
                log = String.valueOf(log) + "TRACE";
                break;
            }
            case 3: {
                log = String.valueOf(log) + "NORMAL";
                break;
            }
            case 4: {
                log = String.valueOf(log) + "ERROR";
                break;
            }
            case 5: {
                log = String.valueOf(log) + "NONE?";
                break;
            }
            case 1: {
                log = String.valueOf(log) + "DUMP";
                break;
            }
        }
        log = String.valueOf(log) + "][" + Thread.currentThread().getName() + "] " + event;
        if (JcbLog.log2console) {
            System.err.println(log);
        }
        if (JcbLog.log2file) {
            try {
                final FileOutputStream fw = new FileOutputStream(JcbLog.path, true);
                final PrintStream ps = new PrintStream(fw);
                ps.println(log);
                ps.close();
                fw.close();
            }
            catch (IOException ex) {}
        }
    }
}
