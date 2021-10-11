package com.kingdee.eas.moya.common;

import org.apache.http.entity.*;
import org.apache.http.util.*;
import java.io.*;
import org.apache.http.impl.client.*;
import org.apache.http.client.methods.*;
import java.util.*;
import org.apache.http.*;
import org.apache.http.client.entity.*;

public class HttpUtil
{
    public static String myPost(final String url, final StringEntity entity) {
        final HttpPost post = new HttpPost(url);
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            if (entity != null) {
                post.setEntity((HttpEntity)entity);
            }
            final CloseableHttpResponse response = httpClient.execute((HttpUriRequest)post);
            final HttpEntity resEntity = response.getEntity();
            final String content = EntityUtils.toString(resEntity, "utf-8");
            System.out.println(content);
            return content;
        }
        catch (Exception e) {
            e.printStackTrace();
            try {
                httpClient.close();
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        finally {
            try {
                httpClient.close();
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
    
    public static String myPost(final String url, final List<NameValuePair> params, final int tag) {
        final HttpPost post = new HttpPost(url);
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            final UrlEncodedFormEntity entity = new UrlEncodedFormEntity((List)params, "UTF-8");
            post.setEntity((HttpEntity)entity);
            final CloseableHttpResponse response = httpClient.execute((HttpUriRequest)post);
            final HttpEntity resEntity = response.getEntity();
            final String content = EntityUtils.toString(resEntity, "utf-8");
            System.out.println(content);
            return content;
        }
        catch (Exception e) {
            e.printStackTrace();
            try {
                httpClient.close();
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        finally {
            try {
                httpClient.close();
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
