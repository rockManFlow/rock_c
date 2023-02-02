package com.kuark.tool.advance.advance20191127;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author rock
 */
@Log4j
public abstract class AbstractConfig<T> {
    protected Cache<String, Optional<T>> loadingCache  =null;

    /**
     * 初始化缓存对象
     * @param intervalTime 间隔多长时间刷新
     * @param timeUnit 单位
     */
    public AbstractConfig(Long intervalTime, TimeUnit timeUnit) {
        loadingCache  = CacheBuilder.newBuilder()
                .expireAfterWrite(intervalTime, timeUnit).build();
    }

    /**
     * 根据key查询
     *
     * @return
     */
    public T getConfig(ConfigQuery configQuery) {
        T config = getConfigInfoByAppkey(configQuery);
        return config;
    }

    protected String getKey(ConfigQuery configQuery){
        //去掉不同类型的空格类型
        String msg="";
        return msg;
    }

    /**
     * 获取本地缓存信息
     *
     * @return
     */
    private T getConfigInfoByAppkey(ConfigQuery configQuery) {
        String key=getKey(configQuery);
        try {
            Optional<T> optional= loadingCache.get(key, new Callable<Optional<T>>() {

                //todo 加Optional<T>是为了防止当查询出来为空的时候报异常
                @Override
                public Optional<T> call() throws Exception {
                    log.info("loadingCache not match ! Prepare find it from DB.");
                    return Optional.ofNullable(refreshConfig(configQuery));
                }

            });
            return optional.isPresent()?optional.get():null;
        } catch (ExecutionException e) {
            log.info("缓存信息失败 ", e);
        }
        return null;
    }

    protected abstract void initConfig();

    protected abstract T dealConfig(List<T> responseCodeList,ConfigQuery configQuery);

    protected abstract T refreshConfig(ConfigQuery configQuery);
}
