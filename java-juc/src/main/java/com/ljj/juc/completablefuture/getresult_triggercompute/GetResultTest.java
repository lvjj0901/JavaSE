package com.ljj.juc.completablefuture.getresult_triggercompute;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-05-01 11:59 a.m.
 * @Version 1.0
 */
public class GetResultTest {
    public static void main(String[] args) {

    }

    @Test
    public void testGet() {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 42;
        });

        try {
            //get() 需要处理异常
            int result = completableFuture.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetWithTimeOut() {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 42;
        });
        try {
            int result = completableFuture.get(1L, TimeUnit.SECONDS);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJoin() {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 42;
        });

        Integer join = completableFuture.join();
        System.out.println(join);
    }

    @Test
    public void testGetNow(){
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 42;
        });

//        try {
//            TimeUnit.SECONDS.sleep(2L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //如果计算完成则返回计算结果否则返回替代值
        Integer now = completableFuture.getNow(404);

        System.out.println(now);
    }

}
