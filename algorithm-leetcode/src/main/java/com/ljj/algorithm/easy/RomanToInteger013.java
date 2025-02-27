package com.ljj.algorithm.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * <p>
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "III"
 * Output: 3
 * Explanation: III = 3.
 * Example 2:
 * <p>
 * Input: s = "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 * Example 3:
 * <p>
 * Input: s = "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 15
 * s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
 * It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 *
 * @Author Jason Lyu
 * @Create 2025-02-26 6:53 p.m.
 * @Version 1.0
 */
public class RomanToInteger013 {
    public static void main(String[] args) {
//        String s = "MCMXCIV";//1994
//        String s = "LVIII";//58
//        String s = "III";//3
        String s = "LVIVII";//3
        int number = romanToInt(s);
        System.out.println(s+"="+number);
    }

    /**
     * solution 1
     * No one has written an algorithm as low-level as mine
     * but Its time O(n)
     * @param s
     * @return
     */
    public static int romanToInt(String s) {

        HashMap<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);
        int length = s.length();
        int back = length;
        int front = length - 2;
        int total = 0;
        while(front>=0){
            String tmp = s.substring(front,back);
            if(map.containsKey(tmp)){
                total+=map.get(tmp);
                back = front;
                front = front -2;
                if(front<0){
                    front = 0;
                    tmp = s.substring(front,back);
                    total+=map.get(tmp);
                    break;
                }
            }else {
                tmp = s.substring(++front,back);
                total+=map.get(tmp);
                back = front;
                front = front -2;
                if(front<0){
                    front = 0;
                    tmp = s.substring(front,back);
                    total+=map.get(tmp);
                    break;
                }
            }
        }
        return total;
    }

    /**
     * solution 2
     *  need high-level code
     * @param s
     * @return
     */
    public static int romanToInt2(String s) {
        //thinking ...
        int total = 0;
        return total;
    }
}
