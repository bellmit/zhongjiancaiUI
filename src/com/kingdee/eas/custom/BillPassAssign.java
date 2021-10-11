package com.kingdee.eas.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.bos.webframework.context.WebContextUtil;
import com.kingdee.bos.webframework.exception.WafBizException;
import com.kingdee.bos.webframework.json.JSONUtils;
import com.kingdee.bos.webframework.workflow.WfWafUtil;
import com.kingdee.bos.workflow.AssignmentInfo;
import com.kingdee.bos.workflow.participant.Person;
import com.kingdee.bos.workflow.service.ormrpc.EnactmentServiceFactory;
import com.kingdee.bos.workflow.service.ormrpc.IEnactmentService;
import com.kingdee.bos.workflow.util.ApplicationUtil;
import com.kingdee.eas.base.multiapprove.MultiApproveInfo;
import com.kingdee.eas.base.multiapprove.MultiApproveManageFactory;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.eas.util.ResourceBase;
import com.kingdee.eas.util.app.ContextUtil;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.StringUtils;

public class BillPassAssign {

	public static String passAssignment(Context ctx, Map map) {   
		try {
			String personId = map.get("personId").toString();
			String opinion =  map.get("opinion").toString();
			//boolean isSendMobile = Boolean.valueOf( request.getParameter("isSendMobile")).booleanValue();
			//boolean isSendMail = Boolean.valueOf(request.getParameter("isSendMail")).booleanValue();
			
			boolean isSendMobile = false;
			boolean isSendMail = false; 
			UserInfo currentUserInfo = ContextUtil.getCurrentUserInfo(ctx);
			PersonInfo currentPerson = currentUserInfo.getPerson();
			String currentPersonId = currentPerson.getId().toString(); 
			if ((personId == null) || (personId.length() == 0)) {
				return "请选择传阅人。";
			}
			IEnactmentService svc = EnactmentServiceFactory.createEnactService(ctx);
			if (StringUtils.isEmpty(opinion)) {
				opinion = "";
			}

			List personIdSet = new ArrayList();
			int count1 = 0;
			int count2 = 0;
			String[] ids = personId.split(";");
			for (String s : ids) {
				if (s.equals(currentPersonId)) {
					throw new WafBizException("不能在同一职员之间传阅任务");
				}
				if (checkPersonHasUser(ctx,s)) {
					personIdSet.add(s);
					++count1;
				} else {
					++count2;
				} 
			} 
			List userIdList = new ArrayList();
			for (int i = 0; i < personIdSet.size(); ++i) {
				Person[] persons = svc.getPersonByPersonID((String) personIdSet.get(i));
				for (int j = 0; j < persons.length; ++j) {
					Person person = persons[j];
					if (person != null) {
						userIdList.add(person.getUserId());
					}
				}
			}

			String[] userIds = new String[userIdList.size()];
			for (int m = 0; m < userIdList.size(); ++m) {
				userIds[m] = ((String) userIdList.get(m));
			}

			Locale[] locales = ApplicationUtil.getContextLocales(ctx);
			Map opinionMap = new HashMap();
			for (int i = 0; i < locales.length; ++i) {
				Locale locale = locales[i];
				String data = new StringBuilder() .append( ResourceBase.getString("com.kingdee.eas.base.multiapprove.MultiApproveResource","Npassaround.user", locale))
						.append(currentUserInfo.getName(locale)).append("\r\n")
						.append(ResourceBase.getString("com.kingdee.eas.base.multiapprove.MultiApproveResource","Npassaround_title", locale))
						.append(opinion).toString(); 
				opinionMap.put(locale, data);
				opinionMap.put(new StringBuilder().append("opinion").append(locale.getDisplayName()).toString(), opinion);
			}
			
			String sourceIds = map.get("sourceIds").toString();
			if ((sourceIds != null) && (!("".equals(sourceIds)))) {
				opinionMap.put("personIdList", personIdSet);
				opinionMap.put("sourceIds", sourceIds);
				MultiApproveInfo info = new MultiApproveInfo();
				String assignId = sourceIds.split(":")[7];
				String billId = sourceIds.split(":")[3];
				if (StringUtils.isEmpty(assignId)) {
					assignId = sourceIds.split(":")[2];
				}
				info.setAssignment(assignId);
				info.setBillId(BOSUuid.read(billId)); 
				if (isSendMobile) {
					opinionMap.put("is_mobile", "true");
				}
				if (isSendMail) {
					opinionMap.put("is_mail", "true");
				}
				MultiApproveManageFactory.getLocalInstance(ctx).sendPassAroundMsgWithOption(info, userIds, opinionMap);
			} else {
				String assignmentID = map.get("assignmentID").toString();
				String billID = getBillId(ctx,assignmentID);
				if (StringUtils.isEmpty(billID))
					billID = map.get("billID").toString();
				//boolean isAllowRepass = Boolean.valueOf(request.getParameter("isAllowRepass")).booleanValue();
				//boolean isProhibittedAfterProcClose = Boolean.valueOf(request.getParameter("isProhibittedAfterProcClose")).booleanValue();

				boolean isAllowRepass = false;
				boolean isProhibittedAfterProcClose = false; 
				String[] tmpAssignmentIds = assignmentID.split(";");
				String[] tmpBillIds = billID.split(";");
				int length = tmpBillIds.length;
				for (int i = 0; i < tmpAssignmentIds.length; ++i) {
					String billid = "";
					MultiApproveInfo info = new MultiApproveInfo();
					info.setAssignment(tmpAssignmentIds[i]);
					if (length > 1)
						billid = BOSUuid.read(tmpBillIds[i]).toString();
					else {
						billid =BOSUuid.read(billID).toString();
					} 
					/*//ziji
					String getBillId = "SELECT FBIZOBJID  FROM  T_WFR_Assign  where  FASSIGNID  = '"+tmpAssignmentIds[i]+"' ";
					final IRowSet rowSet = DbUtil.executeQuery(ctx, getBillId); 
					while(rowSet.next()){
						billid= rowSet.getString("FBIZOBJID");
						System.out.println("passAssignment----------billid:"+billid);
					}*/
					info.setBillId(BOSUuid.read(billid)); 
					
					if (tmpAssignmentIds[i].endsWith("WFPCINST")) {
						StringBuilder sb1 = new StringBuilder();
						sb1.append("PASS::");
						sb1.append(tmpAssignmentIds[i]);
						sb1.append(":").append(tmpBillIds[i]);

						sb1.append(":").append(ctx.getCaller().toString());
						sb1.append(":").append(isProhibittedAfterProcClose);
						sb1.append(":").append(isAllowRepass);
						sb1.append(":").append("null");
						opinionMap.put("personIdList", personIdSet);
						opinionMap.put("sourceIds", sb1.toString());
					} else {
						AssignmentInfo assign = svc.getAssignmentById(tmpAssignmentIds[i]);

						StringBuilder sb1 = new StringBuilder();
						sb1.append("PASS::");
						sb1.append(assign.getProcInstId());
						sb1.append(":").append(assign.getBizObjectIds());

						sb1.append(":").append(ctx.getCaller().toString());
						sb1.append(":").append(isProhibittedAfterProcClose);
						sb1.append(":").append(isAllowRepass);
						sb1.append(":").append(assign.getAssignmentId());
						opinionMap.put("personIdList", personIdSet);
						opinionMap.put("sourceIds", sb1.toString());
					} 
					if (isSendMobile) {
						opinionMap.put("is_mobile", "true");
					}
					if (isSendMail) {
						opinionMap.put("is_mail", "true");
					}
					MultiApproveManageFactory.getLocalInstance(ctx).sendPassAroundMsgWithOption(info, userIds, opinionMap); 
				} 
			}
		
			if (count2 == 0) {
				//JSONUtils.SUCCESS("PASS_AROUND_SUCESS");
				return "success";
			} else {
				ResourceBundle res = ResourceBundle.getBundle(BillPassAssign.class.toString(), ctx.getLocale());
				StringBuilder msg = new StringBuilder();
				if (count1 > 0) {
					msg.append(res.getString("success")).append(count1).append(res.getString("person"));
					msg.append("<br>");
				} 
				msg.append(res.getString("failed")).append(count2).append(res.getString("person")).append(res.getString("reason"));
				//throw new WafBizException(msg.toString());
				return msg.toString();
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
			return e.getMessage().toString();
		} 
	}
	
	
	public static boolean checkPersonHasUser(Context ctx,String personId) throws Exception {
		IEnactmentService svc = EnactmentServiceFactory.createEnactService(ctx);
		Person[] persons = svc.getPersonByPersonID(personId); 
		return ((persons != null) && (persons.length != 0));
	}
	
	private static String getBillId(Context ctx,String assignmentID) throws BOSException {
		String billId = null;
		StringBuilder billIds = new StringBuilder();
		IEnactmentService svc = EnactmentServiceFactory.createEnactService(ctx);
		for (String assignId : assignmentID.split(";")) {
			if (billIds.length() > 0) {
				billIds.append(";");
			}
			AssignmentInfo assign = null;
			if (assignId.endsWith("WFPCINST")) {
				AssignmentInfo[] AssignmentInfo = svc.getAssignmentAllByProcInstId(assignId);
				assign = AssignmentInfo[0];
			} else {
				assign = svc.getAssignmentById(assignId);
			} 
			billId = assign.getBizObjectIds();
			billIds.append(billId);
		}
		return billIds.toString();
	}
}
