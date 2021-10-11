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
public class LogTypeEnum extends IntEnum
{
    public static final int SENDYZJ_VALUE = 1;//alias=������֮�Ҵ���
    public static final int SETYZJ_VALUE = 2;//alias=������֮����Ϣ״̬
    public static final int AUDITPC_VALUE = 3;//alias=PC������
    public static final int AUDITAPP_VALUE = 4;//alias=APP������
    public static final int PCBILLINFO_VALUE = 5;//alias=PC�˻�ȡ������Ϣ
    public static final int APPBILLINFO_VALUE = 6;//alias=APP�˻�ȡ������Ϣ
    public static final int ZJ_VALUE = 7;//alias=ת��
    public static final int APPZJGETUSER_VALUE = 9;//alias=APPת����ѯ��Ա
    public static final int APPGETATTLIST_VALUE = 10;//alias=APP�˻�ȡ�����б�
    public static final int INITATT_VALUE = 11;//alias=�����鿴
    public static final int HISAUDIT_VALUE = 12;//alias=��ʷ������Ϣ
    public static final int HQ_VALUE = 13;//alias=��ǩ������

    public static final LogTypeEnum sendyzj = new LogTypeEnum("sendyzj", SENDYZJ_VALUE);
    public static final LogTypeEnum setyzj = new LogTypeEnum("setyzj", SETYZJ_VALUE);
    public static final LogTypeEnum auditPC = new LogTypeEnum("auditPC", AUDITPC_VALUE);
    public static final LogTypeEnum auditAPP = new LogTypeEnum("auditAPP", AUDITAPP_VALUE);
    public static final LogTypeEnum PCBILLINFO = new LogTypeEnum("PCBILLINFO", PCBILLINFO_VALUE);
    public static final LogTypeEnum APPBILLINFO = new LogTypeEnum("APPBILLINFO", APPBILLINFO_VALUE);
    public static final LogTypeEnum zj = new LogTypeEnum("zj", ZJ_VALUE);
    public static final LogTypeEnum APPzjGETUSER = new LogTypeEnum("APPzjGETUSER", APPZJGETUSER_VALUE);
    public static final LogTypeEnum APPGETATTLIST = new LogTypeEnum("APPGETATTLIST", APPGETATTLIST_VALUE);
    public static final LogTypeEnum initAtt = new LogTypeEnum("initAtt", INITATT_VALUE);
    public static final LogTypeEnum hisAudit = new LogTypeEnum("hisAudit", HISAUDIT_VALUE);
    public static final LogTypeEnum HQ = new LogTypeEnum("HQ", HQ_VALUE);

    /**
     * construct function
     * @param integer logTypeEnum
     */
    private LogTypeEnum(String name, int logTypeEnum)
    {
        super(name, logTypeEnum);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static LogTypeEnum getEnum(String logTypeEnum)
    {
        return (LogTypeEnum)getEnum(LogTypeEnum.class, logTypeEnum);
    }

    /**
     * getEnum function
     * @param String arguments
     */
    public static LogTypeEnum getEnum(int logTypeEnum)
    {
        return (LogTypeEnum)getEnum(LogTypeEnum.class, logTypeEnum);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(LogTypeEnum.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(LogTypeEnum.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(LogTypeEnum.class);
    }
}