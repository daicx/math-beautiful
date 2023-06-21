package com.skuu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * jdk 动态代理,实现日志打印
 *
 * @author dcx
 * @since 2023-06-21 10:12
 **/
public class LogHandler implements InvocationHandler {

    //被代理的对象
    Object target;

    public LogHandler(Object target) {
        this.target = target;
    }

    /**
     * 反射对象
     * @param proxy 代理对象
     * @param method    代理的方法
     * @param args  方法入参
     * @return java.lang.Object  方法执行结果
     * @author dcx
     * @date 2023/6/21 10:15
     **/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void before() {
        System.out.println("before: " + new Date());
    }

    private void after() {
        System.out.println("after: " + new Date());
    }
}
