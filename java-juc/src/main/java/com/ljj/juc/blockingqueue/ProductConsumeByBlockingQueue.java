package com.ljj.juc.blockingqueue;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-21 8:56 p.m.
 * @Version 1.0
 */
public class ProductConsumeByBlockingQueue {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);
        //production threads
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                while(true){
                try {
                    String ele = Thread.currentThread().getName()+"->"+ UUID.randomUUID().toString().substring(0,4);
                    blockingQueue.put(ele);
                    System.out.println(Thread.currentThread().getName()+" put :"+ ele);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }}
            },"P_"+String.valueOf(i)).start();
        }
        //consume threads
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                while(true){
                try {
                    String ele = blockingQueue.take();
                    System.out.println(Thread.currentThread().getName()+" take :"+ele);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }}
            },"C_"+String.valueOf(i)).start();
        }
    }
}
