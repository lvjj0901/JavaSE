package com.ljj.juc.completablefuture.fastercompute;

import org.junit.Test;

import java.sql.Time;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-05-02 11:04 a.m.
 * @Version 1.0
 */
public class FasterComputeTest {
    @Test
    public void testApplyToEither(){
        CompletableFuture<String> playA = CompletableFuture.supplyAsync(() -> {
            System.out.println("Play A come in");
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "PlayA";
        });
        CompletableFuture<String> playB = CompletableFuture.supplyAsync(() -> {
            System.out.println("Play B come in");
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Play B";
        });

        CompletableFuture<String> applyToEither = playA.applyToEither(playB, s -> {
            return s+ " is winner!!";
        });

        System.out.println(applyToEither.join());

        System.out.println("main executes task!!!");

    }
}
