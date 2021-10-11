package com.kingdee.eas.base.message.webservice;


import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.ormapping.ObjectStringPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.service.job.util.SQL;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.bos.workflow.AssignmentInfo;
import com.kingdee.bos.workflow.ProcessInstInfo;
import com.kingdee.bos.workflow.metas.WfAssignmentState;
import com.kingdee.bos.workflow.metas.WfState;
import com.kingdee.bos.workflow.service.EnactmentServiceProxy;
import com.kingdee.bos.workflow.service.ormrpc.IEnactmentService;
import com.kingdee.eas.base.message.AssignReadInfo;
import com.kingdee.eas.base.message.BMCMessageInfo;
import com.kingdee.eas.base.message.MsgBizType;
import com.kingdee.eas.base.message.MsgPriority;
import com.kingdee.eas.base.message.MsgStatus;
import com.kingdee.eas.base.message.MsgType;
import com.kingdee.eas.base.permission.IUser;
import com.kingdee.eas.base.permission.UserFactory;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.util.StringUtils;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.log4j.Logger;

public class MCInvoke {
	private static Logger logger = Logger
			.getLogger("com.kingdee.eas.base.message.webservice.MCInvoke");

	private static final MCInvoke invoke = new MCInvoke();

	private static List configList = null;

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
		message.setContext(ctx);
		try {
			logger.debug("Receiver ID :" + messageInfo.getReceiver());
			UserInfo userInfo = UserFactory.getLocalInstance(ctx).getUserInfo(
					new ObjectUuidPK(messageInfo.getReceiver()));
			logger.debug("String number = userInfo.getNumber();");
			String number = userInfo.getNumber();
			message.setRecieveNumber(number);

			String senderId = "";
			if (!(StringUtils.isEmpty(messageInfo.getSenderId())))
				senderId = messageInfo.getSenderId();
			else {
				senderId = ctx.getCaller().toString();
			}
			message.setSenderNumber(UserFactory.getLocalInstance(ctx)
					.getUserInfo(new ObjectUuidPK(senderId)).getNumber());
		} catch (Exception e) {
			logger.error("获取用户信息时出错:" + e.getMessage(), e);
		}

		return message;
	}

	public static WSMessage intoMessage(AssignReadInfo messageInfo, Context ctx) {
		WSMessage message = new WSMessage();
		AssignmentInfo assInfo = null;

		Locale locale = ctx.getLocale();
		try {
			IEnactmentService iEnactmentService = new EnactmentServiceProxy(ctx);
			assInfo = iEnactmentService.getAssignmentById(messageInfo
					.getAssignID().toString());
		} catch (BOSException e) {
			logger.error("获取任务消息Info对象出错:" + e.getMessage(), e);
		}

		message.setMsgID(messageInfo.getId().toString());
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
		message.setContext(ctx);
		try {
			message.setRecieveNumber(UserFactory.getLocalInstance(ctx)
					.getUserInfo(new ObjectUuidPK(assInfo.getUserId()))
					.getNumber());

			message.setSenderNumber(UserFactory.getLocalInstance(ctx)
					.getUserInfo(new ObjectUuidPK(messageInfo.getSenderId()))
					.getNumber());
		} catch (Exception e) {
			logger.error("获取用户信息时出错:" + e.getMessage(), e);
		}
		return message;
	}

	public WfrProcMessage intoWfrProcMessage(ProcessInstInfo processInstInfo,
			HashMap<String, String> extInfoMap, Context ctx) {
		String billId = (String) extInfoMap.get("billId");
		String orgId = (String) extInfoMap.get("orgId");
		int isAdd = Integer.parseInt((String) extInfoMap.get("isAdd"));
		WfrProcMessage wfrProcMsg = new WfrProcMessage();
		wfrProcMsg.setProcCode(processInstInfo.getCode());
		wfrProcMsg.setProcInstId(processInstInfo.getProcInstId());
		wfrProcMsg.setProcState(processInstInfo.getState());
		wfrProcMsg.setProcStateName(WfState.getEnum(processInstInfo.getState())
				.getAlias());
		wfrProcMsg
				.setProcSubject(processInstInfo.getInstTopic(ctx.getLocale()));
		wfrProcMsg.setCreatedtime(processInstInfo.getCreatedTime());
		wfrProcMsg.setUpdateTime(processInstInfo.getLastStateTime());
		wfrProcMsg.setBillID(billId);
		wfrProcMsg.setInitiator(processInstInfo.getInitiatorName(ctx
				.getLocale()));
		wfrProcMsg.setInitiatorNumber(getPersonNumByUid(ctx,
				processInstInfo.getInitiatorId()).getNumber());
		wfrProcMsg.setOrgID(orgId);
		wfrProcMsg.setIsAdd(isAdd);
		return wfrProcMsg;
	}

	public WfrAssignMessage intoWfrAssignMessage(AssignmentInfo assignInfo,
			HashMap<String, String> extInfoMap, Context ctx) {
		WfrAssignMessage wfrAssignMsg = new WfrAssignMessage();
		wfrAssignMsg.setAssignId(assignInfo.getAssignmentId());
		wfrAssignMsg.setAssignSubject(assignInfo.getSubject(ctx.getLocale()));
		wfrAssignMsg.setProcInstId(assignInfo.getProcInstId());
		wfrAssignMsg.setState(assignInfo.getState().getName());
		wfrAssignMsg.setStateName(WfAssignmentState.getEnum(
				assignInfo.getState().getValue()).getAlias());
		wfrAssignMsg.setAcceptTime(assignInfo.getAcceptTime());
		wfrAssignMsg.setDealTime(assignInfo.getEndTime());
		wfrAssignMsg.setBillID(assignInfo.getBizObjectIds());
		String[] user = getInitorByAssignInfo(assignInfo, ctx);
		if (user != null) {
			wfrAssignMsg.setInitiator(user[0]);
			wfrAssignMsg.setInitiatorNumber(user[1]);
		}
		wfrAssignMsg.setHandler(assignInfo.getUserName(ctx.getLocale()));
		wfrAssignMsg.setHandlerNumber(getPersonNumByUid(ctx,
				assignInfo.getUserId()).getNumber());
		String orgId = "";
		if (extInfoMap != null) {
			orgId = (String) extInfoMap.get("orgId");
		}
		wfrAssignMsg.setOrgID(orgId);
		return wfrAssignMsg;
	}

	public void wsImplReadCplAssign(AssignmentInfo assignInfo,
			HashMap<String, String> extMapInfo, Context ctx) {
		WfrAssignMessage wfrAssignMsg = intoWfrAssignMessage(assignInfo,
				extMapInfo, ctx);
		String methodName = "addCompletedMessage";
		parseCfgAndInvoke(methodName, wfrAssignMsg, ctx);
	}

	public void wsImplReadMyProcInfo(ProcessInstInfo processInstInfo,
			HashMap<String, String> extInfoMap, Context ctx) {
		WfrProcMessage wfrProcMsg = intoWfrProcMessage(processInstInfo,
				extInfoMap, ctx);
		String methodName = "addInitiatorMessage";
		parseCfgAndInvoke(methodName, wfrProcMsg, ctx);
	}

	public void wsImplRead(WSMessage message, Context ctx) {
		WSConfigInfo configInfo = null;
		MessageWebServiceDao dao = null;
		if (configList == null) {
			return;
		}
		if (configList.size() == 0) {
			logger.error("配置信息不正确");
			return;
		}

		Iterator iterator = configList.iterator();
		while (iterator.hasNext()) {
			configInfo = (WSConfigInfo) iterator.next();
			if ((configInfo.getClassName() == null)
					|| (configInfo.getClassName().trim().length() == 0)) {
				logger.error("配置文件配置错误！");
				throw new RuntimeException("wrong config in the WSConfig.xml");
			}
			if (!(ifExecute(configInfo, message, ctx)))
				continue;
			try {
				message.setUrl(configInfo.getServerPath());
				dao = (MessageWebServiceDao) (MessageWebServiceDao) Class
						.forName(configInfo.getClassName()).newInstance();
				dao.addMessage(message);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public void wsImplRemoveBMC(BMCMessageInfo messageInfo, Context ctx) {
		WSConfigInfo configInfo = null;
		MessageWebServiceDao dao = null;

		if (configList == null) {
			return;
		}
		if (configList.size() == 0) {
			logger.error("配置信息不正确");
			return;
		}

		WSMessage message = new WSMessage();
		message.setType(messageInfo.getType());
		message.setBizType(messageInfo.getBizType());

		Iterator iterator = configList.iterator();
		while (iterator.hasNext()) {
			configInfo = (WSConfigInfo) iterator.next();
			if ((configInfo.getClassName() == null)
					|| (configInfo.getClassName().trim().length() == 0)) {
				logger.error("配置文件配置错误！");
				throw new RuntimeException("wrong config in the WSConfig.xml");
			}
			if (!(ifExecute(configInfo, message, ctx)))
				continue;
			try {
				dao = (MessageWebServiceDao) (MessageWebServiceDao) Class
						.forName(configInfo.getClassName()).newInstance();
				dao.removeMessage(messageInfo.getId().toString());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public void wsImplRemoveAss(List IDList, Context ctx) {
		WSConfigInfo configInfo = null;
		MessageWebServiceDao dao = null;

		if (configList == null) {
			return;
		}
		if (configList.size() == 0) {
			logger.error("配置信息不正确");
			return;
		}

		WSMessage message = new WSMessage();
		message.setType(MsgType.TASK);
		message.setBizType(MsgBizType.WORKFLOW);

		Iterator iterator = configList.iterator();
		while (iterator.hasNext()) {
			configInfo = (WSConfigInfo) iterator.next();
			if ((configInfo.getClassName() == null)
					|| (configInfo.getClassName().trim().length() == 0)) {
				logger.error("配置文件配置错误！");
				throw new RuntimeException("wrong config in the WSConfig.xml");
			}

			if (!(ifExecute(configInfo, message, ctx)))
				continue;
			try {
				dao = (MessageWebServiceDao) (MessageWebServiceDao) Class
						.forName(configInfo.getClassName()).newInstance();
				for (int i = 0; i < IDList.size(); ++i)
					dao.removeMessage((String) IDList.get(i));
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public void wsImplUpdate(WSMessage message, Context ctx) {
		WSConfigInfo configInfo = null;
		MessageWebServiceDao dao = null;
		if (configList == null) {
			return;
		}
		if (configList.size() == 0) {
			logger.error("配置信息不正确");
			return;
		}

		Iterator iterator = configList.iterator();
		while (iterator.hasNext()) {
			configInfo = (WSConfigInfo) iterator.next();
			if ((configInfo.getClassName() == null)
					|| (configInfo.getClassName().trim().length() == 0)) {
				logger.error("配置文件配置错误！");
				throw new RuntimeException("wrong config in the WSConfig.xml");
			}
			if (!(ifExecute(configInfo, message, ctx)))
				continue;
			try {
				dao = (MessageWebServiceDao) (MessageWebServiceDao) Class
						.forName(configInfo.getClassName()).newInstance();
				dao.updateMessage(message.getMsgID());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public void wsImplUpdateMulti(String IDs, MsgStatus state, Context ctx) {
		WSConfigInfo configInfo = null;
		MessageWebServiceDao dao = null;
		if (configList == null) {
			return;
		}
		if (configList.size() == 0) {
			logger.error("配置信息不正确");
			return;
		}

		Iterator iterator = configList.iterator();
		while (iterator.hasNext()) {
			configInfo = (WSConfigInfo) iterator.next();
			if ((configInfo.getClassName() == null)
					|| (configInfo.getClassName().trim().length() == 0)) {
				logger.error("配置文件配置错误！");
				throw new RuntimeException("wrong config in the WSConfig.xml");
			}
			try {
				dao = (MessageWebServiceDao) (MessageWebServiceDao) Class
						.forName(configInfo.getClassName()).newInstance();
				dao.updateMessages(IDs, state);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	private boolean ifExecute(WSConfigInfo configInfo, WSMessage messageInfo,
			Context ctx) {
		boolean push = false;

		if (!(ctx.getAIS().equals(configInfo.getDataCenter()))) {
			return push;
		}

		if ((configInfo.isTask())
				&& (MsgType.TASK.equals(messageInfo.getType()))) {
			push = true;
		} else if ((configInfo.isNotice())
				&& (MsgType.NOTICE.equals(messageInfo.getType()))) {
			if ((configInfo.isNotice_workFlow())
					&& (MsgBizType.WORKFLOW.equals(messageInfo.getBizType()))) {
				push = true;
			} else if ((configInfo.isNotice_Forwarn())
					&& (MsgBizType.FORWARN.equals(messageInfo.getBizType()))) {
				push = true;
			} else if ((configInfo.isNotice_online())
					&& (!(MsgBizType.ONLINE.equals(messageInfo.getBizType())))) {
				push = true;
			} else if ((configInfo.isNotice_office())
					&& (!(MsgBizType.OFFICE.equals(messageInfo.getBizType())))) {
				push = true;
			} else if ((configInfo.isNotice_Urgent())
					&& (MsgBizType.URGENT.equals(messageInfo.getBizType()))) {
				push = true;
			} else if ((configInfo.isNotice_async())
					&& (!(MsgBizType.ASYNCHRONISM.equals(messageInfo
							.getBizType())))) {
				push = true;
			} else if ((configInfo.isNotice_xitongoffice())
					&& (!(MsgBizType.XITONGOFFICE.equals(messageInfo
							.getBizType())))) {
				push = true;
			} else if ((configInfo.isNotice_hr())
					&& (!(MsgBizType.HR.equals(messageInfo.getBizType())))) {
				push = true;
			} else
				push = false;
		} else if ((configInfo.isOnline())
				&& (MsgType.ONLINE.equals(messageInfo.getType()))) {
			push = true;
		} else
			push = false;

		return push;
	}

	public static MCInvoke getInstance() {
		if (configList == null) {
			configList = MessageServiceConfig.getMSConfig().LoadConfig();
		}
		return invoke;
	}

	private UserInfo getPersonNumByUid(Context ctx, String userId) {
		if (userId == null) {
			logger.error("第三方消息推送：获取用户信息失败，用户id为：" + userId);
			return null;
		}
		UserInfo userInfo = null;
		try {
			userInfo = UserFactory.getLocalInstance(ctx).getUserByID(
					new ObjectStringPK(userId));
		} catch (EASBizException e) {
			logger.error("第三方消息推送：获取用户信息失败，用户id为：" + userId);
			logger.error(e);
		} catch (BOSException e) {
			logger.error("第三方消息推送：获取用户信息失败，用户id为：" + userId);
			logger.error(e);
		}
		return userInfo;
	}

	private void parseCfgAndInvoke(String methodName, Object obj, Context ctx) {
		WSConfigInfo configInfo = null;
		MessageWebServiceDao dao = null;
		if (configList == null) {
			return;
		}
		if (configList.size() == 0) {
			logger.error("配置信息不正确");
			return;
		}

		Iterator iterator = configList.iterator();
		while (iterator.hasNext()) {
			configInfo = (WSConfigInfo) iterator.next();
			if ((configInfo.getClassName() == null)
					|| (configInfo.getClassName().trim().length() == 0)) {
				logger.error("配置文件配置错误！");
				throw new RuntimeException("wrong config in the WSConfig.xml");
			}
			if (!(configInfo.getDataCenter().equals(ctx.getAIS())))
				continue;
			try {
				dao = (MessageWebServiceDao) (MessageWebServiceDao) Class
						.forName(configInfo.getClassName()).newInstance();
				if ("addInitiatorMessage".equals(methodName)) {
					WfrProcMessage wfrProcMsg = (WfrProcMessage) obj;
					dao.addInitiatorMessage(wfrProcMsg);
				} else if ("addCompletedMessage".equals(methodName)) {
					WfrAssignMessage wfrAssignMsg = (WfrAssignMessage) obj;
					dao.addCompletedMessage(wfrAssignMsg);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	private String[] getInitorByAssignInfo(AssignmentInfo assignInfo,
			Context ctx) {
		String fnumber = "";
		String fname_l2 = "";
		String procinstId = assignInfo.getProcInstId();
		String procSql = "select fnumber,fname_l2 from t_pm_user a left join t_wfr_procinst b on a.fid = b.FINITIATORID where b.FPROCINSTID ='"
				+ procinstId + "'";
		try {
			fnumber = (String) ((Map) SQL.executeQuery(ctx, procSql).get(0))
					.get("fnumber");
			fname_l2 = (String) ((Map) SQL.executeQuery(ctx, procSql).get(0))
					.get("fname_l2");
		} catch (Exception e) {
			logger.error("第三方消息推送：获取用户信息失败,流程实例id为：" + procinstId + ";异常信息为"
					+ e);
			return null;
		}
		return new String[] { fname_l2, fnumber };
	}
}