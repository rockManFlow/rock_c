package com.kuark.tool.advance.advance20200723;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author rock
 * @detail 并行计算底层原理
 * @date 2020/8/24 16:35
 */
@Slf4j
public class ParallelStreamTest {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        cacal();
        System.out.println("cacal cost:"+(System.currentTimeMillis()-start)+"ms");//10ms
        long start2=System.currentTimeMillis();
        calcaParallel();
        System.out.println("calcaParallel cost:"+(System.currentTimeMillis()-start2)+"ms");//30ms
    }

    /**
     * stream的并行计算
     */
    public static void streamParallel(){
        List<String> list=new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        list.add("555");
        list.add("666");
        list.add("777");
        list.add("888");
        list.add("999");
        list.stream().forEach(data->{
            log.info("stream:"+data);
        });
        list.parallelStream().forEach(data->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("parallelStream:"+data);
        });

        /**
         * 默认使用的是ForkJoin的一个共享线程池，线程数是当前机器核心数
         * 如果是CPU密集型任务，这样做没问题。如果非CPU密集型任务，可以变通使用自定义线程数
         * 及自定义ForkJoinPool池的线程数
         */
        ForkJoinPool forkJoinPool2 = new ForkJoinPool(3);
        forkJoinPool2.submit(()->{
            list.parallelStream().forEach(data->{
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("parallelStream:"+data);
            });
        });
    }

    /**
     * 串行运算
     */
    public static void cacal(){
        long sum=10000000;
        long result=0L;
        for(long i=1;i<=sum;i++){
            result+=i;
        }
        System.out.println("result:"+result);
    }

    /**
     * 计算1-1000万之和
     */
    public static void calcaParallel(){
        List<Future<Long>> result=new ArrayList<>();
        long sum=10000000;
        //1、自定义线程池计算
        int cpuNum = Runtime.getRuntime().availableProcessors();
        System.out.println("core cpu:"+cpuNum);
        ExecutorService pool = Executors.newFixedThreadPool(cpuNum);
        long num=sum/cpuNum;

        long from=1L;
        long to=num;
        for(int i=0;i<cpuNum;i++){
            result.add(pool.submit(new CountTask(from,to)));
            from+=num;
            if(i==(cpuNum-2)){
                to=sum;
            }else {
                to+=num;
            }
        }

        long sumResult=0L;
        for(Future<Long> future:result){
            try {
                Long sumValue = future.get();
                sumResult+=sumValue;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("result:"+sumResult);
    }

    public static class CountTask implements Callable<Long>{
        private Long from;
        private Long to;
        public CountTask(Long from,Long to){
            this.from=from;
            this.to=to;
        }

        @Override
        public Long call() throws Exception {
            System.out.println("from:"+from+"|to:"+to);
            long total=0;
            for(long i=from;i<=to;i++){
                total+=i;
            }
            return total;
        }
    }

    //执行任务RecursiveTask：有返回值  RecursiveAction：无返回值
    private static class SumTask extends RecursiveTask<Long> {
        private long[] numbers;
        private int from;
        private int to;

        public SumTask(long[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        //此方法为ForkJoin的核心方法：对任务进行拆分  拆分的好坏决定了效率的高低
        @Override
        protected Long compute() {

            // 当需要计算的数字个数小于6时，直接采用for loop方式计算结果
            if (to - from < 6) {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                return total;
            } else { // 否则，把任务一分为二，递归拆分(注意此处有递归)到底拆分成多少分 需要根据具体情况而定
                int middle = (from + to) / 2;
                SumTask taskLeft = new SumTask(numbers, from, middle);
                SumTask taskRight = new SumTask(numbers, middle + 1, to);
                taskLeft.fork();
                taskRight.fork();
                return taskLeft.join() + taskRight.join();
            }
        }
    }
}
