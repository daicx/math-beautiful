package com.skuu.math.string;

/***
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/">...</a>
 **/
public class LengthOfLongestSubstring {

    /***
     * 滑动窗口模型
     * 关键点：
     * 1。需要一个结果字符串保存最长的字符串。
     * 2。一个临时字符串，做为滑动窗口。
     *
     * 时间复杂度：O*(*n)
     * 空间复杂度：O(n)
     * @Date 18:27 2020/5/11
     * @Param [s]
     * @return int
     **/
    public int lengthOfLongestSubstring(String s) {
        String res = "";
        String temp = "";
        for (int i = 0; i < s.length(); i++) {
            String c = s.charAt(i) + "";
            if (temp.contains(c)) {
                int index = temp.indexOf(c);
                temp = temp.substring(index + 1) + c;
            } else {
                temp += c;
            }
            if (temp.length() > res.length()) {
                res = temp;
            }
        }
        return res.length();
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        int i = lengthOfLongestSubstring.lengthOfLongestSubstring("abcabcbb");
        System.out.println(i);
    }
}
