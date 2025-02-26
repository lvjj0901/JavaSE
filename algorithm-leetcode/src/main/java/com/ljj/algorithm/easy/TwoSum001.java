package com.ljj.algorithm.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Given an array of integers nums and an integer target,
 * return indices of the two numbers such that they add up to target.
 * <p>
 * You may assume that each input would have exactly one solution,
 * and you may not use the same element twice.
 * <p>
 * You can return the answer in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 * <p>
 * Constraints:
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 * <p>
 * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 *
 * @Author Jason Lyu
 * @Create 2025-02-25 7:36 p.m.
 * @Version 1.0
 */
public class TwoSum001 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 8;
        int[] result1 = twoSum1(nums,target);
        System.out.println("twoSum1:"+ Arrays.toString(result1));
        int[] result2 = twoSum2(nums,target);
        System.out.println("twoSum2:"+ Arrays.toString(result2));
    }

    /**
     * my solution 1:
     * The time complexity is O(n^2)
     */
    public static int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = (i+1); j < nums.length ; j++) {
                if ((nums[i] + nums[j]) == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{};
    }
    /**
     * solution 2:
     * new int[]{2, 7, 11, 15}
     * The time complexity is O(n)
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
           int first = nums[i];
           int second = target - first;
           if(map.containsKey(second)){
               return new int[]{i,(int)map.get(second)};
           }
            map.put(first,i);
        }
        return new int[]{};
    }
}
