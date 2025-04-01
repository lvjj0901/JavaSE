package com.ljj.thread.deadlock;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-03-28 10:19 a.m.
 * @Version 1.0
 */
public class DeadLockTest2 {
    static Vendors vendors = new Vendors();
    static Customer customer = new Customer();
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                vendors.getMoney(customer);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                customer.receiveItems(vendors);
            }
        }).start();
    }
}

class Vendors extends Thread{
    public synchronized void getMoney(Customer c){
        try {
            System.out.println("waiting for money");
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        c.payMoney();
        System.out.println("I will send items after paying money");
        sendItems();
    }

    public synchronized void sendItems(){
        System.out.println("sent items!");
    }

}

class Customer extends Thread{
    public synchronized void receiveItems(Vendors v){
        try {
            System.out.println("waiting for items");
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        v.sendItems();
        System.out.println("I will pay after sending items");
        payMoney();
    }

    public synchronized void payMoney(){
        System.out.println("paid !!!");
    }

}
