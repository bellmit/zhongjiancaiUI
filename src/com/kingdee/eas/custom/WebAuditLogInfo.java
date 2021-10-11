package com.kingdee.eas.custom;

import java.io.*;

public class WebAuditLogInfo extends AbstractWebAuditLogInfo implements Serializable
{
    public WebAuditLogInfo() {
    }
    
    protected WebAuditLogInfo(final String pkField) {
        super(pkField);
    }
}
