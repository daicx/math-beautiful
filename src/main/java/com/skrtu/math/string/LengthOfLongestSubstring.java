package com.skrtu.math.string;

/***
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 **/
public class LengthOfLongestSubstring {

    /***
     * @Author dcx
     * @Description //TODO
     * 滑动窗口模型
     *
     *时间复杂度：O*(*n)
     * 空间复杂度：O(n)
     * 执行用时 :11 ms, 在所有 Java 提交中击败了41.43%的用户
     * 内存消耗 :40.5 MB, 在所有 Java 提交中击败了5.20%的用户
     * @Date 18:27 2020/5/11
     * @Param [s]
     * @return int
     **/
    public int lengthOfLongestSubstring(String s) {
        //需要求得字符串,作为滑动的窗口队列,可以是最长的,也可以是最短的
        String subStr = "";
        //临时窗口
        String tmpStr = "";
        for (int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));
            //临时窗口有就去掉元素之前的,再加入新元素,没有就直接加入新元素
            tmpStr = (tmpStr.contains(c) ? tmpStr.substring(tmpStr.indexOf(c) + 1) : tmpStr) + c;
            //记录出现的最长的一个临时窗口
            subStr = subStr.length() > tmpStr.length() ? subStr : tmpStr;
        }
        return subStr.length();
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        int i = lengthOfLongestSubstring.lengthOfLongestSubstring("abcabcbb");
        System.out.println(i);
    }
}
