package com.kuark.tool.advance.advance20191228;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author rock
 * @detail RestTemplate建立连接池
 * @date 2022/3/8 17:39
 */
public class RestTemplateUtil {

    public RestTemplate create(){
        return new RestTemplate(httpRequestFactory());
    }

    public ClientHttpRequestFactory httpRequestFactory(){
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    public HttpClient httpClient(){
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http" , PlainConnectionSocketFactory.getSocketFactory())
                .register( "https" , SSLConnectionSocketFactory.getSocketFactory()).build();
        //复用一个client连接，相当于连接池的作用--MultiThreadedHttpConnectionManager是3.x版本中的类似于线程池得到管理器
//        MultiThreadedHttpConnectionManager manager=new MultiThreadedHttpConnectionManager();
        PoolingHttpClientConnectionManager connectionManager =new PoolingHttpClientConnectionManager(registry);
        // 设置最大连接池的数量
        connectionManager.setMaxTotal(1000);
        // 每个主机的最大并发量 是指域名。--对MaxTotal的细化
        connectionManager.setDefaultMaxPerRoute(300);

        RequestConfig requestConfig =RequestConfig.custom()
                // 数据返回超时时间
                .setSocketTimeout(10*60*1000)
                // 连接超时时间
                .setConnectTimeout(10*60*1000)
                // 从连接池中获取连接的超时时间
                .setConnectionRequestTimeout(1*10*1000)
                .build();

        CloseableHttpClient closeableHttpClient=HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .disableAutomaticRetries()
                .build();

        return closeableHttpClient;
    }
}
