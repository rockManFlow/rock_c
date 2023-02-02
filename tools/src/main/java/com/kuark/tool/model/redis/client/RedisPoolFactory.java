package com.kuark.tool.model.redis.client;

import com.kuark.tool.model.util.ConfigUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by caoqingyuan on 2017/7/6.
 */
public class RedisPoolFactory {
    //redis连接池
    private static JedisPool pool=null;
    private static JedisPoolConfig config=null;
    public static JedisPool getInstance() throws IOException {
        if(pool==null){
            synchronized (RedisPoolFactory.class) {
                if(pool==null) {
                    config = new JedisPoolConfig();
                    //最大保持索引对象连接数
                    config.setMaxIdle(20);
                    //最大分配对象数
                    config.setMaxTotal(20);
                    //没有对象返回时最大等待时间
                    config.setMaxWaitMillis(30 * 1000);
                    //是否进行有效检查
                    config.setTestOnBorrow(true);
                    //是否进行有效检查
                    config.setTestOnReturn(true);
                    Properties properties = ConfigUtil.loadConf("redis.properties");
                    pool = new JedisPool(config, (String) properties.get("redis.ip"), Integer.parseInt((String) properties.get("redis.port")), Integer.parseInt((String) properties.get("redis.timeout")));
                }
            }
        }
        return pool;
    }

    public static void main(String[] args) throws IOException {
        JedisPool instance = RedisPoolFactory.getInstance();
        Jedis resource = instance.getResource();
        System.out.println(resource);
    }
}
