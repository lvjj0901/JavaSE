package com.ljj.juc.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-23 12:59 p.m.
 * @Version 1.0
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        //Execute after 3 Secs delay
        executor.schedule(()->{
            System.out.println(Thread.currentThread().getName()+": Execute after 3 Secs delay");
        }, 3L,TimeUnit.SECONDS);

        //Fixed rate execution(once every 2 Seconds)
        executor.scheduleAtFixedRate(()->{
            System.out.println(Thread.currentThread().getName()+": Fixed rate execution(once every 2 Seconds)");
        },3L,2L,TimeUnit.SECONDS);

    }
}
