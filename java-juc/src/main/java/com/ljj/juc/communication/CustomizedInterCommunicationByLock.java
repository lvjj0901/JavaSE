package com.ljj.juc.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *A线程打印5次A，B线程打印10次B，C线程打印15次C,按照此顺序循环10轮
 * thread A prints A 5 times,then thread B prints B 10 times, and then threadC prints c 15times
 * and repeats this order 10 rounds.
 * @Author Jason Lyu
 * @Create 2025-04-17 11:04 a.m.
 * @Version 1.0
 */
public class CustomizedInterCommunicationByLock {
    public static void main(String[] args) {
        PrintNum pn = new PrintNum();
        new Thread(()->{
            for (int i = 1; i < 11; i++) {
                pn.printA(i);
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 1; i < 11; i++) {
                pn.printB(i);
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 1; i < 11; i++) {
                pn.printC(i);
            }
        },"CC").start();
    }

}

class PrintNum{
    private int flag = 0 ;
    private ReentrantLock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    public void printA(int count){
        lock.lock();
        try {
            while(flag!=0){
                    conditionA.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.print(Thread.currentThread().getName()+":"+count+"-->"+(i+1)+"A ");
            }
            System.out.println();
            flag =1;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }finally {
            lock.unlock();
        }

    }
    public void printB(int count){
        lock.lock();
        try {
            while(flag !=1){
                conditionB.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.print(Thread.currentThread().getName()+":"+count+"-->"+(i+1)+"B ");
            }
            System.out.println();
            flag = 2;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {
            lock.unlock();
        }

    }
    public void printC(int count){
        lock.lock();
        try {
            while(flag!=2){
                conditionC.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.print(Thread.currentThread().getName()+":"+count+"-->"+(i+1)+"C ");
            }
            System.out.println();
            flag = 0;
            conditionA.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }
}
