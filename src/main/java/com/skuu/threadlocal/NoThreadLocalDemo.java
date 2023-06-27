package com.skuu.threadlocal;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 本地变量测试
 *
 * @author dcx
 * @since 2023-06-26 14:57
 **/
public class NoThreadLocalDemo {
    //类属性保存当前用户名
    private static String curName;

    public void login(String name) {
        System.out.println(name + " login");
        curName = name;
    }

    public void getCur() {
        System.out.println(curName+" get");
    }

    public void logout() {
        System.out.println(curName+" logout");
        curName = null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NoThreadLocalDemo threadLocalDemo = new NoThreadLocalDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(() -> {
            threadLocalDemo.login(1 + "");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocalDemo.getCur();
            threadLocalDemo.logout();
        });

        executorService.execute(() -> {
            threadLocalDemo.login(2 + "");
            threadLocalDemo.getCur();
            threadLocalDemo.logout();
        });
        System.out.println("------");
    }

}
