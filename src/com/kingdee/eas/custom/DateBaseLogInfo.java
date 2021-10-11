package com.kingdee.eas.custom;

import java.io.Serializable;

public class DateBaseLogInfo extends AbstractDateBaseLogInfo implements Serializable 
{
    public DateBaseLogInfo()
    {
        super();
    }
    protected DateBaseLogInfo(String pkField)
    {
        super(pkField);
    }
}