package com.ljj.datastructure;

import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-02-27 10:05 p.m.
 * @Version 1.0
 */
public class JasonArrayTest {
    @Test
    public void test(){
        JasonArray array = new JasonArray(5);
        array.add(123);
        array.add("AA");
        array.add("BB");
        array.add(456);
        array.add(new Date());
        array.add(888);
        array.printArray();
    }
}
