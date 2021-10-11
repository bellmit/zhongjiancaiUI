package com.kingdee.eas.custom;

import java.io.Serializable;

public class EAIDataBaseTypeInfo extends AbstractEAIDataBaseTypeInfo implements Serializable 
{
    public EAIDataBaseTypeInfo()
    {
        super();
    }
    protected EAIDataBaseTypeInfo(String pkField)
    {
        super(pkField);
    }
}