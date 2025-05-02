package com.ljj.juc.completablefuture.getresult_triggercompute;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-05-01 12:01 p.m.
 * @Version 1.0
 */
public class TriggerComputeTest {
    @Test
    public void testComplete(){
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
//            throw new RuntimeException(e);
//        }

        int result = 0 ;
        if(completableFuture.complete(404)){
            result = completableFuture.join();
        }else {
           // result = completableFuture.join();
            try {
                result = completableFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println(result);
    }
}
