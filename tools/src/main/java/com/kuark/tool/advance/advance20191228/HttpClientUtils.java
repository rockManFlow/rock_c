package com.kuark.tool.advance.advance20191228;

import com.kuark.tool.advance.advance20191115.HttpConfigParam;
import lombok.extern.log4j.Log4j;
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
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
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

import javax.net.ssl.*;
import java.io.IOException;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author rock
 * @detail HttpClient用法优化
 * @date 2019/11/1 17:15
 */
@Log4j
public class HttpClientUtils {
    /**
     * 单位：s
     */
    private static final int connectTimeout=60;
    private static final int socketTimeout=120;
    private static final int requestTimeout=60;

    private static HttpClientContext context = HttpClientContext.create();

    private static CloseableHttpClient httpClient = HttpClients.custom()
            .setConnectionManager(getConnectionManager())
            .setDefaultRequestConfig(getRequestConfig())
            //最好吧自动重试去掉
//            .disableAutomaticRetries()
            .build();

    /**
     * 获取请求配置
     * @return
     */
    private static RequestConfig getRequestConfig() {
        return RequestConfig.custom().setConnectTimeout(connectTimeout*1000).setSocketTimeout(socketTimeout*1000)
                .setConnectionRequestTimeout(requestTimeout*1000).setCookieSpec(CookieSpecs.STANDARD_STRICT).
                        setExpectContinueEnabled(true).
                        setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST)).
                        setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
    }

    //这种方式实现，可以防止由于JDK对于一些加密算法的禁用，导致一些基本算法不可访问
    //这种的就是不使用JDK的管理器，使用自己实现的
    private static class TrustAnyTrustManager extends X509ExtendedTrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s, Socket socket) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s, Socket socket) throws CertificateException {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s, SSLEngine sslEngine) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s, SSLEngine sslEngine) throws CertificateException {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /**
     * 获取连接池管理器
     * @return
     */
    private static PoolingHttpClientConnectionManager getConnectionManager() {
        try {
            TrustManager manager = new TrustAnyTrustManager();
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{manager}, null);
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE).register("https", socketFactory).build());
            //连接池的大小
            connectionManager.setMaxTotal(200);
            //每个host最大连接数
            connectionManager.setDefaultMaxPerRoute(100);
            return connectionManager;
        } catch (NoSuchAlgorithmException e) {
            log.error("ConnectionManager init Exception", e);
        } catch (KeyManagementException e) {
            log.error("ConnectionManager init Exception", e);
        }
        return null;
    }

    /**
     * https get
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String doHttpsGet(String url, String basicAuth, String xOpayTranid) throws IOException {
        log.info("Https Get Req Param:{}"+url);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
        if (StringUtils.isNotEmpty(basicAuth)) {
            httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + basicAuth);
        }
        if (StringUtils.isNotEmpty(xOpayTranid)) {
            httpGet.setHeader("X-Opay-Tranid", xOpayTranid);
        }
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet, context);
            int code = response.getStatusLine().getStatusCode();
            String reasonPhrase = response.getStatusLine().getReasonPhrase();
            if (code == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            }
            throw new RuntimeException("下游响应码非[200]，响应码为[" + code + ":" + reasonPhrase + "]");
        }finally {
            closeStream(response);
        }
    }

    /**
     * https post
     *
     * @param url
     * @param paramsXml
     * @return
     */
    public static String doHttpsPost(String url, String paramsXml, ContentType contentType) throws IOException {
        return doHttpsPost(url, paramsXml, null, null, contentType);
    }

    /**
     * https post
     *
     * @param url
     * @param paramsXml
     * @return
     */
    public static String doHttpsPost(String url, String paramsXml, String xOpayTranid, ContentType contentType) throws IOException {
        return doHttpsPost(url, paramsXml, null, xOpayTranid, contentType);
    }

    /**
     * https post
     *
     * @param url
     * @param paramsXml
     * @return
     */
    public static String doHttpsPost(String url, String paramsXml, String basicAuth) throws IOException {
        return doHttpsPost(url, paramsXml, basicAuth, null, ContentType.APPLICATION_FORM_URLENCODED);
    }

    public static String doHttpsPost(String url, String paramsXml, String basicAuth, String xOpayTranid) throws IOException {
        return doHttpsPost(url, paramsXml, basicAuth, xOpayTranid, ContentType.APPLICATION_FORM_URLENCODED);
    }


    /**
     * base https post
     *
     * @param url
     * @param paramsXml
     * @return
     * @throws IOException
     */
    public static String doHttpsPost(String url, String paramsXml, String basicAuth, String xOpayTranid, ContentType contentType) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(HttpHeaders.ACCEPT, contentType.getMimeType());
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, contentType.getMimeType());
        if (StringUtils.isNotEmpty(basicAuth)) {
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + basicAuth);
        }
        if (StringUtils.isNotEmpty(xOpayTranid)) {
            httpPost.setHeader("X-Opay-Tranid", xOpayTranid);
        }
        StringEntity entity = new StringEntity(paramsXml, ContentType.create(contentType.getMimeType(), "UTF-8"));
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost, context);
            log.info("HttpsPost response ={}"+ response);
            int code = response.getStatusLine().getStatusCode();
            String reasonPhrase = response.getStatusLine().getReasonPhrase();
            if (code == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            }
            throw new RuntimeException("下游响应码非[200]，响应码为[" + code + ":" + reasonPhrase + "]");
        }finally {
            closeStream(response);
        }
    }

    /**
     * 关闭流
     * @param response
     * @throws IOException
     */
    private static void closeStream(CloseableHttpResponse response) throws IOException {
        if (response != null){
            //清除流内容--加上这个就可以保持长连接，为什么不加不会
            /**
             * 后来仔细读http连接的原理才得知，当一个连接建立，响应数据时，会封装CloseableHttpResponse这个对象里面，
             * 其中的Entity对象就是包含着响应体的数据，我们需要用流去获取。如果你不去获取，那么这个数据就会存在于这个对象中，
             * 连接池就会认为，这个通道里有未处理的数据，然后它不会去复用这个通道，而是选择重建一个通道。
             * 这就完美解释了为什么压测时，对方返回null时，响应速度特别快，而携带返回数据时，响应速度特别慢了。
             */
            EntityUtils.consume(response.getEntity());
            //关闭流
            /**
             * HttpClient4.5.2 连接池原理及注意事项
             * 连接池中连接都是在发起请求的时候建立，并且都是长连接
             *
             * HaoMaiClient.java中的in.close();作用就是将用完的连接释放，下次请求可以复用，这里特别注意的是，如果不使用in.close();
             * 而仅仅使用response.close();结果就是连接会被关闭，并且不能被复用，这样就失去了采用连接池的意义。
             */
//            response.close();
        }
    }


    /**
     * 初始化请求配置
     * @return
     */
    public static HttpRequestBase initHttpClientConfig(HttpRequestBase httpRequestBase, HttpConfigParam configParam){
        if(Objects.isNull(configParam)){
            return httpRequestBase;
        }
        RequestConfig.Builder builder = RequestConfig.custom();
        Integer requestTimeout=!Objects.isNull(configParam.getRequestTimeout())?configParam.getRequestTimeout():HttpClientUtils.requestTimeout;
        builder.setConnectionRequestTimeout(requestTimeout*1000);

        Integer socketTimeout=!Objects.isNull(configParam.getSocketTimeout())?configParam.getSocketTimeout():HttpClientUtils.socketTimeout;
        builder.setSocketTimeout(socketTimeout*1000);

        Integer connectTimeout=!Objects.isNull(configParam.getConnectTimeout())?configParam.getConnectTimeout():HttpClientUtils.connectTimeout;
        builder.setConnectTimeout(connectTimeout*1000);

        RequestConfig requestConfig=builder.setCookieSpec(CookieSpecs.STANDARD_STRICT).
                setExpectContinueEnabled(true).
                setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST)).
                setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();


        httpRequestBase.setConfig(requestConfig);
        return httpRequestBase;
    }

    /**
     * 动态配置连接请求时间
     * @param url
     * @param configParam
     */
    public static void doGetInitConnection(String url,HttpConfigParam configParam){
        HttpGet httpGet = (HttpGet)initHttpClientConfig(new HttpGet(url),configParam);
        //.....
    }

}
