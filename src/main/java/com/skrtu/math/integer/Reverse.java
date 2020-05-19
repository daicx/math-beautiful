package com.skrtu.math.integer;

/***
 * @Author dcx
 * @Description //TODO 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * @Date 10:34 2020/5/19
 **/
public class Reverse {
    /***
     * @Author dcx
     * @Description //TODO
     *
     * 时间复杂度：O(log(x)),x 中大约有 lg (x) 位数字
     * 空间复杂度：O(1)
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 :37.1 MB, 在所有 Java 提交中击败了5.33%的用户
     *
     * @Date 10:35 2020/5/19
     * @Param [x]
     * @return int
     **/
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            //提前预判性会不会溢出
            if (rev * 10 / 10 != rev) return 0;
            //依次加进结果
            rev = rev * 10 + x % 10;
            //依次取出
            x /= 10;
        }
        return rev;
    }

    public static void main(String[] args) {
        Reverse reverse = new Reverse();
        int reverse1 = reverse.reverse(1534236469);
        System.out.println(reverse1);
    }
}
