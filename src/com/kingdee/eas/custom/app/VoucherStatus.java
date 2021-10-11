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
public class VoucherStatus extends StringEnum
{
    public static final String SYNCSUCCESS_VALUE = "1";//alias=同步成功
    public static final String SYNCFAIL_VALUE = "-1";//alias=同步失败
    public static final String VERIFYSUCCESS_VALUE = "2";//alias=验证成功
    public static final String VERIFYFAIL_VALUE = "-2";//alias=验证失败
    public static final String GENSUCCESS_VALUE = "3";//alias=生成成功
    public static final String GENFAIL_VALUE = "-3";//alias=生成失败

    public static final VoucherStatus SYNCSUCCESS = new VoucherStatus("SYNCSUCCESS", SYNCSUCCESS_VALUE);
    public static final VoucherStatus SYNCFAIL = new VoucherStatus("SYNCFAIL", SYNCFAIL_VALUE);
    public static final VoucherStatus VerifySuccess = new VoucherStatus("VerifySuccess", VERIFYSUCCESS_VALUE);
    public static final VoucherStatus VerifyFail = new VoucherStatus("VerifyFail", VERIFYFAIL_VALUE);
    public static final VoucherStatus GENSUCCESS = new VoucherStatus("GENSUCCESS", GENSUCCESS_VALUE);
    public static final VoucherStatus GENFAIL = new VoucherStatus("GENFAIL", GENFAIL_VALUE);

    /**
     * construct function
     * @param String voucherStatus
     */
    private VoucherStatus(String name, String voucherStatus)
    {
        super(name, voucherStatus);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static VoucherStatus getEnum(String voucherStatus)
    {
        return (VoucherStatus)getEnum(VoucherStatus.class, voucherStatus);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(VoucherStatus.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(VoucherStatus.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(VoucherStatus.class);
    }
}