package com.ljj.thread.threadsafe;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-03-25 11:29 a.m.
 * @Version 1.0
 */
public class TicketSaleSynchronizedBlockV2 {
    public static void main(String[] args) {
        Tickets tickets = new Tickets();

        Thread w1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(tickets.getTicket()>0){
                    synchronized (tickets){
                        tickets.sale();
                    }
                }
            }
        },"窗口1");

        Thread w2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(tickets.getTicket()>0){
                    synchronized (tickets){
                        tickets.sale();
                    }
                }
            }
        },"窗口2");

        Thread w3 = new Thread("窗口3"){
            @Override
            public void run() {
                while(tickets.getTicket()>0){
                    synchronized (tickets){
                        tickets.sale();
                    }
                }
            }
        };

        w1.start();
        w2.start();
        w3.start();
    }
}
class Tickets{
    private int ticket = 100;
    public void sale(){
        if(ticket>0) {
            System.out.println(Thread.currentThread().getName() + " sold Ticket: " + ticket);
            ticket--;
        }else{
            System.out.println(Thread.currentThread().getName() +" No more Ticket !!!");
        }
    }
    public int getTicket(){
        return ticket;
    }
}