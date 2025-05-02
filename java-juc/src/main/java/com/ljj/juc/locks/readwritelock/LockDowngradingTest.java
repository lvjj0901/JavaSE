package com.ljj.juc.locks.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description:
 * If you want to lock downgrade,
 * you must follow the following code sequence:
 * writeLock.lock() -> readLock.lock() -> writeLock.unlock()-> readLock.unlock().
 * It means write lock can downgrade to read lock, read lock cannot upgrade write lock!!!
 * @Author Jason Lyu
 * @Create 2025-04-21 10:09 a.m.
 * @Version 1.0
 */
public class LockDowngradingTest {
    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

        //1 write lock
        writeLock.lock();
        System.out.println("got write lock");

        //2 read lock
        readLock.lock();
        System.out.println("got read lock");

        //3 write unlock
        writeLock.unlock();

        //4 read unlock
        readLock.unlock();
    }
}
