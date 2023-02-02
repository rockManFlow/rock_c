package com.kuark.tool.advance.advance20191228;

import com.alibaba.fastjson.JSON;
import com.kuark.tool.base.http.HttpURLConnection;
import lombok.Data;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author rock
 * @detail
 * @date 2020/4/23 14:55
 */
public class HttpClientResolver {
    //httpclient上层是使用的HTTP协议，应用层使用的肯定是TCP/ip协议
    //socket是java底层的通信方式.使用的协议是tcp/ip.所以Java程序实现的通信工具
    //都是对socket的上层封装，及应用层封装

    //HTTP开发是用apache的HttpClient开发，代码复杂，还得操心资源回收等。代码很复杂，冗余代码多
    public static void main(String[] args) throws IOException {
        HttpClient httpClient=new HttpClient();
        PostMethod method=new PostMethod();
        method.addRequestHeader("Content-Type","application/json");
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(100);
        for(int i=0;i<100;i++){
            int finalI = i;
            pool.submit(()->{
                ReqB r=new ReqB();
                r.setRedTag("TEST");
                r.setUserId("88888"+ finalI);
                try {
                    String req=JSON.toJSONString(r);
                    byte[] bytes = HttpURLConnection.postBinResourceTest("http://127.0.0.1:8200/get/red",req, "utf-8", 600);
                    String resp = new String(bytes,"utf-8");
                    System.out.println("resp:"+resp+"|req:"+req);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Data
    static
    class ReqB implements Serializable {
        private String redTag;
        private String userId;
    }
}
