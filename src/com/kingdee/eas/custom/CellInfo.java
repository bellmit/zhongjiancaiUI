package com.kingdee.eas.custom;

public class CellInfo
{
    int colspan;
    int rowspan;
    String value;
    
    public CellInfo() {
        this.colspan = 1;
        this.rowspan = 1;
    }
    
    public int getColspan() {
        return this.colspan;
    }
    
    public void setColspan(final int colspan) {
        this.colspan = colspan;
    }
    
    public int getRowspan() {
        return this.rowspan;
    }
    
    public void setRowspan(final int rowspan) {
        this.rowspan = rowspan;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
}
