package com.octal.thirdparty.kdyzj.api;

public class YzjBackData
{
    private String success;
    private String error;
    private String errorCode;
    private String data;
    
    public String getSuccess() {
        return this.success;
    }
    
    public void setSuccess(final String success) {
        this.success = success;
    }
    
    public String getData() {
        return this.data;
    }
    
    public void setData(final String data) {
        this.data = data;
    }
    
    public String getError() {
        return this.error;
    }
    
    public void setError(final String error) {
        this.error = error;
    }
    
    public String getErrorCode() {
        return this.errorCode;
    }
    
    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }
}
