package com.kingdee.eas.custom;

import com.kingdee.bos.dao.ormapping.*;
import com.kingdee.bos.metadata.entity.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.*;

import java.sql.*;

import com.kingdee.eas.framework.*;
import net.sf.json.*;
import com.kingdee.bos.dao.*;
import com.kingdee.bos.util.BOSUuid; 
import com.kingdee.bos.webframework.exception.WafException;
import com.kingdee.bos.webframework.json.JSONUtils;
import com.kingdee.bos.webframework.workflow.WfWafUtil;
import com.kingdee.bos.workflow.monitor.IDynamicWfServiceFacade;
import com.kingdee.bos.workflow.monitor.WfFacadeUtilFactory;
import com.kingdee.bos.workflow.service.IDynamicWfService;
import com.kingdee.bos.workflow.service.ormrpc.*;
import com.kingdee.bos.workflow.transaction.WfTxContext;
import com.kingdee.bos.workflow.web.AppendActivityController;
import com.kingdee.bos.workflow.biz.trans.*;
import com.kingdee.bos.workflow.define.AssignmentMessage;
import com.kingdee.bos.workflow.define.ExtendedAttributeCollection;
import com.kingdee.bos.workflow.define.enhanced.EMessage;
import com.kingdee.bos.workflow.define.extended.ApproveActivityDef;
import com.kingdee.bos.workflow.ext.ConfigLoader;
import com.kingdee.bos.workflow.*;
import com.kingdee.jdbc.rowset.*;
import com.kingdee.util.StringUtils;

import java.util.*;

import com.kingdee.eas.base.permission.UserFactory;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.eas.common.*;
import com.kingdee.eas.util.app.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class BillPCInfo {
	public static net.sf.json.JSONObject getBillMap(final Context ctx,
			final String billid, final String sourceID, final String key)
			throws BOSException, EASBizException {
		final ObjectUuidPK pk = new ObjectUuidPK(billid);
		final SelectorItemCollection sic = new SelectorItemCollection();
		sic.add("entrys.*");
		sic.add("TabTableEntry.*");
		sic.add("TabEntry.*");
		IUITool factory = null;
		if (ctx != null) {
			factory = UIToolFactory.getLocalInstance(ctx);
		} else {
			factory = UIToolFactory.getRemoteInstance();
		}
		UIToolInfo uiInfo = factory.getUIToolInfo("select id where bostype = '"
				+ pk.getObjectType().toString() + "'");
		uiInfo = factory.getUIToolInfo((IObjectPK) new ObjectUuidPK(uiInfo
				.getId().toString()), sic);
		final SelectorItemCollection billSic = new SelectorItemCollection();
		for (int i = 0; i < uiInfo.getEntrys().size(); ++i) {
			billSic.add(uiInfo.getEntrys().get(i).getEntityAttributes());
		}
		for (int i = 0; i < uiInfo.getTabTableEntry().size(); ++i) {
			billSic.add(uiInfo.getTabTableEntry().get(i).getEntityAttributes());
		}
		final EntityViewInfo evi = new EntityViewInfo();
		evi.setSelector(billSic);
		IObjectValue info = null;
		if (ctx != null) {
			info = DynamicObjectFactory.getLocalInstance(ctx).getValue(
					pk.getObjectType(), (IObjectPK) pk, billSic);
		} else {
			info = DynamicObjectFactory.getRemoteInstance().getValue(
					pk.getObjectType(), (IObjectPK) pk, billSic);
		}
		final Map map = new HashMap();
		if (key.equals("billbase")) {
			if (sourceID != null && !"".equals(sourceID)) {
				final Map mapWF = new HashMap();
				try {
					IEnactmentService server = null;
					if (ctx != null) {
						server = EnactmentServiceFactory
								.createEnactService(ctx);
					} else {
						server = EnactmentServiceFactory
								.createRemoteEnactService();
					}
					final EASWfServiceData data = server.getEASWfServiceData(
							new String[] { sourceID }, false);
					final AssignmentInfo assInfo = data.getAssignmentInfo();
					final Map extendedAttrFromActDef = data
							.getExtendedAttribute();
					final Map map2 = server.getAssignmentArgument(sourceID,
							true);
					mapWF.put("MANUALDECISION_ITEMS", new StringBuilder()
							.append(map2.get("MANUALDECISION_ITEMS"))
							.toString());
					mapWF
							.put("MANUALDECISION_NAME", new StringBuilder()
									.append(map2.get("MANUALDECISION_NAME"))
									.toString());
					mapWF.put("ManualDecisionUIFunction", assInfo
							.getBizFunction());
					if (extendedAttrFromActDef.get("customUIStatus") != null) {
						mapWF.put("customUIStatus", extendedAttrFromActDef.get(
								"customUIStatus").toString());
					} else {
						mapWF.put("customUIStatus", "FINDVIEW");
					}
					if (extendedAttrFromActDef.get("manualDecisionItems_l2") != null) {
						final String manualDecisionItems = extendedAttrFromActDef
								.get("manualDecisionItems_l2").toString();
						mapWF.put("manualDecisionItems", manualDecisionItems);
					}
					mapWF.put("IsDynamicSign", extendedAttrFromActDef
							.get("IsDynamicSign"));
					mapWF.put("alwaysSetNextPersons", extendedAttrFromActDef
							.get("alwaysSetNextPersons"));
					final String sql = "select * from eas_audit where flag = 1 and fassignID='"
							+ sourceID + "'";
					final IRowSet rowSet = DbUtil.executeQuery(ctx, sql);
					mapWF.put("isAudit", rowSet.next() ? "success" : "error");
				} catch (WfException e1) {
					e1.printStackTrace();
				} catch (BOSException e2) {
					e2.printStackTrace();
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
				mapWF.put("BOSTYPE", pk.getObjectType().toString());
				map.put("mapAF", mapWF);
			}
			final Map mapBillInfo = new HashMap();
			for (int j = 0; j < uiInfo.getEntrys().size(); ++j) {
				final String fieldType = uiInfo.getEntrys().get(j)
						.getFieldType();
				final String classname = uiInfo.getEntrys().get(j).getDbName();
				final String entityAttributes = uiInfo.getEntrys().get(j)
						.getEntityAttributes();
				if (entityAttributes.indexOf(".") >= 0) {
					final String[] entrys = entityAttributes.split("\\.");
					if (info.get(entrys[0]) != null) {
						mapBillInfo.put(entityAttributes, BillInfoUtil
								.getValue(fieldType, classname,
										((CoreBaseInfo) info.get(entrys[0]))
												.get(entrys[1])));
					} else {
						mapBillInfo.put(entityAttributes, "");
					}
				} else {
					mapBillInfo.put(entityAttributes, BillInfoUtil.getValue(
							fieldType, classname, info.get(entityAttributes)));
				}
			}
			map.put("mapBillInfo", mapBillInfo);
			final List list = new ArrayList();
			try {
				final String sql2 = "select fid fid,fname_l2 fname,fsimplename fsimplename from T_BAS_Attachment where fid in (select fattachmentid from T_BAS_BoAttchAsso where fboid = '"
						+ billid + "')";
				final IRowSet rowSet2 = DbUtil.executeQuery(ctx, sql2);
				while (rowSet2.next()) {
					final Map mapAtt = new HashMap();
					final String fid = rowSet2.getString("fid");
					mapAtt.put("fid", fid);
					final String fname = rowSet2.getString("fname");
					final String fsimplename = rowSet2.getString("fsimplename");
					mapAtt.put("fname", String.valueOf(fname) + "."
							+ fsimplename);
					list.add(mapAtt);
				}
			} catch (SQLException e4) {
				e4.printStackTrace();
			}
			map.put("att", list);
			final net.sf.json.JSONObject json = net.sf.json.JSONObject
					.fromObject((Object) map);
			return json;
		}
		final net.sf.json.JSONObject json2 = new net.sf.json.JSONObject();
		final JSONArray ja = new JSONArray();
		for (int k = 0; k < uiInfo.getTabEntry().size(); ++k) {
			final UIToolTabEntryInfo tabEntryInfo = uiInfo.getTabEntry().get(k);
			final String entryAtt = tabEntryInfo.getEntryAtt();
			if (entryAtt.equals(key)) {
				final IObjectCollection coll = (IObjectCollection) info
						.get(entryAtt);
				final net.sf.json.JSONObject entryInfo = new net.sf.json.JSONObject();
				for (int l = 0; l < coll.size(); ++l) {
					final IObjectValue baseInfo = coll.getObject(l);
					for (int m = 0; m < uiInfo.getTabTableEntry().size(); ++m) {
						final UIToolTabTableEntryInfo tabTableEntryInfo = uiInfo
								.getTabTableEntry().get(m);
						final String fieldType2 = tabTableEntryInfo
								.getFieldType();
						final String classname2 = tabTableEntryInfo.getDbName();
						if (tabTableEntryInfo.getEntityAttributes().startsWith(
								entryAtt)) {
							final String entryField = tabTableEntryInfo
									.getEntityAttributes().replaceAll(
											String.valueOf(entryAtt) + ".", "");
							if (entryField.indexOf(".") >= 0) {
								final String[] entrys2 = entryField
										.split("\\.");
								if (baseInfo.get(entrys2[0]) != null) {
									entryInfo.put((Object) getKey(String
											.valueOf(entryAtt)
											+ "." + entryField),
											(Object) BillInfoUtil.getValue(
													fieldType2, classname2,
													((CoreBaseInfo) baseInfo
															.get(entrys2[0]))
															.get(entrys2[1])));
								} else {
									entryInfo.put((Object) getKey(String
											.valueOf(entryAtt)
											+ "." + entryField), (Object) "");
								}
							} else {
								entryInfo.put((Object) getKey(String
										.valueOf(entryAtt)
										+ "." + entryField),
										(Object) BillInfoUtil.getValue(
												fieldType2, classname2,
												baseInfo.get(entryField)));
							}
						}
					}
					ja.add((Object) entryInfo);
				}
			}
		}
		json2.put((Object) "Rows", (Object) ja);
		json2.put((Object) "Total", (Object) 1);
		return json2;
	}

	private static String getKey(final String key) {
		return key.replaceAll("\\.", "*");
	}

	public static void assignCountersign(final Context ctx,
			final String assignId, String comments, final String[] personIdArray)
			throws BOSException {
		final IEnactmentService server = EnactmentServiceFactory
				.createEnactService(ctx);
		final EASWfServiceData data = server.getEASWfServiceData(
				new String[] { assignId }, false);
		final AssignmentInfo assInfo = data.getAssignmentInfo();
		final Map extendedAttrFromActDef = data.getExtendedAttribute();
		System.out.println(extendedAttrFromActDef);
		final Map extendMsg = new HashMap();
		comments = String.valueOf(comments)
				+ "\r\n\r\n\u4f1a\u7b7e\u53d1\u8d77\u4eba\uff1a"
				+ ContextUtil.getCurrentUserInfo(ctx).getName(ctx.getLocale());
		extendMsg.put(ctx.getLocale(), comments);
		extendMsg.put("assignCountersign", Boolean.TRUE);
		server.assignCountersign(assInfo.getAssignmentId(), personIdArray,
				extendMsg);

		// server.addAssignToActivity(assInfo.getAssignmentId(), personIdArray);
	}

	public static Map appendActivity(Context ctx, Map params) throws BOSException {
		
		/*activitiesReq = [{"fpersonid":"02YAWFbqRbmfWSqeOrsNrIDvfe0=","fname_l2":"张磊","activityName":"城市经理"}]
		assignId = 99e3e7e8-5023-4571-89cd-50c97e6d2539WFWKITEM
		appendMode = 2
		routeMode = 2
		abortCurrentActivity = false
		signopinion = 请查看加签*/
		
		
		System.out.println("***************");
		long t1 = System.currentTimeMillis();
		String activitiesReq = (String) params.get("activities");
		System.out.println("***************activitiesReq = "+activitiesReq);
		String assignId = (String) params.get("assignId");
		System.out.println("***************assignId = "+assignId);
		String billId = "";
		String appendMode = (String) params.get("appendMode");
		System.out.println("***************appendMode = "+appendMode);
		String routeMode = (String) params.get("routeMode");
		System.out.println("***************routeMode = "+routeMode);
		boolean abortCurrentActivity = Boolean.valueOf((String) params.get("abortCurrentActivity")).booleanValue();
		System.out.println("***************abortCurrentActivity = "+abortCurrentActivity);
		String signopinion = (String) params.get("signopinion");
		System.out.println("***************signopinion = "+signopinion);
		PersonInfo p = ContextUtil.getCurrentUserInfo(ctx).getPerson();
		
		System.out.println("***************p = "+p==null);
		String personId = p.getId().toString();
		System.out.println("***************personId = "+personId);
		JSONArray activitiesJSON = JSON.parseArray(activitiesReq);
		System.out.println("***************activitiesJSON = "+activitiesJSON);
		
		ArrayList<IDynamicWfService.DynamicActivityDef> activities = new ArrayList();
		Map map = new HashMap();
		for (int i = 0; i < activitiesJSON.size(); i++) {
			
			System.out.println("***************activitiesJSON.size() = :"+i);
			
			IDynamicWfService.DynamicActivityDef act = new IDynamicWfService.DynamicActivityDef();
			ArrayList<IDynamicWfService.Performer> performerList = new ArrayList();
			JSONObject obj = activitiesJSON.getJSONObject(i);
			if (obj.getString("activityName").equals("")) {
				act.actName = "新建任务";
			} else {
				act.actName = obj.getString("activityName");
			}
			if (personId.equals(obj.getString("fpersonid"))) {
				map = new HashMap();
				map.put("status", "error1");
				return map;
			}
			IDynamicWfService.Performer p1 = new IDynamicWfService.Performer();
			p1.personId = obj.getString("fpersonid");
			p1.personName = obj.getString("fname_l2");
			performerList.add(p1);
			//IEnactmentService service = EnactmentServiceFactory.createRemoteEnactService(); 
			//System.out.println("***************service = :"+ service );
			
			if (ctx.getCaller() == null) {
				System.out.println("***************ctx.getCaller() = :"+ ctx.getCaller() );
			}
			if (ctx.getCaller().toString().length() == 0) {
				System.out.println("***************ctx.getCaller().toString().length() = :"+ ctx.getCaller().toString().length() );
			}
			
			EnactmentService service = new  EnactmentService(ctx);
			System.out.println("***************service = :"+ service );
			Map map1 = service.getActivityDefAndActivityInstInfo(assignId);
			
			System.out.println("***************map1 = :"+ map1 );
			ApproveActivityDef approveDef = map1.get("ACTIVITYDEF") != null ? (ApproveActivityDef) map1.get("ACTIVITYDEF") : null;
			IDynamicWfService.DynamicActivityDef actd = new IDynamicWfService.DynamicActivityDef();
			if (approveDef != null) {
				ExtendedAttributeCollection extendedAttrs = approveDef .getActivityHeader().getExtendedAttributes();
				String defaultKickBack = extendedAttrs.get("kickBack") != null ? extendedAttrs .get("kickBack").getValue() : "true";
				String defaultJumpTo = extendedAttrs.get("jumpTo") != null ? extendedAttrs .get("jumpTo").getValue() : "true";
				String defaultAppendActivity = extendedAttrs .get("appendActivity") != null ? extendedAttrs.get("appendActivity").getValue() : "true";
				String defaultsubmitNextPerformer = extendedAttrs.get("assignnextparticipantActivity") != null ? extendedAttrs.get("assignnextparticipantActivity").getValue(): "true";
				AssignmentMessage msg = approveDef.getMessage();

				AssignmentInfo assignmentInfo = service.getAssignmentById(assignId);
				billId = assignmentInfo.getBizObjectIds();
				Config cfg = com.kingdee.bos.workflow.service.ormrpc.ConfigLoader.getConfig4Server();
				System.out.println("***************billID = "+billId);
				boolean allowAppend = cfg.getBoolean("append.activity.default.allow.append", billId,StringUtils.isEmpty(defaultAppendActivity) ? "true": defaultAppendActivity);
				System.out.println("***************allowAppend = "+allowAppend);
				boolean allowJumpTo = cfg.getBoolean("append.activity.default.allow.jump.to", billId,StringUtils.isEmpty(defaultJumpTo) ? "true": defaultJumpTo);
				System.out.println("***************allowJumpTo = "+allowJumpTo);
				boolean allowKickBack = cfg.getBoolean("append.activity.default.allow.kick.back", billId,StringUtils.isEmpty(defaultKickBack) ? "true": defaultKickBack);
				System.out.println("***************allowKickBack = "+allowKickBack);
				boolean allowSubmitNextPerformer = cfg.getBoolean("append.activity.default.allow.submitNextPerformer",billId,StringUtils.isEmpty(defaultsubmitNextPerformer) ? "true": defaultsubmitNextPerformer);
				System.out.println("***************allowSubmitNextPerformer = "+allowSubmitNextPerformer);
				boolean isSendSMS = cfg.getBoolean("append.activity.default.sendSMS", billId, "false");
				System.out.println("***************isSendSMS = "+isSendSMS);
				boolean isSendMail = cfg.getBoolean("append.activity.default.sendMail", billId, "false");
				System.out.println("***************isSendMail = "+isSendMail);
				String attachmentMode = cfg.getString("append.activity.default.attachment.mode", billId, "1");
				System.out.println("***************attachmentMode = "+attachmentMode);
				act.allowAppend = allowAppend;
				act.allowJumpTo = allowJumpTo;
				act.allowKickBack = allowKickBack;
				act.allowSubmitNextPerformer = allowSubmitNextPerformer;
				act.isSendSMS = isSendSMS;
				act.isSendMail = isSendMail;
				act.attachmentMode = attachmentMode;
			}
			act.performers = ((IDynamicWfService.Performer[]) performerList .toArray(new IDynamicWfService.Performer[0]));
			activities.add(act);
		}
		try {
			
			System.out.println("***************try = :trytrytrytrytrytry"  );
			
			IEnactmentService service = EnactmentServiceFactory .createEnactService(ctx);
			AssignmentInfo assign = service.getAssignmentById(assignId);

			IDynamicWfServiceFacade svc = WfFacadeUtilFactory.getDynamicWfService(ctx);
			svc.appendActivity(assignId,(IDynamicWfService.DynamicActivityDef[]) activities.toArray(new IDynamicWfService.DynamicActivityDef[0]),appendMode, routeMode, abortCurrentActivity);
//			String personNamestr = "";
//			for (int i = 0; i < activitiesJSON.size(); i++) {
//				JSONObject obj = activitiesJSON.getJSONObject(i);
//				personNamestr = personNamestr + "【" + obj.getString("fname_l2")
//						+ "】";
//				saveAppendOp(ctx, assignId, ctx.getCaller().toString(), obj
//						.getString("fpersonid"), assign.getActInstId());
//
//			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("lightapp appendActivity error :: "+ e.getMessage());
			map.put("success", "false");
			map.put("errorCode", e.getMessage());
			map.put("status", "error2");

			System.out.println("appendActivity timeout :: "+ (System.currentTimeMillis() - t1));
			return map;
		}
		IDynamicWfServiceFacade svc;
		map.put("status", "success");

		System.out.println("appendActivity timeout :: "+ (System.currentTimeMillis() - t1));
		return map;
	}

//	private static String saveAppendOp(Context ctx, String assignId, String userid,
//			String toUserid, String actid) throws Exception {
//		try {
//			String sql = " insert into  T_wf_APPENDACTIVITY (Factinstid,fpersonuserid,ftopersonuserid,fassignid) values('"
//					+ actid
//					+ "','"
//					+ userid
//					+ "','"
//					+ toUserid
//					+ "','"
//					+ assignId + "') ";
//			DbUtil.execute(ctx, sql);
//		} catch (Exception e) {
//			System.out.println("lightapp saveBill error :: " + e.getMessage());
//			throw new Exception("EAS_ERROR_00001", e);
//		}
//		return "true";
//	}
	 
	public static Map appendActivityNew(Context ctx, Map params) throws BOSException {
		/*
		activitiesReq = [{"fpersonid":"02YAWFbqRbmfWSqeOrsNrIDvfe0=","fname_l2":"张磊","activityName":"城市经理"}]
		assignId = 99e3e7e8-5023-4571-89cd-50c97e6d2539WFWKITEM
		appendMode = 2
		routeMode = 2
		abortCurrentActivity = false
		signopinion = 请查看加签*/
		
		boolean isOverrideMsg = false;  
		
		String assignmentID = (String) params.get("assignmentId");
		try {
			  
			String appendMode = (String) params.get("appendMode");
			String routeMode = (String) params.get("routeMode");
			boolean abortCurrentActivity = Boolean.valueOf((String) params.get("abortCurrentActivity")).booleanValue(); 
			
			Locale locale = ctx.getLocale();
			Locale defaultLocale = new Locale("l2");
			ArrayList<IDynamicWfService.DynamicActivityDef> activities = new ArrayList();
			
			 
			
			System.out.println("appendActivityNew--------newUser:"+ctx.getCaller());
			
			activities = getActivities(ctx,params);
			SelectorItemCollection sic = new SelectorItemCollection();
			sic.add(new SelectorItemInfo("person.id"));
			sic.add(new SelectorItemInfo("person.name"));
			 
			System.out.println("appendActivityNew--------ctx.getCaller():"+ctx.getCaller());
			PersonInfo pi = UserFactory.getLocalInstance(ctx).getUserInfo( ctx.getCaller(), sic).getPerson();
			
			String personId;
			if (pi != null) {
				System.out.println("appendActivityNew--------PersonInfo---pi:"+pi.getName());
				personId = pi.getId().toString();
				for (IDynamicWfService.DynamicActivityDef d : activities) {
					for (IDynamicWfService.Performer p : d.performers) {
						if (p.personId.equals(personId)) {
							isOverrideMsg = true;
							throw new  Exception("加签人员不能是自己。");
						}
					}
				}
			} 
			//IDynamicWfServiceFacade svc = WfFacadeUtilFactory.createRemoteDynamicWfService();
			IDynamicWfServiceFacade svc = WfFacadeUtilFactory.getDynamicWfService(ctx);
			svc.appendActivity(assignmentID,
					(IDynamicWfService.DynamicActivityDef[])(IDynamicWfService.DynamicActivityDef[]) activities.toArray(new IDynamicWfService.DynamicActivityDef[0]),
					appendMode, routeMode, abortCurrentActivity);

		} catch (Exception e) {
			Map map = new HashMap();
			String errorMsg = "";

			errorMsg = e.getMessage();
			e.printStackTrace();
			map.put("success", "false");
			map.put("errorCode", e.getMessage());
			map.put("status", "error2");
 
			return map;
			//throw new  Exception(errorMsg, e);
		} finally {
			 
		}
		//JSONUtils.SUCCESS(Boolean.valueOf(true)); 
		Map map = new HashMap();
		map.put("status", "success");
		return map;
	}
		
	private static ArrayList<IDynamicWfService.DynamicActivityDef> getActivities( Context ctx, Map params ) throws BOSException, EASBizException,WafException, SQLException {
		ArrayList<IDynamicWfService.DynamicActivityDef> activities = new ArrayList();
		String assignmentId = ((String) params.get("assignmentId")).split(";")[0];
		String billId = ((String) params.get("billIds")).split(";")[0];
		String id = (String) params.get("ids");
		String position = (String) params.get("positions");
		String option = (String) params.get("option");
		String name = (String) params.get("names");
		EnactmentService service = new  EnactmentService(ctx);
		//Map map = WfWafUtil.getWfService().getActivityDefAndActivityInstInfo(assignmentId);
		Map map = service.getActivityDefAndActivityInstInfo(assignmentId);
		ApproveActivityDef approveDef = (map.get("ACTIVITYDEF") != null) ? (ApproveActivityDef) map.get("ACTIVITYDEF"): null;
		
		String getBillId = "SELECT FBIZOBJID  FROM  T_WFR_Assign  where  FASSIGNID  = '"+assignmentId+"' ";
		final IRowSet rowSet = DbUtil.executeQuery(ctx, getBillId); 
		while(rowSet.next()){
			billId= rowSet.getString("FBIZOBJID");
			System.out.println("getActivities----------billId:"+billId);
		}
		if (approveDef != null) {
			ExtendedAttributeCollection extendedAttrs = approveDef.getActivityHeader().getExtendedAttributes();
			String defaultKickBack = (extendedAttrs.get("kickBack") != null) ? extendedAttrs.get("kickBack").getValue(): "true";
			String defaultJumpTo = (extendedAttrs.get("jumpTo") != null) ? extendedAttrs.get("jumpTo").getValue(): "true";
			String defaultAppendActivity = (extendedAttrs.get("appendActivity") != null) ? extendedAttrs.get("appendActivity").getValue(): "true";
			String defaultsubmitNextPerformer = (extendedAttrs.get("assignnextparticipantActivity") != null) ? extendedAttrs.get("assignnextparticipantActivity").getValue(): "true"; 
			AssignmentMessage msg = approveDef.getMessage();
			
		
			String defaultMessage = msg.getTitle( ctx.getLocale()); 
			boolean sendMail = (msg != null) && (msg.isSendMail());
			EMessage emsg = approveDef.getEMessage();
			boolean sendSMS = (emsg != null) && (emsg.isEnable());
			String defaultIsSendSMS = String.valueOf(sendSMS);
			String defaultIsSendMail = String.valueOf(sendMail);

			//com.kingdee.bos.workflow.ext.Config cfg = WfWafUtil.getWebExtConfig();
			
			com.kingdee.bos.workflow.ext.Config cfg =  ConfigLoader.getConfig4Server();
			
			//Config cfg = ConfigLoader.getConfig4Server();
			boolean allowAppend = cfg.getBoolean("append.activity.default.allow.append", billId,StringUtils.isEmpty(defaultAppendActivity) ? "true": defaultAppendActivity);
			boolean allowJumpTo = cfg.getBoolean( "append.activity.default.allow.jump.to", billId, (StringUtils.isEmpty(defaultJumpTo)) ? "true" : defaultJumpTo);
			boolean allowKickBack = cfg.getBoolean( "append.activity.default.allow.kick.back", billId, (StringUtils.isEmpty(defaultKickBack)) ? "true" : defaultKickBack);
			boolean allowSubmitNextPerformer = cfg.getBoolean("append.activity.default.allow.submitNextPerformer",billId,StringUtils.isEmpty(defaultsubmitNextPerformer) ? "true": defaultsubmitNextPerformer);
			
			boolean isSendSMS = cfg.getBoolean("append.activity.default.sendSMS", billId, "false");
			System.out.println("***************isSendSMS = "+isSendSMS);
			boolean isSendMail = cfg.getBoolean("append.activity.default.sendMail", billId, "false");
			System.out.println("***************isSendMail = "+isSendMail);
			String attachmentMode = cfg.getString("append.activity.default.attachment.mode", billId, "1");  
			String message = cfg.getString("append.activity.default.act.msg", billId, defaultMessage);

			for (int i = 0; i < id.split(";").length; ++i) {
				IDynamicWfService.DynamicActivityDef act = new IDynamicWfService.DynamicActivityDef();
				act.allowAppend = allowAppend;
				act.allowJumpTo = allowJumpTo;
				act.allowKickBack = allowKickBack;
				act.allowSubmitNextPerformer = allowSubmitNextPerformer;
				act.isSendSMS = isSendSMS;
				act.isSendMail = isSendMail;
				act.attachmentMode = attachmentMode;
				act.option = option;
				//String defaultValue = position.split(";")[i] + ResourceBundle.getBundle(AppendActivityController.class.getName(), WfWafUtil.getContext().getLocale()).getString( "approve");
				//String defaultValue = position.split(";")[i] + ResourceBundle.getBundle(AppendActivityController.class.getName(), ctx.getLocale()).getString( "approve");
				 
				String defaultValue = position.split(";")[i] + "\u5ba1\u6279";
				
				act.actName = cfg.getString("append.activity.default.act.name", billId, defaultValue);
				if (act.actName.indexOf("<%POS%>") >= 0) act.actName = act.actName.replace("<%POS%>", position .split(";")[i]);
				act.message = message;
				IDynamicWfService.Performer p = new IDynamicWfService.Performer();
				p.personId = id.split(";")[i];
				p.personName = name.split(";")[i];
				act.performers = new IDynamicWfService.Performer[] { p };
				activities.add(act);
			}
		}

		return activities;
	} 


}
