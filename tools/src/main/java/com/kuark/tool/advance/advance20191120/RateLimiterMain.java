package com.kuark.tool.advance.advance20191120;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author rock
 * @detail
 * @date 2019/11/20 23:26
 */
public class RateLimiterMain {
    public static void main(String[] args){
        //每秒最大10次请求
        RateLimiter limiter=RateLimiter.create(10);
        //获取令牌，获取不到会阻塞
        limiter.acquire();
        //是否可以获取令牌，不可获取，直接返回
        limiter.tryAcquire();
    }
}
