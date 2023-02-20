package com.skuu.math.thread;

/**
 * 两个线程交替运行,wait，notify和synchronized
 *
 * @author dcx
 * @since 2023-02-20 15:44
 **/
public class TwoThread {

    public static void main(String[] args) {
        String str1 = "123456789";
        String str2 = "abcdefghi";
        Object o1 = new Object();
        new Thread(
                ()->{
                    for (int i = 0; i < str1.length(); i++) {
                        synchronized (o1){
                            System.out.println(str1.charAt(i));
                            try {
                                o1.notify();
                                o1.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
        ).start();

        new Thread(
                ()->{
                    for (int i = 0; i < str2.length(); i++) {
                        synchronized (o1){
                            System.out.println(str2.charAt(i));
                            try {
                                o1.notify();
                                o1.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
        ).start();

    }
}
