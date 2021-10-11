package com.kingdee.eas.custom;

import java.io.Serializable;

public class TaskInfo extends AbstractTaskInfo implements Serializable 
{
    public TaskInfo()
    {
        super();
    }
    protected TaskInfo(String pkField)
    {
        super(pkField);
    }
}