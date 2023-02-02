package com.kuark.tool.advance.advance20190815.Bus;

/**
 * @author rock
 * @detail
 * @date 2019/8/16 18:06
 */
public class SubscriberImpl2 implements Subscriber {

    private EventBus eventBus;
    public SubscriberImpl2(EventBus eventBus){
        this.eventBus=eventBus;
        init();
    }

    public void init(){
        eventBus.subscribe(this);
    }

    @Override
    public void onNext(Event object) {
        System.out.println("SubscriberImpl2  获取到的Event信息："+object);
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
