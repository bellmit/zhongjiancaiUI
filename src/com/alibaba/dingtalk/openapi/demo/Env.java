package com.alibaba.dingtalk.openapi.demo;

import com.kingdee.eas.moya.common.PropUtil;


/**
 * ��ҵӦ�ý���ʱ�ĳ�������
 */
public class Env {

    //����
      // ��ҵcorpid 
    //public static String CORP_ID = "ding448c31b041d07d4fbc961a6cb783455b";
	public static String CORP_ID = "ding8ee6c296f6157c08f2c783f7214b6d69"; 
    // Ӧ��agentId 
	//public static String AGENT_ID = "1217834936";
	public static String AGENT_ID = "1243758816"; 
	// Ӧ�õ�appkey 
	//public static String APP_KEY = "dingq5alrcsbgdaf3wey";
	public static String APP_KEY = "dingpvgmprts1uuirhja"; 
	//  Ӧ�õ�appsecret 
	//public static String APP_SECRET = "wAxPtLauFXzdMmGuiGjlx9X2Zv76-CruLz6VkxWcQGSsi2BG_mepHKhG1b5uL8rh";
	public static String APP_SECRET = "MB5AOi-RbB1IVCDvEV-AgvJ3QRW2j2tKI5_FmXGF1WIWloibhcaj5f-hAQoLzion"; 
	 
	
    /**
     * �ص�host
     */
    public static final String CALLBACK_URL_HOST = "";

    /**
     * �ӽ�����Ҫ�õ���token����ҵ���������д���� "123456"
     */
	public static final String TOKEN = "311f32a05c5b302f9d8bb4d87e43f603";

    /**
     * ���ݼ�����Կ�����ڻص����ݵļ��ܣ����ȹ̶�Ϊ43���ַ�����a-z, A-Z, 0-9��62���ַ���ѡȡ,�������������
     */
	public static final String ENCODING_AES_KEY = "abcdefghijabcdefghijabcdefghijabcdefghij123";

    /**
     * DING API��ַ
     */
    public static final String OAPI_HOST = "https://oapi.dingtalk.com";

    /**
     * ɾ����ҵ�ص��ӿ�url
     */
    public static final String DELETE_CALLBACK = "https://oapi.dingtalk.com/call_back/delete_call_back";

    /**
     * ע����ҵ�ص��ӿ�url
     */
    public static final String REGISTER_CALLBACK = "https://oapi.dingtalk.com/call_back/register_call_back";

    /**
     * ��ҵӦ�ú�̨��ַ���û������̨���ʹ��
     */
    public static final String OA_BACKGROUND_URL = "";

    public static final String SSO_Secret = "";
    static {
        Env.CORP_ID = PropUtil.getParameterByName("dd.corpid");
        Env.APP_KEY = PropUtil.getParameterByName("dd.appkey");
        Env.APP_SECRET = PropUtil.getParameterByName("dd.appsecret");
        Env.AGENT_ID = PropUtil.getParameterByName("dd.agentid");
    }
}
