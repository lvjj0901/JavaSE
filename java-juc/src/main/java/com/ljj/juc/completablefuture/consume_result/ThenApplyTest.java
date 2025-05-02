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
public class ThenApplyTest {
    @Test
    public void testThenApply(){
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Async--"+Thread.currentThread().getName() + " 1 come in");
            return 1;
        }).thenApply(v -> {
            System.out.println("Async--"+Thread.currentThread().getName() + " 2 come in");
            return v + 1;
        }).thenApply(v -> {
            System.out.println("Async--"+Thread.currentThread().getName() + " 3 come in");
            return v + 1;
        }).whenComplete((i,e)->{
            if(e==null){
                System.out.println("Async--"+Thread.currentThread().getName()+" result:"+i);
            }
        }).exceptionally(e->{
            e.printStackTrace();
            return null;
        });

        System.out.println(Thread.currentThread().getName()+" executes main's task");

        System.out.println("异步任务结果："+completableFuture.join());
        try {
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    } @Test
    public void testThenApplyWithCustomePool(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Async--"+Thread.currentThread().getName() + " 1 come in");
            return 1;
        }).thenApplyAsync(v -> {
            System.out.println("Async--"+Thread.currentThread().getName() + " 2 come in");
            return v + 1;
        },executorService).thenApply(v -> {
            System.out.println("Async--"+Thread.currentThread().getName() + " 3 come in");
            return v + 1;
        }).whenComplete((i,e)->{
            if(e==null){
                System.out.println("Async--"+Thread.currentThread().getName()+" result:"+i);
            }
        }).exceptionally(e->{
            e.printStackTrace();
            return null;
        });

        System.out.println(Thread.currentThread().getName()+" executes main's task");

        System.out.println("异步任务结果："+completableFuture.join());

        executorService.shutdown();
    }
}
