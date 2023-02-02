package com.kuark.tool.advance.advance20190724Thread.queue;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author rock
 * @detail
 * @date 2022/2/10 16:11
 */
@Data
public class DelayedMessage implements Delayed {
    private String body;
    private long excuteTime;// 延迟时长，这个是必须的属性因为要按照这个判断延时时长。
    private Runnable task;

    public Runnable getTask(){
        return this.task;
    }


    public DelayedMessage(String body, long delayTime) {
        this.body = body;
        this.excuteTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime();
    }

    // 延迟任务是否到时就是按照这个方法判断如果返回的是负数则说明到期否则还没到期
    @Override
    public long getDelay(@NotNull TimeUnit unit) {
        return unit.convert(this.excuteTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    // 自定义实现比较方法返回 1 0 -1三个参数分别表示 该对象大于 等于 小于  按照自然顺序排列 升序

    /**
     * 这个需要注意：根据延迟队列逻辑，优先级越高，越会先被执行（即使优先级低的任务已经到期），
     * 只有等队首元素执行完成之后，之后的元素才会执行。所以，这个优先级的比较最好是根据过期时间的长短来判断
     * 延迟时间越长的，优先级越低，保证先到期的任务先执行。正数往后排，负数往前排（自然顺序排列）
     * @param o
     * @return
     */
    @Override
    public int compareTo(@NotNull Delayed o) {
        DelayedMessage message=(DelayedMessage)o;
        if(this.excuteTime>message.getExcuteTime()){
            return 1;
        }else if(this.excuteTime<message.getExcuteTime()){
            return -1;
        }else {
            return 0;
        }
    }
}
