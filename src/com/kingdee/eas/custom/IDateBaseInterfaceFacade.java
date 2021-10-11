package com.kingdee.eas.custom;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.custom.app.DateBaseProcessType;
import java.lang.String;
import com.kingdee.eas.custom.app.DateBasetype;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;

public interface IDateBaseInterfaceFacade extends IBizCtrl
{
    public void getHROrgFormOA(String database) throws BOSException;
    public void getPersonFormOA(String database) throws BOSException;
    public void getSupplierFormOA(String database) throws BOSException;
    public void getCustomerFormOA(String database) throws BOSException;
    public void getProjectFormOA(String dataBase) throws BOSException;
    public void getOfficeFormOA(String dataBase) throws BOSException;
    public void getSpotFormOA(String dataBase) throws BOSException;
    public void getProcostsFormOA(String dataBase, String period) throws BOSException;
    public void getGatheringFormOA(String dataBase) throws BOSException;
    public void getPaymentFormOA(String dataBase) throws BOSException;
    public void getMainClassFormHis(String dataBase) throws BOSException;
    public void getOrgToMid(String dataBase, String fid) throws BOSException;
    public void getPositionToMid(String dataBase, String fid) throws BOSException;
    public void getPersonToMid(String dataBase, String fid) throws BOSException;
    public void getSupplierToHis(String dataBase, String fid) throws BOSException;
    public void getMaterielToMid(String dataBase, String fid) throws BOSException;
    public void getFreeItemlToMid(String dataBase, String fid) throws BOSException;
    public void getEasPayTypeToMid(String dataBase, String fid) throws BOSException;
    public void updateMidTable(DateBaseProcessType processType, DateBasetype baseType, String dataBase, String number, String name, String id) throws BOSException;
    public void getEasPayTypeTreeToMid(String dataBase, String fid) throws BOSException;
    public void SyncPosition(String dataBase) throws BOSException;
    public void SyncPerson(String dataBase) throws BOSException;
    public void SyncOrg(String dataBase) throws BOSException;
    public void SyncSupplier(String dataBase) throws BOSException;
    public void SyncFreeItem(String dataBase) throws BOSException;
    public void SyncEasPayType(String dataBase) throws BOSException;
    public void SyncEasPayTypeTree(String dataBase) throws BOSException;
    public void SynExpenseType(String dataBase) throws BOSException;
    public void SyncSupplyinfoToMid(String dataBase) throws BOSException;
    public void SyncRuZhi(String dataBase) throws BOSException;
    public void SyncLiZhi(String datebase) throws BOSException;
    public void SyncYiDong(String datebase) throws BOSException;
}