package com.ljj.newfeature.stream;

import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-13 4:05 p.m.
 * @Version 1.0
 */
public class Stream_03_2_End_reduce_Test {
    @Test
    public void test1(){
        Integer reduce = Stream.of(1, 2, 4, 5, 7, 8)
                .reduce(0, (x1, x2) -> x1 + x2);
        System.out.println(reduce);
    }
    @Test
    public void test2(){
        Optional<Integer> reduce = Stream.of(1, 2, 4, 5, 7, 8)
                .reduce((x1, x2) -> x1 > x2 ? x1 : x2);
        System.out.println(reduce.get());
    }
}
