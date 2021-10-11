package com.alibaba.dingtalk.openapi.demo;

import com.kingdee.eas.moya.common.PropUtil;


/**
 * 企业应用接入时的常量定义
 */
public class Env {

    //测试
      // 企业corpid 
    //public static String CORP_ID = "ding448c31b041d07d4fbc961a6cb783455b";
	public static String CORP_ID = "ding8ee6c296f6157c08f2c783f7214b6d69"; 
    // 应用agentId 
	//public static String AGENT_ID = "1217834936";
	public static String AGENT_ID = "1243758816"; 
	// 应用的appkey 
	//public static String APP_KEY = "dingq5alrcsbgdaf3wey";
	public static String APP_KEY = "dingpvgmprts1uuirhja"; 
	//  应用的appsecret 
	//public static String APP_SECRET = "wAxPtLauFXzdMmGuiGjlx9X2Zv76-CruLz6VkxWcQGSsi2BG_mepHKhG1b5uL8rh";
	public static String APP_SECRET = "MB5AOi-RbB1IVCDvEV-AgvJ3QRW2j2tKI5_FmXGF1WIWloibhcaj5f-hAQoLzion"; 
	 
	
    /**
     * 回调host
     */
    public static final String CALLBACK_URL_HOST = "";

    /**
     * 加解密需要用到的token，企业可以随机填写。如 "123456"
     */
	public static final String TOKEN = "311f32a05c5b302f9d8bb4d87e43f603";

    /**
     * 数据加密密钥。用于回调数据的加密，长度固定为43个字符，从a-z, A-Z, 0-9共62个字符中选取,您可以随机生成
     */
	public static final String ENCODING_AES_KEY = "abcdefghijabcdefghijabcdefghijabcdefghij123";

    /**
     * DING API地址
     */
    public static final String OAPI_HOST = "https://oapi.dingtalk.com";

    /**
     * 删除企业回调接口url
     */
    public static final String DELETE_CALLBACK = "https://oapi.dingtalk.com/call_back/delete_call_back";

    /**
     * 注册企业回调接口url
     */
    public static final String REGISTER_CALLBACK = "https://oapi.dingtalk.com/call_back/register_call_back";

    /**
     * 企业应用后台地址，用户管理后台免登使用
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
