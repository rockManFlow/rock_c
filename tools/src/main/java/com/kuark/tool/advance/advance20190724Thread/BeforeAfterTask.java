package com.kuark.tool.advance.advance20190724Thread;

/**
 * @author rock
 * @detail 线程先后执行
 * @date 2019/10/1 16:07
 */
public class BeforeAfterTask {

  public static void main(String[] args) {
    Thread threadA =
        new Thread(
            () -> {
              try {
                  System.out.println("A ...");
                Thread.sleep(5000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              System.out.println("A");
            });

    Thread threadB =
        new Thread(
            () -> {
                try {
                // 阻塞，直到a执行完成之后
                threadA.join();
                System.out.println("B");
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            });

    threadA.start();
    //或者放到这个地方阻塞，完成之后，才会放行threadA.join();
    threadB.start();
  }
}
