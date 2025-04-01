package com.ljj.thread.threadsafemore;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-03-26 9:25 a.m.
 * @Version 1.0
 */
public class EagerSingletonTest{
    //static EagerSingleton es1 = null;
    //static EagerSingleton es2 = null;
    static EnumEagerSingleton es1 = null;
    static EnumEagerSingleton es2 = null;
    public static void main(String[] args) {

        Thread t1 = new Thread(){
            @Override
            public void run() {
                //es1 = EagerSingleton.getInstance();
                es1 = EnumEagerSingleton.INSTANCE;
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                //es2 = EagerSingleton.getInstance();
                es2 = EnumEagerSingleton.INSTANCE;
            }
        };

        t1.start();
        t2.start();

        // make sure t1 thread will be executed before main thread is terminated .
        // so the variable es1 will definitely be initialized.
        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // make sure t2 thread will be executed before main thread is terminated.
        // the variable es2 will be definitely initialized.
        try {
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(es1 == es2);

    }
}
/**
 * Form 1 for eager singleton design pattern
 * The singleton instance is created directly during class initialization,
 * and the class initialization process is inherently thread-safe.
 */
class EagerSingleton {
    private static EagerSingleton instance = new EagerSingleton();
    private EagerSingleton(){
    }
    public static EagerSingleton getInstance(){
        return instance;
    }
}

/**
 * implement eager singleton pattern using enumeration
 */
enum EnumEagerSingleton{
    INSTANCE;
}
