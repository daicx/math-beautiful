package com.skuu.design.singleton;

/**
 * @author dcx
 * @description 使用
 * @create 2025-08-04 17:11
 **/
public class Test {

    public static void main(String[] args) {
        Client client = Single.INSTANCE.getClient();
        Client client1 = Single.INSTANCE.getClient();
        System.out.println(client == client1);
    }
}
