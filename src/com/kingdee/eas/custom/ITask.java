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

public interface ITask extends IDataBase
{
    public TaskInfo getTaskInfo(IObjectPK pk) throws BOSException, EASBizException;
    public TaskInfo getTaskInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public TaskInfo getTaskInfo(String oql) throws BOSException, EASBizException;
    public TaskCollection getTaskCollection() throws BOSException;
    public TaskCollection getTaskCollection(EntityViewInfo view) throws BOSException;
    public TaskCollection getTaskCollection(String oql) throws BOSException;
}