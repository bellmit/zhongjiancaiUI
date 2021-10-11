package com.kingdee.eas.moya.common;

import org.apache.log4j.*;
import java.util.*;
import java.io.*;

import com.kingdee.eas.moya.common.sso.util.Strs;
//import com.moya.api.sso.util.*;

public class PropUtil
{
    private static Logger logger;
    public static final String CONFIG_FILE_PATH = "/server/properties/app/appConfig.properties";
    private static Properties mailConfig;
    
    static {
        PropUtil.logger = Logger.getLogger((Class)PropUtil.class);
        PropUtil.mailConfig = null;
        PropUtil.mailConfig = new Properties();
        final String easHome = System.getProperty("EAS_HOME");
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(String.valueOf(easHome) + "/server/properties/app/appConfig.properties"));
            PropUtil.mailConfig.load(in);
        }
        catch (FileNotFoundException e) {
            PropUtil.logger.error((Object)(String.valueOf(easHome) + "/server/properties/app/appConfig.properties" + "file not found."), (Throwable)e);
        }
        catch (IOException e2) {
            PropUtil.logger.error((Object)(String.valueOf(easHome) + "/server/properties/app/appConfig.properties" + "file load error."), (Throwable)e2);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ex) {}
        }
        try {
            if (in != null) {
                in.close();
            }
        }
        catch (IOException ex2) {}
    }
    
    public static String getParameterByName(final String paraName) {
        final String paraValue = PropUtil.mailConfig.getProperty(paraName);
        if (paraValue == null) {
            PropUtil.logger.error((Object)("[App Config] WARNING: Failed to get parameter(" + paraName + ") from config."));
        }
        return paraValue;
    }
    
    public static boolean isDevMode() {
        final String v = getParameterByName("devMode");
        return !Strs.isEmpty(v) && ("true".equalsIgnoreCase(v) || "1".equalsIgnoreCase(v));
    }
    
    public static float getEASVersion() {
        return new Float(getParameterByName("eas.version"));
    }
}
