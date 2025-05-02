package com.ljj.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-23 12:33 p.m.
 * @Version 1.0
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 8; i++) {
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName()+" execute task !");
            });
        }

        executorService.shutdown();//shutdown threadpool
    }
}
