package com.kuark.tool.base.http;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import javax.net.ssl.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;

/**
 * Created by caoqingyuan on 2016/9/1.
 */
public class URLConnection {
    public static byte[] postBinResource(String urlStr, String reuestStr, String encode,int timeOutInSeconds) throws Exception {
        HttpURLConnection http = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            URL url = new URL(urlStr);
            http = (HttpURLConnection)url.openConnection();
            if(urlStr.startsWith("https")){
                initHttps();
                HttpsURLConnection https = (HttpsURLConnection) http;
                //表示相信所有的证书
                https.setHostnameVerifier(DO_NOT_VERIFY);
            }
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setUseCaches(false);
            http.setConnectTimeout(timeOutInSeconds*1000);//设置连接超时
            //如果在建立连接之前超时期满，则会引发一个 java.net.SocketTimeoutException。超时时间为零表示无穷大超时。
            http.setReadTimeout(timeOutInSeconds*1000);//设置读取超时
            //如果在数据可读取之前超时期满，则会引发一个 java.net.SocketTimeoutException。超时时间为零表示无穷大超时。
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
            http.connect();

            outputStream = http.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(outputStream, encode);
            osw.write(reuestStr);
            osw.flush();
            osw.close();

            if (http.getResponseCode() == 200) {
                inputStream = http.getInputStream();
                byte[] returnValue1 = IOUtils.toByteArray(inputStream);
                return returnValue1;
            }else{
                System.out.println("http read ["+http.getResponseCode()+"]");
//                throw new RuntimeException("http read ["+http.getResponseCode()+"]");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (http != null) http.disconnect();
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
        return null;
    }
    private static void initHttps() {
        myX509TrustManager xtm = new myX509TrustManager();
        myHostnameVerifier hnv = new myHostnameVerifier();
        SSLContext sslContext = null;
        try {
            //返回一个实现SSL协议的对象
            sslContext = SSLContext.getInstance("SSL"); //或SSL
            X509TrustManager[] xtmArray = new X509TrustManager[] {xtm};
            sslContext.init(null, xtmArray, new java.security.SecureRandom());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        if (sslContext != null) {
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        }
        HttpsURLConnection.setDefaultHostnameVerifier(hnv);
    }
    static class myX509TrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
    static class myHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            //System.out.println("Warning: URL Host: " + hostname + " vs. " + session.getPeerHost());
            return true;
        }
    }
    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };


    @Test
    public void testHttps(){
        String com="这只是一个测试只用，呵呵，啥jsjdduonsa 此时此刻哈加加速度";
        String url="https://localhost:8443/mod/httpController/processRequest.do?com="+com;
        try {
            postBinResource(url,"","UTF-8",30);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
