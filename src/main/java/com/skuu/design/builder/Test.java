package com.skuu.design.builder;

/**
 * @author dcx
 * @description
 * @create 2025-09-16 18:36
 **/
public class Test {
    public static void main(String[] args) {
        User user = User.builder()
                .id(1)
                .name("nnn")
                .desc("ddd")
                .build();
        System.out.println(user);
    }
}
