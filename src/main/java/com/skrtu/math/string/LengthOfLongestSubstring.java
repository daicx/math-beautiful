package com.skrtu.math.string;

import java.util.HashMap;

/***
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 **/
public class LengthOfLongestSubstring {

    /***
     * @Author dcx
     * @Description //TODO
     * @Date 18:27 2020/5/11
     * @Param [s]
     * @return int
     **/
    public int lengthOfLongestSubstring(String s) {
        //记录起始点下标开始
        int left = 0;
        //记录最大长度
        int max = 0;
        //key存值,value存最大长度
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //当map中存在的时候,重置left为最大的一个
            if (map.containsKey(c)) {
                left = i + 1;
            }
            map.put(c, i);
            //求最大的
            max = Math.max(max, i-left+1);
        }
        return max;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        int i = lengthOfLongestSubstring.lengthOfLongestSubstring("pwwkew");
        System.out.println(i);
    }
}
