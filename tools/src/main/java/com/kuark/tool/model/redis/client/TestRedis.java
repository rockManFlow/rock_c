package com.kuark.tool.model.redis.client;

import com.kuark.tool.model.util.HighRateOrderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.IOException;

/**
 * Created by caoqingyuan on 2018/1/12.
 */
public class TestRedis implements Runnable{
    private static final Logger logger= LoggerFactory.getLogger(TestRedis.class);
    //测试单节点redis的操作在多线程环境下，是否是线程安全的---单独一个操作安全，多个组合不一定安全
    public void test(){
        Jedis jedis =null;
        try {
            jedis = RedisPoolFactory.getInstance().getResource();
            String random= HighRateOrderUtil.getSerialId();
            logger.info("random:"+random);
            jedis.set("KKKK",random);
            Thread.sleep(1000);
            logger.info("jedis:"+jedis.get("KKKK"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        test();
    }

    public static void main(String[] args){
        for(int i=0;i<2;i++) {
            if(i==1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            new Thread(new TestRedis()).start();
        }
    }
}
