package com.ljj.juc.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-17 10:11 a.m.
 * @Version 1.0
 */
public class InterThreadCommunicationByLock {
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                myNumber.add();
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                myNumber.minus();
            }
        },"BB").start();
    }
}

class MyNumber{
    private int num =0 ;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void add(){
        lock.lock();
        try {
            while(num!=0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println(Thread.currentThread().getName()+" ::"+num++);
            condition.signal();
        }finally {
            lock.unlock();
        }

    }

    public void minus(){
        lock.lock();
        try {
            while(num==0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName()+"=="+num--);
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}
