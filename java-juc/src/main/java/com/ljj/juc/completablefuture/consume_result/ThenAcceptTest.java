package com.ljj.juc.completablefuture.consume_result;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-05-02 10:38 a.m.
 * @Version 1.0
 */
public class ThenAcceptTest {
    @Test
    public void testThenAccept() {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Async--" + Thread.currentThread().getName() + " 1 come in");
            return 1;
        }).thenAccept(integer -> {
            System.out.println("Async--" + Thread.currentThread().getName() + " 1 come in");
            System.out.println("Async task result:" + integer);
        });


        System.out.println(Thread.currentThread().getName() + " execute task");

        System.out.println(Thread.currentThread().getName() +" result:" +completableFuture.join());
    } @Test
    public void testThenAcceptWithPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Async--" + Thread.currentThread().getName() + " 1 come in");
            return 1;
        }).thenAcceptAsync(integer -> {
            System.out.println("***Async--" + Thread.currentThread().getName() + " 1 come in");
            System.out.println("Async task result:" + integer);
        }, executorService);


        System.out.println(Thread.currentThread().getName() + " execute task");

        System.out.println(Thread.currentThread().getName() +" result:" +completableFuture.join());
    }
}
