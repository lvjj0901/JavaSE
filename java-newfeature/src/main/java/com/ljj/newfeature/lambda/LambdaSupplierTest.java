package com.ljj.newfeature.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-08 10:35 a.m.
 * @Version 1.0
 */
public class LambdaSupplierTest {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "Java Lambda Supplier";
        System.out.println(supplier.get());
    }
    
    @Test
    public void testStreamSupplier(){
        Stream.generate(()->(int) (Math.random() * (10 - 1 + 1)) + 1).limit(5).forEach(s-> System.out.println(s));
    }

}
