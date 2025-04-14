package com.ljj.newfeature.stream;

import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-13 3:36 p.m.
 * @Version 1.0
 */
public class Stream_03_1_End_Match_Find_Test {
    @Test
    public void test1() {
        boolean b = Stream.of(1, 3, 5, 7)
                .allMatch(x -> x % 2 != 0);
        System.out.println(b);
    }

    @Test
    public void test2() {
        boolean b = Stream.of(1, 2, 5, 7)
                .anyMatch(x -> x % 2 == 0);
        System.out.println(b);
    }

    @Test
    public void test3() {
        boolean b = Stream.of(1, 3, 5, 7)
                .noneMatch(x -> x % 2 == 0);
        System.out.println(b);
    }

    @Test
    public void test4() {
        Optional<Integer> first = Stream.of(1, 2, 3, 4)
                .findFirst();
        System.out.println(first.get());
    }

    @Test
    public void test5() {
        Optional<Integer> any = Stream.of(1, 2, 3, 4, 5)
                .findAny();
        System.out.println(any.get());
    }

    @Test
    public void test6() {
        long count = Stream.of(1, 2, 3, 4, 5)
                .count();
        System.out.println(count);
    }
    @Test
    public void test7(){
        Optional<Integer> max = Stream.of(1, 2, 3, 14, 5, 6, 7)
                .max(Integer::compare);
        System.out.println(max.get());
    }
    @Test
    public void test8(){
        Optional<Integer> min = Stream.of(1, 2, 3, -14, 5, 6, 7)
                .min(Integer::compareTo);
        System.out.println(min.get());
    }
    @Test
    public void test9(){
        Stream.of(1,2,3,4,5,6,7)
                .forEach(System.out::println);
    }
}
