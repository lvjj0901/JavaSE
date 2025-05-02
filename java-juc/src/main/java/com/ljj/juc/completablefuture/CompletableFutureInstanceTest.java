package com.ljj.juc.completablefuture;

import java.util.concurrent.*;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-30 7:19 p.m.
 * @Version 1.0
 */
public class CompletableFutureInstanceTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //custom my thread pool
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                5,
                3L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

       // instanceByRunasync();
        //instanceByRunAsyncWithThreadPool(threadPoolExecutor);
        //String s = instanceBySuppplyAsync();
        String s = instanceBySupplyAsyncWithThreadPool(threadPoolExecutor);

        System.out.println("main thread get return:"+s);


        try { Thread.sleep(3000); } catch (InterruptedException e) {}
        System.out.println("主线程继续执行，不等待异步任务");

        //should no forget shutdown the pool
        threadPoolExecutor.shutdown();
    }

    /**
     * if method prints nothing, don't doubt program
     * cause default threadPool which is forkJoinPool starts daemon threads,
     * so main thread completed , the daemon thread will be shut down
     * in order to print something, main thread needs to sleep in a few seconds.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void instanceByRunasync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " in asynchronous task!!!");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Asynchronous task completed !!!");
        });
    }

    public static void instanceByRunAsyncWithThreadPool(ThreadPoolExecutor threadPoolExecutor) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " in asynchronous task!!!");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Asynchronous task completed !!!");
        }, threadPoolExecutor);

    }

    public static String instanceBySuppplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---->in [supply Asynchronous] task!!!");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String str = "42";
            System.out.println("Asynchronous task completed !!! return:"+str);
            return str;
        });
        String result  = completableFuture.get();
        return result;
    }
    public static String instanceBySupplyAsyncWithThreadPool(ThreadPoolExecutor threadPoolExecutor) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---->in [supply Asynchronous with pool] task!!!");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String str = "42";
            System.out.println("Asynchronous task completed !!! return:"+str);
            return str;
        },threadPoolExecutor);
        String result  = completableFuture.get();
        return result;
    }

}
