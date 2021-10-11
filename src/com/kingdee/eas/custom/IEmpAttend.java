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

public interface IEmpAttend extends IDataBase
{
    public EmpAttendInfo getEmpAttendInfo(IObjectPK pk) throws BOSException, EASBizException;
    public EmpAttendInfo getEmpAttendInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public EmpAttendInfo getEmpAttendInfo(String oql) throws BOSException, EASBizException;
    public EmpAttendCollection getEmpAttendCollection() throws BOSException;
    public EmpAttendCollection getEmpAttendCollection(EntityViewInfo view) throws BOSException;
    public EmpAttendCollection getEmpAttendCollection(String oql) throws BOSException;
}