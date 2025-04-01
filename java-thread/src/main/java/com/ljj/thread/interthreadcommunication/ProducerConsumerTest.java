package com.ljj.thread.interthreadcommunication;

import java.util.function.Consumer;

/**
 * Description:
 * producer-consumer problem, also known as Bounded-Buffer problem,
 * is a classic example of inter-thread communication.
 *
 * It can solve produce-consume problem, but there are flaws:
 * 1.The design of the Clerk class has flaws,
 *   Getters/setters are exposed but not used correctly,
 *   which defeats the purpose of encapsulation.
 * 2.It did not fully leverage the advantages of Runnable to achieve decoupling,
 *   with code and threads independent.
 * @Author Jason Lyu
 * @Create 2025-03-31 10:51 a.m.
 * @Version 1.0
 */
public class ProducerConsumerTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk(10);

        Producer p1 = new Producer(clerk);
        Producer p2 = new Producer(clerk);
        Customer c1 = new Customer(clerk);

        new Thread(p1,"producer 1--> ").start();
        new Thread(p2,"producer 2--> ").start();
        new Thread(c1,"consumer 1>>> ").start();
    }
}
class Clerk{
    private int amount;
    private int currentCount;

    public Clerk(int amount){
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }
}
class Producer implements Runnable {
    private Clerk clerk;
    public Producer(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true){
            synchronized (clerk){
                if (clerk.getCurrentCount()<clerk.getAmount()){
                    clerk.setCurrentCount(clerk.getCurrentCount()+1);
                    System.out.println(Thread.currentThread().getName()+"produce item: "+clerk.getCurrentCount());
                    clerk.notifyAll();
                }else {
                    try {
                        clerk.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
class Customer implements Runnable {
    private Clerk clerk;
    public Customer(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true){
            synchronized (clerk){
                if (clerk.getCurrentCount()>0){
                    System.out.println(Thread.currentThread().getName()+"consume item: "+clerk.getCurrentCount());
                    clerk.setCurrentCount(clerk.getCurrentCount()-1);
                    clerk.notifyAll();
                }else {
                    try {
                        clerk.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
