package com.kuark.tool.model.redis.examples.example2;



import com.kuark.tool.model.redis.client.RedisPoolFactory;
import redis.clients.jedis.Jedis;

import java.io.IOException;

/**
 * TODO 单redis实例实现分布式锁
 * Created by caoqingyuan on 2018/1/12.
 */
public class RedisLock {
    /**
     * 单节点加锁--线程安全的
     * @param keyName 共享资源的共享key名
     * @param OnlyValue 每个线程的唯一值
     * @param timeout 超时时间(s)
     * @return 是否加锁成功
     */
    public boolean singleNodeLock(String keyName,String OnlyValue,long timeout){
        Jedis jedis =null;
        try {
            jedis = RedisPoolFactory.getInstance().getResource();
            String lockStatus = jedis.set(keyName, OnlyValue, "NX", "EX", timeout);
            if(lockStatus!=null){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 单节点释放锁---线程安全的
     * @param keyName 共享key名
     * @param onlyValue 线程唯一值
     * @return 是否解锁成功
     */
    public boolean singleNodeUnlock(String keyName,String onlyValue){
        if(onlyValue==null||"".equals(onlyValue)){
            return false;
        }
        Jedis jedis=null;
        try{
            jedis=RedisPoolFactory.getInstance().getResource();
            //线程不安全--需加锁，必须锁住整个类对象
            synchronized (RedisLock.class) {
                String courrentValue = jedis.get(keyName);
                if (courrentValue == null) {
                    //说明对应的key已失效
                    return true;
                }
                if (courrentValue.equals(onlyValue)) {
                    jedis.del(keyName);
                }
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return false;
    }
}
