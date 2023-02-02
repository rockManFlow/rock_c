package com.kuark.tool.advance.advance20190925;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.nio.charset.Charset;

/**
 * @author rock
 * @detail
 * @date 2019/9/25 11:23
 */
public class LimitPort {
    public static void main(String[] a){

    }

    /**
     * 分布式限流
     * Java中判断是否需要限流的代码
     */
    public static boolean acquire()throws Exception {
        //使用lua语言，可以在Redis中执行脚本程序
        String luaScript = Files.toString(new File("com/kuark/tool/advance/advance20190925/limit.lua"), Charset.defaultCharset());
        Jedis jedis = new Jedis("192.168.147.52",6379);
        String key = "ip:"+ System.currentTimeMillis()/1000;//此处将当前时间戳取秒数
        String limit ="3";//限流大小
        return(Long)jedis.eval(luaScript, Lists.newArrayList(key), Lists.newArrayList(limit)) ==1;
    }
}
