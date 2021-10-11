package com.kingdee.eas.custom;

import com.kingdee.eas.base.multiapprove.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.workflow.service.ormrpc.*;
import com.kingdee.eas.common.*;
import com.kingdee.bos.*;
import java.util.*;
import com.kingdee.eas.util.app.*;
import com.kingdee.bos.metadata.*;
import com.kingdee.bos.workflow.biz.trans.*;
import com.kingdee.util.*;
import com.kingdee.bos.workflow.biz.*;
import com.kingdee.bos.workflow.*;

import net.sf.json.*;

public class ManualDecisionUtil
{
    private static ManualDecisionInfo setEntityManualDecision(final Context ctx, final String sourceID, final String opinion, final String opinionStr) throws EASBizException, BOSException {
        final IEnactmentService server = EnactmentServiceFactory.createEnactService(ctx);
        final Map map1 = server.getAssignmentArgument(sourceID, true);
        final String items = map1.get("MANUALDECISION_ITEMS").toString();
        final ManualDecisionInfo entityManualDecision = new ManualDecisionInfo();
        entityManualDecision.setName(map1.get("MANUALDECISION_NAME").toString());
        entityManualDecision.setSelectItemCount(1);
        final String[] itemStrs = items.split(";");
        for (int i = 0; i < itemStrs.length; ++i) {
            final String itemStr = itemStrs[i];
            if (itemStr.equals(opinion)) {
                entityManualDecision.setSelectedIndices(new StringBuilder(String.valueOf(i + 1)).toString());
                entityManualDecision.setSelectedItems(itemStr);
            }
        }
        entityManualDecision.setSelectItems(items);
        entityManualDecision.setDescription(opinionStr);
        final BOSUuid uuid = BOSUuid.create(entityManualDecision.getBOSType());
        entityManualDecision.setId(uuid);
        return entityManualDecision;
    }
    
    public static void submitWfAssignment(final Context ctx, final String sourceID, final String opinion, final String opinionStr) throws EASBizException, BOSException {
        final ManualDecisionInfo entityManualDecision = setEntityManualDecision(ctx, sourceID, opinion, opinionStr);
        final Map dataMap = new HashMap();
        dataMap.put(entityManualDecision.getId().toString(), entityManualDecision);
        final boolean result = false;
        final String userId = ContextUtil.getCurrentUserInfo(ctx).getId().toString();
        try {
            final IMetaDataPK matePK = (IMetaDataPK)new MetaDataPK("com.kingdee.eas.base.multiapprove.client", "ManualDecisionUI");
            final Map uiContext = new HashMap();
            final IEnactmentService server = EnactmentServiceFactory.createEnactService(ctx);
            server.getAssignmentArgument(sourceID, true);
            final EASWfServiceData data = server.getEASWfServiceData(new String[] { sourceID }, false);
            final AssignmentInfo assInfo = data.getAssignmentInfo();
            uiContext.put("WfAssignmentInfo", assInfo);
            submitAssignment(ctx, dataMap, userId, uiContext, matePK, null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void submitAssignment(final Context ctx, final Map dataObjects, final String userId, final Map uiContext, final IMetaDataPK uiObjPK, final String action) throws BOSException {
        final IEnactmentService engine = EnactmentServiceFactory.createEnactService(ctx);
        final LowTimer timer = new LowTimer();
        AssignmentInfo assignment = null;
        if (uiContext != null) {
            assignment = (AssignmentInfo) uiContext.get("WfAssignmentInfo");
        }
        String packageName = "";
        String functionName = "";
        String operation = "";
        final Map operations = null;
        packageName = assignment.getBizPackage();
        functionName = assignment.getBizFunction();
        operation = assignment.getBizOperation();
        final SubmitAssignResult result = engine.submitAssignment(dataObjects, userId, packageName, functionName, operation, assignment, (BizProcInfo)null);
    }
    
    public static JSONObject getManualDeciSion(final Context ctx, final String sourceID) throws BOSException {
        final JSONObject json = new JSONObject();
        final IEnactmentService server = EnactmentServiceFactory.createEnactService(ctx);
        final Map map1 = server.getAssignmentArgument(sourceID, true);
        json.put((Object)"MANUALDECISION_ITEMS", map1.get("MANUALDECISION_ITEMS"));
        return json;
    }
}
