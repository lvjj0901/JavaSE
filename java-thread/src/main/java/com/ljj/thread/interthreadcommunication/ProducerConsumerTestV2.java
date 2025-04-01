package com.ljj.thread.interthreadcommunication;

/**
 * Description:
 * It is the best implementation for produce-consume problem.
 * If you have the better one, Pls show me, Thanks!!!
 * @Author Jason Lyu
 * @Create 2025-04-01 7:45 p.m.
 * @Version 1.0
 */
public class ProducerConsumerTestV2 {
    public static void main(String[] args) {
        ClerkV2 v2 = new ClerkV2();

        ProducerV2 pv21 = new ProducerV2(v2);
        ProducerV2 pv22 = new ProducerV2(v2);
        pv21.setName("ProducerV2 1 ");
        pv22.setName("ProducerV2 2 ");

        pv21.start();
        pv22.start();

        CustomerV2 cv21 = new CustomerV2(v2);
        cv21.setName("CustomerV2 1 ");

        cv21.start();
    }
}
class ClerkV2{
    private int maxCount;
    private int currentCount;
    public ClerkV2(){
        this(10);
    }

    public ClerkV2(int maxCount){
        this.maxCount = maxCount;
    }

    public synchronized void produce(){
        if(currentCount <= maxCount){
            currentCount++;
            System.out.println(Thread.currentThread().getName()+" ---> produce: "+currentCount);
            this.notifyAll();
        }else{
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consume(){
        if(currentCount > 0){
            System.out.println(Thread.currentThread().getName()+" ***> consume: "+currentCount);
            currentCount--;
            this.notifyAll();
        }else{
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class ProducerV2 extends Thread{
    private ClerkV2 clerkV2;
    public ProducerV2(ClerkV2 clerkV2){
        this.clerkV2 = clerkV2;
    }

    @Override
    public void run() {
        while (true) {
            clerkV2.produce();
        }
    }
}

class CustomerV2 extends Thread{
    private ClerkV2 clerkV2;
    public CustomerV2(ClerkV2 clerkV2){
        this.clerkV2 = clerkV2;
    }
    @Override
    public void run() {
        while(true){
            clerkV2.consume();
        }
    }
}