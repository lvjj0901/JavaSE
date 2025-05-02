package com.ljj.juc.utils;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-20 8:26 a.m.
 * @Version 1.0
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    //current thread sleep for a  random  number of milliseconds
                    Thread.sleep(new Random().nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" Student left classRoom!");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
        System.out.println("the leader lock the classroom after all students left");
    }
}
