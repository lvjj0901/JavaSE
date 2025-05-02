package com.ljj.juc.collection;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Description:
 * Here is a not thread-safe demo for collection
 * multiple threads modify the same collection at the same time
 *
 * @Author Jason Lyu
 * @Create 2025-04-18 8:41 a.m.
 * @Version 1.0
 */
public class ArrayListNotThreadSafeTest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString());
                System.out.println(list);
            }, "AA").start();
        }

    }
}
