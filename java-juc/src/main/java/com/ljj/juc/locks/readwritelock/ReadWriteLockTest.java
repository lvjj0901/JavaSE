package com.ljj.juc.locks.readwritelock;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-21 9:09 a.m.
 * @Version 1.0
 */

class MyShare{
    private volatile HashMap<String,String> map = new HashMap<>();
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    public void put(String k,String v){
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" Starting to write data...");
            Thread.sleep(100);
            map.put(k,v);
            System.out.println(Thread.currentThread().getName()+" Finish writing....");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            rwLock.writeLock().unlock();
        }
    }

    public String get(String k){
        String result = null;
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" Starting to read data---");
            Thread.sleep(50);
            result = map.get(k);
            System.out.println(Thread.currentThread().getName()+" Finish reading---");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            rwLock.readLock().unlock();
        }
        return result;
    }
}
public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyShare myShare = new MyShare();
        //5 threads do write
        for (int i = 1; i <= 5; i++) {
            final String tmp = i+"";
            new Thread(()->{
                myShare.put(tmp,tmp);
            },String.valueOf(i)+"-->").start();
        }

        //5 threads do read
        for (int i = 1; i <= 5; i++) {
            final String tmp = i+"";
            new Thread(()->{
                myShare.get(tmp);
            },String.valueOf(i)+">>>").start();
        }
    }
}
