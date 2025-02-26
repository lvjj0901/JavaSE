package com.ljj.algorithm.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * <p>
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: x = 121
 * Output: true
 * Explanation: 121 reads as 121 from left to right and from right to left.
 * Example 2:
 * <p>
 * Input: x = -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * Example 3:
 * <p>
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -231 <= x <= 231 - 1
 * <p>
 * <p>
 * Follow up: Could you solve it without converting the integer to a string?
 *
 * @Author Jason Lyu
 * @Create 2025-02-25 10:06 p.m.
 * @Version 1.0
 */
public class PalindromeNumber009 {
    public static void main(String[] args) {
        int x = 12421;
        boolean flag1 = isPalindrome1(x);
        System.out.println(flag1);

        boolean flag2 = isPalindrome2(x);
        System.out.println(flag2);
    }

    /**
     * solution 1
     * sorry ! I used String !
     * @param x
     * @return
     */
    public static boolean isPalindrome1(int x) {
        String str = x+"";
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length/2; i++) {
            char tmp = chars[chars.length-1-i];
            chars[chars.length-1-i] = chars[i];
            chars[i] = tmp;
        }
        return str.equals(String.valueOf(chars));
    }
    /**
     * solution 2
     * forget String !!!
     * @param x 1234321
     * @return
     */
    public static boolean isPalindrome2(int x) {
        int origin = x;
        int newNum = 0;
        int tmp = 0 ;
        while(x != 0){
            tmp = x % 10;
            newNum = newNum*10 + tmp;
            x/=10;
        }
        return origin == newNum;
    }
}
