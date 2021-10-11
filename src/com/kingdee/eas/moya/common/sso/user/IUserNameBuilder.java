package com.kingdee.eas.moya.common.sso.user;

import javax.servlet.http.*;

public interface IUserNameBuilder
{
    String getUserName(final HttpServletRequest p0);
}
