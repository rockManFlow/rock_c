package com.kuark.tool.base.http;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/**
 * @author rock
 * @detail
 * @date 2019/11/1 17:15
 */
@Log4j
public class HttpClientUtils {
    private static final String X_WWW_FORM_URLENCODED ="application/x-www-form-urlencoded";

    /**
     * 请求json格式的参数
     * @param url
     * @return
     */
    public static String postJson(String url, String paramsJson) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        log.info("httpclient="+httpclient);
        HttpPost httpPost = new HttpPost(url);
//        httpPost.setHeader(HttpHeaders.ACCEPT, X_WWW_FORM_URLENCODED);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        StringEntity entity = new StringEntity(paramsJson, ContentType.create(ContentType.APPLICATION_JSON.getMimeType(), "UTF-8"));
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            log.info("httpclient response="+response);
            int code = response.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                log.info("httpclient response jsonString="+jsonString);
                return jsonString;
            }
            if (response != null) {
                response.close();
            }
        }finally {
            if(httpclient!=null){
                httpclient.close();
            }
        }
        return null;
    }



    public static String postXml(String url, String paramsXml,String basicAuth) throws IOException {
        return postXml(url,paramsXml,basicAuth,null);
    }
    /**
     * 请求json格式的参数
     * @param url
     * @param paramsXml
     * @param basicAuth 请求头中加基本验证
     * @return
     */
    public static String postXml(String url, String paramsXml,String basicAuth,String xOpayTranid) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(HttpHeaders.ACCEPT, X_WWW_FORM_URLENCODED);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, X_WWW_FORM_URLENCODED);
        if(StringUtils.isNotEmpty(basicAuth)){
            httpPost.setHeader(HttpHeaders.AUTHORIZATION,"Basic "+basicAuth);
        }
        if(StringUtils.isNotEmpty(xOpayTranid)){
            httpPost.setHeader("X-Opay-Tranid",xOpayTranid);
        }
        StringEntity entity = new StringEntity(paramsXml, ContentType.create(ContentType.APPLICATION_FORM_URLENCODED.getMimeType(), "UTF-8"));
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            log.info("httpclient response="+response);
            int code = response.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                log.info("httpclient response jsonString="+jsonString);
                return jsonString;
            }
            if (response != null) {
                response.close();
            }
        }finally {
            if(httpclient!=null){
                httpclient.close();
            }
        }
        return null;
    }

    private static HttpClientContext context = HttpClientContext.create();
    private static RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(120000)
            .setConnectionRequestTimeout(60000).setCookieSpec(CookieSpecs.STANDARD_STRICT).
                    setExpectContinueEnabled(true).
                    setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST)).
                    setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();

    private static SSLConnectionSocketFactory socketFactory;
    private static TrustManager manager = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
        }
        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
        }
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };

    private static void enableSSL() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{manager}, null);
            socketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        } catch (NoSuchAlgorithmException e) {
            log.error("SSL init Exception",e);
        } catch (KeyManagementException e) {
            log.error("SSL init Exception",e);
        }
    }

    public static String doHttpsGet(String url) throws IOException {
        return doHttpsGet(url,null,null);
    }
    /**
     * https get
     * @param url
     * @return
     * @throws IOException
     */
    public static String doHttpsGet(String url,String basicAuth,String xOpayTranid) throws IOException {
        enableSSL();
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE).register("https", socketFactory).build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig).disableAutomaticRetries().build();
        log.info("Https Get Req Param="+url);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HttpHeaders.ACCEPT, X_WWW_FORM_URLENCODED);
        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, X_WWW_FORM_URLENCODED);
        if(StringUtils.isNotEmpty(basicAuth)){
            httpGet.setHeader(HttpHeaders.AUTHORIZATION,"Basic "+basicAuth);
        }
        if(StringUtils.isNotEmpty(xOpayTranid)){
            httpGet.setHeader("X-Opay-Tranid",xOpayTranid);
        }
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet, context);
            int code = response.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            }
            if (response != null) {
                response.close();
            }
        }finally {
            if(httpClient!=null){
                 httpClient.close();
            }
        }
        return null;
    }

    /**
     * https post
     * @param url
     * @param paramsXml
     * @return
     */
    public static String doHttpsPost(String url, String paramsXml) throws IOException {
        return doHttpsPost(url, paramsXml,null,null);
    }

    /**
     * https post
     * @param url
     * @param paramsXml
     * @return
     */
    public static String doHttpsPost(String url, String paramsXml,String basicAuth) throws IOException {
        return doHttpsPost(url, paramsXml,basicAuth,null);
    }
    /**
     * https post
     * @param url
     * @param paramsXml
     * @return
     * @throws IOException
     */
    public static String doHttpsPost(String url, String paramsXml,String basicAuth,String xOpayTranid) throws IOException {
        enableSSL();
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE).register("https", socketFactory).build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig).disableAutomaticRetries().build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(HttpHeaders.ACCEPT, X_WWW_FORM_URLENCODED);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, X_WWW_FORM_URLENCODED);
        if(StringUtils.isNotEmpty(basicAuth)){
            httpPost.setHeader(HttpHeaders.AUTHORIZATION,"Basic "+basicAuth);
        }
        if(StringUtils.isNotEmpty(xOpayTranid)){
            httpPost.setHeader("X-Opay-Tranid",xOpayTranid);
        }
        StringEntity entity = new StringEntity(paramsXml, ContentType.create(ContentType.APPLICATION_FORM_URLENCODED.getMimeType(), "UTF-8"));
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost, context);
            log.info("HttpsPost response ="+response);
            int code = response.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            }
            if (response != null) {
                response.close();
            }
        }finally {
            if(httpClient!=null){
                httpClient.close();
            }
        }
        return null;
    }
}
