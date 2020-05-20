package com.skrtu.math.integer;

/***
 * @Author dcx
 * @Description //TODO 回文数
 * @Date 15:04 2020/5/20
 **/
public class IsPalindrome {

    /***
     * @Author dcx
     * @Description //TODO
     * @Date 15:05 2020/5/20
     * @Param [x]
     * @return boolean
     **/
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
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
