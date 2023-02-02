package com.kuark.tool.advance.advance20190815.google;

import com.kuark.tool.advance.advance20190815.google.event.EventBusImpl;
import com.kuark.tool.advance.advance20190815.google.subscriber.TestSubscriber;

/**
 * @author rock
 * @detail
 * @date 2019/9/3 11:48
 */
public class MainTest {

    public static void main(String[] args){
        EventBusImpl eventBus=new EventBusImpl();
        //注册订阅者
        eventBus.register(new TestSubscriber());
        //构建消息体
        LogMsg logMsg=new LogMsg();
        logMsg.setDesc("测试消息");
        logMsg.setMsgBody("xxxxxxxx");
        logMsg.setMsgId("1111");
        LocalEvent event=new LocalEvent();
        event.setContext(logMsg);
        //发送消息体
        eventBus.post(event);
        System.out.println("end");

        run(eventBus);
    }

    public static void run(EventBusImpl eventBus){
        LocalEvent event=new LocalEvent();
        for(int i=0;i<100;i++){
            new Thread(new Runnable(){
                @Override
                public void run() {
                    LogMsg logMsg=new LogMsg();
                    logMsg.setDesc("测试消息");
                    logMsg.setMsgBody("xxxxxxxx");
                    logMsg.setMsgId("11111");
                    event.setContext(logMsg);
                    //发送消息体
                    eventBus.post(event);
                }
            }).start();
        }
        System.out.println("run end");
    }
}
