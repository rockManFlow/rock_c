package com.kuark.tool.advance.advance20191120;

import com.alibaba.fastjson.JSONObject;
import com.kuark.tool.base.http.HttpClientUtils;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author rock
 * @detail
 * @date 2019/11/13 15:34
 */
@Log4j
public class LinkNumMainTest {
    private static final String url="http://127.0.0.1:8989/rockBlog/link.htm";

    private static final String limitUrl="http://127.0.0.1:8989/rockBlog/limiter.htm";

    private static final String queryUrl="http://127.0.0.1:8080/tv-bankfront/api/channel/payu/VerifyTopupForRest";

    private static final String checkUrl="http://127.0.0.1:8999/interswitch-bankfront/check";

    private static final String queryAccountUrl="http://127.0.0.1:8080/capricorn-bankfront/api/channel/capricornVerifyAccount/exchange";

    public static void main(String[] args) throws InterruptedException, IOException {

        log.info("start main");
//        String postJson = HttpClientUtils.postJson(queryAccountUrl, "{\"customerID\":\"4390130356\",\"serviceID\":\"capricorn_ikeja_postpaid\"}");
//        System.out.println(postJson);
        ExecutorService threadPool = Executors.newCachedThreadPool();
//        for(int i=0;i<100;i++){
//            threadPool.submit(new LinkNumMainTest().new LinkTask(i));
//        }

        for(int i=0;i<4;i++){
            TimeUnit.MILLISECONDS.sleep(200);
            threadPool.submit(new LinkNumMainTest().new LinkUserTask(i));
        }
        log.info("end main");
    }

    class LinkUserTask implements Runnable{
        private int num;
        public LinkUserTask(int num){
            this.num=num;
        }
        @Override
        public void run() {
            try {
                String limitUserUrl="http://127.0.0.1:8181/limit/rate?id='1234567890'";
                String postJson = HttpClientUtils.doHttpsGet(limitUserUrl);
                log.info("result="+postJson);
            } catch (IOException e) {
                log.error("",e);
            }
        }
    }

    class LinkTask implements Runnable{
        private int num;
        public LinkTask(int num){
            this.num=num;
        }
        @Override
        public void run() {
            LinkReq req = LinkReq.builder().reqNo(UUID.randomUUID().toString()).value(num + "TOOL").reqMsg("MSG" + num).build();
            try {
                log.info("req="+JSONObject.toJSONString(req));
                String postJson = HttpClientUtils.postJson(limitUrl, JSONObject.toJSONString(req));
                log.info("result="+postJson);
            } catch (IOException e) {
                log.error("",e);
            }
        }
    }

    class QueryTask implements Runnable{
        private int num;
        public QueryTask(int num){
            this.num=num;
        }
        @Override
        public void run() {
            String uid = UUID.randomUUID().toString();
            QueryReq req=QueryReq.builder().orderID(uid).transactionID(uid).build();
            try {
                log.info("req="+JSONObject.toJSONString(req));
                String postJson = HttpClientUtils.postJson(queryUrl, JSONObject.toJSONString(req));
                log.info("result="+postJson);
            } catch (IOException e) {
                log.error("",e);
            }
        }
    }

    public static class CheckTask implements Runnable{
        private int num;
        public CheckTask(int num){
            this.num=num;
        }
        @Override
        public void run() {
            try {
                log.info("Req="+num);
                String postJson = HttpClientUtils.postJson(checkUrl, "");
                log.info("result="+postJson);
            } catch (IOException e) {
                log.error("",e);
            }
        }
    }
}
