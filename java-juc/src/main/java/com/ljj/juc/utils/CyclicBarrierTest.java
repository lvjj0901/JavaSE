package com.ljj.juc.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-20 8:40 a.m.
 * @Version 1.0
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("7颗龙珠收集完成，召唤神龙！！！");
        });

        for (int i = 1; i <=7 ; i++) {
            new Thread(()->{
                try {
                    System.out.println("收集第 "+Thread.currentThread().getName()+" 颗龙珠完成。");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            },String.valueOf(i)).start();
        }

    }
}
