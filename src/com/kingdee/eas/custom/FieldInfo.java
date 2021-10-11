package com.kingdee.eas.custom;

public class FieldInfo
{
    private String name;
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.getName();
    }
}
