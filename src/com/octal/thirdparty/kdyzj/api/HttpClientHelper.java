package com.octal.thirdparty.kdyzj.api;

import org.apache.commons.logging.*;
import java.util.concurrent.locks.*;
import org.apache.http.impl.conn.*;
import org.apache.http.impl.client.*;
import org.apache.http.client.config.*;
import org.apache.http.conn.*;
import org.apache.http.client.methods.*;
import java.io.*;
import org.apache.commons.httpclient.params.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.*;

import java.util.*;

public class HttpClientHelper
{
    protected static Log log;
    private static HttpClientHelper instance;
    private static Lock lock;
    private CloseableHttpClient httpClient;
    
    static {
        HttpClientHelper.log = LogFactory.getLog((Class)HttpClientHelper.class);
        HttpClientHelper.instance = null;
        HttpClientHelper.lock = new ReentrantLock();
    }
    
    public static HttpClientHelper getHttpClient() {
        if (HttpClientHelper.instance == null) {
            HttpClientHelper.lock.lock();
            try {
                if (HttpClientHelper.instance == null) {
                    (HttpClientHelper.instance = new HttpClientHelper()).init();
                }
            }
            finally {
                HttpClientHelper.lock.unlock();
            }
            HttpClientHelper.lock.unlock();
        }
        return HttpClientHelper.instance;
    }
    
    private void init() {
        final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(1000);
        cm.setDefaultMaxPerRoute(40);
        final HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        final RequestConfig rc = RequestConfig.custom().setConnectTimeout(120000).setSocketTimeout(120000).build();
        this.httpClient = httpClientBuilder.setDefaultRequestConfig(rc).setConnectionManager((HttpClientConnectionManager)cm).build();
    }
    
    public String execute(final HttpRequestBase request) {
        final byte[] bytes = this.executeAndReturnByte(request);
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            return new String(bytes, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
    
    public byte[] executeAndReturnByte(final HttpRequestBase request) {
        throw new Error("\u65e0\u6cd5\u89e3\u6790\u7684\u7f16\u8bd1\u95ee\u9898\uff1a\n\t\u6ca1\u6709\u4e3a\u7c7b\u578b EntityUtils \u5b9a\u4e49\u65b9\u6cd5 consumeQuietly\uff08HttpEntity\uff09\n");
    }
    
    public static String post(final String url, final Map<String, String> head) {
        final HttpClient client = new HttpClient();
        final HttpClientParams clientparam = new HttpClientParams();
        clientparam.setConnectionManagerTimeout(10000L);
        clientparam.setContentCharset("UTF-8");
        client.setParams(clientparam);
        final HttpMethod method = (HttpMethod)new PostMethod(url);
        if (head != null) {
            for (final String key : head.keySet()) {
                method.addRequestHeader(key, (String)head.get(key));
            }
        }
        String result = null;
        try {
            client.executeMethod(method);
            result = method.getResponseBodyAsString();
        }
        catch (Exception e) {
            return null;
        }
        method.releaseConnection();
        return result;
    }
    
    public static String post(final String url, final Map<String, String> head, final String body) {
        final HttpClient client = new HttpClient();
        final HttpClientParams clientparam = new HttpClientParams();
        clientparam.setConnectionManagerTimeout(10000L);
        clientparam.setContentCharset("UTF-8");
        client.setParams(clientparam);
        final PostMethod method = new PostMethod(url);
        if (head != null) {
            for (final String key : head.keySet()) {
                method.addRequestHeader(key, (String)head.get(key));
            }
        }
        String result = null;
        try {
            if (body != null && !"".equals(body)) {
                method.setRequestEntity((RequestEntity)new StringRequestEntity(body, "application/json", "UTF-8"));
            }
            client.executeMethod((HttpMethod)method);
            result = method.getResponseBodyAsString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        method.releaseConnection();
        return result;
    }
    
    public static String post(final String url, final Map<String, String> head, final Map<String, String> body) {
        final HttpClient client = new HttpClient();
        final HttpClientParams clientparam = new HttpClientParams();
        clientparam.setConnectionManagerTimeout(10000L);
        clientparam.setContentCharset("UTF-8");
        client.setParams(clientparam);
        final PostMethod method = new PostMethod(url);
        if (head != null && head.size() != 0) {
            for (final String key : head.keySet()) {
                method.addRequestHeader(key, (String)head.get(key));
            }
        }
        if (body != null && body.size() != 0) {
            final Iterator<String> iterator = body.keySet().iterator();
            final List<NameValuePair> params = new ArrayList<NameValuePair>();
            while (iterator.hasNext()) {
                final String key2 = iterator.next();
                final String value = body.get(key2).toString();
                params.add(new NameValuePair(key2, value));
            }
            method.addParameters((NameValuePair[])params.toArray(new NameValuePair[params.size()]));
        }
        String result = null;
        try {
            client.executeMethod((HttpMethod)method);
            result = method.getResponseBodyAsString();
        }
        catch (Exception e) {
            return null;
        }
        method.releaseConnection();
        return result;
    }
}
