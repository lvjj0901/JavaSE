package com.ljj.juc.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-30 8:38 p.m.
 * @Version 1.0
 */
public class CompletableFuture_whenCompleteTest {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+" --> is executing...");
            //int result = 42;
            int result = 42/0;
            System.out.println(Thread.currentThread().getName()+" --> completed.");
            return result;
        }).whenComplete((r, e)->{
            if(e!=null){
                System.out.println("捕获到异常: "+e.getCause().getMessage());
            }else{
                System.out.println("get result:"+r);
            }
        }).exceptionally(e->{
            System.out.println("****异常type："+e.getCause().getClass().getSimpleName());
            System.out.println("****异常信息："+e.getCause().getMessage());
            return null;
        });

        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()+" executes its task!!!!");
    }
}
