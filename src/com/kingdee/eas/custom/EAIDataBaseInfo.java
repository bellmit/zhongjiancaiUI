package com.kingdee.eas.custom;

import java.io.Serializable;

public class EAIDataBaseInfo extends AbstractEAIDataBaseInfo implements Serializable 
{
    public EAIDataBaseInfo()
    {
        super();
    }
    protected EAIDataBaseInfo(String pkField)
    {
        super(pkField);
    }
}