package com.kingdee.eas.moya.common.model;

import java.io.*;
import com.kingdee.bos.metadata.entity.*;
import com.kingdee.bos.workflow.*;
import com.kingdee.bos.workflow.metas.*;
import java.sql.*;
import com.kingdee.eas.base.permission.*;
import java.util.*;

public class AssignInfo implements Serializable
{
    private static final long serialVersionUID = 7551721718370642874L;
    private EntityObjectInfo bo;
    private AssignmentInfo assignmentInfo;
    private ActInstInfo actInstInfo;
    private String actDefName;
    private String assignID;
    private String billID;
    private String billNumber;
    private String bosType;
    private int attachCount;
    private String entityName;
    private String body;
    private String billJson;
    private String initiatorID;
    private String initiatorNumber;
    private String initiatorName;
    private Timestamp initiatorTime;
    private String type;
    private boolean includeReject;
    private String senderID;
    private UserInfo sender;
    private String receiverID;
    private UserInfo receiver;
    private List<Map<String, String>> attachList;
    private String entityAlias;
    
    public AssignInfo() {
        this.attachList = new ArrayList<Map<String, String>>();
    }
    
    public AssignmentInfo getAssignmentInfo() {
        return this.assignmentInfo;
    }
    
    public void setAssignmentInfo(final AssignmentInfo assignmentInfo) {
        this.assignmentInfo = assignmentInfo;
    }
    
    public String getBillID() {
        return this.billID;
    }
    
    public void setBillID(final String billID) {
        this.billID = billID;
    }
    
    public String getBillNumber() {
        return this.billNumber;
    }
    
    public void setBillNumber(final String billNumber) {
        this.billNumber = billNumber;
    }
    
    public int getAttachCount() {
        return this.attachCount;
    }
    
    public void setAttachCount(final int attachCount) {
        this.attachCount = attachCount;
    }
    
    public List<Map<String, String>> getAttachList() {
        return this.attachList;
    }
    
    public void setAttachList(final List<Map<String, String>> attachList) {
        this.attachList = attachList;
    }
    
    public String getBosType() {
        return this.bosType;
    }
    
    public void setBosType(final String bosType) {
        this.bosType = bosType;
    }
    
    public String getEntityName() {
        return this.entityName;
    }
    
    public void setEntityName(final String entityName) {
        this.entityName = entityName;
    }
    
    public String getSenderID() {
        return this.senderID;
    }
    
    public void setSenderID(final String senderID) {
        this.senderID = senderID;
    }
    
    public UserInfo getSender() {
        return this.sender;
    }
    
    public void setSender(final UserInfo sender) {
        this.sender = sender;
    }
    
    public String getReceiverID() {
        return this.receiverID;
    }
    
    public void setReceiverID(final String receiverID) {
        this.receiverID = receiverID;
    }
    
    public UserInfo getReceiver() {
        return this.receiver;
    }
    
    public void setReceiver(final UserInfo receiver) {
        this.receiver = receiver;
    }
    
    public String getBody() {
        return this.body;
    }
    
    public void setBody(final String body) {
        this.body = body;
    }
    
    public EntityObjectInfo getBo() {
        return this.bo;
    }
    
    public void setBo(final EntityObjectInfo bo) {
        this.bo = bo;
    }
    
    public String getAssignID() {
        return this.assignID;
    }
    
    public void setAssignID(final String assignID) {
        this.assignID = assignID;
    }
    
    public String getBillJson() {
        return this.billJson;
    }
    
    public void setBillJson(final String billJson) {
        this.billJson = billJson;
    }
    
    public String getInitiatorID() {
        return this.initiatorID;
    }
    
    public void setInitiatorID(final String initiatorID) {
        this.initiatorID = initiatorID;
    }
    
    public String getInitiatorNumber() {
        return this.initiatorNumber;
    }
    
    public void setInitiatorNumber(final String initiatorNumber) {
        this.initiatorNumber = initiatorNumber;
    }
    
    public String getInitiatorName() {
        return this.initiatorName;
    }
    
    public void setInitiatorName(final String initiatorName) {
        this.initiatorName = initiatorName;
    }
    
    public Timestamp getInitiatorTime() {
        return this.initiatorTime;
    }
    
    public void setInitiatorTime(final Timestamp initiatorTime) {
        this.initiatorTime = initiatorTime;
    }
    
    public boolean isIncludeReject() {
        return this.includeReject;
    }
    
    public void setIncludeReject(final boolean includeReject) {
        this.includeReject = includeReject;
    }
    
    public ActInstInfo getActInstInfo() {
        return this.actInstInfo;
    }
    
    public void setActInstInfo(final ActInstInfo actInstInfo) {
        this.actInstInfo = actInstInfo;
    }
    
    public String getActDefName() {
        return this.actDefName;
    }
    
    public void setActDefName(final String actDefName) {
        this.actDefName = actDefName;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public String getEntityAlias() {
        return this.entityAlias;
    }
    
    public void setEntityAlias(final String entityAlias) {
        this.entityAlias = entityAlias;
    }
}
