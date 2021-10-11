/**
 * output package name
 */
package com.kingdee.eas.custom;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import com.kingdee.util.enums.IntEnum;

/**
 * output class name
 */
public class PCPageTypeEnum extends IntEnum
{
    public static final int THREE_VALUE = 3;//alias=三列
    public static final int FOUR_VALUE = 4;//alias=四列

    public static final PCPageTypeEnum THREE = new PCPageTypeEnum("THREE", THREE_VALUE);
    public static final PCPageTypeEnum FOUR = new PCPageTypeEnum("FOUR", FOUR_VALUE);

    /**
     * construct function
     * @param integer pCPageTypeEnum
     */
    private PCPageTypeEnum(String name, int pCPageTypeEnum)
    {
        super(name, pCPageTypeEnum);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static PCPageTypeEnum getEnum(String pCPageTypeEnum)
    {
        return (PCPageTypeEnum)getEnum(PCPageTypeEnum.class, pCPageTypeEnum);
    }

    /**
     * getEnum function
     * @param String arguments
     */
    public static PCPageTypeEnum getEnum(int pCPageTypeEnum)
    {
        return (PCPageTypeEnum)getEnum(PCPageTypeEnum.class, pCPageTypeEnum);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(PCPageTypeEnum.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(PCPageTypeEnum.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(PCPageTypeEnum.class);
    }
}