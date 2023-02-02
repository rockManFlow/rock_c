package com.kuark.tool.advance.advance20190724Thread;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author rock
 * @detail CompletableFuture部分基本使用
 * 可以添加监听器：当完成的时候执行指定的动作，不用再使用get方法来监听使用
 * @date 2020/8/25 14:35
 */
@Slf4j
public class CompletableFutureMain {

    public static void main1() throws ExecutionException, InterruptedException {
//        CompletableFuture future=new CompletableFuture();

        /**
         * 创建CompletableFuture对象，有四种方式对于Runnable和Callable等指定线程池和不指定线程池
         */
        //默认使用ForkJoinPool.commonPool(); 没有指定线程池默认使用这个线程池
        CompletableFuture<String> supplyFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("xxxxx");
            if(true){
                throw new RuntimeException("test exception");
            }
            return "ok";
        });
//        String s = supplyFuture.get();
//        System.out.println("future result:"+s);

        //当上面完成的时候执行这个action
        supplyFuture.whenComplete(new BiConsumer<String, Throwable>(){

            //这里的String 是上面执行的结果,throwable是上面执行抛出的异常
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println("s:" + s);
                System.out.println("throwable:"+throwable.getMessage());
            }
        });

        String exceptionResult = supplyFuture.exceptionally(new Function<Throwable, String>() {

            @Override
            public String apply(Throwable throwable) {
                System.out.println("运行抛出异常时执行这个方法：" + throwable.getMessage());
                return "exception ok";
            }
        }).get();

        System.out.println("exceptionResult:"+exceptionResult);

        //a b默认是返回类型及抛出的异常
        CompletableFuture<String> stringCompletableFuture = supplyFuture.whenComplete((a, b) -> {
            System.out.println("a:" + a);
            System.out.println("b:" + b.getMessage());
        });

    }

    public static void learnOne(){
//        ForkJoinPool.commonPool();
        CompletableFuture<Void> noResultFuture = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("无返回值");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, Executors.newSingleThreadExecutor());

        System.out.println("minxxxx");
        noResultFuture.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void t, Throwable action) {
                System.out.println("执行完成！");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("endxxxx");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        learnTwo();
    }

    public static void learnTwo(){
        //两个线程顺序执行
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            log.info("run 1");
        }, Executors.newSingleThreadExecutor(new ThreadFactory(){

            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread t=new Thread(r);
                t.setName("order-"+t.getId());
                return t;
            }
        }));
        future.thenRun(()->{
            log.info("顺序执行run2");
        });
    }

    public static void learn2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello future1";
            }
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello future2";
            }
        });
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello future4";
            }
        });
        CompletableFuture<String> future5 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello future5";
            }
        });
        CompletableFuture<String> result = future1.thenCombine(future2, new BiFunction<String, String, String>() {
            //结果交给这个线程来一起处理
            @Override
            public String apply(String t, String u) {
                return t+" "+u;
            }
        });
        System.out.println(result.get());

        CompletableFuture<Void> future3 = future1.runAfterBoth(future2, () -> {
            // 结果交给指定线程来执行--两个线程同步结果一起处理
        });
    }

    /**
     * 创建
     * public static CompletableFuture<Void> runAsync(Runnable runnable)
     * public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
     * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
     * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
     * 没有指定Executor的方法会使用ForkJoinPool.commonPool() 作为它的线程池执行异步代码。如果指定线程池，则使用指定的线程池运行。以下所有的方法都类同。
     *
     *     runAsync方法不支持返回值。
     *     supplyAsync可以支持返回值。
     *
     * 当CompletableFuture的计算结果完成，或者抛出异常的时候，可以执行特定的Action
     * public CompletableFuture<T> whenComplete(BiConsumer<? super T,? super Throwable> action)
     * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
     * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
     * public CompletableFuture<T> exceptionally(Function<Throwable,? extends T> fn)
     *
     * whenComplete 和 whenCompleteAsync 的区别：
     * whenComplete：是执行当前任务的线程执行继续执行 whenComplete 的任务。
     * whenCompleteAsync：是执行把 whenCompleteAsync 这个任务继续提交给线程池来进行执行。
     *
     * thenApply 方法
     *
     * 当一个线程依赖另一个线程执行结果时，可以使用 thenApply 方法来把这两个线程串行化。
     * public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
     * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
     * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
     * 示例：
     * private static void thenApply() throws Exception {
     *     CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
     *         @Override
     *         public Long get() {
     *             long result = new Random().nextInt(100);
     *             System.out.println("result1="+result);
     *             return result;
     *         }
     *     }).thenApply(new Function<Long, Long>() {
     *         @Override
     *         public Long apply(Long t) {
     *             long result = t*5;
     *             System.out.println("result2="+result);
     *             return result;
     *         }
     *     });
     *
     *     long result = future.get();
     *     System.out.println(result);
     * }
     *
     * handle 方法
     *
     * handle 是执行任务完成时对结果的处理。
     * handle 方法和 thenApply 方法处理方式基本一样。不同的是 handle 是在任务完成后再执行，还可以处理异常的任务。thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法。
     *
     * thenAccept 消费处理结果
     * 接收任务的处理结果，并消费处理，无返回结果。
     *
     * thenRun 方法
     * 跟 thenAccept 方法不一样的是，不关心任务的处理结果。只要上面的任务执行完成，就开始执行 thenRun 。
     *
     * thenCombine 合并任务
     * thenCombine 会把 两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理。
     * 示例：
     * private static void thenCombine() throws Exception {
     *     CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
     *         @Override
     *         public String get() {
     *             return "hello";
     *         }
     *     });
     *     CompletableFuture<String> future2 = CompletableFuture.supplyAsync(new Supplier<String>() {
     *         @Override
     *         public String get() {
     *             return "hello";
     *         }
     *     });
     *     CompletableFuture<String> result = future1.thenCombine(future2, new BiFunction<String, String, String>() {
     *         @Override
     *         public String apply(String t, String u) {
     *             return t+" "+u;
     *         }
     *     });
     *     System.out.println(result.get());
     * }
     *
     * thenAcceptBoth方法
     * 当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消耗
     * 使用与上面一致
     *
     * applyToEither 方法
     * 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作。
     *
     * acceptEither 方法
     * 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的消耗操作。
     *
     * runAfterEither 方法
     * 两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
     *
     * runAfterBoth
     * 两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
     *
     * thenCompose 方法
     * thenCompose 方法允许你对两个 CompletionStage 进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作。
     * 示例：
     * private static void thenCompose() throws Exception {
     *         CompletableFuture<Integer> f = CompletableFuture.supplyAsync(new Supplier<Integer>() {
     *             @Override
     *             public Integer get() {
     *                 int t = new Random().nextInt(3);
     *                 System.out.println("t1="+t);
     *                 return t;
     *             }
     *         }).thenCompose(new Function<Integer, CompletionStage<Integer>>() {
     *             @Override
     *             public CompletionStage<Integer> apply(Integer param) {
     *                 return CompletableFuture.supplyAsync(new Supplier<Integer>() {
     *                     @Override
     *                     public Integer get() {
     *                         int t = param *2;
     *                         System.out.println("t2="+t);
     *                         return t;
     *                     }
     *                 });
     *             }
     *
     *         });
     *         System.out.println("thenCompose result : "+f.get());
     *     }
     */
}
