package com.kingdee.eas.moya.message.app.landray;


import javax.servlet.http.*;

//import com.moya.api.config.*;
//import com.moya.api.sso.util.*;
//import com.moya.api.sso.user.*;

import com.kingdee.eas.moya.common.sso.user.IUserNameBuilder;
import com.kingdee.eas.moya.common.sso.util.CookiesUtil;
import com.kingdee.eas.moya.common.sso.util.Strs;

public class LandrayUserNameBuilder implements IUserNameBuilder
{
    public String getUserName(final HttpServletRequest request) {
        //final String ssoToken = CookiesUtil.getCookieValueByName(request, ApiConfigParameters.getParameterByName("landray.cookies.name"));
    	final String ssoToken = CookiesUtil.getCookieValueByName(request, "ssoToken");
        if (Strs.isEmpty(ssoToken)) {
            return null;
        }
        try {
            System.out.println("Landray ssoToken=" + ssoToken);
            final DESUtil desUtil = new DESUtil();
            final String key = "";
            return DESUtil.getUserName(ssoToken, key);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Get Landray user error. ssoToken=" + ssoToken);
            return null;
        }
        
    }
}
