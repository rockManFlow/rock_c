package com.kuark.tool.model.connection;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * Created by caoqingyuan on 2016/11/10.
 */
public class HttpConnect {
    private static final Logger logger = Logger.getLogger(HttpConnect.class);

    public static byte[] httpConnection(String url, byte[] context, Integer timeOut) throws IOException {
        HttpURLConnection http = null;
        InputStream inputStream =null;
        try {
            URL urls = new URL(url);
            http = (HttpURLConnection) urls.openConnection();
            http.setRequestMethod("POST");
            http.setConnectTimeout(timeOut * 1000);
            http.setReadTimeout(timeOut * 1000);
            //打算使用URL的连接作为输入的连接对象
            http.setDoInput(true);
            http.setDoOutput(true);
            //是否使用连接使用缓存
            http.setUseCaches(false);
            //setRequestProperty主要是设置HttpURLConnection请求头里面
            // 的属性比如Cookie、User-Agent（浏览器类型）
            //http.setRequestProperty("","");
            http.connect();
            //指定编码方式
            OutputStream outputStream = http.getOutputStream();
            outputStream.write(context);
            outputStream.flush();
            outputStream.close();
            StringBuilder response = new StringBuilder();
            if (http.getResponseCode() == 200) {
                inputStream = http.getInputStream();
                byte[] bytes=IOUtils.toByteArray(inputStream);
                return bytes;
            } else {
                logger.info("http connect[" + http.getResponseCode() + "]");
                return null;
            }
        } finally {
            if (http != null)
                http.disconnect();
            IOUtils.closeQuietly(inputStream);
        }
    }

    public static String proxyConnection(Proxy proxy, byte[] context, String urlReq, Integer outTime) throws IOException {
        StringBuilder res = new StringBuilder();
        HttpURLConnection httpProxy = null;
        try {
            httpProxy = (HttpURLConnection) new URL(urlReq).openConnection(proxy);
            httpProxy.setRequestMethod("POST");
            httpProxy.setReadTimeout(outTime * 1000);
            httpProxy.setConnectTimeout(outTime * 1000);
            httpProxy.setDoOutput(true);
            httpProxy.setDoInput(true);
            httpProxy.setUseCaches(false);
            httpProxy.connect();
            OutputStream stream = httpProxy.getOutputStream();
            stream.write(context);
            stream.flush();
            stream.close();
            byte[] response = new byte[1024];
            if (httpProxy.getResponseCode() == 200) {
                InputStream inputStream = httpProxy.getInputStream();
                while (inputStream.read(response) != -1) {
                    res.append(response);
                }
            } else {
                logger.info("connection error [" + httpProxy.getResponseCode() + "]");
            }
        } finally {
            if (httpProxy != null)
                httpProxy.disconnect();
        }
        return res.toString();
    }

    public static void proxy() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("ip", 8080));
    }
}
