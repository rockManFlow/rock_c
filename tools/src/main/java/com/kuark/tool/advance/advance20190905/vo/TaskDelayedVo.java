package com.kuark.tool.advance.advance20190905.vo;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author rock
 * @detail 定义一个延迟对象
 * @date 2019/9/9 14:21
 */
public class TaskDelayedVo<T extends Runnable> implements Delayed {
    //总个数
    private static final AtomicLong sumNum=new AtomicLong(0);

    /**
     * 延迟对象
     */
    private final T taskDelay;

    /**
     * 延迟时间
     */
    private final long timeDelay;
    /**
     * 当前位于第几个
     */
    private long currentNum;
    public TaskDelayedVo(long timeout,T t){
        this.taskDelay=t;
        //毫秒
        this.timeDelay=System.currentTimeMillis()+timeout;
        this.currentNum=sumNum.getAndIncrement();
    }



    /**
     * 以给定的时间单位表示，剩余的延迟时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.timeDelay-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if(o==this){
            return 0;
        }
        if(o instanceof TaskDelayedVo){
            TaskDelayedVo vo=(TaskDelayedVo)o;
            long times=this.timeDelay-vo.timeDelay;
            if(times<0){
                return -1;
            }else if(times>0){
                return 1;
            }
            return 0;
        }
        long d = (getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }

    public T getTaskDelay(){
        return this.taskDelay;
    }
}
