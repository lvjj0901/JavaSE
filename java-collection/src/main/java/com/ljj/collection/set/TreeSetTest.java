package com.ljj.collection.set;

import org.junit.jupiter.api.Test;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-02-24 10:12 a.m.
 * @Version 1.0
 */
public class TreeSetTest {
    /**
     * 产生a到b的随机数。
     * 公式为:(int) (Math.random()*(b-a+1)+a)
     * 产生1到9的随机数
     */
    @Test
    public void testRandomNumber(){
        for (int i = 0; i < 10; i++) {
        int ramdom = (int) (Math.random()*(9-1+1)+1);
            System.out.println("1到10的随机数:"+ramdom);
        }

    }
}
