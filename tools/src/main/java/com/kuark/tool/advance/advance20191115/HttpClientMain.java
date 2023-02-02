package com.kuark.tool.advance.advance20191115;

import com.kuark.tool.advance.advance20191228.HttpClientUtils;
import lombok.extern.log4j.Log4j;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author rock
 * @detail
 * @date 2019/11/27 18:31
 */
@Log4j
public class HttpClientMain {
    private static final String selectUrl="http://127.0.0.1:8099/thread/task";
    public static void main(String[] args) throws IOException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for(int i=0;i<50;i++){
            threadPool.submit(new HttpClientMain().new QueryTask(i));
        }
//        String postJson = HttpClientUtils.doHttpsPost("http://127.0.0.1:8099/thread/task","{'no':'11'}",ContentType.APPLICATION_JSON);
//        System.out.println(postJson);
//        String postJsong = HttpClientUtils.doHttpsGet("http://127.0.0.1:8099/docker/entry?no=1234",null,null);
//        System.out.println(postJsong);
    }

    class QueryTask implements Runnable{
        private int num;
        public QueryTask(int num){
            this.num=num;
        }
        @Override
        public void run() {
            String uid = UUID.randomUUID().toString();
            try {
                log.info("num="+num+"|uid="+ uid);
                long startime = System.currentTimeMillis();
                String postJson = HttpClientUtils.doHttpsPost(selectUrl,uid, ContentType.APPLICATION_JSON);
                long endTime = System.currentTimeMillis();
                log.info("num="+num+"uid="+uid+"|result="+postJson+"|costime="+(endTime-startime)+"ms");
            } catch (IOException e) {
                log.error("",e);
            }
        }
    }
}
