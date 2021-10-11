package com.kingdee.eas.custom;

import com.kingdee.bos.ctrl.swing.*;
import com.kingdee.bos.ctrl.swing.tree.*;

import net.sf.json.*;
import com.kingdee.bos.workflow.monitor.*;
import com.kingdee.bos.workflow.service.ormrpc.*;
import com.kingdee.bos.workflow.biz.trans.*;
import javax.swing.tree.*;
import com.kingdee.bos.workflow.participant.*;
import com.kingdee.bos.*;
import java.util.*;

import com.kingdee.bos.workflow.define.*;

public class DesignatePerformerUtil
{
    public static JSONObject getPersons(final Context ctx, final Map uiContext) throws Exception {
        final IEnactmentService server = EnactmentServiceFactory.createEnactService(ctx);
        final String[] actInstIds = (String[]) uiContext.get("actInstIds");
        final ArrayList actInstList = (ArrayList)uiContext.get("DesignatePerformerUI.BASE_ACT_ID");
        final WfProcessDiagram diag = new WfProcessDiagram(ctx, "");
        final HashMap[] postActivities = diag.findNextManpownerActivities(actInstIds);
        final ProcessDef processDef = diag.getTopProcessDef();
        final DesignatePerformerData data = server.getManpowerActDefByActInstIds((List)actInstList);
        final String procInstId = data.getProcessInsId();
        final KDTree treePostActivities = new KDTree();
        treePostActivities.setName("treePostActivities");
        final DefaultKingdeeTreeNode root = (DefaultKingdeeTreeNode)treePostActivities.getModel().getRoot();
        treePostActivities.removeAllChildrenFromParent((MutableTreeNode)root);
        treePostActivities.setRootVisible(false);
        treePostActivities.setShowCheckBox(true);
        final TreeSelectionModel model = new DefaultTreeSelectionModel();
        model.setSelectionMode(1);
        treePostActivities.setSelectionModel(model);
        initPostActivitiesTree(ctx, treePostActivities, postActivities, root);
        treePostActivities.expandOnLevel(2);
        final DefaultKingdeeTreeNode root2 = (DefaultKingdeeTreeNode)treePostActivities.getModel().getRoot();
        final ArrayList participants = new ArrayList();
        final ArrayList personIds = new ArrayList();
        final Enumeration enu = root2.children();
        while (enu.hasMoreElements()) {
            final DefaultKingdeeTreeNode tmp2 = (DefaultKingdeeTreeNode) enu.nextElement();
            final Enumeration enu2 = tmp2.children();
            while (enu2.hasMoreElements()) {
                final DefaultKingdeeTreeNode tmp3 = (DefaultKingdeeTreeNode) enu2.nextElement();
                final ParticipantDef p = (ParticipantDef)tmp3.getUserObject();
                participants.add(p.getID());
            }
            final ManpowerActivityDef actDef = (ManpowerActivityDef)tmp2.getUserObject();
            final List<Person> persons = server.getPersonsByParticipants(procInstId, actDef.getID(), (List)participants);
            for (final Person person : persons) {
                personIds.add(person.getEmployeeId());
            }
        }
        final JSONObject json = new JSONObject();
        final JSONArray ja = new JSONArray();
        final ArrayList<Map> list = PostParticipantUtil.queryPerson("id", "id,number,name,AdminOrgUnit.name", personIds, "com.kingdee.eas.basedata.person.app.F7PersonQuery", "name", ctx);
        for (final Map item : list) {
            final JSONObject jo = new JSONObject();
            jo.put((Object)"uid", item.get("id"));
            jo.put((Object)"code", item.get("number"));
            jo.put((Object)"name", item.get("name"));
            jo.put((Object)"org", item.get("AdminOrgUnit.name"));
            ja.add((Object)jo);
        }
        json.put((Object)"Rows", (Object)ja);
        json.put((Object)"Total", (Object)1);
        return json;
    }
    
    public static void setNextPerson(final Context ctx, final ArrayList actInstList, final String[] actInstIds, final ArrayList list) throws BOSException {
        final IEnactmentService server = EnactmentServiceFactory.createEnactService(ctx);
        final WfProcessDiagram diag = new WfProcessDiagram(ctx, "");
        final HashMap[] postActivities = diag.findNextManpownerActivities(actInstIds);
        final DesignatePerformerData data = server.getManpowerActDefByActInstIds((List)actInstList);
        final String procInstId = data.getProcessInsId();
        final String key = ParticipantUtils.createDesignatePerformerKey((ActivityDef)postActivities[0].get("actDef"));
        server.setProcessContext(procInstId, key, (Object)list);
    }
    
    private static void initPostActivitiesTree(final Context ctx, final KDTree treePostActivities, final HashMap[] activities, final DefaultKingdeeTreeNode parent) throws Exception {
        for (int i = 0; i < activities.length; ++i) {
            final HashMap act = activities[i];
            final ActivityDef actDef = (ActivityDef) act.get("actDef");
            final DefaultKingdeeTreeNode node = new DefaultKingdeeTreeNode((Object)actDef);
            node.setCheckBoxVisible(false);
            node.setText((String)act.get("name"));
            treePostActivities.addNodeInto((MutableTreeNode)node, (MutableTreeNode)parent);
            initParticipants(ctx, treePostActivities, (ManpowerActivityDef)actDef, node);
        }
    }
    
    private static void initParticipants(final Context ctx, final KDTree treePostActivities, final ManpowerActivityDef actDef, final DefaultKingdeeTreeNode node) throws Exception {
        final ParticipantCollection participants = actDef.getParticipants();
        for (int i = 0; i < participants.size(); ++i) {
            final ParticipantDef participant = participants.get(i);
            final DefaultKingdeeTreeNode p = new DefaultKingdeeTreeNode((Object)participant);
            final ProcessDef processDef = (ProcessDef)actDef.getContainer();
            final Locale locale = new Locale("L2");
            p.setText(com.kingdee.eas.base.multiapprove.ParticipantUtils.getParticipantName(participant, processDef, locale, ctx));
            p.setCheckBoxVisible(true);
            treePostActivities.addNodeInto((MutableTreeNode)p, (MutableTreeNode)node);
        }
    }
}
