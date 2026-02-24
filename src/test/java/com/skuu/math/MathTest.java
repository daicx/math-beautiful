package com.skuu.math;

import java.util.Arrays;
import java.util.List;

/**
 * 测试类
 *
 * @author dcx
 * @since 2023-02-20 16:58
 **/
public class MathTest {


    public static void main1(String[] args) {
        int[] ints = new int[2];
        int[] ints1 = {12,21};
        int[] ints2 = {};
        int length = ints.length;
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax.stream().map((cost) -> cost + 0.12*cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);
    }

    public static void main(String[] args) {
        String silenceLock = String.format("%s::%s", "ddddd", 111);
        System.out.println(silenceLock);
Byte a = 1;
        System.out.println(a.toString());
    }

}
