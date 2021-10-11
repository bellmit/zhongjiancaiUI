package com.kingdee.eas.custom.app;

import org.apache.log4j.*;
import com.kingdee.bos.workflow.monitor.*;
import com.kingdee.bos.workflow.define.*;
import com.kingdee.bos.workflow.participant.*;
import com.kingdee.bos.workflow.service.ormrpc.*;
import java.util.*;
import com.kingdee.bos.workflow.biz.trans.*;
import com.kingdee.bos.*;

public class DesignatePerformerFacadeControllerBean extends AbstractDesignatePerformerFacadeControllerBean
{
    private static Logger logger;
    
    static {
        DesignatePerformerFacadeControllerBean.logger = Logger.getLogger("com.kingdee.eas.custom.app.DesignatePerformerFacadeControllerBean");
    }
    
    @Override
    protected void _setNextPerson(final Context ctx, final ArrayList actInstList, final String[] actInstIds, final ArrayList list) throws BOSException {
        System.out.println("\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\u04bb\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd");
        final IEnactmentService server = EnactmentServiceFactory.createEnactService(ctx);
        final WfProcessDiagram diag = new WfProcessDiagram(ctx, "");
        final HashMap[] postActivities = diag.findNextManpownerActivities(actInstIds);
        final DesignatePerformerData data = server.getManpowerActDefByActInstIds((List)actInstList);
        final String procInstId = data.getProcessInsId();
        final String key = ParticipantUtils.createDesignatePerformerKey((ActivityDef)postActivities[0].get("actDef"));
        server.setProcessContext(procInstId, key, (Object)list);
    }
}
