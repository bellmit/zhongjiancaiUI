package com.kingdee.eas.custom;

import java.lang.reflect.Type;
import java.util.*;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.kingdee.bos.json.JsonContext;
import com.kingdee.bos.json.Kson;
import com.kingdee.bos.metadata.MetaDataLoaderFactory;
import com.kingdee.bos.metadata.entity.EntityObjectInfo;
import com.kingdee.bos.ui.face.*;
import com.kingdee.bos.webframework.context.WafContext;
import com.kingdee.bos.webframework.exception.WafException;
import com.kingdee.bos.webframework.json.JSONUtils;
import com.kingdee.bos.workflow.exception.AlreadyInProcessQueueException;
import com.kingdee.bos.workflow.monitor.ApproveUtil;
import com.kingdee.bos.workflow.monitor.util.SignatureUtil;
import com.kingdee.bos.workflow.service.*;
import com.kingdee.bos.dao.ormapping.*;
import com.kingdee.bos.dao.*;
import com.kingdee.bos.framework.DynamicObjectFactory;
import com.kingdee.bos.util.*;
import com.kingdee.eas.framework.*;
import com.kingdee.bos.*;
import com.kingdee.eas.common.*;
import com.kingdee.eas.base.multiapprove.*;
import com.kingdee.bos.workflow.service.ormrpc.*;
import com.kingdee.bos.workflow.*;
import com.kingdee.eas.base.permission.*;
import com.kingdee.eas.base.security.EASSignatureFactory;
import com.kingdee.eas.base.security.IEASSignature;

public class BillAudit
{
    public static void audit(final Context ctx, final Map map) {
        if (UIRuleUtil.isNotNull((Object)map)) {
            final int isPass = UIRuleUtil.getInt(map.get("isPass"));
            final String assignId = map.get("assignId").toString();
            final String opinion = map.get("opinion").toString();
            final int handlerOpinion = UIRuleUtil.getInt(map.get("handlerOpinion"));
            final String handlerOpinionStr = map.get("handlerOpinionStr").toString();
            System.out.println("--------isPass:" + isPass + "  assignId:" + assignId + "   opinion:" + opinion + "    handlerOpinion:" + handlerOpinion + "   handlerOpinionStr:" + handlerOpinionStr);
            IMultiApprove multiapprove = null;
            IEnactmentService service = null;
            try {
                if (ctx != null) {
                    multiapprove = MultiApproveFactory.getLocalInstance(ctx);
                    service = EnactmentServiceFactory.createEnactService(ctx);
                    EngineUtil.getEngine(ctx).getCacheManager().removeAssignment(assignId);
                }
                else {
                    multiapprove = MultiApproveFactory.getRemoteInstance();
                    service = EnactmentServiceFactory.createRemoteEnactService();
                    EngineUtil.getEngine(ctx).getCacheManager().removeAssignment(assignId);
                }
                final AssignmentInfo assignmentInfo = service.getAssignmentById(assignId);
                ctx.setCaller((IObjectPK)new ObjectUuidPK(assignmentInfo.getAssignInfo().getPersonUserID()));
                final MultiApproveInfo info = new MultiApproveInfo();
                info.setAssignment(assignId);
                info.setBillId(BOSUuid.read(assignmentInfo.getBizObjectIds()));
                info.setExtendedProperty("businuessObjectId", assignmentInfo.getBizObjectIds());
                info.setExtendedProperty("assignmentID", assignId);
                info.setExtendedProperty("isAddNew", "isAddNew");
                info.setIsMailNotifyNext(false);
                info.setIsMobelNotifyNext(false);
                info.setOpinion(opinion);
                if (isPass == 1) {
                    info.setIsPass(ApproveResult.PASS);
                }
                else {
                    info.setIsPass(ApproveResult.NOT_PASS);
                }
                info.setHandlerOpinion(handlerOpinion);
                info.setHandlerContent(handlerOpinionStr);
                info.setStatus(MultiApproveStatusEnum.SUBMIT);
                multiapprove.submit((CoreBaseInfo)info);
                System.out.println("--------multiapprove.submit((CoreBaseInfo)info):"+info.getId());
            }
            catch (BOSException e1) {
                e1.printStackTrace();
            }
            catch (EASBizException e2) {
                e2.printStackTrace();
            }
            try {
                final UserCollection userCollection = UserFactory.getLocalInstance(ctx).getUserCollection("where number = '" + ctx.getUserName() + "'");
                if (!userCollection.isEmpty()) {
                    ctx.setCaller((IObjectPK)new ObjectUuidPK(userCollection.get(0).getId()));
                }
            }
            catch (BOSException e3) {
                e3.printStackTrace();
            }
        }
    	/*
    	if (UIRuleUtil.isNotNull((Object)map)) {
            final int isPass = UIRuleUtil.getInt(map.get("isPass"));
            final String assignId = map.get("assignId").toString();
            final String opinion = map.get("opinion").toString();
            final int handlerOpinion = UIRuleUtil.getInt(map.get("handlerOpinion"));
            final String handlerOpinionStr = map.get("handlerOpinionStr").toString();
            System.out.println("isPass:" + isPass + "  assignId:" + assignId + "   opinion:" + opinion + "    handlerOpinion:" + handlerOpinion + "   handlerOpinionStr:" + handlerOpinionStr);
             
            ApproveUtil u = new ApproveUtil(ctx);
            //Sring str = request.getParameter("approve");
    		//String str = "{\"assignId\":\"2fb3c056-bb6f-44cb-b2ac-32576a0fb97cWFWKITEM\",\"id\":\"\",\"opinion\":\"112333\",\"handlerOpinion\":\"0\",\"handlerContent\":\"同意\",\"approveResult\":\"true\",\"transitionId\":\"\",\"isSendSMS\":false,\"isSendMail\":false,\"operation\":\"approve\",\"isReadOnly\":\"true\",\"nextActs\":[]}";
    		//str = "{\"assignId\":\"0b6ddb75-0cd1-4412-944e-51eeba138f38WFWKITEM\",\"id\":\"bNsAAAAaPtfA2tAN\",\"opinion\":\"123666\",\"handlerOpinion\":\"0\",\"handlerContent\":\"同意\",\"approveResult\":\"true\",\"transitionId\":\"\",\"isSendSMS\":false,\"isSendMail\":false,\"operation\":\"approve\",\"model\":\"{\"hrOrgUnit\":{\"id\":\"00000000-0000-0000-0000-000000000000CCE7AED4\"},\"entrys\":[{\"person\":{\"id\":\"bNsAAAAUbtiA733t\",\"number\":\"1008609\"},\"oldPosition\":{\"id\":\"ZbD2nbaQSwGItzjor6Ru3HSuYS4=\",\"adminOrgUnit\":{\"department\":{\"id\":\"bNsAAAAA/2XM567U\"}}},\"position\":{\"id\":\"cBFid8jXScablpS1LyNW6nSuYS4=\",\"adminOrgUnit\":{\"department\":{\"id\":\"bNsAAAAANebM567U\"}}},\"oldAdminOrg\":{\"id\":\"bNsAAAAA/2XM567U\"},\"adminOrg\":{\"id\":\"bNsAAAAANebM567U\"},\"oldCompany\":{\"id\":\"bNsAAAAANd3M567U\"},\"company\":{\"id\":\"bNsAAAAANd3M567U\"},\"oldJob\":{\"id\":\"\"},\"job\":{\"id\":\"\"},\"oldJobLevel\":{\"id\":\"bNsAAAAA38VoMP7d\"},\"jobLevel\":{\"id\":\"\"},\"oldJobGrade\":{\"id\":\"bNsAAAAA349n8CMw\"},\"jobGrade\":{\"id\":\"\"},\"xclb\":{\"id\":\"bNsAAAAA62/x3INc\"},\"workpro\":{\"id\":\"bNsAAAAA6r+MSLJh\"},\"gzcity\":\"北京\",\"xmmc\":{\"id\":\"\"},\"bizDate\":\"2021-07-31\",\"description\":\"\",\"oldEmpType\":{\"id\":\"00000000-0000-0000-0000-000000000002A29E85B3\"},\"empType\":{\"id\":\"00000000-0000-0000-0000-000000000002A29E85B3\"},\"hrBizDefine\":{\"id\":\"DawAAAApVj/maL7Z\"},\"affairActionReason\":{\"id\":\"7ta1EwaZTZmts8L4z/J0LJYRae4=\"},\"variationReason\":{\"id\":\"9rV2XVMHTr+fk5wIZZffLeas36w=\"},\"id\":\"bNsAAAAaPtjBlkhl\"}],\"billState\":\"1\",\"personId\":\"\",\"number\":\"DDD-20210715-0001\",\"applyDate\":\"2021-07-15\",\"applier\":{\"id\":\"nZo4FjtYRE+SpIzBCs97sYDvfe0=\"},\"id\":\"bNsAAAAaPtfA2tAN\",\"_entityName\":\"com.kingdee.eas.hr.affair.app.FluctuationBizBill\"}\",\"isReadOnly\":\"false\",\"nextActs\":[]}";
    		HashMap approve;
    		try {
    			approve = (HashMap) com.kingdee.bos.json.JSONUtils.convertJsonToObject(ctx,str, HashMap.class);
    		} catch (BOSException e) {
    			throw new WafException(e);
    		}
    		//Map approve = com.kingdee.bos.webframework.json.JSONUtils.convertJsonToObject(str);
    		 approve = new HashMap<Object, Object>();
    		approve.put("assignId", map.get("assignId").toString());
    		approve.put("id", "bNsAAAAaPtfA2tAN");
    		approve.put("opinion", "同i");
    		approve.put("handlerOpinion", "0");
    		approve.put("handlerContent", "同意");
    		approve.put("approveResult", "true");  
    		approve.put("transitionId", "");
    		approve.put("isSendSMS", "false");
    		approve.put("isSendMail", "false");
    		approve.put("operation", "approve");
    		//isSignatureApprove
    		//transitionId
    		approve.put("isReadOnly", "true");
    		approve.put("nextActs", new ArrayList());
    		List nextActs = (List) approve.get("nextActs");
    		String s = "";
			try {
				//s = u.submit(approve, nextActs);
				
				//s = u.submit(approve, nextActs);
				
				IEnactmentService svc = EnactmentServiceFactory.createEnactService(ctx);
				String assignId = (String) approve.get("assignId");
				AssignmentInfo assign = svc.getAssignmentById(assignId);
				
				ctx.setCaller((IObjectPK)new ObjectUuidPK(assign.getAssignInfo().getPersonUserID()));
				
				String operation = (String) approve.get("operation");
				if ("approve".equals(operation)) {
					String isReadOnly = (String) approve.get("isReadOnly");
					String billIsChanged = ("true".equals(isReadOnly)) ? "false" : "true";
					svc.setProcessContext(assign.getProcInstId(), new StringBuilder() .append("$approveBillEdit").append(assignId).toString(), billIsChanged);
		
					saveAndSignatureApproveBill(  ctx,approve, assignId, assign.getBizObjectIds());
					String id =  approve(ctx,approve, nextActs, svc, assignId, assign);
					System.out.println("--------id:"+id);
					//JSONUtils.SUCCESS(id);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        }*/
    }
    
    private static Logger logger = Logger.getLogger(BillAudit.class);
    
	public static String PROPERTY_NAME = "ca_CheckAndSignWhenApprove";

	public static String NONE = "CA_NONE";
	public static String SIGN_ONLY = "CA_SIGN_ONLY";
	public static String CHECK_ONLY = "CA_CHECK_ONLY";
	public static String BOTH = "CA_BOTH";

	private static Integer NONE_VALUE = new Integer(5);
	private static Integer SIGN_ONLY_VALUE = new Integer(1);
	private static Integer CHECK_ONLY_VALUE = new Integer(2);
	private static Integer BOTH_VALUE = new Integer(0);
	
    private static void saveAndSignatureApproveBill(Context ctx,Map approveMap,
			String tAssignmentId, String tBillId) throws Exception {
		String isSignatureApprove = (String) approveMap.get("isSignatureApprove");
		 
		if ("true".equals(isSignatureApprove)) {
			String tPlainText = (String) approveMap.get("plainText");
			String tSignature = (String) approveMap.get("signature");

			String tCAType = SignatureUtil.getCATypeByAssign(tAssignmentId);
			//String tCAType = "";
			try {
				IEnactmentService svc = EnactmentServiceFactory.createEnactService(ctx);
				String procInstId = svc.getAssignmentById(tAssignmentId).getProcInstId();
				String revalue = BOTH;
				Object value = svc.getProcessContext(procInstId, PROPERTY_NAME);
				if (SIGN_ONLY_VALUE.equals(value))
					value = SIGN_ONLY;
				if (CHECK_ONLY_VALUE.equals(value))
					value = CHECK_ONLY;
				if (BOTH_VALUE.equals(value))
					value = BOTH;
				if (NONE_VALUE.equals(value))
					value = NONE;
				
				tCAType = revalue;
			} catch (BOSException e) {
				logger.error("can not load workflow Property:" + PROPERTY_NAME);
			}

			IEASSignature iEASSignature = EASSignatureFactory.getLocalInstance(ctx);
			
			BOSObjectType bosType = BOSUuid.read(tBillId).getType(); 
			if ((tCAType.equals(SignatureUtil.CHECK_ONLY)) || (tCAType.equals(SignatureUtil.BOTH))) {
				List tBillIdList = new ArrayList();
				tBillIdList.add(tBillId);
				iEASSignature.checkSignature(bosType, tBillIdList);

				saveApprove(ctx,approveMap);

				String isReadOnly = (String) approveMap.get("isReadOnly");
				if (!("true".equals(isReadOnly))) {
					iEASSignature.checkSignature(bosType, tBillIdList);
				}

			}

			if ((tCAType.equals(SignatureUtil.SIGN_ONLY))
					|| (tCAType.equals(SignatureUtil.BOTH))) {
				if (tCAType.equals(SignatureUtil.SIGN_ONLY)) {
					saveApprove(ctx,approveMap);
				}

				if (tPlainText.indexOf("CANOTSIGNATURE") <= -1) {
					iEASSignature.checkSignature4Web(tPlainText, tSignature, bosType.toString());
					List tBillIdList = new ArrayList();
					tBillIdList.add(tBillId);
					List tSignaList = new ArrayList();
					tSignaList.add(tSignature);
					iEASSignature.checkSignature4Web(tBillIdList, tSignaList, bosType.toString());
					iEASSignature.saveSignature4Web(tSignature, tPlainText, tBillId, bosType.toString());
				}
			}
		} else {
			saveApprove(ctx,approveMap);
		}
	}
    
    private static boolean saveApprove(Context ctx,Map approveMap) throws Exception {
		String isReadOnly = (String) approveMap.get("isReadOnly");
		if (!("true".equals(isReadOnly)))
			try {
				String billid = "bNsAAAAaPtfA2tAN";
				final ObjectUuidPK pk = new ObjectUuidPK(billid);
				String jsonString = (String) approveMap.get("model");
				if (jsonString != null) {
					Map dataMap = com.kingdee.bos.json.JSONUtils.convertJsonToObject(ctx,jsonString);

					BOSObjectType bostype = (dataMap.get("bosType") != null) ? BOSObjectType
							.create((String) dataMap.get("bosType"))
							: BOSUuid.read((String) dataMap.get("id"))
									.getType();
					Class objectValueClass = getBizObjectValueClass(ctx,bostype);
					//IObjectValue bizModel = (IObjectValue) com.kingdee.bos.json.JSONUtils.convertJsonToObject(ctx, jsonString,objectValueClass);
					IObjectValue bizModel = DynamicObjectFactory.getLocalInstance(ctx).getValue(pk.getObjectType(), (IObjectPK)pk);
					
					if ((bizModel != null)
							&& (bizModel instanceof CoreBaseInfo))
						MultiApproveFactory.getLocalInstance(ctx).bizSubmit(
								(CoreBaseInfo) bizModel);
				}
			} catch (Exception e) {
				throw new BOSException(e.getMessage(), e);
			}
		else {
			return false;
		}

		return true;
	}
     
    private static Class getBizObjectValueClass(Context ctx, BOSObjectType bosType)throws BOSException {
		EntityObjectInfo entityObjectInfo = MetaDataLoaderFactory.getMetaDataLoader(ctx).getEntity(bosType);
		String clzObjectValueName = entityObjectInfo.getObjectValueClass();
		Class clzObjectValue = null;
		try {
			clzObjectValue = Class.forName(clzObjectValueName);
		} catch (ClassNotFoundException e) {
			throw new BOSException(new StringBuilder().append("can't find ")
					.append(clzObjectValueName).toString());
		}
		
		return clzObjectValue;
	}
    
    private static String approve(Context ctx, Map approve, List nextActs, IEnactmentService svc,
			String assignId, AssignmentInfo assign) throws Exception {
		saveNextActsPersons(nextActs, svc, assign);
		MultiApproveInfo info = generateApproveInfo(approve, assignId, assign);
		info.setStatus(MultiApproveStatusEnum.SUBMIT);

		info.setInputType(InputType.PROC_CENTER);
		try {
			MultiApproveFactory.getLocalInstance(ctx).submit(info);
			return info.getId().toString();
		} catch (AlreadyInProcessQueueException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
    
    private static void saveNextActsPersons(List nextActs, IEnactmentService svc,
			AssignmentInfo assign) throws WfException {
		if (nextActs != null)
			for (int i = 0; i < nextActs.size(); ++i) {
				Map act = (Map) nextActs.get(i);
				String key = (String) act.get("actKey");
				Map persons = (Map) act.get("persons");
				ArrayList personIds = new ArrayList();
				Iterator it = persons.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry person = (Map.Entry) it.next();
					if ((person.getValue() != null)
							&& (!("".equals(person.getValue())))) {
						personIds.add(person.getKey());
					}
				}
				svc.setProcessContext(assign.getProcInstId(), key, personIds);
			}
	}
    
    private static MultiApproveInfo generateApproveInfo(Map approve, String assignId,
			AssignmentInfo assign) {
		MultiApproveInfo info = new MultiApproveInfo();
		info.setAssignment(assignId);
		info.setBillId(BOSUuid.read(assign.getBizObjectIds()));
		info.setExtendedProperty("businuessObjectId", assign.getBizObjectIds());
		info.setExtendedProperty("assignmentID", assignId);
		info.setExtendedProperty("isAddNew", "isAddNew");

		boolean isSendSMS = "true".equals(approve.get("isSendSMS").toString());
		boolean isSendMail = "true".equals(approve.get("isSendMail").toString());
		info.setIsMailNotifyNext(isSendMail);
		info.setIsMobelNotifyNext(isSendSMS);
		String opinion = (String) approve.get("opinion");
		info.setOpinion(opinion, new Locale("L1"));
		info.setOpinion(opinion, new Locale("L2"));
		info.setOpinion(opinion, new Locale("L3"));
		String result = (String) approve.get("approveResult");
		info.setIsPass(ApproveResult.getEnum(result));
		String handlerOpinion = (String) approve.get("handlerOpinion");
		info.setHandlerOpinion(Integer.parseInt(handlerOpinion));
		String handlerContent = (String) approve.get("handlerContent");
		info.setHandlerContent(handlerContent);
		/*String esignatureid = (String) approve.get("esignatureid");
		if ((esignatureid != null) && (esignatureid.length() > 0)) {
			info.setEsignatureId(esignatureid);
		}*/
		String id = (String) approve.get("id");
		if ((id != null) && (id.length() > 0)) {
			info.setId(BOSUuid.read(id));
		}
		return info;
	}
}
