package com.skuu.math.string;

/***
 * @Author dcx
 * @Description //TODO 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * @Date 10:42 2020/5/18
 **/
public class LongestPalindrome {

    /***
     * @Author dcx
     * @Description //TODO 中心扩散法
     *
     *时间复杂度：O*(*n^2)
     *
     * 空间复杂度：O(1)
     *
     * 执行用时 :5 ms, 在所有 Java 提交中击败了96.54%的用户
     *
     * 内存消耗 :39.5 MB, 在所有 Java 提交中击败了15.18%的用户
     *
     * @Date 17:39 2020/5/18
     * @Param [s]
     * @return java.lang.String
     **/
    public String longestPalindrome(String s) {
        String result = "";
        char[] chars = s.toCharArray();
        //相邻字母重复的时候,直接跳过
        int beginIndex = 0;
        while (beginIndex < chars.length) {
            int min = beginIndex;
            int max = beginIndex + 1;
            //找到相邻字母重复的临界下标
            while (max < chars.length && chars[min] == chars[max]) {
                max++;
            }
            //再次进行判断的时候,不需要再去判断重复的字母.
            beginIndex = max;
            //从中心向外扩展
            while (min - 1 >= 0 && max < chars.length && chars[min - 1] == chars[max]) {
                min--;
                max++;
            }
            //记录中间最长的字符串
            result = max - min > result.length() ? s.substring(min, max) : result;
            //当循环的点到最右边的距离小于已经出现的最大字符的一半时,后续的判断也不再需要
            if (chars.length - beginIndex < result.length() / 2) {
                break;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        String s = longestPalindrome.longestPalindrome("bccaccb");
        System.out.println(s);
    }
}
