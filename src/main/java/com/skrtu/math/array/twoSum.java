package com.skrtu.math.array;

import java.util.Arrays;
import java.util.HashMap;

/***
 * @Author dcx
 * @Description //TODO
 * @Date 17:53 2020/5/6
 * @Param
 * @return
 **/
public class twoSum {

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
        twoSum twoSum = new twoSum();
        int[] ints = twoSum.twoSum(arr, targert);
        System.out.println(Arrays.toString(ints));
    }
}
