package com.ljj.juc.threadpool;

import java.util.concurrent.*;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-23 1:13 p.m.
 * @Version 1.0
 */
public class CustomThreadPoolAndRejection {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, // 核心线程数
                4, // 最大线程数
                30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2), // 容量为 2 的队列
                new RejectedExecutionHandler() { // 自定义拒绝策略
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("Task rejected: " + r);
                        System.out.println("Retrying..." + r);
                        if(!executor.isShutdown()){
                            try {
                                boolean retry = executor.getQueue().offer(r,1,TimeUnit.SECONDS);
                                System.out.println("Retry success"+r);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
        );

        for (int i = 0; i < 7; i++) {
            int taskId = i;
            executor.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName());
            });
        }

        executor.shutdown();
    }
}
