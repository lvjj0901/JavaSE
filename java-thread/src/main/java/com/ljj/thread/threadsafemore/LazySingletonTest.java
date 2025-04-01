package com.ljj.thread.threadsafemore;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-03-27 9:36 a.m.
 * @Version 1.0
 */
public class LazySingletonTest {
    static LazySingleton1 ls1 = null;
    static LazySingleton1 ls2 = null;
    public static void main(String[] args) {


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ls1 = LazySingleton1.getInstance1();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                ls2 = LazySingleton1.getInstance1();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(ls1==ls2);
    }
}

/**
 * Form 1 for Lazy singleton implementation
 */
class LazySingleton1{
    private volatile static LazySingleton1 instance = null;
    private LazySingleton1(){}
    //implementation 1
    public synchronized static LazySingleton1 getInstance1(){
        if(instance==null){
            instance = new LazySingleton1();
        }
        return instance;
    }
    //implementation 2
    public static LazySingleton1 getInstance2(){
        synchronized (LazySingleton1.class) {
            if (instance == null) {
                instance = new LazySingleton1();
            }
        }
        return instance;
    }
    //implementation 3
    public static LazySingleton1 getInstance3(){
        if(instance==null) {
            synchronized (LazySingleton1.class) {
                if (instance == null) {
                    instance = new LazySingleton1();
                }
            }
        }
        return instance;
    }
}
/**
 * Form 2 for Lazy singleton implementation using Inner class,
 * it is the best one of singleton implementation,
 * cause it is thread-safe even no synchronized,
 * the inner class will initialize when it will be invoked.
 * The inner class can asle be defined using enum.
 */
class LazySingleton2{
    private LazySingleton2(){}

    public static LazySingleton2 getInstance() {
        return Inner.instance;
    }
    //implementation using inner class
    private static class Inner{
        static final LazySingleton2 instance = new LazySingleton2();
    }
}

class LazySingleton3{
    private LazySingleton3(){}

    public static LazySingleton3 getInstance() {
        return Inner.INSTANCE.getInstance();
    }
    //implementation using enum
    private enum Inner{
        INSTANCE;
        private final LazySingleton3 instance;
        Inner(){
            instance =new LazySingleton3();
        }

        private LazySingleton3 getInstance(){
            return instance;
        }
    }
}