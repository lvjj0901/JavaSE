package com.ljj.juc.completablefuture.processresult;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-05-01 1:02 p.m.
 * @Version 1.0
 */
public class ThenApplyTest {
    @Test
    public void testThenApply(){
        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(111);
            System.out.println(Thread.currentThread().getName()+" 1 step:" + 40);
            return 40;
        }, executorService).thenApply(num -> {
            System.out.println(222);
            num++;
           int t = 42/0;
            System.out.println(Thread.currentThread().getName()+" 2 step:" + num);
            return num;
        }).thenApply(num -> {
            System.out.println(333);
            num++;
            System.out.println(Thread.currentThread().getName()+" 3 step:" + num);
            return num;
        }).whenComplete((i,e)->{
            if(e==null) {
                System.out.println("final result:" + i);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });

        System.out.println("main thread executes its task!!!");
        try {
            System.out.println("异步任务结果："+completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
