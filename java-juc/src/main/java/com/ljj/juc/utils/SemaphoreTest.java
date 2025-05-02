package com.ljj.juc.utils;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Description:
 * 6 cars competing for 3 parking spaces.
 * @Author Jason Lyu
 * @Create 2025-04-20 8:53 a.m.
 * @Version 1.0
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    Thread.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName()+" car got parking space!");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName()+" car left!");
                }
            },String.valueOf(i)).start();
        }
    }
}
