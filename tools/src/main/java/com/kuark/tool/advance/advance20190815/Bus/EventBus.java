package com.kuark.tool.advance.advance20190815.Bus;

import io.reactivex.processors.PublishProcessor;
import org.springframework.context.annotation.Scope;

/**
 * @author rock
 * @detail
 * @date 2019/8/15 17:30
 */
@Scope("singleton")//默认是单例(singleton),更改为多例(prototype)
public class EventBus {
    /**
     * 这是一个发布订阅者模式
     */
    PublishProcessor<Event> outputListener = PublishProcessor.create();
    public void publish(Event object){
        outputListener.onNext(object);
        outputListener.onError(new Exception("ccccc"));
    }

    public void onComplete(){
        outputListener.onComplete();
    }

    /**
     * 把什么样的消费者订阅到那个服务器上--前提条件必须是同一个服务器（及相同的EventBus）
     * @param subscriber
     */
    public void subscribe(Subscriber subscriber){
        //可以对要发布的消息进行集合操作，来筛选出需要的数据
        outputListener
                .subscribe(
                subscriber::onNext,
                subscriber::onError,
                subscriber::onComplete);
    }
}
