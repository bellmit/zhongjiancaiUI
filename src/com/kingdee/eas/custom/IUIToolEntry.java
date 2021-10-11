package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.ICoreBillEntryBase;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import java.lang.String;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.util.*;

public interface IUIToolEntry extends ICoreBillEntryBase
{
    public UIToolEntryInfo getUIToolEntryInfo(IObjectPK pk) throws BOSException, EASBizException;
    public UIToolEntryInfo getUIToolEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public UIToolEntryInfo getUIToolEntryInfo(String oql) throws BOSException, EASBizException;
    public UIToolEntryCollection getUIToolEntryCollection() throws BOSException;
    public UIToolEntryCollection getUIToolEntryCollection(EntityViewInfo view) throws BOSException;
    public UIToolEntryCollection getUIToolEntryCollection(String oql) throws BOSException;
}