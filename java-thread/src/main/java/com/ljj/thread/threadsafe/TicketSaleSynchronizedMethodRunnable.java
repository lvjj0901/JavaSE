package com.ljj.thread.threadsafe;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-03-25 10:12 a.m.
 * @Version 1.0
 */
public class TicketSaleSynchronizedMethodRunnable {
    public static void main(String[] args) {
        TicketWindow window = new TicketWindow();

        Thread thread1 = new Thread(window,"窗口一");
        Thread thread2 = new Thread(window,"窗口二");
        Thread thread3 = new Thread(window,"窗口三");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
class TicketWindow implements Runnable{
    private static int ticket = 100;
    @Override
    public void run() {
        while (ticket>0){
            sale();
        }
    }

    private synchronized void sale(){
        if(ticket>0){
            System.out.println(Thread.currentThread().getName()+" sold ticket: "+ticket);
            ticket--;
        }
    }
}