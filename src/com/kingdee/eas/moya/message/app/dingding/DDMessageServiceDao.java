package com.kingdee.eas.moya.message.app.dingding;

import java.util.Map;

import com.kingdee.eas.moya.message.app.*;
import com.kingdee.eas.base.message.MsgStatus;
import com.kingdee.eas.base.message.WsMessageCenterFacade;
import com.kingdee.eas.base.message.WsMessageCenterFacadeFactory;
import com.kingdee.eas.base.message.webservice.*;
import com.kingdee.eas.custom.EAIDataBaseInfo;
import com.kingdee.eas.custom.EAISynTemplate;
import com.kingdee.eas.custom.unit.CoreUtil;
import com.kingdee.eas.moya.common.JcbLog;
import com.kingdee.eas.moya.common.PropUtil;
import com.kingdee.eas.moya.common.WorkflowUtils;
import com.kingdee.eas.moya.common.model.*;
import com.kingdee.eas.util.app.*;
import com.kingdee.bos.*;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.dao.query.SQLExecutorFactory;
import com.kingdee.bos.workflow.AssignmentInfo;
import com.kingdee.bos.workflow.service.ormrpc.EnactmentServiceFactory;
import com.kingdee.bos.workflow.service.ormrpc.IEnactmentService;
import com.alibaba.fastjson.*;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiWorkrecordUpdateRequest;
import com.dingtalk.api.response.OapiWorkrecordUpdateResponse;
import com.kingdee.jdbc.rowset.*;
import com.kingdee.shr.base.syssetting.context.SHRContext;
import com.sun.xml.internal.ws.resources.WsservletMessages;

public class DDMessageServiceDao extends BaseMessageServiceDao
{ 
    @Override
    public void handleAddMessage(Context ctx, WSMessage message, AssignInfo assignInfo) throws Exception {
       /* System.out.println("发送钉钉消息");
       
        if(message.getType().getValue()==0){
            Notifies.sendWorkRecord(assignInfo.getReceiver().getCell(), message.getTitle(), message.getBody());
        }else{
            Notifies.sendWorkRecord(assignInfo.getReceiver().getCell(), message.getTitle(), message.getUrl(), message.getBody());
        }
        System.out.println("任务ID：" + message.getSourceID());
        StringBuffer sql = new StringBuffer();
        sql.append("insert into CT_BAS_Task(fassignid)");
        sql.append(" values(");
        sql.append("'" + message.getSourceID() + "'");
        sql.append(")");
        try {
            DbUtil.execute(ctx, sql.toString());
        }
        catch (BOSException e) {
            e.printStackTrace();
        }
        System.out.println("发送钉钉消息");*/
    }
     


	@Override
    public void handleRemoveMessage(Context ctx, String assignID, AssignInfo assignInfo) throws Exception {
       /* System.out.println("更新消息状态");
        String sql = "select record_id from CT_BAS_Task where fassignid='" + assignID + "'";
        IRowSet rowSet = DbUtil.executeQuery(ctx, sql);
        if (rowSet.next()) {
            assignID = rowSet.getString("record_id");
        }
        System.out.println("更新消息：" + assignID);
//        JSONObject resObj = Notifies.updateWordRecord(assignInfo.getReceiver().getName(), assignID);
//        if (resObj.getInteger("errcode") != 0) {
//            throw new RuntimeException(resObj.getString("errmsg"));
//        }
        System.out.println("更新消息状态");*/
    	
    	System.out.println("handleRemoveMessage---assignID:"+assignID);
		String selectSql = " select cfrecordid AS RECORDID , cfddid as DDID from  CT_CUS_Task where  cfassignid = '"+assignID+"'";
		IRowSet rowSet;
		try {
			rowSet = DbUtil.executeQuery(ctx, selectSql);
			System.out.println("removeMessage---------======sql:"+selectSql);
		    System.out.println("removeMessage---------======rowSet:"+rowSet.size());
		    String ddRecodID= "";
		    String ddid = "";
		    while (rowSet.next()) { 
		    	ddRecodID = rowSet.getString("RECORDID"); 
		    	ddid = rowSet.getString("DDID"); 
	        }
		    if(ddRecodID != null && !ddRecodID.equals("")){ 
		    	
		    	DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/workrecord/update");
				OapiWorkrecordUpdateRequest req = new OapiWorkrecordUpdateRequest();
				req.setUserid(ddid);
				req.setRecordId(ddRecodID);
				OapiWorkrecordUpdateResponse rsp = client.execute(req, Notifies.getToken());
				System.out.println(rsp.getBody());
		    	 
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

	@Override
	public boolean addCompletedMessage(WfrAssignMessage paramWfrAssignMessage) {
		// TODO Auto-generated method stub
		//System.out.println("发送钉钉消息---addCompletedMessage");
		
		
		/*System.out.println("发送钉钉消息---paramWfrAssignMessage.getAssignId():"+paramWfrAssignMessage.getAssignId());
		
		System.out.println("发送钉钉消息---paramWfrAssignMessage.getAssignId():"+paramWfrAssignMessage.getState());*/
		
		return false;
	}

	@Override
	public boolean addInitiatorMessage(WfrProcMessage paramWfrProcMessage) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public boolean updateMessage(String paramString, Context ctx) {
		// TODO Auto-generated method stub
		System.out.println("updateMessag-----------eupdateMessage"+paramString);
		return super.updateMessage(paramString, ctx);
	}


	@Override
	public boolean updateMessages(String assignID, MsgStatus paramMsgStatus,
			Context ctx) {
		// TODO Auto-generated method stub
		System.out.println("updateMessagesupdateMessagesupdateMessagesupdateMessagesupdateMessages"+assignID);
		return super.updateMessages(assignID, paramMsgStatus, ctx);
	}
	
	
	
	@Override
	public boolean addMessage(WSMessage message) {
		// TODO Auto-generated method stub
		/*System.out.println("发送钉钉消息---addMessage");
		
	    System.out.println("发送钉钉消息message.getRecieverID():"+message.getRecieverID());
	    
        if(message.getType().getValue()==0){
            Notifies.sendWorkRecord("18716006950", message.getTitle(), message.getBody());
        }else{
            Notifies.sendWorkRecord("18716006950", message.getTitle(), message.getUrl(), message.getBody());
        }
        
        System.out.println("发送钉钉消息");*/
		Context ctx = message.getContext();
		System.out.println("addMessageaddMessage-----------this.ctx :"+ ctx );

		System.out.println("addMessageaddMessage-----------ctx.getAIS() :"+ ctx.getAIS() );
		System.out.println("addMessageaddMessage-----------ctx.getContextID() :"+ ctx.getContextID() );
		System.out.println("addMessageaddMessage-----------ctx.getSolution() :"+ ctx.getSolution() );
		 
        System.out.println("addMessageaddMessage-----------message.getMsgID():"+message.getMsgID());
        System.out.println("addMessageaddMessage-----------message.getOrgID():"+message.getOrgID());
        System.out.println("addMessageaddMessage-----------message.getUrl():"+message.getUrl());
        System.out.println("addMessageaddMessage-----------message.getSourceID():"+message.getSourceID()); 
        System.out.println("addMessageaddMessage-----------message.getSourceID():"+message.getSourceID()); 
        System.out.println("addMessageaddMessage-----------message.getRecieveNumber():"+message.getRecieveNumber()); 
        System.out.println("addMessageaddMessage-----------message.getRecieverID():"+message.getRecieverID()); 
        
        System.out.println("addMessageaddMessage-----------1003770.equals(message.getRecieveNumber()):"+"1003770".equals(message.getRecieveNumber())); 
        try {
        	//if("1003770".equals(message.getRecieveNumber())){}
    		final IEnactmentService service = EnactmentServiceFactory.createEnactService(ctx);
            final AssignmentInfo assignmentInfo = service.getAssignmentById(message.getSourceID()); 
            String billid = assignmentInfo.getBizObjectIds();
            String assignID = message.getSourceID();
            String dataCenter = ctx.getAIS();
            ObjectUuidPK pk = new ObjectUuidPK(billid);
            String bostype = pk.getObjectType().toString();
            String rootUrl = PropUtil.getParameterByName("eas.rooturl");
            String link = String.valueOf(rootUrl) + "/portal/UserAccountValidate.jsp?";
            link = String.valueOf(link) + "dataCenter=" + dataCenter + "&userNumber=" + message.getRecieveNumber() + "&billid=" + billid + "&bostype=" + bostype;
            link = String.valueOf(link) + "&assignID=" + message.getSourceID()+"&isdb=1";
            System.out.println("addMessageaddMessage-----------link:"+link); 
            String pcUrl = link;
            
            String body = "";
            String sqlBody = "select   fbody_l1  as BODY from  T_WFR_Assign where  FASSIGNID = '"+assignID+"'";
            IRowSet rowSet = DbUtil.executeQuery(ctx, sqlBody);
			System.out.println("---------======sqlBody:"+sqlBody);
		    System.out.println("---------======rowSet:"+rowSet.size());
		    while (rowSet.next()) {
		    	body = rowSet.getString("BODY"); 
	        }
		    System.out.println("addMessageaddMessage-----------body:"+body); 
		    if(body == null || body.equals("")){
		    	body = message.getTitle();
		    }
			String[] arr = body.split("类型"); 
			/*if(arr.length >  1 ){
				body =  "类型:"+arr[1];
			}else{
				if(body.indexOf("】") >=0){
					body =  "类型:"+body.split("】")[0]+"】";
				}else{
					body =  "类型:"+arr[0];
				}
				
			}*/
			if(arr.length >  1 ){
				body =   arr[1];
			}else{
				if(body.indexOf("】") >=0){
					body =   body.split("】")[0]+"】";
				}else{
					body =   arr[0];
				}
				
			}
			
			System.out.println("addMessageaddMessage-----------bodybody:"+body); 
			
            System.out.println("addMessageaddMessage-----------message.getTitle():"+message.getTitle()); 
            System.out.println("addMessageaddMessage-----------message.getBody():"+message.getBody()); 
			String  ddToDoID = Notifies.sendTask(ctx,message.getRecieveNumber(), link ,  pcUrl ,message.getTitle(),body,assignID);
			
			
    			
        	
        	 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		return false;
	}

	 
	
	@Override
	public boolean removeMessage(String assignID, Context ctx) {
		// TODO Auto-generated method stub
		System.out.println("delete---assignID:"+assignID);
		String selectSql = " select cfrecordid AS RECORDID , cfddid as DDID from  CT_CUS_Task where  cfassignid = '"+assignID+"'";
		IRowSet rowSet;
		try {
			rowSet = DbUtil.executeQuery(ctx, selectSql);
			System.out.println("removeMessage---------======sql:"+selectSql);
		    System.out.println("removeMessage---------======rowSet:"+rowSet.size());
		    String ddRecodID= "";
		    String ddid = "";
		    while (rowSet.next()) { 
		    	ddRecodID = rowSet.getString("RECORDID"); 
		    	ddid = rowSet.getString("DDID"); 
	        }
		    if(ddRecodID != null && !ddRecodID.equals("")){ 
		    	
		    	DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/workrecord/update");
				OapiWorkrecordUpdateRequest req = new OapiWorkrecordUpdateRequest();
				req.setUserid(ddid);
				req.setRecordId(ddRecodID);
				OapiWorkrecordUpdateResponse rsp = client.execute(req, Notifies.getToken());
				System.out.println(rsp.getBody());
		    	 
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return super.removeMessage(assignID, ctx);
	}

	@Override
	public boolean removeMessage(String paramString) {
		// TODO Auto-generated method stub
		System.out.println("delete---removeMessage:"+paramString);   
		
		try {
			Context ctx  = CoreUtil.contextNoCtx(  "user");
			 
			ctx.setAIS("lbchrtest");
			//ctx.setAIS("lbchr");
			//7aee2bf8-a5e2-4cde-b1af-19705af2f059 
			ctx.setSolution("eas"); 
			System.out.println("removeMessage-----------Context :"+ctx);
			
			/*EAIDataBaseInfo dbInfo = new EAIDataBaseInfo();
			dbInfo.setNumber(item)
			dbCenterNumber = dbInfo.getNumber();*/
			 
			//Map<String,Object> countResult = EAISynTemplate.queryRowOne(ctx, dbCenterNumber, countSql);
			
			String selectSql = " select cfrecordid AS RECORDID , cfddid as DDID from  CT_CUS_Task where  cfassignid = '"+paramString+"'";
			//IRowSet rowSet = SQLExecutorFactory.getRemoteInstance(selectSql.toString()).executeSQL();
			IRowSet rowSet = DbUtil.executeQuery(ctx, selectSql);
			System.out.println("removeMessage---------======sql:"+selectSql);
		    System.out.println("removeMessage---------======rowSet:"+rowSet.size());
		    String ddRecodID= "";
		    String ddid = "";
		    while (rowSet.next()) { 
		    	ddRecodID = rowSet.getString("RECORDID"); 
		    	ddid = rowSet.getString("DDID"); 
	        }
		    if(ddRecodID != null && !ddRecodID.equals("")){ 
		    	
		    	DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/workrecord/update");
				OapiWorkrecordUpdateRequest req = new OapiWorkrecordUpdateRequest();
				req.setUserid(ddid);
				req.setRecordId(ddRecodID);
				OapiWorkrecordUpdateResponse rsp = client.execute(req, Notifies.getToken());
				System.out.println(rsp.getBody());
		    	 
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean updateMessage(String paramString) {
		// TODO Auto-generated method stub
		System.out.println(paramString+"---updateMessage:"+paramString);
		Context ctx  = SHRContext.getInstance().getContext();
		System.out.println("removeMessage-----------Context :"+ctx);
		String sqlAsg = " select  ttask.cfassignid as SIGNID , ttask.cfrecordid AS RECORDID , ttask.cfddid as DDID  from T_BAS_AssignRead readd "+
		" left join     CT_CUS_Task ttask on ttask.cfassignid = readd.fassignid "+
		 "where      readd.fid ='"+paramString+"' ";
		System.out.println("delete---removeMessage----sqlAsg:"+sqlAsg); 
		IRowSet rowSet;
		try {
			rowSet = DbUtil.executeQuery(ctx, sqlAsg);
			System.out.println("delete---removeMessage----rowSet.size():"+rowSet.size());  
			String ddRecodID= "";
		    String ddid = "";
		    String signid = "";
			while (rowSet.next()) { 
		    	ddRecodID = rowSet.getString("RECORDID"); 
		    	ddid = rowSet.getString("DDID"); 
		    	signid = rowSet.getString("SIGNID"); 
	        }
			
			System.out.println("delete---removeMessage----ddRecodID:"+ddRecodID); 
			System.out.println("delete---removeMessage----ddid:"+ddid); 
			System.out.println("delete---removeMessage----signid:"+signid); 
			
		    if(ddRecodID != null && !ddRecodID.equals("")){  
		    	DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/workrecord/update");
				OapiWorkrecordUpdateRequest req = new OapiWorkrecordUpdateRequest();
				req.setUserid(ddid);
				req.setRecordId(ddRecodID);
				OapiWorkrecordUpdateResponse rsp = client.execute(req, Notifies.getToken());
				System.out.println(rsp.getBody());
		    	 
		    }
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean updateMessages(String paramString, MsgStatus paramMsgStatus) {
		// TODO Auto-generated method stub
		System.out.println(paramMsgStatus+"----paramMsgStatus---updateMessages:"+paramString);
		return false;
	}
	
	
}
