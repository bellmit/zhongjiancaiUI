package com.kingdee.eas.moya.common;

import com.kingdee.eas.base.message.webservice.*;
import com.kingdee.util.*;
import com.kingdee.bos.dao.ormapping.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.workflow.service.ormrpc.*;
import com.kingdee.bos.workflow.*;
import com.kingdee.bos.workflow.define.*;
import java.util.*;
import java.util.Locale;

import com.kingdee.bos.metadata.*;
import com.kingdee.bos.metadata.entity.*;
import com.kingdee.bos.dao.*;
import com.kingdee.eas.base.permission.*;
import com.kingdee.eas.basedata.person.*;
import com.kingdee.bos.*;
import com.kingdee.bos.ContextUtils;
import com.kingdee.eas.common.*;
import com.kingdee.eas.util.app.*;
import java.sql.*;
import com.kingdee.jdbc.rowset.*;
import com.kingdee.eas.moya.common.model.*; 
import com.kingdee.eas.octal.*;

public class WorkflowUtils
{
    private static Locale locale;
    
    static {
        WorkflowUtils.locale = ContextUtils.getLocaleFromEnv();
    }
    
    public static AssignInfo buildAssignInfo(final Context ctx, final WSMessage message) throws BOSException, EASBizException {
        final IEnactmentService service = EnactmentServiceFactory.createEnactService(ctx);
        final AssignmentInfo assignmentInfo = service.getAssignmentById(message.getMsgID());
        final ProcessInstInfo processInstInfo = service.getProcessInstInfoByAssign(message.getMsgID());
        final String initiatorID = processInstInfo.getInitiatorId();
        final ActivityInstInfo actInstInfo = service.getActivityInstByActInstId(assignmentInfo.getActInstId());
        final ActivityDef actDef = service.getActDefByActInstInfo(actInstInfo);
        final AssignInfo assignInfo = new AssignInfo();
        if (actInstInfo != null) {
            assignInfo.setActInstInfo(actInstInfo.getActInstInfo());
            if (assignInfo.getActInstInfo() != null) {
                assignInfo.setActDefName(assignInfo.getActInstInfo().getActDefName());
            }
        }
        final Map m = service.getActivityDefAndActivityInstInfo(assignmentInfo.getAssignmentId());
        final Object act = m.get("ACTIVITYDEF");
        if (act instanceof ManpowerActivityDef) {
            assignInfo.setType("manpower");
        }
        assignInfo.setAssignID(message.getMsgID());
        assignInfo.setAssignmentInfo(assignmentInfo);
        assignInfo.setBillID(assignmentInfo.getBizObjectIds());
        assignInfo.setEntityName(assignmentInfo.getBillEntity());
        assignInfo.setSenderID(assignmentInfo.getAssignInfo().getPriorPerformerID());
        assignInfo.setReceiverID(message.getRecieverID());
        assignInfo.setBody(message.getBody());
        final IMetaDataLoader metaDataLoader = MetaDataLoaderFactory.getRemoteMetaDataLoader();
        final IMetaDataPK metaDataPK = (IMetaDataPK)MetaDataPK.create(assignmentInfo.getBillEntity());
        final EntityObjectInfo entity = metaDataLoader.getEntity(metaDataPK);
        assignInfo.setBo(entity);
        if (!StringUtils.isEmpty(assignInfo.getBillID())) {
            final ObjectUuidPK pk = new ObjectUuidPK(assignInfo.getBillID());
            assignInfo.setBosType(pk.getObjectType().toString());
            final SelectorItemCollection sic = new SelectorItemCollection();
            sic.add("id");
            sic.add("number");
            final IObjectValue model = DynamicObjectFactory.getLocalInstance(ctx).getValue(pk.getObjectType(), (IObjectPK)pk, sic);
            if (model != null) {
                assignInfo.setBillNumber(model.getString("number"));
            }
        }
        if (!StringUtils.isEmpty(assignInfo.getSenderID())) {
            final UserInfo userInfo = UserFactory.getLocalInstance(ctx).getUserInfo("select id,number,name,person.id,person.number,person.name where id='" + assignInfo.getSenderID() + "'");
            assignInfo.setSender(userInfo);
        }
        if (!StringUtils.isEmpty(assignInfo.getReceiverID())) {
            final UserInfo userInfo = UserFactory.getLocalInstance(ctx).getUserInfo("select id,number,name,person.id,person.number,cell,person.name where id='" + assignInfo.getReceiverID() + "'");
            assignInfo.setReceiver(userInfo);
        }
        assignInfo.setInitiatorID(initiatorID);
        assignInfo.setInitiatorTime(processInstInfo.getCreatedTime());
        if (!StringUtils.isEmpty(initiatorID)) {
            final UserInfo userInfo = UserFactory.getLocalInstance(ctx).getUserInfo("select id,number,name,person.id,person.number,person.name where id='" + initiatorID + "'");
            final PersonInfo personInfo = userInfo.getPerson();
            if (personInfo != null) {
                assignInfo.setInitiatorNumber(personInfo.getNumber());
            }
            assignInfo.setInitiatorName(userInfo.getName());
        }
        assignInfo.setEntityAlias(entity.getAlias());
        return assignInfo;
    }
    
    public static AssignInfo buildNoticeAssignInfo(final Context ctx, final WSMessage message) throws BOSException, EASBizException {
        final AssignInfo assignInfo = new AssignInfo();
        assignInfo.setAssignID(message.getMsgID());
        assignInfo.setReceiverID(message.getRecieverID());
        assignInfo.setBody(message.getBody());
      
        if (!StringUtils.isEmpty(assignInfo.getReceiverID())) {
            final UserInfo userInfo = UserFactory.getLocalInstance(ctx).getUserInfo("select id,number,name,person.id,person.number,cell,person.name where id='" + assignInfo.getReceiverID() + "'");
            assignInfo.setReceiver(userInfo);
        }
        return assignInfo;
    }
    
    public static AssignInfo buildAssignInfo(final Context ctx, final String assignID) throws BOSException, EASBizException {
        if (StringUtils.isEmpty(assignID)) {
            return null;
        }
        final AssignInfo assignInfo = new AssignInfo();
        assignInfo.setAssignID(assignID);
        final StringBuffer sql = new StringBuffer();
        sql.append("select top 1 * from CT_BAS_TaskLog where FASSIGNID='" + assignID + "'");
        final IRowSet rs = DbUtil.executeQuery(ctx, sql.toString());
        String receiver = null;
        try {
            if (rs.next()) {
                receiver = rs.getString("FReceivers");
            }
        }
        catch (SQLException e) {
            throw new BOSException((Throwable)e);
        }
        if (StringUtils.isEmpty(receiver)) {
            final UserInfo userInfo = UserFactory.getLocalInstance(ctx).getUserInfo("select id,number,name,person.id,person.number,person.name where number='" + receiver + "'");
            assignInfo.setReceiver(userInfo);
            if (userInfo != null) {
                assignInfo.setReceiverID(userInfo.getString("id"));
            }
        }
        return assignInfo;
    }
    
    public static void writeLog(final Context ctx, final MsgLogInfo msgLogInfo) throws BOSException, EASBizException {
        IJcbMsgLog logFactory = null;
        if (ctx != null) {
            logFactory = JcbMsgLogFactory.getLocalInstance(ctx);
        }
        else {
            logFactory = JcbMsgLogFactory.getRemoteInstance();
        }
        logFactory.insertNotifyLog(msgLogInfo);
    }
}
