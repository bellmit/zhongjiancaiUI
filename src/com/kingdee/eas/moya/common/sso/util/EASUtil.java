package com.kingdee.eas.moya.common.sso.util;

import com.kingdee.eas.moya.common.*;

public class EASUtil
{
    private static final float majorEasVersion;
    
    static {
        majorEasVersion = getMajorEASVersion();
    }
    
    private static final float getMajorEASVersion() {
        return PropUtil.getEASVersion();
    }
    
    public static boolean is80() {
        return EASUtil.majorEasVersion < 8.2f && !is20();
    }
    
    public static boolean is82() {
        return EASUtil.majorEasVersion >= 8.2f;
    }
    
    public static boolean is20() {
        return EASUtil.majorEasVersion <= 2.0f;
    }
}
