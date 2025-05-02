package com.ljj.juc.collection;

import java.util.*;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-18 9:47 a.m.
 * @Version 1.0
 */
public class ArrayListOfThreadSafeByCollections {
    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString());
                System.out.println(list);
            }).start();
        }
    }
}
