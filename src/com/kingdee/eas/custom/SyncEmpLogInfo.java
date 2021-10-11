package com.kingdee.eas.custom;

import java.io.Serializable;

public class SyncEmpLogInfo extends AbstractSyncEmpLogInfo implements Serializable 
{
    public SyncEmpLogInfo()
    {
        super();
    }
    protected SyncEmpLogInfo(String pkField)
    {
        super(pkField);
    }
}