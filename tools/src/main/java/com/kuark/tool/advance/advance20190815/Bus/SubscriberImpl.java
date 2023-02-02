package com.kuark.tool.advance.advance20190815.Bus;

/**
 * @author rock
 * @detail
 * @date 2019/8/16 18:06
 */
public class SubscriberImpl implements Subscriber {

    private EventBus eventBus;
    public SubscriberImpl(EventBus eventBus){
        this.eventBus=eventBus;
        init();
    }

    /**
     * 注册到相同的监听器中
     */
    public void init(){
        eventBus.subscribe(this);
    }

    /**
     * 订阅者还是在这个地方进行消费处理的
     * @param object
     */
    @Override
    public void onNext(Event object) {

        System.out.println("SubscriberImpl获取到的Event信息："+object);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("Exception info"+t.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Zhi Finished");
    }
}
