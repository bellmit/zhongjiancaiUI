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

public interface IWebAuditLog extends IDataBase
{
    public WebAuditLogInfo getWebAuditLogInfo(IObjectPK pk) throws BOSException, EASBizException;
    public WebAuditLogInfo getWebAuditLogInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public WebAuditLogInfo getWebAuditLogInfo(String oql) throws BOSException, EASBizException;
    public WebAuditLogCollection getWebAuditLogCollection() throws BOSException;
    public WebAuditLogCollection getWebAuditLogCollection(EntityViewInfo view) throws BOSException;
    public WebAuditLogCollection getWebAuditLogCollection(String oql) throws BOSException;
}