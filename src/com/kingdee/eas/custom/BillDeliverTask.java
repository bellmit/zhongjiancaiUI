package com.kingdee.eas.custom;

import com.kingdee.eas.util.app.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.*;

import java.sql.Timestamp;
import java.util.*;
import java.util.Locale;

import com.kingdee.bos.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.service.ServiceStateManager;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.bos.webframework.exception.WafBizException;
import com.kingdee.bos.webframework.exception.WafException;
import com.kingdee.bos.workflow.metas.IInvokeMethodDelegate;
import com.kingdee.bos.workflow.metas.IOption;
import com.kingdee.bos.workflow.metas.InvokeMethodDelegateFactory;
import com.kingdee.bos.workflow.metas.OptionFactory;
import com.kingdee.bos.workflow.metas.OptionInfo;
import com.kingdee.bos.workflow.metas.OptionType;
import com.kingdee.bos.workflow.monitor.DynamicOperationInfo;
import com.kingdee.bos.workflow.monitor.DynamicOperationServiceFactory;
import com.kingdee.bos.workflow.monitor.IDynamicOperationService;
import com.kingdee.bos.workflow.monitor.IDynamicWfServiceFacade;
import com.kingdee.bos.workflow.monitor.IWfUtil;
import com.kingdee.bos.workflow.monitor.WfFacadeUtilFactory;
import com.kingdee.bos.workflow.monitor.WfUtil;
import com.kingdee.bos.workflow.service.AssignmentControlService;
import com.kingdee.bos.workflow.service.IAssignmentControlService;
import com.kingdee.bos.workflow.service.ormrpc.*;
import com.kingdee.bos.workflow.transaction.WfTxContext;
import com.kingdee.bos.workflow.transaction.WfTxHelper;
import com.kingdee.bos.workflow.transaction.WfTxInvocationDesc;
import com.kingdee.bos.workflow.transaction.WfTxInvokeHandler;
import com.kingdee.bos.workflow.transaction.WfTxInvoker;
import com.kingdee.bos.workflow.util.MethodDesc;
import com.kingdee.bos.workflow.web.CommonController;
import com.kingdee.bos.workflow.biz.trans.*;
import com.kingdee.bos.workflow.engine.core.ThreadCache;
import com.kingdee.bos.workflow.*;
import com.kingdee.eas.base.permission.*;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.util.StringUtils;

public class BillDeliverTask
{
    public static String deliverTask(Context ctx, Map map) {
        try { 
            String returnStr = "";
            IEnactmentService server = null;
            if (ctx != null) {
                server = EnactmentServiceFactory.createEnactService(ctx);
            }
            else {
                server = EnactmentServiceFactory.createRemoteEnactService();
            }
            String assignId = map.get("assignId").toString();
            String userID = map.get("userID").toString();
            String strData = map.get("strData").toString();
            
            //String opinion = map.get("opinion").toString();
            
            EASWfServiceData data = server.getEASWfServiceData(new String[] { assignId }, false);
            AssignmentInfo assignmentInfo = data.getAssignmentInfo();
            HashMap localeMap = new HashMap();
            UserInfo UserInfo = null;
            if (ctx != null) { 
                UserInfo = ContextUtil.getCurrentUserInfo(ctx);
            }
            else {
                UserInfo = SysContext.getSysContext().getCurrentUserInfo();
            }
            
            String userZhuanJiaoID = "";
            com.kingdee.bos.workflow.participant.Person[] persons;
			try {
				persons = server.getPersonByPersonID(userID);
				if ((persons.length > 0) && (persons[0] != null)) {
					userZhuanJiaoID = persons[0].getUserId();
	    		}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		 
            
            Locale local = new Locale("l1");
            //String str = String.valueOf(strData) + "\nvalue_new" + UserInfo.getName(local);
            String str = String.valueOf(strData) + "\nTransmitted to people" + UserInfo.getName(local)+ "\n" ;
            
            localeMap.put(local, str);
            local = new Locale("l2");
            //str = String.valueOf(strData) + "\n\u05ea\ufffd\ufffd\ufffd\ufffd:" + UserInfo.getName(local);
            str = String.valueOf(strData) + "\n\u8F6C\u4EA4\u4EBA\uFF1A" + UserInfo.getName(local)+ "\n" ;
            localeMap.put(local, str);
            local = new Locale("l3");
            //str = String.valueOf(strData) + "\n\ufffdD\ufffd\ufffd\ufffd\ufffd:" + UserInfo.getName(local);
            str = String.valueOf(strData) + "\n\u8F6C\u4EA4\u4EBA\uFF1A" + UserInfo.getName(local)+ "\n" ;
            localeMap.put(local, str);
            //AssignmentInfo forwarAssignInfo =  server.forwardAssignment(assignmentInfo.getAssignmentId().toString(), userZhuanJiaoID.toString(), (Map)localeMap);
            
            AssignmentInfo forwarAssignInfo =  forwardAssignment(ctx,assignmentInfo.getAssignmentId().toString(), userID, (Map)localeMap);
            
            
            
             
			/*WfUtil wfUtil = new WfUtil(ctx);
			try {
				wfUtil.forward(assignId, userID, localeMap, strData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
			
			
            IEnactmentService svc = EnactmentServiceFactory.createEnactService(ctx);
            AssignmentInfo assign = svc.getAssignmentById(assignId);
            String procInstId = assign.getProcInstId();
    		String actInstId = assign.getActInstId();
    		String actName = assign.getActDefName(ctx.getLocale());
    		String billId = assign.getBizObjectIds();

    		saveDynamicOperationInfo(ctx,"forward", procInstId, actInstId, actName,billId, null, null, strData);
    		
    		String userName = svc.getUserNumberByID(userID);
    		if (StringUtils.isEmpty(userName)) {
    			try {
					userName = svc.getPersonByPersonID(userID)[0].getUserName(ctx.getLocale());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		String descrip = "转交任务给";
    		if (ctx.getLocale().getLanguage().equalsIgnoreCase("l1"))
    			descrip = " foward task to ";
    		else if (ctx.getLocale().getLanguage().equalsIgnoreCase("l3")) {
    			descrip = "轉交任務給";
    		}
    		String result = new StringBuilder().append(descrip).append(":").append(userName).toString();

    		String forwarAssignid = "";
    		String forwarActivityId = "";
    		if (forwarAssignInfo != null) {
    			forwarAssignid = forwarAssignInfo.getAssignmentId();
    			forwarActivityId = forwarAssignInfo.getActInstId();
    		}
    		saveOperation( ctx,strData, billId, OptionType.deliverAssignment, 
    				ctx.getCaller().toString(), userID, assignId, 
    				forwarAssignid,actInstId, forwarActivityId, result, procInstId);
        }
        catch (WfException e) {
            e.printStackTrace();
        }
        catch (BOSException e2) {
            e2.printStackTrace();
        }
        return "";
    }
    
    
    public static AssignmentInfo forwardAssignment(Context ctx ,String assignmentId,
			String destUserId, Map forwardMsg) throws WfException {
		Class classCaller = IAssignmentControlService.class;
		Object obj = AssignmentControlService.getService(ctx );
		String methodName = "forwardAssignment";
		Class[] parameterTypes = { String.class, String.class, Map.class };

		Object[] args = { assignmentId, destUserId, forwardMsg };
		MethodDesc methodDesc = new MethodDesc(classCaller, methodName,
				parameterTypes);

		WfTxInvocationDesc invocationDesc = new WfTxInvocationDesc(methodDesc,
				obj, args);

		invocationDesc.addMutexObject(assignmentId);
		boolean hasCache = ThreadCache.exist();
		if (!(hasCache))
			ThreadCache.create();
		try {
			//Object result = WfTxHelper.invokeRequired(ctx, invocationDesc);
			Object result =  invokeRequired(ctx, invocationDesc);
			AssignmentInfo localAssignmentInfo = (AssignmentInfo) result;

			return localAssignmentInfo;
		} finally {
			if (!(hasCache))
				ThreadCache.destroy();
		}
	}
    
    public static Object invokeRequired(Context ctx,
			WfTxInvocationDesc invocationDesc) throws WfException {
		WfTxContext wfContext = WfTxContext.beginTx(ctx);
		try {
			ServiceStateManager.getInstance().disableNextCallServices();
			WfTxInvoker invoker = new WfTxInvoker(invocationDesc);
			invoker.setTxInvokeHandler(new WfTxInvokeHandler(invocationDesc,
					wfContext));
			IInvokeMethodDelegate delegate = getTxDelegate(ctx);
			Object retValue = delegate.invokeRequired(invoker.getClass()
					.getName(), invoker, "invoke", null, null);
			wfContext.endTx();
			return retValue;
		} catch (Throwable e) {
			wfContext.rollbackTx();

			if (e instanceof WfException)
				throw ((WfException) e);
			if (e instanceof Error)
				throw ((Error) e);
			if (e instanceof RuntimeException) {
				throw ((RuntimeException) e);
			}
			throw new WfException(e);
		}
	}
    private static IInvokeMethodDelegate getTxDelegate(Context ctx)
	throws BOSException {
    	return InvokeMethodDelegateFactory.getLocalInstance(ctx);
	}
    
    private static void saveOperation(Context ctx,String option, String billId, OptionType type,
			String personId, String nextPersonid, String assignId,
			String nextAssignId, String actinstId, String nextActinstId,
			String result, String procinstId) throws BOSException {
		try {
			IOption Wfoption = OptionFactory.getLocalInstance( ctx);
			OptionInfo opinfo = new OptionInfo();
			opinfo.setOption(option);
			if (!(StringUtils.isEmpty(billId))) {
				opinfo.setBillID(BOSUuid.read(billId));
			}
			opinfo.setType(type);
			if (!(StringUtils.isEmpty(personId))) {
				opinfo.setPersonID(BOSUuid.read(personId));
			}
			if (!(StringUtils.isEmpty(nextPersonid))) {
				opinfo.setNextHandlerPersonID(nextPersonid);
			}
			if (!(StringUtils.isEmpty(assignId))) {
				opinfo.setAssignmentID(BOSUuid.read(assignId));
			}
			if (!(StringUtils.isEmpty(nextAssignId))) {
				opinfo.setNextAssignid(BOSUuid.read(nextAssignId));
			}
			if (!(StringUtils.isEmpty(actinstId))) {
				opinfo.setCurrentAcinstID(BOSUuid.read(actinstId));
			}
			if (!(StringUtils.isEmpty(nextActinstId))) {
				opinfo.setActinstID(BOSUuid.read(nextActinstId));
			}
			opinfo.setResult(result);
			if (!(StringUtils.isEmpty(procinstId))) {
				opinfo.setProcinstID(BOSUuid.read(procinstId));
			}

			Wfoption.addnew(opinfo);
		} catch (Exception e) {
			 
			throw new BOSException(e);
		}
	}
    
     
    private static void saveDynamicOperationInfo(Context ctx,String operType, String procInstId,
			String actInstId, String actName, String billId,
			String targetActDefId, String targetActDefName, String opinion)
			throws BOSException {
		IDynamicOperationService serivce = DynamicOperationServiceFactory.getLocalInstance(ctx);

		DynamicOperationInfo info = new DynamicOperationInfo();
		info.setType(operType);
		info.setBillId(billId);
		info.setProcInstId(procInstId);
		info.setActInstId(actInstId);
		info.setActName(actName);
		info.setTargetActId(targetActDefId);
		info.setTargetActName(targetActDefName);
		info.setComment(opinion, new Locale("l1"));
		info.setComment(opinion, new Locale("l2"));
		info.setComment(opinion, new Locale("l3"));
		info.setCreatorId( ctx.getCaller().toString());
		String userName = ContextUtil.getCurrentUserInfo(ctx).getName(ctx.getLocale()); 
		info.setCreatorName(userName);
		info.setCreateTime(new Timestamp(System.currentTimeMillis()));
		serivce.addNewDynamicOperationInfo(info);
	}
    
    public static String deliverTaskNew(Context ctx, Map map)  {
    	/*String personId = request.getParameter("personId");
		//String personInfo = request.getParameter("personInfo");
		String assignmentID = request.getParameter("assignmentID");
		String opinion = request.getParameter("opinion"); */
		
		String assignmentID = map.get("assignId").toString();
        String personId = map.get("userID").toString();
        String opinion = map.get("strData").toString();
        
        
		Locale l = ctx.getLocale(); 
		System.out.println(personId); 
		System.out.println(assignmentID);
		 

		if (StringUtils.isEmpty(personId)) {
			//request.setAttribute("reason", "pleaseSelectPerson");
			return null;
		}
		UserInfo currentUserInfo = ContextUtil.getCurrentUserInfo(ctx); 
		
		SelectorItemCollection sic = new SelectorItemCollection();
		sic.add(new SelectorItemInfo("person.id"));
		sic.add(new SelectorItemInfo("person.name"));
		PersonInfo currentPerson;
		try {
			currentPerson = UserFactory.getLocalInstance(ctx).getUserInfo( ctx.getCaller(), sic).getPerson();
			
			IEnactmentService svc = EnactmentServiceFactory.createEnactService(ctx);
			if ((currentPerson != null) && (personId.equals(currentPerson.getId().toString()))) {
				throw new  Exception("转交人员不能是自己");
			}
			
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}  
		

		/*Person[] person1 = svc.getPersonByPersonID(personId);
		if ((person1 == null) || (person1.length == 0)) {
			throw new WafBizException( " ");
		}*/
		/*Person[] persons = svc.getPersonByPersonID(personId);
		String userID = "";
		if ((persons.length > 0) && (persons[0] != null)) {
			userID = persons[0].getUserId();
		}*/
		String userID =  currentUserInfo.getId().toString();
		Locale[] locales = com.kingdee.bos.workflow.util.ApplicationUtil.getContextLocales(ctx);
		Map opinionMap = new HashMap();
		for (int i = 0; i < locales.length; ++i) {
			Locale local = locales[i];
			String data = opinion;
			data = data + "\n" + ResourceBundle.getBundle(CommonController.class.getName(), local).getString("deliver.user") + currentUserInfo.getName(local);
			data = data + "\n"  ;
			opinionMap.put(local, data);
		}
		try {
			//IWfUtil util = WfFacadeUtilFactory.createRemoteWfUtil();
			
			WfUtil util = new WfUtil();
			IDynamicWfServiceFacade utilfa = WfFacadeUtilFactory.getDynamicWfService(ctx);
			for (String assignId : assignmentID.split(";")) {
				util.forward(assignId, userID, opinionMap, opinion); 
			}
			
			return null;
		} catch (Exception e) {
			try {
				throw new  Exception( e.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return userID;
    } 
}
