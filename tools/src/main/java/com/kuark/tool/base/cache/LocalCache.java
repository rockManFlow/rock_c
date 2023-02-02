package com.kuark.tool.base.cache;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author caoqingyuan
 * @detail 本地缓存的实现
 * @date 2019/5/22 10:43
 */
public class LocalCache {
    //1、第一种方式：是在真正使用的地方才进行缓存数据的获取
    static Cache<String,String> loadingCache  = CacheBuilder.newBuilder()
            //设置缓存最大个数
            .maximumSize(200)
            .expireAfterWrite(10L, TimeUnit.MINUTES).build();
    public static String get() throws ExecutionException {
        String key="";
        return loadingCache.get(key, new Callable<String>() {
            @Override
            public String call() throws Exception {
                //在这个地方进行加载数据
                return loadInfo(key);
            }
        });
    }

    //2、第二种方式：再进行初始化的时候就进行数据的获取
    static Cache<String,Object> loadCache2=CacheBuilder.newBuilder().
            expireAfterWrite(10,TimeUnit.SECONDS).
            build(new CacheLoader<String, Object>(){

        @Override
        public Object load(String s) throws Exception {
            return loadInfo(s);
        }
    });

    private static String loadInfo(String key){

        return "";
    }

    public static void main(String[] args){

    }
}
