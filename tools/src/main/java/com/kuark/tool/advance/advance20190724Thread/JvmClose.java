package com.kuark.tool.advance.advance20190724Thread;

/**
 * @author rock
 * @detail
 * 一个关机钩子只是一个初始化但未启动的线程。
 * 当虚拟机开始关闭序列时，它将以一些未指定的顺序启动所有注册的关闭挂钩，并让它们同时运行。
 * 当所有的钩子都完成后，如果已经启用了最终确定的退出，它将运行所有未被终止的终结器。
 * 最后，虚拟机将停止。
 * 请注意，守护程序线程将在关闭序列期间继续运行，非守护线程如果通过调用exit方法启动关闭，则该守护程序线程也将继续。
 * @date 2020/10/30 17:06
 */
public class JvmClose {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("当虚拟机正常关闭时，会触发这个线程的执行");
            }
        }));

        System.out.println("exit");
        //终止当前运行的Java虚拟机。 该参数作为状态代码; 按照惯例，非零状态码表示异常终止。
        System.exit(0);
    }
}
