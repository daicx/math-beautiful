package com.skuu.threadlocal;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 变量父子线程传递
 *
 * @author dcx
 * @since 2023-06-27 11:23
 *
 **/
public class InheritableThreadLocalDemo {
    private static final InheritableThreadLocal<String> curName = new InheritableThreadLocal<>();

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
        InheritableThreadLocalDemo threadLocalDemo = new InheritableThreadLocalDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
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
        executorService.execute( () -> {
            threadLocalDemo.login(2 + "");
            threadLocalDemo.getCur();
            threadLocalDemo.logout();
        });
        System.out.println("---------");
    }
}
