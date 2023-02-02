package com.kuark.tool.base.http;

import org.apache.commons.io.IOUtils;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by lixiaoming on 15/9/2.
 */
public class HttpURLConnection {

    public static byte[] postBinResourceTest(String urlStr, String reuestStr, String encode,int timeOutInSeconds) throws Exception {
        java.net.HttpURLConnection http = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            URL url = new URL(urlStr);
            http = (java.net.HttpURLConnection)url.openConnection();
            if(urlStr.startsWith("https")){
                initHttps();
                HttpsURLConnection https = (HttpsURLConnection) http;
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
            http.setRequestProperty("Content-Type", "application/json; charset=" + encode);
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
                throw new RuntimeException("http read ["+http.getResponseCode()+"]");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (http != null) http.disconnect();
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
    }

    public static byte[] postBinResource(String urlStr, String reuestStr, String encode,int timeOutInSeconds) throws Exception {
        java.net.HttpURLConnection http = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            URL url = new URL(urlStr);
            http = (java.net.HttpURLConnection)url.openConnection();
            if(urlStr.startsWith("https")){
                initHttps();
                HttpsURLConnection https = (HttpsURLConnection) http;
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
            http.setRequestProperty("Content-Type", "application/json; charset=" + encode);
            http.connect();

            outputStream = http.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(outputStream, encode);
            osw.write(URLEncoder.encode(reuestStr, encode));
            osw.flush();
            osw.close();

            if (http.getResponseCode() == 200) {
                inputStream = http.getInputStream();
                byte[] returnValue1 = IOUtils.toByteArray(inputStream);
                return returnValue1;
            }else{
                throw new RuntimeException("http read ["+http.getResponseCode()+"]");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (http != null) http.disconnect();
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
    }

    public static byte[] postBinResource(String urlStr, Map<String, Object> reqMap, String encode, int timeOutInSeconds) throws Exception {
        java.net.HttpURLConnection http = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            URL url = new URL(urlStr);
            http = (java.net.HttpURLConnection)url.openConnection();
            if(urlStr.startsWith("https")){
                initHttps();
                HttpsURLConnection https = (HttpsURLConnection) http;
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
            http.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
            http.setRequestProperty("User-Agent", "top-sdk-java");
//            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset="+encode);
            http.setRequestProperty("Content-Type", "application/json;charset="+encode);
            http.connect();

            Map<String, String> params = new HashMap<String, String>();
            for (Map.Entry<String, Object> en : reqMap.entrySet()) {
                params.put(en.getKey(), (String) en.getValue());
            }

            String query = buildQuery(params, "UTF-8");
//            byte[] content = {};
//            if (query != null) {
//                content = query.getBytes("utf-8");
//            }
//            List lst = request.keyList();
//            StringBuffer params = new StringBuffer();
//            for(Object obj:lst){
//                String key = (String)obj;
//                String value = ""+request.get(key);
//                if(params.length() > 0) {
//                    params.append("&");
//                }
//                params.append(URLEncoder.encode(key, encode));
//                params.append("=");
//                params.append(URLEncoder.encode(value, encode));
//            }

            outputStream = http.getOutputStream();
            outputStream.write(query.getBytes());
            outputStream.flush();
            outputStream.close();

            if (http.getResponseCode() == 200) {
                inputStream = http.getInputStream();
                byte[] returnValue1 = IOUtils.toByteArray(inputStream);
                return returnValue1;
            }else{
                throw new RuntimeException("http read ["+http.getResponseCode()+"]");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (http != null) http.disconnect();
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
    }

    public static String buildQuery(Map<String, String> params, String charset) throws IOException {
        if (params == null || params.isEmpty()) {
            return null;
        }

        StringBuilder query = new StringBuilder();
        Set<Map.Entry<String, String>> entries = params.entrySet();
        boolean hasParam = false;

        for (Map.Entry<String, String> entry : entries) {
            String name = entry.getKey();
            String value = entry.getValue();
            // 忽略参数名或参数值为空的参数
                if (hasParam) {
                    query.append("&");
                } else {
                    hasParam = true;
                }

                query.append(name).append("=").append(URLEncoder.encode(value, charset));
        }

        return query.toString();
    }

    //所谓的HTTPS只是给通信信息进行了加密传输，而这个加密就是使用的http中的一些加密协议进行加密，是的传输更有安全性
    private static void initHttps() {
         myX509TrustManager xtm = new myX509TrustManager();

         myHostnameVerifier hnv = new myHostnameVerifier();
        SSLContext sslContext = null;
        try {
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

    /**
     * 重写一个方法
     * @author Administrator
     *
     */
    static class myHostnameVerifier implements HostnameVerifier {

        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }


    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {

        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };


    public static byte[] postXmlBinResource(String urlStr, String reuestStr, String encode,int timeOutInSeconds) throws Exception {
        java.net.HttpURLConnection http = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            URL url = new URL(urlStr);
            http = (java.net.HttpURLConnection)url.openConnection();
            if(urlStr.startsWith("https")){
                initHttps();
                HttpsURLConnection https = (HttpsURLConnection) http;
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
            http.setRequestProperty("Content-Type", "text/xml");
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
                throw new RuntimeException("http read ["+http.getResponseCode()+"]");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (http != null) http.disconnect();
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
    }
}
