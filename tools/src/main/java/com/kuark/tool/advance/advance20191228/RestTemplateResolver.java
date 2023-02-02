package com.kuark.tool.advance.advance20191228;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


/**
 * @author rock
 * @detail
 * @date 2020/4/22 17:15
 */

/**
 * RestTemplate是Spring提供的用于访问Rest服务的客户端，
 * RestTemplate提供了多种便捷访问远程Http服务的方法,能够大大提高客户端的编写效率。
 * 请求方式  方法
 * delete   delete
 * get      getForObject+getForEntity
 * head     headForHeaders
 * options  optionForAllow
 * post     postForLocal+postForObject
 * put      put
 * any      exchange+execute
 *
 * 是Spring用于同步client端的核心类，简化了与http服务的通信，并满足RestFul原则，程序代码可以给它提供URL，并提取结果。
 * 默认情况下，RestTemplate默认依赖jdk的HTTP连接工具。
 * 当然你也可以 通过setRequestFactory属性切换到不同的HTTP源，比如Apache HttpComponents、Netty和OkHttp。
 *
 * 在内部，RestTemplate默认使用HttpMessageConverter实例将HTTP消息转换成POJO或者从POJO转换成HTTP消息。
 * 默认情况下会注册主mime类型的转换器，但也可以通过setMessageConverters注册其他的转换器。
 *
 * 在内部，RestTemplate默认使用SimpleClientHttpRequestFactory和DefaultResponseErrorHandler来分别处理HTTP的创建和错误，
 * 但也可以通过setRequestFactory和setErrorHandler来覆盖。
 */
public class RestTemplateResolver {
    public static void main(String[] args) {
//        RestTemplate restTemplate=new RestTemplate();
//        restTemplate.exchange("", HttpMethod.GET,new BasicHttpEntity(),responseType);
        //需要强调的是，execute()方法是以上所有方法的底层调用。
        getClient();
    }

    public static String  getClient(){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setOutputStreaming(false);
//        requestFactory.setReadTimeout(10*60*1000);
//        requestFactory.setConnectTimeout(10*60*1000);
        RestTemplate restTemplate=new RestTemplate(requestFactory);
        //仅get
        String response=restTemplate.getForObject("http://127.0.0.1:8000/base/send1",String.class);
        System.out.println("response="+response);
        return response;
    }
}
