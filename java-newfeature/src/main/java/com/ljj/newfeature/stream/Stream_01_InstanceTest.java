package com.ljj.newfeature.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-12 3:22 p.m.
 * @Version 1.0
 */
public class Stream_01_InstanceTest {
    //Method 1:using Collection
    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        Stream<Integer> stream = list.stream();
    }

    //Method 2:using Array
    @Test
    public void test2(){
        String[] array = {"Hello","Stream"};
        Stream<String> stream = Arrays.stream(array);
    }

    @Test
    public void test3(){
        int[] array ={1,2,3,4,5,6};
        IntStream stream = Arrays.stream(array);
    }

    //Method 3:using the of() fo Stream
    @Test
    public void test4(){
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        stream.forEach(System.out::println);
    }
    
    //Method 4:
    @Test
    public void test5(){
        // iterator
        Stream<Integer> stream = Stream.iterate(0, x -> x + 2);
        stream.limit(10).forEach(System.out::println);

        //generate
        Stream.generate(Math::random).limit(2).forEach(System.out::println);
    }
}
