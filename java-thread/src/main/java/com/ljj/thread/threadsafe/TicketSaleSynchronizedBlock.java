package com.ljj.thread.threadsafe;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-03-25 10:11 a.m.
 * @Version 1.0
 */
public class TicketSaleSynchronizedBlock {
    public static void main(String[] args) {
        Ticket tickets = new Ticket();

        Thread client1 = new Thread("Client1"){
            @Override
            public void run() {
                tickets.sale();
            }
        };

        Thread client2 = new Thread("Client2"){
            @Override
            public void run() {
                tickets.sale();
            }
        };

        Thread client3 = new Thread("Client3"){
            @Override
            public void run() {
                tickets.sale();
            }
        };

        client1.start();
        client2.start();
        client3.start();
    }
}
class Ticket{
    private static int ticket = 100;
    public void sale(){
        while (ticket>0) {
            synchronized (this) {
                if(ticket>0) {
                    System.out.println(Thread.currentThread().getName() + " sold Ticket: " + ticket);
                    ticket--;
                }else{
                    System.out.println(Thread.currentThread().getName() +" No more Ticket !!!");
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
