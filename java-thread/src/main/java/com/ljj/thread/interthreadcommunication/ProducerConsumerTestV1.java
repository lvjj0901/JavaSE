package com.ljj.thread.interthreadcommunication;

/**
 * Description:
 *  Here is the better implementation for Bounded-buffer problem.
 *  It followed the encapsulation of OOP.
 *  But it did not fully implement independent code and threads .
 * @Author Jason Lyu
 * @Create 2025-04-01 10:02 a.m.
 * @Version 1.0
 */
public class ProducerConsumerTestV1 {
    public static void main(String[] args) {
        ClerkV1 v1 = new ClerkV1();

        ProducerV1 pv1 = new ProducerV1(v1);
        ProducerV1 pv2 = new ProducerV1(v1);

        CustomerV1 cv1 = new CustomerV1(v1);

        new Thread(pv1,"pv1").start();
        new Thread(pv1,"pv2").start();
        new Thread(cv1,"cv1").start();

    }
}

class ClerkV1 {
    private int maxCapacity;
    private int size;
    public ClerkV1(){
        this(10);
    }
    public ClerkV1(int maxCapacity){
        this.maxCapacity = maxCapacity;
    }

    public void produce(){
        size++;
        System.out.println(Thread.currentThread().getName()+" --> produce item: "+size);
        notifyAll();
    }

    public void consume(){
        System.out.println(Thread.currentThread().getName()+" >>> consume item: "+size);
        size--;
        notifyAll();
    }

    public boolean isFull(){
        return size >= maxCapacity;
    }

    public boolean isEmpty(){
        return size < 1;
    }
}

class ProducerV1 implements Runnable{
    private ClerkV1 clerkV1;
    public ProducerV1(ClerkV1 clerkV1){
        this.clerkV1 = clerkV1;
    }

    @Override
    public void run() {
        while (true){
            synchronized (clerkV1) {
                if (clerkV1.isFull()) {
                    try {
                        clerkV1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    clerkV1.produce();
                    clerkV1.notifyAll();
                }
            }
        }
    }
}

class CustomerV1 implements Runnable{
    private ClerkV1 clerkV1;
    public CustomerV1(ClerkV1 clerkV1){
        this.clerkV1 = clerkV1;
    }
    @Override
    public void run() {
        while(true){
            synchronized (clerkV1) {
                if (clerkV1.isEmpty()) {
                    try {
                        clerkV1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    clerkV1.consume();
                    clerkV1.notifyAll();
                }
            }
        }
    }
}