import org.springframework.aop.framework.AopContext;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by cc on 2016/4/15.
 */
public class MyTask extends TimerTask {
    public void run() {
        System.out.println("dddd");
    }
    public static void main(String[] s) throws InterruptedException {
        //启动一个线程就会自动定时执行
//        Timer timer = new Timer();
//        timer.schedule(new MyTask(),0, 2000);

        Integer a=10;
        Integer b=11;
//        synchronized (a){
//            System.out.println("eeee");
//            b.wait();
//        }

        synchronized (a){
            System.out.println("dddd");
            b.notify();
            a.wait();
        }


    }
}
