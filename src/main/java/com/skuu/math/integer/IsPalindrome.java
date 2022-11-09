package com.skuu.math.integer;

/***
 * @Author dcx
 * @Description //TODO 回文数
 * @Date 15:04 2020/5/20
 **/
public class IsPalindrome {

    /***
     * @Author dcx
     * @Description //TODO
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     *
     *时间复杂度: O(n)
     * 空间复杂度: O(1)
     * 执行用时 :9 ms, 在所有 Java 提交中击败了99.27%的用户
     * 内存消耗 :39.4 MB, 在所有 Java 提交中击败了5.14%的用户
     *
     * @Date 15:05 2020/5/20
     * @Param [x]
     * @return boolean
     **/
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int rev = 0;
        int copy = x;
        while (x != 0) {
            //累加数
            rev = rev * 10 + x % 10;
            //累取数
            x /= 10;
        }
        return copy == rev;
    }

    public static void main(String[] args) {
        IsPalindrome isPalindrome = new IsPalindrome();
        boolean palindrome = isPalindrome.isPalindrome(121);
        System.out.println(palindrome);
    }
}
