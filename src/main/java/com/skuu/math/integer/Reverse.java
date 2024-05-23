package com.skuu.math.integer;

/***
 * @Author dcx
 * @Description
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <a href="https://leetcode.cn/problems/reverse-integer/submissions/534091633/">...</a>
 * @Date 10:34 2020/5/19
 **/
public class Reverse {


    /**
     * 关键点：
     * 1.用整除（/）循环
     * 2.用取余（%）叠加
     * 3.int 最大值和最小值判定。
     *
     * @param x
     * @return int
     * @author dcx
     * @date 2024/5/23 10:00
     **/
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            if (result > 214748364 || result < -214748364) {
                return 0;
            }
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(123 / 10);
        System.out.println(123 % 10);
    }
}
