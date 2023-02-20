package com.skuu.math.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * 两个线程交替运行,LockSupport
 *
 * @author dcx
 * @since 2023-02-20 15:44
 **/
public class TwoThread1 {

    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) {
        String str1 = "123456789";
        String str2 = "abcdefghi";

        t1 = new Thread(
                () -> {
                    for (int i = 0; i < str1.length(); i++) {
                        System.out.print(str1.charAt(i));
                        LockSupport.unpark(t2);
                        LockSupport.park();

                    }
                }
        );

        t2 = new Thread(
                () -> {
                    for (int i = 0; i < str2.length(); i++) {
                        System.out.print(str2.charAt(i));
                        LockSupport.unpark(t1);
                        LockSupport.park();
                    }
                }
        );
        t1.start();
        t2.start();

    }
}
