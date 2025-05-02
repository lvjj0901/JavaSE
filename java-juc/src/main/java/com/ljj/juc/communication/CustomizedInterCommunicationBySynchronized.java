package com.ljj.juc.communication;

/**
 * Description:
 * *A线程打印5次A，B线程打印10次B，C线程打印15次C,按照此顺序循环10轮
 *  * thread A prints A 5 times,then thread B prints B 10 times, and then threadC prints c 15times
 *  * and repeats this order 10 rounds.
 * @Author Jason Lyu
 * @Create 2025-04-17 11:43 a.m.
 * @Version 1.0
 */
public class CustomizedInterCommunicationBySynchronized {
    public static void main(String[] args) {
        PrintNums pns = new PrintNums();
        new Thread(()->{
            for (int i = 1; i < 11; i++) {
                pns.printA(i);
            }
        },"AA ").start();

        new Thread(()->{
            for (int i = 1; i < 11; i++) {
                pns.printB(i);
            }
        },"BB ").start();

        new Thread(()->{
            for (int i = 1; i < 11; i++) {
                pns.printC(i);
            }
        },"CC ").start();
    }
}

class PrintNums{
    private int flag =0 ;//control execution order:0->A,1->B,2->C

    public void printA(int count){
        synchronized (this) {
            while (flag != 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            for (int i = 0; i < 5; i++) {
                System.out.print(Thread.currentThread().getName() + ":" + count + "_" + (i + 1) + "-A");
            }
            System.out.println();

            flag=1;
            this.notifyAll();
        }
    }

    public void printB(int count){
        synchronized (this) {
            while (flag != 1) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            for (int i = 0; i < 10; i++) {
                System.out.print(Thread.currentThread().getName() + ":" + count + "_" + (i + 1) + "-B");
            }
            System.out.println();

            flag=2;
            this.notifyAll();
        }
    }

    public void printC(int count) {
        synchronized (this) {
            while (flag != 2) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            for (int i = 0; i < 15; i++) {
                System.out.print(Thread.currentThread().getName() + ":" + count + "_" + (i + 1) + "-C");
            }
            System.out.println();

            flag = 0;
            this.notifyAll();
        }
    }
}
