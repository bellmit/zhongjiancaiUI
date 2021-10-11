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

public interface ISyncEmpLog extends IDataBase
{
    public SyncEmpLogInfo getSyncEmpLogInfo(IObjectPK pk) throws BOSException, EASBizException;
    public SyncEmpLogInfo getSyncEmpLogInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public SyncEmpLogInfo getSyncEmpLogInfo(String oql) throws BOSException, EASBizException;
    public SyncEmpLogCollection getSyncEmpLogCollection() throws BOSException;
    public SyncEmpLogCollection getSyncEmpLogCollection(EntityViewInfo view) throws BOSException;
    public SyncEmpLogCollection getSyncEmpLogCollection(String oql) throws BOSException;
}