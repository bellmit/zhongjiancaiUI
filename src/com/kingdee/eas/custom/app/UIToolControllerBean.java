package com.kingdee.eas.custom.app;

import org.apache.log4j.*;
import com.kingdee.bos.dao.*;
import com.kingdee.bos.*;
import com.kingdee.eas.common.*;

public class UIToolControllerBean extends AbstractUIToolControllerBean
{
    private static Logger logger;
    
    static {
        UIToolControllerBean.logger = Logger.getLogger("com.kingdee.eas.custom.app.UIToolControllerBean");
    }
    
    protected IObjectPK _save(final Context ctx, final IObjectValue model) throws BOSException, EASBizException {
        final IObjectPK pk = super._save(ctx, model);
        return pk;
    }
}
