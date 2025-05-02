package com.ljj.juc.threadpool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-23 12:43 p.m.
 * @Version 1.0
 */
public class CallableAndFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<String> future = executorService.submit(() -> {
            System.out.println("Task executed by " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "success";
        });

        String result = future.get();//get result
        System.out.println(result);

        executorService.shutdown();

    }
}
