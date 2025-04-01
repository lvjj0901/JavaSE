package com.ljj.thread.deadlock;

/**
 * Description:
 * A: give your chopstick to me, I need one more to eat my noodles;
 * B: give YOUR chopstick to ME, I need one more to eat MINE.
 * This story denotes you should have plan B,should not always wait for others;
 * sometimes fork is better choice.
 * @Author Jason Lyu
 * @Create 2025-03-28 9:53 a.m.
 * @Version 1.0
 */
public class DeadLockTest1 {
    public static void main(String[] args) {
        Chopstick chopstick1 = new Chopstick();
        Chopstick chopstick2 = new Chopstick();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (chopstick1){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("I got chopstick1...one more for eating");
                    System.out.println("Jason, please give your chopstick!!!");
                    synchronized (chopstick2){
                        System.out.println("I have a pair of chopsticks, I can enjoy noodles!!!");
                    }
                }
            }
        },"Andy").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (chopstick2){
                    System.out.println("I got chopstick2...one more for eating");
                    System.out.println("Andy, please give your chopstick!!!");
                    synchronized (chopstick1){
                        System.out.println("I have a pair of chopsticks, I can enjoy noodles!!!");
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        },"Jason").start();
    }
}
class Chopstick{
}
