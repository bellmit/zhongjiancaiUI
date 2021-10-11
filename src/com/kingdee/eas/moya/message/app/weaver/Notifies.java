package com.kingdee.eas.moya.message.app.weaver;

import com.kingdee.eas.base.message.webservice.*;
import com.kingdee.eas.base.permission.*;
import java.util.*;
import javax.xml.rpc.*;
import java.rmi.*;
import com.kingdee.eas.moya.common.model.*;
import com.kingdee.eas.moya.common.*;
import com.kingdee.eas.moya.message.app.weaver.webservice.*;
import com.kingdee.eas.common.*;
import com.kingdee.bos.*;
import com.kingdee.eas.util.app.*;
import java.sql.*;
import java.text.*;
import com.kingdee.jdbc.rowset.*;

public final class Notifies
{
    static final SimpleDateFormat sdf;
    
    static {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    
    public static String sendTodo(final Context ctx, final WSMessage message, final AssignInfo assignInfo, final String assignID, final String subject, final String body, final String url, final String recieveNumber, final UserInfo sender, final String receiverID, final Timestamp sendTime, final Timestamp receiveTime, final String modelName) throws EASBizException, BOSException {
        final StringBuffer requestJson = new StringBuffer();
        requestJson.append("{'syscode':").append("'EAS',");
        requestJson.append("'flowid':").append("'").append(assignID).append("',");
        requestJson.append("'requestname':").append("'").append(subject).append("',");
        requestJson.append("'workflowname':").append("'").append(body).append("',");
        requestJson.append("'nodename':").append("'").append(recieveNumber).append("',");
        requestJson.append("'pcurl':").append("'").append(url).append("',");
        requestJson.append("'appurl':").append("'").append(url).append("',");
        requestJson.append("'creator':").append("'").append(sender).append("',");
        requestJson.append("'receiver':").append("'").append(receiverID).append("',");
        requestJson.append("'createdatetime':").append("'").append(Notifies.sdf.format(sendTime)).append("',");
        requestJson.append("'receivedatetime':").append("'").append(Notifies.sdf.format(receiveTime)).append("'}");
        System.out.println("------addOAMessageJson------" + (Object)requestJson + "----------");
        String returnVal = "";
        try {
            final OfsTodoDataWebServicePortType ofsTodoDataWebServicePortType = new OfsTodoDataWebServiceLocator().getOfsTodoDataWebServiceHttpPort();
            returnVal = ofsTodoDataWebServicePortType.receiveTodoRequestByJson(requestJson.toString());
        }
        catch (ServiceException e) {
            JcbLog.log(4, e.getLocalizedMessage());
        }
        catch (RemoteException e2) {
            JcbLog.log(4, e2.getLocalizedMessage());
        }
        finally {
            WorkflowUtils.writeLog(ctx, MsgLogInfo.New().setAssignID(assignID).setBiztype(message.getBizType().getValue()).setOpt("add").setReceiver(recieveNumber).setSender((sender != null) ? sender.getNumber() : "").setResult(returnVal));
        }
        WorkflowUtils.writeLog(ctx, MsgLogInfo.New().setAssignID(assignID).setBiztype(message.getBizType().getValue()).setOpt("add").setReceiver(recieveNumber).setSender((sender != null) ? sender.getNumber() : "").setResult(returnVal));
        return returnVal;
    }
    
    public static String setTodoDone(final Context ctx, final String assignID, final AssignInfo assignInfo, final String modelName) throws EASBizException, BOSException {
        String FSUBJECT_L2 = "";
        String FBODY_L2 = "";
        String nodename = "";
        String sender = "";
        String receiver = "";
        String FCREATEDTIME = "";
        String FLASTSTATETIME = "";
        try {
            final StringBuffer assginSql = new StringBuffer();
            assginSql.append(" select FBIZOBJID,FCREATEDTIME,FLASTSTATETIME,reciver.fnumber reciver,reciver.fname_l2 reciverName,sender.fnumber sender   \n");
            assginSql.append(" ,FBILLENTITY ,FPRIORPERFORMER,  FSUBJECT_L2 ,FBODY_L2,FACTDEFNAME_L2   \n");
            assginSql.append(" from T_WFR_ASSIGN assign   \n");
            assginSql.append(" left join t_pm_user sender   \n");
            assginSql.append(" on sender.fid = assign.FPRIORPERFORMER   \n");
            assginSql.append(" left join t_pm_user reciver   \n");
            assginSql.append(" on reciver.fid = assign.FPERSONUSERID   \n");
            assginSql.append(" where assign.FASSIGNID = '" + assignID + "'   \n");
            assginSql.append(" union all \n");
            assginSql.append(" select FBIZOBJID,FCREATEDTIME,FLASTSTATETIME,reciver.fnumber reciver,reciver.fname_l2 reciverName,sender.fnumber sender   \n");
            assginSql.append(" ,FBILLENTITY ,FPRIORPERFORMER,  FSUBJECT_L2 ,FBODY_L2,FACTDEFNAME_L2   \n");
            assginSql.append(" from T_WFR_ASSIGNDETAIL assign   \n");
            assginSql.append(" left join t_pm_user sender   \n");
            assginSql.append(" on sender.fid = assign.FPRIORPERFORMER   \n");
            assginSql.append(" left join t_pm_user reciver   \n");
            assginSql.append(" on reciver.fid = assign.FPERSONUSERID   \n");
            assginSql.append(" where assign.FASSIGNID = '" + assignID + "'   \n");
            final IRowSet rs = DbUtil.executeQuery(ctx, assginSql.toString());
            if (rs.next()) {
                FSUBJECT_L2 = rs.getString("FSUBJECT_L2");
                FBODY_L2 = rs.getString("FBODY_L2");
                sender = rs.getString("sender");
                nodename = rs.getString("reciverName");
                receiver = rs.getString("reciver");
                FCREATEDTIME = rs.getString("FCREATEDTIME");
                FLASTSTATETIME = rs.getString("FLASTSTATETIME");
            }
        }
        catch (SQLException e) {
            JcbLog.log(4, e.getLocalizedMessage());
        }
        catch (BOSException e2) {
            JcbLog.log(4, e2.getLocalizedMessage());
        }
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final StringBuffer requestJson = new StringBuffer();
        requestJson.append("{'syscode':").append("'" + nodename + "',");
        requestJson.append("'flowid':").append("'").append(assignID).append("',");
        requestJson.append("'requestname':").append("'").append(FSUBJECT_L2).append("',");
        requestJson.append("'workflowname':").append("'").append(FBODY_L2).append("',");
        requestJson.append("'nodename':").append("'").append(nodename).append("',");
        requestJson.append("'pcurl':").append("'").append("").append("',");
        requestJson.append("'appurl':").append("'").append("").append("',");
        requestJson.append("'creator':").append("'").append(sender).append("',");
        requestJson.append("'receiver':").append("'").append(receiver).append("',");
        try {
            requestJson.append("'createdatetime':").append("'").append(sdf.format(sdf.parse(FCREATEDTIME))).append("',");
            requestJson.append("'receivedatetime':").append("'").append(sdf.format(sdf.parse(FLASTSTATETIME))).append("'}");
        }
        catch (ParseException e3) {
            JcbLog.log(4, e3.getLocalizedMessage());
        }
        System.out.println("--removeMessageJson----------" + (Object)requestJson + "----------");
        String returnVal = "";
        try {
            final OfsTodoDataWebServicePortType ofsTodoDataWebServicePortType = new OfsTodoDataWebServiceLocator().getOfsTodoDataWebServiceHttpPort();
            returnVal = ofsTodoDataWebServicePortType.processDoneRequestByJson(requestJson.toString());
        }
        catch (RemoteException e4) {
            JcbLog.log(4, e4.getLocalizedMessage());
        }
        catch (ServiceException e5) {
            JcbLog.log(4, e5.getLocalizedMessage());
        }
        finally {
            WorkflowUtils.writeLog(ctx, MsgLogInfo.New().setAssignID(assignID).setBiztype(0).setOpt("add").setReceiver(receiver).setSender(sender).setResult(returnVal));
        }
        WorkflowUtils.writeLog(ctx, MsgLogInfo.New().setAssignID(assignID).setBiztype(0).setOpt("add").setReceiver(receiver).setSender(sender).setResult(returnVal));
        return returnVal;
    }
}
