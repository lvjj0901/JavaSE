package com.ljj.juc.completablefuture.combineresult;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-05-02 11:31 a.m.
 * @Version 1.0
 */
public class ThenCombineTest {
    @Test
    public void testThenCombine(){
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " come in---");
            return 10;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " come in---");
            return 10;
        }), (i, j) -> {
            System.out.println(Thread.currentThread().getName() + (i + j));
            return i + j;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " come in---");
            return 10;
        }), (x, y) -> x + y);

        System.out.println(Thread.currentThread().getName()+" execute task!!!!");
        System.out.println(completableFuture.join());
    }
}
