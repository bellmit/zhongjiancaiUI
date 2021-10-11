package com.kingdee.eas.base.message.webservice;

import org.apache.log4j.*;
import com.kingdee.bos.dao.ormapping.*;
import com.kingdee.bos.dao.*;
import com.kingdee.eas.base.permission.*;
import com.kingdee.bos.workflow.service.*;
import com.kingdee.bos.*;
import java.sql.*;
import com.kingdee.bos.workflow.*;
import com.kingdee.bos.workflow.service.ormrpc.*;
import java.util.*;
import java.util.Date;
import java.util.Locale;

import com.kingdee.eas.base.message.*;

public class MCInvokeEx
{
    private static Logger logger;
    private static final MCInvokeEx invoke;
    private static List configList;
    
    static {
        MCInvokeEx.logger = Logger.getLogger("com.kingdee.eas.base.message.webservice.MCInvokeEx");
        invoke = new MCInvokeEx();
        MCInvokeEx.configList = null;
    }
    
    public static WSMessage intoMessage(BMCMessageInfo messageInfo, Context ctx) {
        WSMessage message = new WSMessage();
        message.setMsgID(messageInfo.getId().toString());
        message.setRecieverID(messageInfo.getReceiver());
        message.setBizType(messageInfo.getBizType());
        message.setType(messageInfo.getType());
        message.setTitle(messageInfo.getTitle());
        message.setBody(messageInfo.getBody());
        message.setPriority(messageInfo.getPriority());
        message.setSendTime(messageInfo.getSendTime());
        message.setReceiveTime(messageInfo.getReceiveTime());
        message.setSourceID(messageInfo.getSourceID());
        message.setSender(messageInfo.getSender());
        message.setState(messageInfo.getStatus());
        message.setReceivers(messageInfo.getNreceivers());
        message.setOrgID(messageInfo.getOrgID());
        message.setLocale(ctx.getLocale());
        try {
            MCInvokeEx.logger.debug((Object)("Receiver ID :" + messageInfo.getReceiver()));
            UserInfo userInfo = UserFactory.getLocalInstance(ctx).getUserInfo((IObjectPK)new ObjectUuidPK(messageInfo.getReceiver()));
            MCInvokeEx.logger.debug((Object)"String number = userInfo.getNumber();");
            String number = userInfo.getNumber();
            message.setRecieveNumber(number);
        }
        catch (Exception e) {
            MCInvokeEx.logger.error((Object)"\u83b7\u53d6\u7528\u6237\u4fe1\u606f\u65f6\u51fa\u9519");
            e.printStackTrace();
        }
        return message;
    }
    
    public static WSMessage intoMessage(AssignReadInfo messageInfo, Context ctx) {
        WSMessage message = new WSMessage();
        AssignmentInfo assInfo = null;
        Locale locale = ctx.getLocale();
        UserInfo senderInfo = null;
        try {
            IEnactmentService iEnactmentService = (IEnactmentService)new EnactmentServiceProxy(ctx);
            assInfo = iEnactmentService.getAssignmentById(messageInfo.getAssignID().toString());
        }
        catch (BOSException e) {
            MCInvokeEx.logger.error((Object)"\u83b7\u53d6\u4efb\u52a1\u6d88\u606fInfo\u5bf9\u8c61\u51fa\u9519");
            e.printStackTrace();
        }
        message.setMsgID(assInfo.getAssignmentId());
        message.setBizType(MsgBizType.WORKFLOW);
        message.setType(MsgType.TASK);
        message.setTitle(assInfo.getSubject(locale));
        message.setBody(assInfo.getBody(locale));
        message.setPriority(MsgPriority.MIDDLE);
        message.setOrgID(messageInfo.getOrgID());
        message.setSender(messageInfo.getSender());
        message.setState(messageInfo.getStatus());
        message.setSendTime(assInfo.getCreatedTime());
        message.setReceiveTime(new Timestamp(new Date().getTime()));
        message.setRecieverID(assInfo.getUserId());
        message.setLocale(locale);
        message.setReceivers(assInfo.getUserName(locale));
        message.setSourceID(messageInfo.getAssignID().toString());
        message.setSender(messageInfo.getSender());
        try {
            message.setRecieveNumber(UserFactory.getLocalInstance(ctx).getUserInfo((IObjectPK)new ObjectUuidPK(assInfo.getUserId())).getNumber());
        }
        catch (Exception e2) {
            MCInvokeEx.logger.error((Object)"\u83b7\u53d6\u7528\u6237\u4fe1\u606f\u65f6\u51fa\u9519");
            e2.printStackTrace();
        }
        return message;
    }
    
    public void wsImplRead(WSMessage message, Context ctx) {
        WSConfigInfo configInfo = null;
        MessageWebServiceDao dao = null;
        Class daoImpl = null;
        if (MCInvokeEx.configList == null) {
            return;
        }
        if (MCInvokeEx.configList.size() == 0) {
            MCInvokeEx.logger.error((Object)"\u914d\u7f6e\u4fe1\u606f\u4e0d\u6b63\u786e");
            return;
        }
        Iterator<WSConfigInfo> iterator = MCInvokeEx.configList.iterator();
        while (iterator.hasNext()) {
            configInfo = iterator.next();
            if (configInfo.getClassName() == null || configInfo.getClassName().trim().length() == 0) {
                MCInvokeEx.logger.error((Object)"\u914d\u7f6e\u6587\u4ef6\u914d\u7f6e\u9519\u8bef\uff01");
                throw new RuntimeException("wrong config in the WSConfig.xml");
            }
            if (!this.ifExecute(configInfo, message, ctx)) {
                continue;
            }
            try {
                message.setUrl(configInfo.getServerPath());
                dao = (MessageWebServiceDao)Class.forName(configInfo.getClassName()).newInstance();
                dao.addMessage(message, ctx);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void wsImplRemoveBMC(BMCMessageInfo messageInfo, Context ctx) {
        WSConfigInfo configInfo = null;
        MessageWebServiceDao dao = null;
        if (MCInvokeEx.configList == null) {
            return;
        }
        if (MCInvokeEx.configList.size() == 0) {
            MCInvokeEx.logger.error((Object)"\u914d\u7f6e\u4fe1\u606f\u4e0d\u6b63\u786e");
            return;
        }
        WSMessage message = new WSMessage();
        message.setType(messageInfo.getType());
        message.setBizType(messageInfo.getBizType());
        Iterator<WSConfigInfo> iterator = MCInvokeEx.configList.iterator();
        while (iterator.hasNext()) {
            configInfo = iterator.next();
            if (configInfo.getClassName() == null || configInfo.getClassName().trim().length() == 0) {
                MCInvokeEx.logger.error((Object)"\u914d\u7f6e\u6587\u4ef6\u914d\u7f6e\u9519\u8bef\uff01");
                throw new RuntimeException("wrong config in the WSConfig.xml");
            }
            if (!this.ifExecute(configInfo, message, ctx)) {
                continue;
            }
            try {
                dao = (MessageWebServiceDao)Class.forName(configInfo.getClassName()).newInstance();
                dao.removeMessage(messageInfo.getId().toString(), ctx);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void wsImplRemoveAss(List<String> IDList, Context ctx) {
        WSConfigInfo configInfo = null;
        MessageWebServiceDao dao = null;
        if (MCInvokeEx.configList == null) {
            return;
        }
        if (MCInvokeEx.configList.size() == 0) {
            MCInvokeEx.logger.error((Object)"\u914d\u7f6e\u4fe1\u606f\u4e0d\u6b63\u786e");
            return;
        }
        WSMessage message = new WSMessage();
        message.setType(MsgType.TASK);
        message.setBizType(MsgBizType.WORKFLOW);
        Iterator<WSConfigInfo> iterator = MCInvokeEx.configList.iterator();
        while (iterator.hasNext()) {
            configInfo = iterator.next();
            if (configInfo.getClassName() == null || configInfo.getClassName().trim().length() == 0) {
                MCInvokeEx.logger.error((Object)"\u914d\u7f6e\u6587\u4ef6\u914d\u7f6e\u9519\u8bef\uff01");
                throw new RuntimeException("wrong config in the WSConfig.xml");
            }
            if (!this.ifExecute(configInfo, message, ctx)) {
                continue;
            }
            try {
                dao = (MessageWebServiceDao)Class.forName(configInfo.getClassName()).newInstance();
                for (int i = 0; i < IDList.size(); ++i) {
                    dao.removeMessage(IDList.get(i), ctx);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void wsImplUpdate(WSMessage message, Context ctx) {
        WSConfigInfo configInfo = null;
        MessageWebServiceDao dao = null;
        Class daoImpl = null;
        if (MCInvokeEx.configList == null) {
            return;
        }
        if (MCInvokeEx.configList.size() == 0) {
            MCInvokeEx.logger.error((Object)"\u914d\u7f6e\u4fe1\u606f\u4e0d\u6b63\u786e");
            return;
        }
        Iterator<WSConfigInfo> iterator = MCInvokeEx.configList.iterator();
        while (iterator.hasNext()) {
            configInfo = iterator.next();
            if (configInfo.getClassName() == null || configInfo.getClassName().trim().length() == 0) {
                MCInvokeEx.logger.error((Object)"\u914d\u7f6e\u6587\u4ef6\u914d\u7f6e\u9519\u8bef\uff01");
                throw new RuntimeException("wrong config in the WSConfig.xml");
            }
            if (!this.ifExecute(configInfo, message, ctx)) {
                continue;
            }
            try {
                dao = (MessageWebServiceDao)Class.forName(configInfo.getClassName()).newInstance();
                dao.updateMessage(message.getMsgID(), ctx);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void wsImplUpdateMulti(String IDs, MsgStatus state, Context ctx) {
        WSConfigInfo configInfo = null;
        MessageWebServiceDao dao = null;
        Class daoImpl = null;
        if (MCInvokeEx.configList == null) {
            return;
        }
        if (MCInvokeEx.configList.size() == 0) {
            MCInvokeEx.logger.error((Object)"\u914d\u7f6e\u4fe1\u606f\u4e0d\u6b63\u786e");
            return;
        }
        Iterator<WSConfigInfo> iterator = MCInvokeEx.configList.iterator();
        while (iterator.hasNext()) {
            configInfo = iterator.next();
            if (configInfo.getClassName() == null || configInfo.getClassName().trim().length() == 0) {
                MCInvokeEx.logger.error((Object)"\u914d\u7f6e\u6587\u4ef6\u914d\u7f6e\u9519\u8bef\uff01");
                throw new RuntimeException("wrong config in the WSConfig.xml");
            }
            try {
                dao = (MessageWebServiceDao)Class.forName(configInfo.getClassName()).newInstance();
                dao.updateMessages(IDs, state, ctx);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private boolean ifExecute(WSConfigInfo configInfo, WSMessage messageInfo, Context ctx) {
        boolean push = false;
        if (!ctx.getAIS().equals(configInfo.getDataCenter())) {
            return push;
        }
        if (!configInfo.isTask() && MsgType.TASK.equals((Object)messageInfo.getType())) {
            return push;
        }
        if (!configInfo.isNotice() && MsgType.NOTICE.equals((Object)messageInfo.getType())) {
            return push;
        }
        if (!configInfo.isOnline() && MsgType.ONLINE.equals((Object)messageInfo.getType())) {
            return push;
        }
        if (configInfo.isTask() && MsgType.TASK.equals((Object)messageInfo.getType())) {
            push = true;
            return push;
        }
        if (configInfo.isNotice()) {
            if (configInfo.isNotice_workFlow() && MsgType.NOTICE.equals((Object)messageInfo.getType()) && MsgBizType.WORKFLOW.equals((Object)messageInfo.getBizType())) {
                push = true;
                return push;
            }
            if (configInfo.isNotice_Forwarn() && MsgType.NOTICE.equals((Object)messageInfo.getType()) && MsgBizType.FORWARN.equals((Object)messageInfo.getBizType())) {
                push = true;
                return push;
            }
            if (configInfo.isNotice_Urgent() && MsgType.NOTICE.equals((Object)messageInfo.getType()) && MsgBizType.URGENT.equals((Object)messageInfo.getBizType())) {
                push = true;
                return push;
            }
        }
        if (configInfo.isOnline() && MsgType.ONLINE.equals((Object)messageInfo.getType())) {
            push = true;
            return push;
        }
        return push;
    }
    
    public static MCInvokeEx getInstance() {
        if (MCInvokeEx.configList == null) {
            MCInvokeEx.configList = MessageServiceConfig.getMSConfig().LoadConfig();
        }
        return MCInvokeEx.invoke;
    }
}
