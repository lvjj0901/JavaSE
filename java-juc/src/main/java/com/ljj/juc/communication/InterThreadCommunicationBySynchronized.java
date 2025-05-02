package com.ljj.juc.communication;

/**
 * Description:
 * two threads, one performs plus 1 for the variable number,
 * another  performs minus 1 for the same variable,
 * requiring inter-thread communication
 * @Author Jason Lyu
 * @Create 2025-04-17 9:38 a.m.
 * @Version 1.0
 */
public class InterThreadCommunicationBySynchronized {
    public static void main(String[] args) {
        MyNum myNum = new MyNum();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                myNum.add();
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                myNum.min();
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                myNum.add();
            }
        },"CC").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                myNum.min();
            }
        },"DD").start();
    }

}

class MyNum {
    private int num=0;

    public synchronized void add(){
        while(num!=0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName()+" : " + num++);

        this.notifyAll();
    }

    public synchronized void min(){
        while(num==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(Thread.currentThread().getName()+":: "+ num-- );

        this.notifyAll();
    }
}