package com.ljj.juc.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-19 6:07 p.m.
 * @Version 1.0
 */
public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyRunnableThread myRunnableThread = new MyRunnableThread();
        new Thread(myRunnableThread,"Runnable-1").start();

        MyCallableThread myCallableThread = new MyCallableThread();
        FutureTask<Long> longFutureTask = new FutureTask<Long>(myCallableThread);

        new Thread(longFutureTask,"Callable-1").start();

        Long aLong = longFutureTask.get();

        System.out.println("Result of myRunnableThread:"+aLong);
    }
}

class MyRunnableThread implements Runnable{

    @Override
    public void run() {
        System.out.println("performing run() in the implementor of Runnable");
    }
}

class MyCallableThread implements Callable{

    @Override
    public Object call() throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("performing call() in the implementor of Callable");
        Thread.sleep(1000);
        long l = System.currentTimeMillis() - start;
        System.out.println("Elapsed:" +l +" in the call()!");
        return l;
    }
}
