package com.kingdee.bos.workflow.ext;

import java.io.Serializable;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.bos.util.rpc.Ajax;
import com.kingdee.bos.util.rpc.ObjectFactory;
import com.kingdee.bos.util.rpc.RpcProxy;
import com.kingdee.bos.workflow.ext.app.WfUserAdapter;

public class Config implements Serializable {
	private static final long serialVersionUID = -412708232446441877L;
	private static Logger log = Logger.getLogger(Config.class);
	public static final String ASSIGN_DEVOLVE_MODE = "assignment.devolve.mode";
	public static final String APPROVE_HISTORY_ORDER_BY = "approve.history.order.by";
	public static final String MULTIAPPROVE_CLIENT_EXT = "multiapprove.client.ext";
	public static final String MULTIAPPROVE_SERVER_EXT = "multiapprove.server.ext";
	public static final String MULTIAPPROVE_WEB_SCRIPT_FILE = "multiapprove.web.scriptFile";
	public static final String MULTIAPPROVE_WEB_INCLUDE_HTML = "multiapprove.web.includeHTML";
	public static final String MULTIAPPROVE_WEB_SCRIPT_CLASS = "multiapprove.web.scriptClass";
	public static final String WF_MONITOR_IMAGE_UI_FACADE = "wf.monitor.image.ui.facade";
	public static final String WF_MONITOR_EXT_CLASS = "wf.monitor.ext.class";
	public static final String WF_MONITOR_IS_SUPPORT_NEXT_PERSON = "wf.monitor.is.support.next.person";
	public static final String MULTIAPPROVE_EXT_PRINT = "multiapprove.ext.onprint";
	public static final String APPEND_ACTIVITY_DEFAULT_ATTACHMENTMODE = "append.activity.default.attachment.mode";
	public static final String APPEND_ACTIVITY_DEFAULT_ACCEPTMODE = "append.activity.default.accept.mode";
	public static final String APPEND_ACTIVITY_DEFAULT_ROUTEMODE = "append.activity.default.route.mode";
	public static final String APPEND_ACTIVITY_DEFAULT_APPENDMODE = "append.activity.default.append.mode";
	public static final String APPEND_ACTIVITY_DEFAULT_ALLOWKICKBACK = "append.activity.default.allow.kick.back";
	public static final String APPEND_ACTIVITY_DEFAULT_ALLOWJUMPTO = "append.activity.default.allow.jump.to";
	public static final String APPEND_ACTIVITY_DEFAULT_ALLOWAPPEND = "append.activity.default.allow.append";
	public static final String APPEND_NEXTPERFORMER_DEFAULT_ALLOWSUBMITNEXTPERFORMER = "append.activity.default.allow.submitNextPerformer";
	public static final String APPEND_ACTIVITY__DEFAULT_SENDSMS = "append.activity.default.sendSMS";
	public static final String APPEND_ACTIVITY__DEFAULT_SENDMAIL = "append.activity.default.sendMail";
	public static final String APPEND_ACTIVITY_DEFAULT_ABORTCURRENTACTIVITY = "append.activity.default.abort.current.activity";
	public static final String APPEND_ACTIVITY_DEFAULT_ACT_NAME = "append.activity.default.act.name";
	public static final String APPEND_ACTIVITY_DEFAULT_ACT_MSG = "append.activity.default.act.msg";
	public static final String SUBMIT_NEXTPERFORMER_DEFAULT_ATTACHMENTMODE = "submit.nextPerformer.default.attachment.mode";
	public static final String SUBMIT_NEXTPERFORMER_DEFAULT_ACCEPTMODE = "submit.nextPerformer.default.accept.mode";
	public static final String SUBMIT_NEXTPERFORMER_DEFAULT_ALLOWKICKBACK = "submit.nextPerformer.default.allow.kick.back";
	public static final String SUBMIT_NEXTPERFORMER_DEFAULT_ALLOWJUMPTO = "submit.nextPerformer.default.allow.jump.to";
	public static final String SUBMIT_NEXTPERFORMER_DEFAULT_ALLOWAPPEND = "submit.nextPerformer.default.allow.append";
	public static final String SUBMIT_NEXTPERFORMER_DEFAULT_ALLOWSUBMITNEXTPERFORMER = "submit.nextPerformer.default.allow.submitNextPerformer";
	public static final String SUBMIT_NEXTPERFORMER_DEFAULT_SENDSMS = "submit.nextPerformer.default.sendSMS";
	public static final String SUBMIT_NEXTPERFORMER_DEFAULT_SENDMAIL = "submit.nextPerformer.default.sendMail";
	public static final String SUBMIT_NEXTPERFORMER_DEFAULT_ACT_NAME = "submit.nextPerformer.default.act.name";
	public static final String SUBMIT_NEXTPERFORMER_DEFAULT_ACT_MSG = "submit.nextPerformer.default.act.msg";
	public static final String PASS_FEEDBACK_CONTROL_MANDATORY = "pass.feedback.control.mandatory";
	public static final String PASS_FEEDBACK_ALLOW_REPASS = "pass.feedback.allow.repass";
	public static final String PASS_FEEDBACK_CONTROL_DEFAULT = "pass.feedback.control.default";
	public static final String PASS_OPINION_SAVEED_DEFAULT = "pass.opinion.saved.default";
	public static final String PASS_OPINION_MUST_SAVED = "pass.opinion.must.saved";
	public static final String NEXT_PERSON_COUNT_CONTROL = "next.person.count.control";
	public static final String NEXT_PERSON_EXT = "next.person.ext";
	public static final String NEXT_PERSON_URL = "next.person.url";
	public static final String NEXT_PERSON_DEFAULT_SHOW_ALL_ORG = "next.person.default.show.all.org";
	public static final String DEFAULT_MULTIAPPROVE_URL = "default.multiapprove.url";
	public static final String DEFAULT_BATCH_MULTIAPPROVE_URL = "default.batch.multiapprove.url";
	public static final String WF_ADAPTER_USER_PROVIDER = "wf.adapter.user.provider";
	public static final String WF_IS_BOS_RUNTIME = "wf.is.bos.runtime";
	public static final String AUTO_APPROVE_OPINION = "auto.approve.opinion";
	public static final String AUTO_APPROVE_INTERVAL = "auto.approve.interval";
	public static final String AUTO_APPROVE_DELAY = "auto.approve.delay";
	public static final String MUST_SET_ALL_NEXT_PERSONS = "must.set.all.next.persons";
	public static final String MULTI_APPROVE_MUST_SELECT_DECISION = "multiapprove.must.select.decision";
	public static final String BILL_DIGEST_TEMPLATE_CACHEABLE = "bill.digest.template.cacheable";
	public static final String MULTIAPPROVE_HISTORY_GRID_URL = "multiapprove.history.grid.url";
	public static final String MULTIAPPROVE_HISTORY_GRID_UI = "multiapprove.history.grid.ui";
	public static final String MULTIAPPROVE_ACTIVITY_GRID_URL = "multiapprove.activity.grid.url";
	public static final String MULTIAPPROVE_ACTIVITY_GRID_UI = "multiapprove.activity.grid.ui";
	public static final String MULTIAPPROVE_PASS_COMMENT_GRID_URL = "multiapprove.pass.comment.grid.url";
	public static final String MULTIAPPROVE_PASS_COMMENT_GRID_UI = "multiapprove.pass.comment.grid.ui";
	public static final String MULTIAPPROVE_DYNAMIC_OP_GRID_UI = "multiapprove.dynamic.op.grid.ui";
	public static final String MULTIAPPROVE_COUNTER_SIGN_GRID_URL = "multiapprove.counter.sign.grid.url";
	public static final String MULTIAPPROVE_COUNTER_SIGN_GRID_UI = "multiapprove.counter.sign.grid.ui";
	public static final String MESSAGE_CENTER_HIDE_ROLLBACK = "cp.mc.hideBtnRollBack";
	public static final String MULTIAPPOVE_LIST_USE_QUERY_COLUMN_WIDTH = "multiapprove.list.use.query.column.width";
	public static final String WF_BILLUICONFIG_DEP_ENABLE = "wf.billuiconfig.dep.enable";
	Properties properties;

	public boolean isMultiapproveListUseQueryColumnWidth(String billId) {
		return getBoolean("multiapprove.list.use.query.column.width", billId,
				"false");
	}

	public String getMultiApproveHistoryGridURL(String billId) {
		return getString("multiapprove.history.grid.url", billId,
				"/easweb/workflow/approveHistoryStd.jsf");
	}

	public String getMultiApproveActivityGridURL(String billId) {
		return getString("multiapprove.activity.grid.url", billId, null);
	}

	public String getMultiApproveCounterSignGridURL(String billId) {
		return getString("multiapprove.counter.sign.grid.url", billId, null);
	}

	public String getMultiApprovePassCommentGridURL(String billId) {
		return getString("multiapprove.pass.comment.grid.url", billId, null);
	}

	public IApproveHistoryTableUI getMultiApproveHistoryGridUI(String billId) {
		String className = getMultiApproveHistoryGridUIClassName(billId);
		Class cls = getClass(className);
		Object obj = newInstance(cls);
		return ((IApproveHistoryTableUI) obj);
	}

	public String getMultiApproveHistoryGridUIClassName(String billId) {
		String className = getString("multiapprove.history.grid.ui", billId,
				"com.kingdee.eas.base.multiapprove.client.ApproveHistoryListStdUI");

		return className;
	}

	public IApproveActivityTableUI getMultiApproveActivityGridUI(String billId) {
		String className = getString("multiapprove.activity.grid.ui", billId,
				null);

		Class cls = getClass(className);
		Object obj = newInstance(cls);
		return ((IApproveActivityTableUI) obj);
	}

	public IApproveDynamicOpTableUI getApproveDynamicOpTableUI(String billId) {
		String className = getString("multiapprove.history.grid.ui", billId,
				"com.kingdee.eas.base.multiapprove.client.DynamicOpListUI");

		Class cls = getClass(className);
		Object obj = newInstance(cls);
		return ((IApproveDynamicOpTableUI) obj);
	}

	public IApproveCounterSignTableUI getMultiApproveCounterSignGridUI(
			String billId) {
		String className = getString("multiapprove.counter.sign.grid.ui",
				billId,
				"com.kingdee.eas.base.multiapprove.client.CounterSignListUI");

		Class cls = getClass(className);
		Object obj = newInstance(cls);
		return ((IApproveCounterSignTableUI) obj);
	}

	public IApprovePassCommentTableUI getMultiApprovePassCommentGridUI(
			String billId) {
		String className = getString("multiapprove.pass.comment.grid.ui",
				billId,
				"com.kingdee.eas.base.multiapprove.client.ApprovePassCommentTableStdUI");

		Class cls = getClass(className);
		Object obj = newInstance(cls);
		return ((IApprovePassCommentTableUI) obj);
	}

	public boolean getBoolean(String key, String billId, String defaultValue) {
		String s = getString(key, billId, defaultValue).toLowerCase();
		return "true".equals(s);
	}

	public int getInt(String key, String billId, String defaultValue) {
		String s = getString(key, billId, defaultValue).toLowerCase();
		return Integer.parseInt(s);
	}

	public String getString(String key, String billId, String defaultValue) {
		String s = getConfig(key, billId);
		if ((s == null) || (s.length() == 0))
			return defaultValue;
		return s;
	}

	public boolean isBosRuntime() {
		return "true".equalsIgnoreCase(getString("wf.is.bos.runtime", null,
				"true"));
	}

	public IMultiApproveClientExt getMultiApproveClientExt(String billId) {
		String className = getConfig("multiapprove.client.ext", billId);
		Class c = getClass(className);
		Object o = newInstance(c);
		if (o == null)
			return null;
		if (o instanceof IMultiApproveClientExt) {
			return ((IMultiApproveClientExt) o);
		}
		String msg = "class {" + className + "} didn't implement interface {"
				+ IMultiApproveClientExt.class.getName() + "}.";

		throw new RuntimeException(msg);
	}

	public IMultiApproveServerExt getMultiApproveServerExt(String billId) {
		String className = getConfig("multiapprove.server.ext", billId);
		Class c = getClass(className);
		Object o = newInstance(c);
		if (o == null)
			return null;
		if (o instanceof IMultiApproveServerExt) {
			return ((IMultiApproveServerExt) o);
		}
		String msg = "class {" + className + "} didn't implement interface {"
				+ IMultiApproveServerExt.class.getName() + "}.";

		throw new RuntimeException(msg);
	}

	public Class getExtClass(String key, String billId) {
		String className = getConfig("multiapprove.server.ext", billId);
		return getClass(className);
	}

	public Object getExtClassInstance(String key, String billId) {
		Class c = getExtClass(key, billId);
		return newInstance(c);
	}

	public String getMultiApproveWebScriptBlock(String billId) {
		String src = getConfig("multiapprove.web.scriptFile", billId);
		if ((src == null) || (src.length() == 0)) {
			return "";
		}

		StringBuffer block = new StringBuffer();
		block.append("\r\n<script src='").append(src).append("'></script>");

		String html = getConfig("multiapprove.web.includeHTML", billId);
		if (html != null) {
			block.append("\r\n").append(html);
		}

		String className = getConfig("multiapprove.web.scriptClass", billId);
		Class c = getClass(className);
		if (c != null) {
			try {
				String rpcStub = Ajax.register(c, "_");
				block.append("\r\n<script src='invoke.jsp?").append(rpcStub)
						.append("'></script>");
			} catch (BOSException e) {
				throw new RuntimeException("register web rpc stub for {"
						+ className + "} failed!", e);
			}
		}

		return block.toString();
	}

	public static Object newInstance(Class c) {
		if (c == null)
			return null;
		try {
			return c.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("initiate object instance of {" + c
					+ "} failed!", e);
		}
	}

	public static Class getClass(String className) {
		if ((className == null) || (className.length() == 0))
			return null;
		try {
			Class cls = Class.forName(className);
			return cls;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Can't find class {" + className + "}.",
					e);
		}
	}

	public String getConfig(String key, String billId) {
		String bosType = getBOSType(billId);
		String value = getValue(bosType + "." + key);
		if (value == null) {
			value = getValue(key);
		}
		return value;
	}

	public static String getBOSType(String billId) {
		if ((billId == null) || (billId.length() == 0))
			return "";
		if (billId.length() < 10) {
			return billId;
		}
		BOSUuid id = BOSUuid.read(billId);
		return id.getType().toString();
	}

	public String getValue(String key) {
		String v = this.properties.getProperty(key);
		if ((v != null) && (v.length() == 0))
			v = null;
		return v;
	}

	public String getWfMonitorImageUIFacadeClass() {
		return getValue("wf.monitor.image.ui.facade");
	}

	private String getUserAdapterClassName() {
		return getString("wf.adapter.user.provider", null, WfUserAdapter.class
				.getName());
	}

	public IWfUserAdapter getUserAdapter() {
		String className = getUserAdapterClassName();
		return ((IWfUserAdapter) RpcProxy.wrapNoSupport(IWfUserAdapter.class,
				className));
	}

	public IWfUserAdapter getUserAdapter(Context ctx) {
		String className = getUserAdapterClassName();
		Class cls = getClass(className);
		try {
			return ((IWfUserAdapter) ObjectFactory.newInstance(cls,
					new Class[] { Context.class }, new Object[] { ctx }));
		} catch (BOSException e) {
			throw new RuntimeException("get wf user adapter failed!", e);
		}
	}

	public IWfMonitorExt getWfMonitorExt(Context ctx) {
		String className = getConfig("wf.monitor.ext.class", null);
		if ((className == null) || (className.length() == 0))
			return null;
		try {
			Class cls = ObjectFactory.getClass(className);
			Object o = ObjectFactory.newInstance(cls,
					new Class[] { Context.class }, new Object[] { ctx });

			return ((IWfMonitorExt) o);
		} catch (Throwable t) {
			log.error("get_wf_monitor_ext failed!", t);
		}
		return null;
	}

	public IWfNextPersonExt getWfNextPersonExt(Context ctx, String billId) {
		String className = getConfig("next.person.ext", billId);
		if ((className == null) || (className.length() == 0))
			return null;
		try {
			Class cls = ObjectFactory.getClass(className);
			Object o = ObjectFactory.newInstance(cls,
					new Class[] { Context.class }, new Object[] { ctx });

			return ((IWfNextPersonExt) o);
		} catch (Throwable t) {
			log.error("get_wf_monitor_ext failed!", t);
		}
		return null;
	}

	public String getWfNextPersonUrl(String billId) {
		return getString("next.person.url", billId, "designatePerformer2.jsf");
	}

	public boolean isBillDigestTemplateCacheable(String billId) {
		return getBoolean("bill.digest.template.cacheable", billId, "true");
	}

	Config(Properties properties) {
		this.properties = properties;
	}

	public String toString() {
		return this.properties.toString();
	}

	public boolean isDEPEnableInUIConfig() {
		return "true".equalsIgnoreCase(getString("wf.billuiconfig.dep.enable",
				null, "true"));
	}
}
