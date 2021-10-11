package com.kingdee.eas.custom.app;

import org.apache.log4j.*;

public class WebAuditLogControllerBean extends AbstractWebAuditLogControllerBean
{
    private static Logger logger;
    
    static {
        WebAuditLogControllerBean.logger = Logger.getLogger("com.kingdee.eas.custom.app.WebAuditLogControllerBean");
    }
}
