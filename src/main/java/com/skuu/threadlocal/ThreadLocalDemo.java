package com.skuu.threadlocal;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程本地变量测试
 *
 * @author dcx
 * @since 2023-06-26 14:57
 **/
public class ThreadLocalDemo {
    private static final ThreadLocal<String> curName = new ThreadLocal<>();

    public void login(String name) {
        System.out.println(name + " login");
        curName.set(name);
    }

    public void getCur() {
        String s = curName.get();
        System.out.println(s + " get");
        new Thread(
                ()->{
                    System.out.println("子线程获取name："+curName.get());
                }
        ).start();
    }

    public void logout() {
        System.out.println(curName.get() + " logout");
        curName.remove();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
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
        System.out.println("---------");
    }

}
