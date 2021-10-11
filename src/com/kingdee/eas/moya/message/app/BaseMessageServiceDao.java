package com.kingdee.eas.moya.message.app;

import java.net.URLEncoder;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.workflow.service.ormrpc.EnactmentServiceFactory;
import com.kingdee.bos.workflow.service.ormrpc.IEnactmentService;
import com.kingdee.eas.base.message.MsgBizType;
import com.kingdee.eas.base.message.MsgStatus;
import com.kingdee.eas.base.message.webservice.MessageWebServiceDao;
import com.kingdee.eas.base.message.webservice.WSMessage;
import com.kingdee.eas.base.permission.UserFactory;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.moya.common.JcbLog;
import com.kingdee.eas.moya.common.PropUtil;
import com.kingdee.eas.moya.common.WorkflowUtils;
import com.kingdee.eas.moya.common.model.AssignInfo;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;

public abstract class BaseMessageServiceDao implements MessageWebServiceDao, IMessageService
{
    protected boolean checkIfPushed(Context ctx, String assignID) throws BOSException, EASBizException {
    	System.out.println("BaseMessageServiceDao-----------------------checkIfPushed");
        StringBuffer sql = new StringBuffer();
        sql.append("select top 1 fid from CT_BAS_TaskLog where FASSIGNID='" + assignID + "'");
        try {
            IRowSet rs = DbUtil.executeQuery(ctx, sql.toString());
            if (rs.next()) {
                return true;
            }
        }
        catch (Exception e) {
            throw new BOSException((Throwable)e);
        }
        return false;
    }
    
    protected String getUrl(WSMessage message, Context ctx) throws Exception {
    	System.out.println("BaseMessageServiceDao-----------------------getUrl");
        IEnactmentService service = EnactmentServiceFactory.createEnactService(ctx);
        String fn = service.getAssignmentById(message.getSourceID()).getBizFunction();
        if ("ManualDecisionUIFunction".equals(fn)) {
            return this.getDecisionUrl(message);
        }
        return this.getApproveUrl(message);
    }
    
    protected String getApproveUrl(WSMessage message) {
    	System.out.println("BaseMessageServiceDao-----------------------getApproveUrl");
        StringBuffer msgUrl = new StringBuffer(message.getUrl());
        msgUrl.append("?username=" + message.getRecieveNumber());
        msgUrl.append("&redirectTo=");
        String url = "/easweb/webviews/workflow/approve.jsp?assignmentId=" + this.getAssignID(message) + "&waf2skin=easbase";
        msgUrl.append(URLEncoder.encode(url));
        return msgUrl.toString();
    }
    
    protected String getDecisionUrl(WSMessage message) {
        StringBuffer msgUrl = new StringBuffer(message.getUrl());
        msgUrl.append("?username=" + message.getRecieveNumber());
        msgUrl.append("&redirectTo=");
        String url = "/easweb/webviews/workflow/manualDecisionNew.jsp?assignmentId=" + this.getAssignID(message) + "&waf2skin=easbase";
        msgUrl.append(url);
        return msgUrl.toString();
    }
    
    protected String getSHRApproveUrl(WSMessage message) {
    	System.out.println("BaseMessageServiceDao-----------------------getSHRApproveUrl");
        StringBuffer msgUrl = new StringBuffer(message.getUrl());
        msgUrl.append("?username=" + message.getRecieveNumber());
        msgUrl.append("&redirectTo=");
        String url = "/dynamic.do?uipk=shr.workflow.workflowAssignment&assignmentid=" + this.getAssignID(message);
        msgUrl.append(URLEncoder.encode(url));
        return msgUrl.toString();
    }
    
    protected String getAssignID(WSMessage message) {
    	System.out.println("BaseMessageServiceDao-----------------------getAssignID");
        return message.getSourceID();
    }
    
    @Override
    public boolean addMessage(WSMessage message, Context ctx) {
    	System.out.println("BaseMessageServiceDao-----------------------addMessage");
    	 JcbLog.log(3, "发送代办111");
         try {
        	
        	 JcbLog.log(3, "message.getType().getValue()" + message.getType().getValue());
             JcbLog.log(3, "message.getBizType().getValue()" + message.getBizType().getValue());
             if(message.getType().getValue()==0){
            	 JcbLog.log(3, "发送通知111");
            	 AssignInfo assignInfo = WorkflowUtils.buildNoticeAssignInfo(ctx, message);
            	 JcbLog.log(3, "发送通知222"+assignInfo.getReceiver().getCell());
                 this.handleAddMessage(ctx, message, assignInfo);
            	 JcbLog.log(3, "发送通知333");
             }else{
            	 if (message.getBizType().getValue() == 0 && this.isSupportTask()) {
                     JcbLog.log(3, "发送代办222");
                     AssignInfo assignInfo = WorkflowUtils.buildAssignInfo(ctx, message);
                     JcbLog.log(3, "发送代办3333");
                     String billid = assignInfo.getBillID();
                     String assignID = message.getSourceID();
                     String dataCenter = ctx.getAIS();
                     ObjectUuidPK pk = new ObjectUuidPK(billid);
                     String bostype = pk.getObjectType().toString();
                     String rootUrl = PropUtil.getParameterByName("eas.rooturl");
                     String link = String.valueOf(rootUrl) + "/portal/UserAccountValidate.jsp?";
                     link = String.valueOf(link) + "dataCenter=" + dataCenter + "&userNumber=" + message.getRecieveNumber() + "&billid=" + billid + "&bostype=" + bostype;
                     link = String.valueOf(link) + "&assignID=" + message.getSourceID();
                     message.setUrl(link);
                     JcbLog.log(3, "发送代办4444" + link);
                     this.handleAddMessage(ctx, message, assignInfo);
                     JcbLog.log(3, "发送代办5555");
                 }
             }
         }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    protected boolean isSupportTask() {
        return true;
    }
    
    @Override
    public boolean removeMessage(String assignID, Context ctx) {
    	System.out.println("BaseMessageServiceDao-----------------------removeMessage");
        try {
            JcbLog.log(3, "更新消息ID：" + assignID);
            if (this.checkIfPushed(ctx, assignID)) {
                AssignInfo assignInfo = WorkflowUtils.buildAssignInfo(ctx, assignID);
                this.handleRemoveMessage(ctx, assignID, assignInfo);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean updateMessage(String paramString, Context ctx) {
        return false;
    }
    
    @Override
    public boolean updateMessages(String assignID, MsgStatus paramMsgStatus, Context ctx) {
        return false;
    }
}
