package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import java.lang.String;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.framework.ICoreBillBase;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.util.*;

public interface IUITool extends ICoreBillBase
{
    public UIToolCollection getUIToolCollection() throws BOSException;
    public UIToolCollection getUIToolCollection(EntityViewInfo view) throws BOSException;
    public UIToolCollection getUIToolCollection(String oql) throws BOSException;
    public UIToolInfo getUIToolInfo(IObjectPK pk) throws BOSException, EASBizException;
    public UIToolInfo getUIToolInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public UIToolInfo getUIToolInfo(String oql) throws BOSException, EASBizException;
    public void resutPage() throws BOSException;
    public void getUserMapping() throws BOSException;
}