package com.kingdee.eas.moya.message.app.landray;

import sun.misc.*;
import java.security.*;

public class DESUtil
{
    public static byte[] securityKey;
    
    public static String getUserName(final String tokenString, final String key) throws Exception {
        System.out.println("token=" + tokenString);
        if (isNull(tokenString) || "\"\"".equals(tokenString)) {
            return null;
        }
        final BASE64Decoder base64Decoder = new BASE64Decoder();
        DESUtil.securityKey = base64Decoder.decodeBuffer(key);
        final byte[] token = base64Decoder.decodeBuffer(tokenString);
        if (!checkToken(token)) {
            return null;
        }
        byte[] bytes = new byte[8];
        System.arraycopy(token, 4, bytes, 0, 8);
        final long create = Long.parseLong(new String(bytes), 16) * 1000L;
        bytes = new byte[8];
        System.arraycopy(token, 12, bytes, 0, 8);
        final long expire = Long.parseLong(new String(bytes), 16) * 1000L;
        final int length = token.length - 40;
        bytes = new byte[length];
        System.arraycopy(token, 20, bytes, 0, length);
        final String username = formatUser(new String(bytes, "UTF-8"));
        return username;
    }
    
    private static String formatUser(final String user) {
        final String[] userInfo = user.split("\\s*[/,;]\\s*");
        final int index = userInfo[0].indexOf(61);
        if (index == -1) {
            return userInfo[0];
        }
        return userInfo[0].substring(index + 1);
    }
    
    private static boolean isNull(final String str) {
        return str == null || str.trim().length() == 0;
    }
    
    private static boolean checkToken(final byte[] token) throws Exception {
        final int length = token.length - 20;
        final byte[] curDigest = new byte[20];
        System.arraycopy(token, length, curDigest, 0, 20);
        final byte[] bytes = new byte[length + DESUtil.securityKey.length];
        System.arraycopy(token, 0, bytes, 0, length);
        System.arraycopy(DESUtil.securityKey, 0, bytes, length, DESUtil.securityKey.length);
        final byte[] newDigest = MessageDigest.getInstance("SHA-1").digest(bytes);
        return MessageDigest.isEqual(curDigest, newDigest);
    }
}
