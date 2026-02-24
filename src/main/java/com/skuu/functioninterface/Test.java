package com.skuu.functioninterface;

/**
 * @author dcx
 * @description
 * @create 2025-10-30 17:03
 **/
public class Test {
    public static void main(String[] args) {
        A a = Integer::sum;
        int aa = a.aa(1, 2);
        System.out.println(aa);
    }
}
