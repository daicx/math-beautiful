package com.skuu.math;

/**
 * 测试类
 *
 * @author dcx
 * @since 2023-02-20 16:58
 **/
public class MathTest {

    public int lengthOfLongestSubstring(String s) {
        String res = "";
        String temp = "";
        for (int i = 0; i < s.length(); i++) {
            String c = s.charAt(i) + "";
            if (temp.contains(c)) {
                int index = temp.indexOf(c);
                temp = temp.substring(index+1) + c;
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
        MathTest mathTest = new MathTest();
        mathTest.lengthOfLongestSubstring("pwwkew");
    }
}
