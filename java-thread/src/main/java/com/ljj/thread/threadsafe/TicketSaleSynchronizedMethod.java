package com.ljj.thread.threadsafe;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-03-25 9:46 a.m.
 * @Version 1.0
 */
class TicketSaleWindow extends Thread{
    private static int ticket = 100;
    @Override
    public void run() {
        while (ticket>0) {
            sale();
        }
    }

    private synchronized static void sale(){
        if(ticket>0) {
            System.out.println(Thread.currentThread().getName() + " sold ticket: " + ticket);
            ticket--;
        }
    }
}
public class TicketSaleSynchronizedMethod {
    public static void main(String[] args) {

        TicketSaleWindow window1 = new TicketSaleWindow();
        TicketSaleWindow window2 = new TicketSaleWindow();
        TicketSaleWindow window3 = new TicketSaleWindow();

        window1.setName("窗口1");
        window2.setName("窗口2");
        window3.setName("窗口3");

        window1.start();
        window2.start();
        window3.start();
    }
}
