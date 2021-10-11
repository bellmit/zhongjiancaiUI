package com.octal.thirdparty.kdyzj.api;

import org.apache.commons.codec.binary.*;
import org.slf4j.*;
import org.apache.commons.lang.*;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.net.*;
import java.io.*;
import javax.crypto.spec.*;
import java.security.*;
import javax.crypto.*;

public class AuthorizationUtil
{
    private static final Logger logger;
    public static Random RAND;
    private static final Base64 BASE64;
    private static final String HMAC_SHA1 = "HmacSHA1";
    private static final String DEFAULT_SIGNATURE_METHOD = "HMAC-SHA1";
    private static String characterEncoding;
    
    static {
        logger = LoggerFactory.getLogger((Class)AuthorizationUtil.class);
        AuthorizationUtil.RAND = new Random();
        BASE64 = new Base64();
        AuthorizationUtil.characterEncoding = "UTF-8";
    }
    
    public static String generateAuthorizationHeader(final String consumerKey, final String consumerSecret, String signatureMethod, final long timestamp, final String nonce, final float version, final String oauthToken, final String oauthTokenSecret, final String verifier, final String url, final Map<String, Object> parameters, final String requestType) throws URISyntaxException, IOException, GeneralSecurityException {
        if (StringUtils.isEmpty(consumerKey)) {
            throw new IllegalArgumentException("consumerKey is null or empty!");
        }
        if (StringUtils.isEmpty(consumerSecret)) {
            throw new IllegalArgumentException("consumerSecret is null or empty!");
        }
        if (nonce == null) {
            throw new IllegalArgumentException("nonce is null!");
        }
        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url is null or empty!");
        }
        if (StringUtils.isEmpty(requestType)) {
            throw new IllegalArgumentException("requestType is null or empty!");
        }
        if (StringUtils.isEmpty(signatureMethod)) {
            signatureMethod = "HMAC-SHA1";
        }
        final Map<String, String> oauthHeaderParams = new LinkedHashMap<String, String>();
        oauthHeaderParams.put("oauth_consumer_key", consumerKey);
        oauthHeaderParams.put("oauth_signature_method", signatureMethod);
        oauthHeaderParams.put("oauth_timestamp", String.valueOf(timestamp));
        oauthHeaderParams.put("oauth_nonce", nonce);
        oauthHeaderParams.put("oauth_version", String.valueOf(version));
        if (StringUtils.isNotEmpty(oauthToken)) {
            if (StringUtils.isEmpty(oauthTokenSecret)) {
                throw new IllegalArgumentException("oauthTokenSecret is null or empty!");
            }
            oauthHeaderParams.put("oauth_token", oauthToken);
        }
        if (StringUtils.isNotEmpty(verifier)) {
            oauthHeaderParams.put("x_auth_mode", verifier);
        }
        final Map<String, Object> signatureBaseParams = new HashMap<String, Object>(oauthHeaderParams);
        if (parameters != null && !parameters.isEmpty()) {
            signatureBaseParams.putAll(parameters);
        }
        parseGetParameters(url, signatureBaseParams);
        final String oauthBaseString = getBaseString(requestType, url, signatureBaseParams);
        final String signature = base64Encode(computeSignature(consumerSecret, oauthTokenSecret, oauthBaseString));
        oauthHeaderParams.put("oauth_signature", signature);
        final String authorization = "OAuth " + encodeParameters(oauthHeaderParams, ",", true);
        return authorization;
    }
    
    private static void parseGetParameters(final String url, final Map<String, Object> signatureBaseParams) {
        final int queryStart = url.indexOf("?");
        if (-1 != queryStart) {
            final String[] queryStrs = url.substring(queryStart + 1).split("&");
            try {
                String[] array;
                for (int length = (array = queryStrs).length, i = 0; i < length; ++i) {
                    final String query = array[i];
                    final String[] split = query.split("=");
                    if (split.length == 2) {
                        signatureBaseParams.put(URLDecoder.decode(split[0], "UTF-8"), URLDecoder.decode(split[1], "UTF-8"));
                    }
                    else {
                        signatureBaseParams.put(URLDecoder.decode(split[0], "UTF-8"), "");
                    }
                }
            }
            catch (UnsupportedEncodingException ex) {}
        }
    }
    
    private static String getBaseString(final String requestType, final String url, final Map<String, Object> parameters) throws URISyntaxException, IOException {
        return String.valueOf(percentEncode(requestType.toUpperCase())) + '&' + percentEncode(normalizeUrl(url)) + '&' + percentEncode(normalizeParameters(parameters));
    }
    
    private static String normalizeUrl(final String url) throws URISyntaxException {
        final URI uri = new URI(url);
        final String scheme = uri.getScheme().toLowerCase();
        String authority = uri.getAuthority().toLowerCase();
        final boolean dropPort = (scheme.equals("http") && uri.getPort() == 80) || (scheme.equals("https") && uri.getPort() == 443);
        if (dropPort) {
            final int index = authority.lastIndexOf(":");
            if (index >= 0) {
                authority = authority.substring(0, index);
            }
        }
        String path = uri.getRawPath();
        if (path == null || path.length() <= 0) {
            path = "/";
        }
        return String.valueOf(scheme) + "://" + authority + path;
    }
    
    private static List<Map.Entry<String, Object>> getParameters(final Collection<ComparableParameter> parameters) {
        if (parameters == null) {
            return null;
        }
        final List<Map.Entry<String, Object>> list = new ArrayList<Map.Entry<String, Object>>(parameters.size());
        for (final ComparableParameter parameter : parameters) {
            list.add(parameter.value);
        }
        return list;
    }
    
    private static String normalizeParameters(final Map<String, Object> parameters) throws IOException {
        if (parameters == null || parameters.isEmpty()) {
            return "";
        }
        final List<ComparableParameter> p = new ArrayList<ComparableParameter>(parameters.size());
        for (final Map.Entry<String, Object> parameter : parameters.entrySet()) {
            if (!"oauth_signature".equals(parameter.getKey())) {
                p.add(new ComparableParameter(parameter));
            }
        }
        Collections.sort(p);
        return formEncode(getParameters(p));
    }
    
    public static String percentEncode(final String s) {
        if (StringUtils.isEmpty(s)) {
            return "";
        }
        try {
            return URLEncoder.encode(s, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
        }
        catch (UnsupportedEncodingException wow) {
            throw new RuntimeException(wow.getMessage(), wow);
        }
    }
    
    public static String decodeCharacters(final byte[] from) {
        if (StringUtils.isNotEmpty(AuthorizationUtil.characterEncoding)) {
            try {
                return new String(from, AuthorizationUtil.characterEncoding);
            }
            catch (UnsupportedEncodingException e) {
                System.err.println(new StringBuilder().append(e).toString());
            }
        }
        return new String(from);
    }
    
    public static String formEncode(final Iterable<? extends Map.Entry<String, Object>> parameters) throws IOException {
        final ByteArrayOutputStream b = new ByteArrayOutputStream();
        formEncode(parameters, b);
        return decodeCharacters(b.toByteArray());
    }
    
    public static void formEncode(final Iterable<? extends Map.Entry<String, Object>> parameters, final OutputStream into) throws IOException {
        if (parameters != null) {
            boolean first = true;
            for (final Map.Entry<String, Object> parameter : parameters) {
                if (first) {
                    first = false;
                }
                else {
                    into.write(38);
                }
                into.write(encodeCharacters(percentEncode(toString(parameter.getKey()))));
                into.write(61);
                into.write(encodeCharacters(percentEncode(toString(parameter.getValue()))));
            }
        }
    }
    
    public static byte[] encodeCharacters(final String from) {
        if (AuthorizationUtil.characterEncoding != null) {
            try {
                return from.getBytes(AuthorizationUtil.characterEncoding);
            }
            catch (UnsupportedEncodingException e) {
                System.err.println(new StringBuilder().append(e).toString());
            }
        }
        return from.getBytes();
    }
    
    private static final String toString(final Object from) {
        return (from == null) ? null : from.toString();
    }
    
    private static byte[] computeSignature(final String consumerSecret, final String tokenSecret, final String baseString) throws GeneralSecurityException, UnsupportedEncodingException {
        final String keyString = String.valueOf(percentEncode(consumerSecret)) + '&' + percentEncode(tokenSecret);
        final byte[] keyBytes = keyString.getBytes("UTF-8");
        final SecretKey key = new SecretKeySpec(keyBytes, "HmacSHA1");
        final Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(key);
        final byte[] text = baseString.getBytes("UTF-8");
        return mac.doFinal(text);
    }
    
    private static String base64Encode(final byte[] b) {
        final byte[] b2 = AuthorizationUtil.BASE64.encode(b);
        try {
            return new String(b2, "ISO-8859-1");
        }
        catch (UnsupportedEncodingException e) {
            System.err.println(new StringBuilder().append(e).toString());
            return new String(b2);
        }
    }
    
    private static String encodeParameters(final Map<String, String> signatureBaseParams, final String splitter, final boolean quot) {
        final StringBuffer buf = new StringBuffer();
        for (final String key : signatureBaseParams.keySet()) {
            if (buf.length() != 0) {
                if (quot) {
                    buf.append("\"");
                }
                buf.append(splitter);
            }
            buf.append(percentEncode(key)).append("=");
            if (quot) {
                buf.append("\"");
            }
            buf.append(percentEncode(signatureBaseParams.get(key)));
        }
        if (buf.length() != 0 && quot) {
            buf.append("\"");
        }
        return buf.toString();
    }
    
    public static void main(final String[] args) throws URISyntaxException, IOException, GeneralSecurityException {
        final String oauth_consumer_key = "500029221";
        final String oauth_consumer_secret = "yongxerp&";
        final String oauth_signature_method = "HMAC-SHA1";
        final long oauth_timestamp = 1487646340L;
        final String oauth_nonce = "t2Ploq";
        final float oauth_version = 1.0f;
        final String x_auth_mode = "client_auth";
        final String oauth_token = "40799d52692f610b58ae8a7dffe6b1e";
        final String oauth_token_sercet = "e37626890a4fa7d4b37beb0f859e9f7";
        final String url = "https://www.yunzhijia.com/openapi/v1/ticket/acquire.json";
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("mid", "10109");
        parameters.put("appid", "101091429");
        parameters.put("source", 2);
        parameters.put("openToken", "4021d7dc4b4db1bbacb52d1e4a641c5");
        parameters.put("urlParam", "https://do.yunzhijia.com/lightapp/c/redirect.json?lappName=workreport&viewName=index");
        generateAuthorizationHeader(oauth_consumer_key, oauth_consumer_secret, oauth_signature_method, oauth_timestamp, oauth_nonce, oauth_version, oauth_token, oauth_token_sercet, x_auth_mode, url, parameters, "POST");
    }
    
    private static class ComparableParameter implements Comparable<ComparableParameter>
    {
        final Map.Entry<String, Object> value;
        private final String key;
        
        ComparableParameter(final Map.Entry<String, Object> value) {
            this.value = value;
            final String n = toString(value.getKey());
            final String v = toString(value.getValue());
            this.key = String.valueOf(AuthorizationUtil.percentEncode(n)) + ' ' + AuthorizationUtil.percentEncode(v);
        }
        
        private static String toString(final Object from) {
            return (from == null) ? null : from.toString();
        }
        
        @Override
        public int compareTo(final ComparableParameter that) {
            return this.key.compareTo(that.key);
        }
        
        @Override
        public String toString() {
            return this.key;
        }
    }
}
