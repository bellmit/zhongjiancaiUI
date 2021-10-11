package com.octal.thirdparty.kdyzj.api;

public class SendDataToYzj
{
    private String nonce;
    private String eid;
    private Object data;
    
    public String getNonce() {
        return this.nonce;
    }
    
    public void setNonce() {
        this.nonce = String.valueOf(Math.random());
    }
    
    public String getEid() {
        return this.eid;
    }
    
    public void setEid(final String eid) {
        this.eid = eid;
    }
    
    public Object getData() {
        return this.data;
    }
    
    public void setData(final Object data) {
        this.data = data;
    }
}
