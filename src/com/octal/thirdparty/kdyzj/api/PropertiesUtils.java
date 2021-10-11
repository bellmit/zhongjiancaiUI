package com.octal.thirdparty.kdyzj.api;

import org.apache.log4j.*;

import java.util.*;
import java.io.*;

public class PropertiesUtils
{
    static Logger logger;
    private static PropertiesUtils parse;
    private static Properties properties;
    static String path;
    
    static {
        PropertiesUtils.logger = Logger.getLogger((Class)PropertiesUtils.class);
        PropertiesUtils.parse = new PropertiesUtils();
    }
    
    public static void init(final String path) {
        PropertiesUtils.path = path;
        System.out.println(path);
    }
    
    public String getProperty(final String key) {
        return PropertiesUtils.properties.getProperty(key);
    }
    
    public String getProperty(final String key, final String defaultValue) {
        return PropertiesUtils.properties.getProperty(key, defaultValue);
    }
    
    public void setProperty(final String key, final String value) {
        try {
            PropertiesUtils.properties.load(new FileInputStream(PropertiesUtils.path));
            final OutputStream fos = new FileOutputStream(PropertiesUtils.path);
            PropertiesUtils.properties.setProperty(key, value);
            PropertiesUtils.properties.store(fos, "Update '" + key + "' value");
        }
        catch (IOException e) {
            System.err.println("\u5c5e\ufffd?\u6587\u4ef6\u66f4\u65b0\u9519\u8bef");
        }
    }
    
    public static PropertiesUtils getInstance() {
        if (PropertiesUtils.properties == null) {
            try {
                PropertiesUtils.properties = new Properties();
                final InputStream in = PropertiesUtils.class.getResourceAsStream(PropertiesUtils.path);
                PropertiesUtils.properties.load(in);
            }
            catch (FileNotFoundException e) {
                PropertiesUtils.logger.info((Object)(String.valueOf(e.getMessage()) + e.getCause()));
            }
            catch (IOException e2) {
                PropertiesUtils.logger.info((Object)(String.valueOf(e2.getMessage()) + e2.getCause()));
            }
        }
        return PropertiesUtils.parse;
    }
}
