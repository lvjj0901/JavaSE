package com.ljj.newfeature.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-08 10:29 a.m.
 * @Version 1.0
 */
public class LambdaConsumerTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Andy", "Jason", "Wanda");
        list.forEach(s -> System.out.println(s));
    }

    @Test
    public void testCollectionConsumer(){
        List<String> list = Arrays.asList("Hello", "java", "Lambda");
        list.forEach(s-> System.out.println(s));
    }
    
    @Test
    public void testMapConsumer(){
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1,"Hello");
        map.put(2,"andy");
        map.put(3,"jason");
        map.put(4,"wanda");

        map.forEach((k,v)-> System.out.println(k+":"+v));
    }
}
