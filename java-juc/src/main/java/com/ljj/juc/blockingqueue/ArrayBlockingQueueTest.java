package com.ljj.juc.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-21 9:31 p.m.
 * @Version 1.0
 */
public class ArrayBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
//        //group 1
//        System.out.println(blockingQueue.add(1));
//        System.out.println(blockingQueue.add(2));
//        //System.out.println(blockingQueue.add(3)); throw IllegalStateException: Queue full
//
//        System.out.println(blockingQueue.element());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        //System.out.println(blockingQueue.remove()); throw .NoSuchElementException


//        //group 2
//        System.out.println(blockingQueue.offer(1));
//        System.out.println(blockingQueue.offer(2));
//        System.out.println(blockingQueue.offer(3));//cause queue is full , return false
//
//        System.out.println(blockingQueue.peek());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());//no more element , return null

//        //group 3
//
//        blockingQueue.put(1);
//        blockingQueue.put(2);
//       //blockingQueue.put(3); put() is blocking, when queue is full
//
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        //System.out.println(blockingQueue.take()); take() is blocking, when no more element in queue

        //group 4
        System.out.println(blockingQueue.offer(1, 3L, TimeUnit.MILLISECONDS));
        System.out.println(blockingQueue.offer(1, 3L, TimeUnit.MILLISECONDS));
        //System.out.println(blockingQueue.offer(1, 3L, TimeUnit.MILLISECONDS)); return false, when cannot offer element in 3 ms after queue is full

        System.out.println(blockingQueue.poll(3L, TimeUnit.MILLISECONDS));
        System.out.println(blockingQueue.poll(3L, TimeUnit.MILLISECONDS));
        //System.out.println(blockingQueue.poll(3L, TimeUnit.MILLISECONDS));return null , when cannot poll element in 3ms after queue is empty
    }
}
