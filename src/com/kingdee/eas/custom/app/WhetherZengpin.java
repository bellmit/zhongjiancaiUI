/**
 * output package name
 */
package com.kingdee.eas.custom.app;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import com.kingdee.util.enums.StringEnum;

/**
 * output class name
 */
public class WhetherZengpin extends StringEnum
{
    public static final String NOTZENGPIN_VALUE = "0";//alias=不是赠品
    public static final String ISZENGPIN_VALUE = "1";//alias=是赠品

    public static final WhetherZengpin NotZengpin = new WhetherZengpin("NotZengpin", NOTZENGPIN_VALUE);
    public static final WhetherZengpin IsZengpin = new WhetherZengpin("IsZengpin", ISZENGPIN_VALUE);

    /**
     * construct function
     * @param String whetherZengpin
     */
    private WhetherZengpin(String name, String whetherZengpin)
    {
        super(name, whetherZengpin);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static WhetherZengpin getEnum(String whetherZengpin)
    {
        return (WhetherZengpin)getEnum(WhetherZengpin.class, whetherZengpin);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(WhetherZengpin.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(WhetherZengpin.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(WhetherZengpin.class);
    }
}