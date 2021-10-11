package com.octal.thirdparty.kdyzj.api;

import java.util.*;
import org.apache.commons.lang.*;
import org.apache.commons.codec.digest.*;
import com.alibaba.fastjson.*;
import org.apache.commons.lang.StringUtils;

public class PubaccUtil
{
    public static JSONObject genernateFrom(final String no, final String pubId, final String pubsercet, final String nonce, final String time) {
        final JSONObject jsonFrom = new JSONObject();
        jsonFrom.put("no", (Object)no);
        jsonFrom.put("pub", (Object)pubId);
        jsonFrom.put("nonce", (Object)nonce);
        jsonFrom.put("time", (Object)time);
        final String pubtoken = sha(no, pubId, pubsercet, nonce, time);
        jsonFrom.put("pubtoken", (Object)pubtoken);
        return jsonFrom;
    }
    
    private static String sha(final String... data) {
        Arrays.sort(data);
        final String join = StringUtils.join((Object[])data);
        final String pubtoken = DigestUtils.shaHex(join);
        return pubtoken;
    }
    
    public static JSONArray generateTo(final String tono, final JSONArray userArray) {
        final JSONArray jsonto1 = new JSONArray();
        final JSONObject jsonto2 = new JSONObject();
        if (StringUtils.isNotEmpty(tono) && userArray.size() == 0) {
            jsonto2.put("no", (Object)tono);
            jsonto2.put("code", (Object)"all");
            jsonto1.add((Object)jsonto2);
        }
        else if (StringUtils.isNotEmpty(tono) && userArray.size() > 0) {
            jsonto2.put("no", (Object)tono);
            jsonto2.put("user", (Object)userArray);
            jsonto1.add((Object)jsonto2);
        }
        return jsonto1;
    }
}
