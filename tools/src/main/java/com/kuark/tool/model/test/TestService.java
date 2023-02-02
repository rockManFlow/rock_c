package com.kuark.tool.model.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by caoqingyuan on 2017/7/4.
 */
public class TestService {
    public static final ThreadLocal<String> local = new ThreadLocal<String>();
    private static final Logger logger = LoggerFactory.getLogger(TestService.class);

    public static void setLocal(String st) {
        logger.info("往指定局部变量中设置属性值|" + st);
        local.set(st);
    }

    public static String getLocal() {
        return local.get();
    }

    public static void remove() {
        local.remove();
    }

    public static void main(String[] args) {
        System.out.println("start");
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    double ll = 1000 + Math.random() * 1000;
                    setLocal("line|" + ll);
                    try {
                        Thread.sleep(2 * 1000);
                        String local = getLocal();
                        logger.info("get value:" + local);
//                        Thread.sleep(1 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        remove();
                        logger.info("remove finish");
                    }
                }
            }).start();
        }
        System.out.println("end");
    }
}
