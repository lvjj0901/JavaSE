package com.ljj.newfeature.methodreferences;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.Function;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-11 11:50 a.m.
 * @Version 1.0
 */
public class ArrayConstructorReferencesTest {
    //数组引用
    //Function中的R apply(T t)
    @Test
    public void test() {
        //Lambda
        Function<Integer,String[]> fun1 = length->new String[length];
        System.out.println(Arrays.toString(fun1.apply(2)));

        //Array constructor references

        Function<Integer,String[]> fun2 = String[] :: new;
        System.out.println(Arrays.toString(fun2.apply(3)));
    }
}
