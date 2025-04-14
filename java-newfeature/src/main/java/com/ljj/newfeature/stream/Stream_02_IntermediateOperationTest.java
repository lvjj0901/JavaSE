package com.ljj.newfeature.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-12 5:41 p.m.
 * @Version 1.0
 */
public class Stream_02_IntermediateOperationTest {
    @Test
    public void test1() {
        //create an instance of Stream
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8);
        //set filter rules
        stream = stream.filter(integer -> integer % 2 == 0);
        //print result
        stream.forEach(System.out::println);
    }

    @Test
    public void test2() {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .filter(x -> x % 2 != 0)
                .forEach(System.out::println);
    }

    //test distinct()
    @Test
    public void test3() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6);
        stream.distinct().forEach(System.out::println);
    }

    //test limit()
    @Test
    public void test4() {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .limit(3)
                .forEach(System.out::println);
    }

    //the composition of middle operation
    @Test
    public void test5() {
        Stream.of(1, 2, 2, 3, 3, 4, 4, 5, 2, 3, 4, 5, 6, 7)
                .distinct()
                .filter(x -> x % 2 == 0)
                .limit(3)
                .forEach(System.out::println);
    }

    //test skip()
    @Test
    public void test6() {
        Stream.of(1, 2, 3, 4, 5, 6, 2, 2, 3, 3, 4, 4, 5)
                .skip(5)
                .forEach(System.out::println);
    }

    //composition
    @Test
    public void test7() {
        Stream.of(1, 2, 3, 4, 5, 6, 2, 2, 3, 3, 4, 4, 5)
                .skip(5)
                .distinct()
                .filter(x -> x % 3 == 0)
                .forEach(System.out::println);
    }
    @Test
    public void test8(){
        long count = Stream.of(1, 2, 3, 4, 5, 6, 2, 2, 3, 3, 4, 4, 5)
                .distinct()
                .peek(System.out::println)
                .count();
        System.out.println("count: "+count);
    }
    @Test
    public void test9(){
        //requirements:find out the first three largest number without duplicate
        Stream.of(11,2,39,4,54,6,2,22,3,3,4,54,54)
                .distinct()
                .sorted((x1,x2)->-(x1-x2))
                .limit(3)
                .forEach(System.out::println);
    }
    //test map()
    @Test
    public void test10(){
        Stream.of(1,2,3,4,5)
                .map(x->x+1)
                .forEach(System.out::println);
    }
    @Test
    public void test11(){
        String[] strs = {"Hello","java","Stream"};
        Arrays.stream(strs)
                .map(x->x.toUpperCase())
                .forEach(System.out::println);
    }
    //test flatMap()
    @Test
    public void test12(){
        String[] strs = {"Hello","java","Stream"};
        Arrays.stream(strs)
                .flatMap(s->Stream.of(s.split("|")))
                .forEach(System.out::println);
    }
}
