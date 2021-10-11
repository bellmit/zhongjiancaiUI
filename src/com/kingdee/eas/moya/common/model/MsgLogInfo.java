package com.kingdee.eas.moya.common.model;

import java.io.*;

public class MsgLogInfo implements Serializable
{
    private static final long serialVersionUID = 6544329815910750749L;
    private String result;
    private int biztype;
    private String receiver;
    private String sender;
    private String assignID;
    private String opt;
    
    public MsgLogInfo() {
    }
    
    public static MsgLogInfo New() {
        return new MsgLogInfo();
    }
    
    public MsgLogInfo(final String result, final int biztype, final String receiver, final String sender, final String assignID, final String opt) {
        this.result = result;
        this.biztype = biztype;
        this.receiver = receiver;
        this.sender = sender;
        this.assignID = assignID;
        this.opt = opt;
    }
    
    public String getResult() {
        return this.result;
    }
    
    public MsgLogInfo setResult(final String result) {
        this.result = result;
        return this;
    }
    
    public int getBiztype() {
        return this.biztype;
    }
    
    public MsgLogInfo setBiztype(final int biztype) {
        this.biztype = biztype;
        return this;
    }
    
    public String getReceiver() {
        return this.receiver;
    }
    
    public MsgLogInfo setReceiver(final String receiver) {
        this.receiver = receiver;
        return this;
    }
    
    public String getSender() {
        return this.sender;
    }
    
    public MsgLogInfo setSender(final String sender) {
        this.sender = sender;
        return this;
    }
    
    public String getAssignID() {
        return this.assignID;
    }
    
    public MsgLogInfo setAssignID(final String assignID) {
        this.assignID = assignID;
        return this;
    }
    
    public String getOpt() {
        return this.opt;
    }
    
    public MsgLogInfo setOpt(final String opt) {
        this.opt = opt;
        return this;
    }
}
