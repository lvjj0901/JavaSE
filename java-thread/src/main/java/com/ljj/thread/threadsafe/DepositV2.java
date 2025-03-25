package com.ljj.thread.threadsafe;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-03-25 8:06 p.m.
 * @Version 1.0
 */
public class DepositV2 {
    public static void main(String[] args) {
        BankAcount acount = new BankAcount();
        Customer jason = new Customer("Jason", acount);
        Customer andy = new Customer("Andy", acount);

        jason.start();
        andy.start();
    }
}
class BankAcount{
    private int balance;

    public synchronized void deposit(int amount){
        balance+=amount;
        System.out.println(Thread.currentThread().getName()+" :deposit 1000 Dollar!  The account balance is :"+balance);
    }
}

class Customer extends Thread{
    private BankAcount account;
    public Customer(String name,BankAcount account){
        super(name);
        this.account = account;
    }
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            account.deposit(1000);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
