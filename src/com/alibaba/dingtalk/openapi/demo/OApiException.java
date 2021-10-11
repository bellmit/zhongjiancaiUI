package com.alibaba.dingtalk.openapi.demo;

public class OApiException extends Exception
{
    public static final int ERR_RESULT_RESOLUTION = -2;
    
    public OApiException(final String field) {
        this(-2, "Cannot resolve field " + field + " from oapi resonpse");
    }
    
    public OApiException(final int errCode, final String errMsg) {
        super("error code: " + errCode + ", error message: " + errMsg);
    }
}
