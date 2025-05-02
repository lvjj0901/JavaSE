package com.ljj.juc.completablefuture.consume_result;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-05-02 10:38 a.m.
 * @Version 1.0
 */
public class ThenRunTest {
    @Test
    public void testThenRun(){
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Async--" + Thread.currentThread().getName() + " come in");
            return 1;
        }).thenRun(() -> {
            System.out.println("Async--" + Thread.currentThread().getName() + " come in");
        });

        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName() + " executes task");
    } @Test
    public void testThenRunWithPool(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Async--" + Thread.currentThread().getName() + " come in");
            return 1;
        }).thenRunAsync(() -> {
            System.out.println("Async--" + Thread.currentThread().getName() + " come in");
        },executorService);

        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName() + " executes task");
    }
}
