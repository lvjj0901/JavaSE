package com.ljj.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-03-28 8:19 p.m.
 * {@code @Version} 1.0
 */
public class TicketTest {
    public static void main(String[] args) {
        Ticket t1 = new Ticket();
        t1.setName("Window 1");
        Ticket t2 = new Ticket();
        t2.setName("Window 2");
        Ticket t3 = new Ticket();
        t3.setName("Window 3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Ticket extends Thread{
    private static int ticket = 100;
    private static  final ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true){
            lock.lock();
            try {
                if(ticket>0){
                    System.out.println(Thread.currentThread().getName()+" sold ticket: " +ticket);
                    ticket--;
                }else {
                    System.out.println("Tickets sold out!!!");
                    break;
                }
            }finally {
                lock.unlock();
            }

        }
    }
}
