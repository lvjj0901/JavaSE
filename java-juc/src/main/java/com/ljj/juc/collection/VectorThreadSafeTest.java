package com.ljj.juc.collection;

import java.util.UUID;
import java.util.Vector;

/**
 * Description:
 * Though Vector is an old collection,
 * it is thread-safe.
 * @Author Jason Lyu
 * @Create 2025-04-18 9:37 a.m.
 * @Version 1.0
 */
public class VectorThreadSafeTest {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                vector.add(UUID.randomUUID().toString());
                System.out.println(vector);
            }).start();
        }
    }
}
