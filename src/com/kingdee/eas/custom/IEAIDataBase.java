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
import com.kingdee.eas.framework.IDataBase;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.util.*;

public interface IEAIDataBase extends IDataBase
{
    public EAIDataBaseInfo getEAIDataBaseInfo(IObjectPK pk) throws BOSException, EASBizException;
    public EAIDataBaseInfo getEAIDataBaseInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public EAIDataBaseInfo getEAIDataBaseInfo(String oql) throws BOSException, EASBizException;
    public EAIDataBaseCollection getEAIDataBaseCollection() throws BOSException;
    public EAIDataBaseCollection getEAIDataBaseCollection(EntityViewInfo view) throws BOSException;
    public EAIDataBaseCollection getEAIDataBaseCollection(String oql) throws BOSException;
}