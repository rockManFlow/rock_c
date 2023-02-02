package com.kuark.tool.model.redis.examples.example2;

import com.kuark.tool.model.redis.client.RedisPoolFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;

/**
 * Created by caoqingyuan on 2018/1/12.
 */
public class LockTest {
    public static void main(String[] args){
        try {
            JedisPool pool = RedisPoolFactory.getInstance();
            Jedis jedis = pool.getResource();
            for(int i=0;i<3;i++) {
                //NX：只有当redis中不存在指定的key时才会把这个唯一值放到redis中
                //这样，为了防止当A客户端执行完成后去释放锁由于超时，redis把其对应的锁给释放了，
                //这时A再执行释放锁会把B加的锁给释放掉，导致B不安全
                //这种的可以使A只是放其对应的唯一值的共享锁
                //TODO 这种是较好的获取锁和释放锁----如果redis不宕机，这种获取方法的总是安全的
                String status = jedis.set("shareKey", "所有客户端唯一值", "NX", "EX", 30);
                //加锁成功会返回OK，失败会返回null
                System.out.println(status);
            }
            jedis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }
    }
}
