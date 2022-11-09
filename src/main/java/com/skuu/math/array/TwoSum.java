package com.skuu.math.array;

import java.util.Arrays;
import java.util.HashMap;

/***
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 **/
public class TwoSum {

    /***
     * @Author dcx
     * @Description //TODO
     * 时间复杂度O(n),
     * 空间复杂度:O(n)
     *执行用时 :2 ms, 在所有 Java 提交中击败了99.64%的用户
     * 内存消耗 :39.8 MB, 在所有 Java 提交中击败了5.06%的用户
     *
     * @Date 17:25 2020/5/8
     * @Param [nums, target]
     * @return int[]
     **/
    public int[] twoSum(int[] nums, int target) {
        //map,key是值,value是下标
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        //循环一次
        for (int i = 0; i < nums.length; i++) {
            //求差
            int tmp = target - nums[i];
            //如果map里面有了差,输出value下标
            if (hashMap.containsKey(tmp)) {
                return new int[]{i, hashMap.get(tmp)};
                //否则,将此次循环到的下标和值放进map.
            } else {
                hashMap.put(nums[i], i);
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
