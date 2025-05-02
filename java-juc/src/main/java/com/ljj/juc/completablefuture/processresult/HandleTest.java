package com.ljj.juc.completablefuture.processresult;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-05-01 1:02 p.m.
 * @Version 1.0
 */
public class HandleTest {
    @Test
    public void testHandle(){
        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()+" 1 step:");
            return 40;
        },executorService).handle((r, e) -> {
            r++;
            int t = 42/0;
            System.out.println(Thread.currentThread().getName()+" 2 step:");
            return r;
        }).handle((r, e) -> {
            System.out.println(Thread.currentThread().getName()+" 3 step:" );
            r++;
            return r;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println(Thread.currentThread().getName()+" final result:" + v);
            }
        }).exceptionally(e -> {
            System.out.println(Thread.currentThread().getName()+"***********蒱获异常");
            e.printStackTrace();
            return null;
        });
        System.out.println(Thread.currentThread().getName()+" execute task!!!!");


        try {
            System.out.println(Thread.currentThread().getName()+"异步任务结果："+completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
