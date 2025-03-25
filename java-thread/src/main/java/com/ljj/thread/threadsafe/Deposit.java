package com.ljj.thread.threadsafe;

/**
 * Description:
 * Two clients deposit 6000 dollar to the same bank account,
 * each client deposit 3000 dollar to the bank account, 1000 dollar each time, for three times.
 * The account balance is printed after each deposit.
 * @Author Jason Lyu
 * @Create 2025-03-25 7:32 p.m.
 * @Version 1.0
 */
public class Deposit {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();

        Thread t1 = new Thread("Client A"){
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    synchronized (bankAccount){
                        bankAccount.deposit(1000);
                        System.out.print(Thread.currentThread().getName()+" :deposit 1000 Dollar! ");
                        System.out.println("The account balance is :"+bankAccount.getBalance());
                    }
                }
            }
        };

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    synchronized (bankAccount){
                        bankAccount.deposit(1000);
                        System.out.print(Thread.currentThread().getName()+" :deposit 1000 Dollar! ");
                        System.out.println("The account balance is :"+bankAccount.getBalance());
                    }
                }
            }
        },"Client B");

        t1.start();
        t2.start();
    }
}
class BankAccount{
    private int balance;

    public void deposit(int amount){
        if(amount>0){
            balance+=amount;
        }else{
            System.out.println("Illegal amount");
        }
    }

    public int getBalance(){
        return balance;
    }

}
