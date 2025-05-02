package com.ljj.juc.collection;


import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-18 10:16 a.m.
 * @Version 1.0
 */
public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                arrayList.add(UUID.randomUUID().toString());
                System.out.println(arrayList);
            }).start();
        }
    }
}
