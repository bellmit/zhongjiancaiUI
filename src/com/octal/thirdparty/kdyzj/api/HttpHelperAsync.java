package com.octal.thirdparty.kdyzj.api;

import org.apache.commons.lang.*;
import org.apache.http.impl.nio.client.*;
import org.apache.http.impl.nio.conn.*;
import org.apache.http.impl.nio.reactor.*;
import org.slf4j.*;
import com.alibaba.fastjson.*;
import org.apache.http.message.*;
import org.apache.http.*;
import org.apache.http.client.entity.*;
import java.util.*;
import org.junit.*;
import java.io.*;
import org.apache.http.util.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.commons.lang.StringUtils;

public class HttpHelperAsync
{
    private static final int DEFAULT_ASYNC_TIME_OUT = 10000;
    private static final int MAX_TOTEL = 10000;
    private static final int MAX_CONNECTION_PER_ROUTE = 10000;
    public static final String UTF8 = "UTF-8";
    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
    private static Get get;
    private static Post post;
    private static PostJSON postJSON;
    
    static {
        HttpHelperAsync.get = new Get();
        HttpHelperAsync.post = new Post();
        HttpHelperAsync.postJSON = new PostJSON();
    }
    
    private static final void headerLog(final HttpResponse response) {
        final Header[] headers = response.getAllHeaders();
        headerLog(headers);
    }
    
    private static final void headerLog(final Header[] headers) {
        for (final Header header : headers) {
            final String key = header.getName();
            header.getValue();
        }
    }
    
    public static Response get(final String url, final Headers headers, final Map<String, Object> parameters, final long timeoutMillis) throws Exception {
        TimeCalcUtil.setReqType("Get");
        return HttpHelperAsync.get.request(url, headers, parameters, timeoutMillis);
    }
    
    public static Response post(final String url, final Headers headers, final Map<String, Object> parameters, final long timeoutMillis) throws Exception {
        TimeCalcUtil.setReqType("Post");
        return HttpHelperAsync.post.request(url, headers, parameters, timeoutMillis);
    }
    
    public static Response postJSON(final String url, final Headers headers, final JSONObject parameters, final long timeoutMillis) throws Exception {
        TimeCalcUtil.setReqType("Post");
        return HttpHelperAsync.postJSON.request(url, headers, parameters, timeoutMillis);
    }
    
    public static void main(final String[] args) throws Exception {
        final JSONObject parameters = JSONObject.parseObject("{\"from\":{\"no\":\"10109\",\"nonce\":\"0.6917891008699647\",\"pub\":\"XT-102200\",\"pubtoken\":\"123c34f11ada8219f0eeeb913d679b362c3f0810\",\"time\":\"1430471411\"},\"msg\":{\"todo\":\"1\",\"appid\":\"10009103\",\"text\":\"\u5404\u4f4d\u91d1\u8776\u540c\u4e8b\uff0c\u4e3a\u4e86\u7ed9\u7528\u6237\u63d0\u4f9b\u66f4\u597d\u7684\u4e91\u4e4b\u5bb6\u670d\u52a1\uff0c\u8fd1\ufffd?\ufffd\ufffd\u5185\uff0c\u6211\u4eec\u4f1a\u5728\u975e\u9ad8\u5cf0\u4f7f\u7528\u65f6\u6bb5\u5bf9\u4e91\u4e4b\u5bb6\u670d\u52a1\u505a\u5fc5\u8981\u7684\ufffd?\u80fd\u6d4b\u8bd5\u53ca\u4f18\u5316\uff0c\u6d4b\u8bd5\u548c\u4f18\u5316\u8303\u56f4\u4ec5\u9650\u91d1\u8776\u5185\u90e8\u7528\u6237\uff0c\u5916\u90e8\u5ba2\u6237\u4e0d\u53d7\u4efb\u4f55\u5f71\u54cd\ufffd?\n\u5728\u6b64\u671f\u95f4\uff0c\u5982\u9047\u4efb\u4f55\u5f02\u5e38\u4f7f\u7528\u95ee\u9898\uff0c\u8bf7\u53ca\u65f6\u4e0e\u6211\u4eec\u8054\u7cfb\uff1a\u6797\u6bd3\u94ee 0755-86072654\u3002\u8c22\u8c22\u5927\u5bb6\uff01\"},\"to\":[{\"no\":\"10109\",\"user\":[\"8b1096ec-90be-42a8-bde2-e1c6a7ed4371\"]}],\"type\":2}");
        postJSON("http://192.168.22.144/pubacc/api/pubsend", null, parameters, 0L);
    }
    
    private static class PropertiesUtils1
    {
        static Properties defaultProperty;
        public static String HTTP_MAX_CONNECTION;
        public static String HTTP_MAX_CONNECTION_PER_ROUTE;
        public static boolean debug;
        
        static {
            PropertiesUtils1.HTTP_MAX_CONNECTION = getProperty("HTTP_MAX_CONNECTION", "1000");
            PropertiesUtils1.HTTP_MAX_CONNECTION_PER_ROUTE = getProperty("HTTP_MAX_CONNECTION_PER_ROUTE", "1000");
            PropertiesUtils1.debug = Boolean.valueOf(getProperty("http.debug", "true"));
        }
        
        public static void init(final String config) throws Exception {
            if (PropertiesUtils1.defaultProperty != null) {
                PropertiesUtils1.defaultProperty.clear();
            }
            PropertiesUtils1.defaultProperty = new Properties();
            final InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(config);
            PropertiesUtils1.defaultProperty.load(in);
        }
        
        public static String getProperty(final String property, final String defaultValue) {
            if (StringUtils.isEmpty(property)) {
                return null;
            }
            return PropertiesUtils1.defaultProperty.getProperty(property, defaultValue);
        }
    }
    
    private static class HttpHelperAsyncClientHolder
    {
        private static HttpHelperAsyncClient instance;
        
        static {
            HttpHelperAsyncClientHolder.instance = new HttpHelperAsyncClient();
        }
    }
    
    private static class HttpHelperAsyncClient
    {
        private CloseableHttpAsyncClient httpClient;
        private PoolingNHttpClientConnectionManager cm;
        private DefaultConnectingIOReactor ioReactor;
        private static HttpHelperAsyncClient instance;
        private Logger logger;
        
        private HttpHelperAsyncClient() {
            this.logger = LoggerFactory.getLogger((Class)HttpHelperAsyncClient.class);
        }
        
        public static HttpHelperAsyncClient getInstance() {
            HttpHelperAsyncClient.instance = HttpHelperAsyncClientHolder.instance;
            try {
                HttpHelperAsyncClient.instance.init();
            }
            catch (Exception ex) {}
            return HttpHelperAsyncClient.instance;
        }
        
        private void init() throws Exception {
            throw new Error("\u65e0\u6cd5\u89e3\u6790\u7684\u7f16\u8bd1\u95ee\u9898\uff1a\n\t\u6784\u9020\u51fd\u6570 DefaultConnectingIOReactor\uff08\uff09\u672a\u5b9a\u4e49\n");
        }
        
        private Response execute(final HttpUriRequest request, final long timeoutmillis) throws Exception {
            throw new Error("\u65e0\u6cd5\u89e3\u6790\u7684\u7f16\u8bd1\u95ee\u9898\uff1a\n\t\u6ca1\u6709\u4e3a\u7c7b\u578b EntityUtils \u5b9a\u4e49\u65b9\u6cd5 consumeQuietly\uff08HttpEntity\uff09\n");
        }
    }
    
    public static class Headers extends HashMap<String, Object>
    {
        private static final long serialVersionUID = -6699349634305847872L;
    }
    
    public static class Response
    {
        private int code;
        private String content;
        private String error;
        
        public Response() {
            this.code = 400;
        }
        
        public String getError() {
            return this.error;
        }
        
        public void setError(final String error) {
            this.error = error;
        }
        
        public int getCode() {
            return this.code;
        }
        
        public void setCode(final int code) {
            this.code = code;
        }
        
        public String getContent() {
            return this.content;
        }
        
        public void setContent(final String content) {
            this.content = content;
        }
        
        public JSONObject getJsonContent() {
            return JSONObject.parseObject(this.content);
        }
        
        public JSONArray getJsonArrayContent() {
            return JSONArray.parseArray(this.content);
        }
        
        public JSONObject getJsonError() {
            return JSONObject.parseObject(this.error);
        }
        
        @Override
        public String toString() {
            return JSONObject.toJSON((Object)this).toString();
        }
    }
    
    private abstract static class HttpAsyncRequest
    {
        public Object setParameter(final JSONObject parameters) {
            return parameters;
        }
        
        public Object setParameter(final Map<String, Object> parameters) {
            final List<NameValuePair> params = new ArrayList<NameValuePair>();
            if (parameters == null || parameters.isEmpty()) {
                return params;
            }
            final Iterator<String> iterator = parameters.keySet().iterator();
            final StringBuffer paramsBuffer = new StringBuffer();
            while (iterator.hasNext()) {
                final String name = iterator.next();
                final String value = parameters.get(name).toString();
                params.add((NameValuePair)new BasicNameValuePair(name, value));
                if (iterator.hasNext()) {
                    paramsBuffer.append(name).append("=").append(value).append("&");
                }
                else {
                    paramsBuffer.append(name).append("=").append(value);
                }
            }
            return params;
        }
        
        public HttpEntity setHttpEntity(final Object params) throws Exception {
            final List<NameValuePair> nameValuePairs = (List<NameValuePair>)params;
            final UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity((List)nameValuePairs, "UTF-8");
            return (HttpEntity)urlEncodedFormEntity;
        }
        
        public HttpUriRequest setHttpUriRequest(final String url, final HttpEntity httpEntity) throws Exception {
            final HttpPost post = new HttpPost(url);
            post.setEntity(httpEntity);
            return (HttpUriRequest)post;
        }
        
        private void headers(final HttpUriRequest request, final Headers headers) {
            if (headers != null && !headers.isEmpty()) {
                final Set<String> set = ((HashMap<String, Object>)headers).keySet();
                for (final String name : set) {
                    final String value = new StringBuilder().append(((HashMap<String, Object>)headers).get(name)).toString();
                    request.addHeader(name, value);
                }
            }
        }
        
        public Response request(final String url, final Headers headers, final Map<String, Object> parameters, final long timeoutMillis) throws Exception {
            try {
                this.preOpera(url, timeoutMillis);
                final UrlParam urlParam = this.parseUrlParam(url, parameters);
                final Object contentTypeObject = (headers == null) ? null : ((HashMap<String, Object>)headers).get("Content-Type");
                final String contentType = (contentTypeObject == null) ? "application/x-www-form-urlencoded" : contentTypeObject.toString();
                Response response = null;
                if (contentType.contains("application/json")) {
                    response = this.applicationJSON(url, headers, JSONObject.parseObject(JSONObject.toJSON((Object)parameters).toString()), timeoutMillis);
                }
                else {
                    Object params = this.setParameter(urlParam.parameters);
                    params = ((params == null) ? new Object() : params);
                    final HttpEntity httpEntity = this.setHttpEntity(params);
                    final HttpUriRequest request = this.setHttpUriRequest(urlParam.url, httpEntity);
                    this.headers(request, headers);
                    response = HttpHelperAsyncClient.getInstance().execute(request, timeoutMillis);
                }
                return response;
            }
            catch (AssertionError e) {
                throw new Exception(e);
            }
        }
        
        public Response request(final String url, final Headers headers, final JSONObject parameters, final long timeoutMillis) throws Exception {
            try {
                this.preOpera(url, timeoutMillis);
                return this.applicationJSON(url, headers, parameters, timeoutMillis);
            }
            catch (AssertionError e) {
                throw new Exception(e);
            }
        }
        
        private void preOpera(final String url, long timeoutMillis) {
            Assert.assertFalse("url is null or empty!", StringUtils.isEmpty(url));
            TimeCalcUtil.setStartTimeUrl(System.currentTimeMillis(), url);
            if (0L == timeoutMillis) {
                timeoutMillis = 10000L;
            }
        }
        
        private UrlParam parseUrlParam(String url, Map<String, Object> params) {
            Assert.assertFalse("url is null or empty!", StringUtils.isEmpty(url));
            final int wenhao = url.indexOf("?");
            final boolean hasUrlParam = -1 != wenhao && -1 != url.indexOf("=");
            if (params == null && hasUrlParam) {
                params = new HashMap<String, Object>();
            }
            if (hasUrlParam) {
                final String srcUrl = url;
                url = url.substring(0, wenhao);
                final String keyValues = srcUrl.substring(wenhao + 1);
                if (StringUtils.isNotEmpty(keyValues)) {
                    final String[] keyValueArray = keyValues.split("&");
                    String[] array;
                    for (int length = (array = keyValueArray).length, i = 0; i < length; ++i) {
                        final String keyValue = array[i];
                        if (StringUtils.isNotEmpty(keyValue)) {
                            final String[] valuePair = keyValue.split("=");
                            if (2 == valuePair.length) {
                                final String name = valuePair[0];
                                final String value = valuePair[1];
                                if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(value)) {
                                    params.put(name, value);
                                }
                            }
                        }
                    }
                }
            }
            return new UrlParam(url, params);
        }
        
        private Response applicationJSON(final String url, final Headers headers, final JSONObject parameters, final long timeoutMillis) throws Exception {
            Object params = this.setParameter(parameters);
            params = ((params == null) ? new Object() : params);
            final HttpEntity httpEntity = this.setHttpEntity(params);
            final HttpUriRequest request = this.setHttpUriRequest(url, httpEntity);
            this.headers(request, headers);
            final Response response = HttpHelperAsyncClient.getInstance().execute(request, timeoutMillis);
            return response;
        }
    }
    
    private static class UrlParam implements Serializable
    {
        private static final long serialVersionUID = -5041417788475125724L;
        private String url;
        private Map<String, Object> parameters;
        
        public UrlParam(final String url, final Map<String, Object> parameters) {
            this.url = url;
            this.parameters = parameters;
        }
    }
    
    private static class Get extends HttpAsyncRequest
    {
        private Get() {
            super();
        }
        
        @Override
        public HttpUriRequest setHttpUriRequest(String url, final HttpEntity httpEntity) throws Exception {
            final String param = EntityUtils.toString(httpEntity);
            url = String.valueOf(url) + ((url.indexOf(63) != -1) ? "&" : "?") + param;
            final HttpGet get = new HttpGet(url);
            return (HttpUriRequest)get;
        }
    }
    
    private static class Post extends HttpAsyncRequest
    {
        private Post() {
            super();
        }
    }
    
    private static class PostJSON extends HttpAsyncRequest
    {
        private PostJSON() {
            super();
        }
        
        @Override
        public HttpEntity setHttpEntity(final Object params) throws Exception {
            final JSONObject jsonParams = JSONObject.parseObject(params.toString());
            final StringEntity stringEntity = new StringEntity(jsonParams.toString(), "UTF-8");
            stringEntity.setContentType("application/json");
            return (HttpEntity)stringEntity;
        }
    }
}
