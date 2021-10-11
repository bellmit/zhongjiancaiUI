package com.kingdee.eas.custom;

import java.io.Serializable;

public class EmpAttendInfo extends AbstractEmpAttendInfo implements Serializable 
{
    public EmpAttendInfo()
    {
        super();
    }
    protected EmpAttendInfo(String pkField)
    {
        super(pkField);
    }
}