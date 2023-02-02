package com.kuark.tool.model.redis.examples.example1;

import com.kuark.tool.model.redis.client.RedisPoolFactory;
import com.kuark.tool.model.util.DataUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by caoqingyuan on 2018/1/10.
 */
public class CountMain implements Runnable {
    //用于统计每分钟的次数的结果---也可以使用redis来保存永久结果，变化只是修改对应的值
    private static final ConcurrentHashMap<String,Long> countMap=new ConcurrentHashMap();
    //redis中怎么来组织这个缓存的数据？？
    public static void main(String[] args) throws InterruptedException {
//        for(int i=0;i<2;i++) {
//            new CountMain().run();
//        }
        for (int i = 0; i < 100; i++) {
            new Thread(new CountMain()).start();
            Thread.sleep(200);
        }
    }

    /**
     * TODO 统计某个接口或者功能每分钟调用的次数---可以使用redis实现
     * @param keyName
     * @param timeOutSecond 超时时间 s
     * @return 当前的一个值
     */
    public Long safeAdd(String keyName, Integer timeOutSecond) {
        JedisPool instance = null;
        Jedis jedis = null;
        try {
            instance = RedisPoolFactory.getInstance();
            jedis = instance.getResource();
            //这部分必须保证是线程安全的
            synchronized(CountMain.class) {
                if (timeOutSecond != null && timeOutSecond > 0) {
                    if (jedis.ttl(keyName)==-1||jedis.ttl(keyName)==-2) {
                        jedis.expire(keyName, timeOutSecond);
                        showResultAndReleace();
                        countMap.put(DataUtil.getCournt(), 0L);
                    }
                }
                return safeAdd(jedis, keyName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        } finally {
            jedis.close();
        }
    }

    private Long safeAdd(Jedis jedis,String keyName) {
        Long result=jedis.incr(keyName);//这个值是累加的，经测试是安全的
        System.out.println(result);
        if(countMap.isEmpty()){
            countMap.put(DataUtil.getCournt(), 0L);
        }
        countMap.put(countMap.keySet().iterator().next(),result);
        return result;
    }

    //用于获取最终需要的数据
    private void showResultAndReleace(){
        if(countMap.isEmpty()){
            return;
        }
        StringBuffer key = new StringBuffer(countMap.keySet().iterator().next());
        System.out.println(key.toString()+"|"+countMap.get(key.toString()));
        countMap.clear();
    }

    /**
     * TODO 使用redis实现安全计数功能
     */
    @Test
    public void add() {
        JedisPool instance = null;
        Jedis jedis = null;
        try {
            instance = RedisPoolFactory.getInstance();
            jedis = instance.getResource();
//            Long aLong = jedis.incr("key");//这个值是累加的，经测试是安全的
//            Long key = jedis.expire("key", 10);
//            System.out.println("before:" + aLong + "|key:" + key);

//            Long kkk = jedis.incrBy("kkk", 1);//设定每次增加步数和incr
//            System.out.println(kkk);

            Long countKey221 = jedis.ttl("countKey221");
            System.out.println(countKey221);

//            int anInt = Integer.parseInt(jedis.get("kkk"));//get是线程不安全的
//            System.out.println("after:" + anInt);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            instance.returnResource(jedis);
        }
    }

    @Override
    public void run() {
        safeAdd("countKey221",1);
    }
}
