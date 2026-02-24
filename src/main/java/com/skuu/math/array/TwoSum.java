package com.skuu.math.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/***
 * 1.给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <OrderCglibProxy href="https://leetcode.cn/problems/two-sum/description/">...</OrderCglibProxy>
 **/
public class TwoSum {

    /***
     * 关键点：
     * 1。b=c-OrderCglibProxy,遍历数组的时候，缓存下a的值和下标。这样在遍历到b的时候，可以快速定位带a。
     * 2。循环一次。
     * 时间复杂度O(n),
     * 空间复杂度:O(n)
     *
     * @Date 17:25 2020/5/8
     * @Param [nums, target]
     * @return int[]
     **/
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int a =  nums[i];
            int b = target-a;
            if (map.containsKey(b)){
                return new int[]{i,map.get(b)};
            }else {
                map.put(a,i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 7, 11, 15};
        int targert = 9;
        TwoSum twoSum = new TwoSum();
        int[] ints = twoSum.twoSum(arr, targert);
        System.out.println(Arrays.toString(ints));
    }
}
