package com.ljj.thread.interthreadcommunication;

/**
 * Description:
 * Two threads alternate to print numbers from 1 to 100
 *
 * @Author Jason Lyu
 * @Create 2025-03-30 10:04 a.m.
 * @Version 1.0
 */
public class InterThreadCommunicationTest1 {
    public static void main(String[] args) {
        PrintNumber1 pn = new PrintNumber1();

        new Thread(pn, "printer 1 ").start();
        new Thread(pn, "printer 2 ").start();
    }

}

class PrintNumber implements Runnable {
    private static int num = 1;

    @Override
    public void run() {
        while (num < 101) {
            synchronized (this) {
                notify();
                if (num < 101) {
                    System.out.println(Thread.currentThread().getName() + "print num: " + num);
                    num++;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    notify();
                    break;
                }

            }
        }
        synchronized (this) {
            notify();
        }
    }
}

class PrintNumber1 implements Runnable {
int num = 1;
    @Override
    public void run() {
        while (true){
            synchronized (this){
                notify();
                if(num<=100){
                    System.out.println(Thread.currentThread().getName() + "print num: " + num);
                    num++;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    break;
                }
            }
        }
    }
}
