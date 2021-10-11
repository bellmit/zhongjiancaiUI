package com.octal.thirdparty.kdyzj.api;

import org.apache.http.message.*;
import org.apache.http.client.entity.*;
import org.apache.http.*;
import java.util.*;
import org.apache.http.client.methods.*;
import org.apache.commons.lang3.*;
import org.apache.http.entity.*;
import org.apache.http.entity.mime.*;

import java.net.*;
import java.io.*;

public final class HttpHelper
{
    private static String UTF8;
    
    static {
        HttpHelper.UTF8 = "UTF-8";
    }
    
    public static String post(final Map<String, String> params, final String url) throws Exception {
        HttpPost post = null;
        try {
            post = new HttpPost(url);
            final List<NameValuePair> uvp = new LinkedList<NameValuePair>();
            final Set<String> set = params.keySet();
            for (final String key : set) {
                uvp.add((NameValuePair)new BasicNameValuePair(key, (String)params.get(key)));
            }
            final UrlEncodedFormEntity entity = new UrlEncodedFormEntity((List)uvp, HttpHelper.UTF8);
            post.setEntity((HttpEntity)entity);
            return HttpClientHelper.getHttpClient().execute((HttpRequestBase)post);
        }
        finally {
            if (post != null) {
                post.abort();
            }
        }
    }
    
    public static String post(final Map<String, String> headers, final Map<String, String> params, final String url) throws Exception {
        HttpPost post = null;
        try {
            post = new HttpPost(url);
            if (headers != null) {
                final Set<String> set = headers.keySet();
                for (final String key : set) {
                    post.addHeader(key, (String)headers.get(key));
                }
            }
            if (params != null) {
                final List<NameValuePair> uvp = new LinkedList<NameValuePair>();
                final Set<String> set2 = params.keySet();
                for (final String key2 : set2) {
                    uvp.add((NameValuePair)new BasicNameValuePair(key2, (String)params.get(key2)));
                }
                final UrlEncodedFormEntity entity = new UrlEncodedFormEntity((List)uvp, HttpHelper.UTF8);
                post.setEntity((HttpEntity)entity);
            }
            return HttpClientHelper.getHttpClient().execute((HttpRequestBase)post);
        }
        finally {
            if (post != null) {
                post.abort();
            }
        }
    }
    
    public static byte[] getAsBytes(final Map<String, String> headers, final Map<String, String> params, final String url) {
        HttpGet get = null;
        try {
            if (params != null) {
                final StringBuffer uri = new StringBuffer(url);
                uri.append("?");
                final Set<String> set = params.keySet();
                for (final String key : set) {
                    final String value = params.get(key);
                    uri.append(urlUTF8Encode(key)).append("=").append(urlUTF8Encode(value)).append("&");
                }
                uri.deleteCharAt(uri.length() - 1);
                get = new HttpGet(uri.toString());
            }
            else {
                get = new HttpGet(url);
            }
            if (headers != null) {
                final Set<String> set2 = headers.keySet();
                for (final String key2 : set2) {
                    get.addHeader(key2, (String)headers.get(key2));
                }
            }
            return HttpClientHelper.getHttpClient().executeAndReturnByte((HttpRequestBase)get);
        }
        finally {
            if (get != null) {
                get.abort();
            }
        }
    }
    
    public static String get(final Map<String, String> params, final String url) throws Exception {
        HttpGet get = null;
        try {
            if (params != null) {
                final StringBuffer uri = new StringBuffer(url);
                uri.append("?");
                final Set<String> set = params.keySet();
                for (final String key : set) {
                    final String value = params.get(key);
                    uri.append(urlUTF8Encode(key)).append("=").append(urlUTF8Encode(value)).append("&");
                }
                uri.deleteCharAt(uri.length() - 1);
                get = new HttpGet(uri.toString());
            }
            else {
                get = new HttpGet(url);
            }
            return HttpClientHelper.getHttpClient().execute((HttpRequestBase)get);
        }
        finally {
            if (get != null) {
                get.abort();
            }
        }
    }
    
    public static String post(final Map<String, String> headers, final String jsonObject, final String url) throws Exception {
        HttpPost post = null;
        try {
            post = new HttpPost(url);
            if (headers != null) {
                final Set<String> set = headers.keySet();
                for (final String key : set) {
                    post.addHeader(key, (String)headers.get(key));
                }
            }
            if (StringUtils.isEmpty(jsonObject)) {
                throw new Exception("json\ufffd\ufffd\ufffd\ufffd\u03aa\ufffd\u0563\ufffd");
            }
            final StringEntity entity = new StringEntity(jsonObject, "UTF-8");
            post.setEntity((HttpEntity)entity);
            return HttpClientHelper.getHttpClient().execute((HttpRequestBase)post);
        }
        finally {
            if (post != null) {
                post.abort();
            }
        }
    }
    
    public static String get(final Map<String, String> headers, final Map<String, String> params, final String url) throws Exception {
        HttpGet get = null;
        try {
            if (params != null) {
                final StringBuffer uri = new StringBuffer(url);
                uri.append("?");
                final Set<String> set = params.keySet();
                for (final String key : set) {
                    final String value = params.get(key);
                    uri.append(urlUTF8Encode(key)).append("=").append(urlUTF8Encode(value)).append("&");
                }
                uri.deleteCharAt(uri.length() - 1);
                get = new HttpGet(uri.toString());
            }
            else {
                get = new HttpGet(url);
            }
            if (headers != null) {
                final Set<String> set2 = headers.keySet();
                for (final String key2 : set2) {
                    get.addHeader(key2, (String)headers.get(key2));
                }
            }
            return HttpClientHelper.getHttpClient().execute((HttpRequestBase)get);
        }
        finally {
            if (get != null) {
                get.abort();
            }
        }
    }
    
    public static String get(final Map<String, String> params, final String url, final String charset) throws Exception {
        HttpGet get = null;
        try {
            if (params != null) {
                final StringBuffer uri = new StringBuffer(url);
                uri.append("?");
                final Set<String> set = params.keySet();
                for (final String key : set) {
                    final String value = params.get(key);
                    uri.append(urlEncode(key, charset)).append("=").append(urlEncode(value, charset)).append("&");
                }
                uri.deleteCharAt(uri.length() - 1);
                get = new HttpGet(uri.toString());
            }
            else {
                get = new HttpGet(url);
            }
            return HttpClientHelper.getHttpClient().execute((HttpRequestBase)get);
        }
        finally {
            if (get != null) {
                get.abort();
            }
        }
    }
    
    public static String post(final Map<String, String> basicParams, final String url, final Map<String, byte[]> fileParams) throws Exception {
        HttpPost post = null;
        try {
            post = new HttpPost(url);
            final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            final Set<String> set1 = basicParams.keySet();
            for (final String key : set1) {
                builder.addTextBody(key, (String)basicParams.get(key));
            }
            final Set<String> set2 = fileParams.keySet();
            for (final String key2 : set2) {
                builder.addBinaryBody(key2, (byte[])fileParams.get(key2));
            }
            final HttpEntity entity = builder.build();
            post.setEntity(entity);
            return HttpClientHelper.getHttpClient().execute((HttpRequestBase)post);
        }
        finally {
            if (post != null) {
                post.abort();
            }
        }
    }
    
    private static String urlUTF8Encode(final String str) {
        try {
            return URLEncoder.encode(str, HttpHelper.UTF8);
        }
        catch (Exception e) {
            return str;
        }
    }
    
    private static String urlEncode(final String str, final String charset) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, charset);
    }
}
