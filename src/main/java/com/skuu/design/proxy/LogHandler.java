package com.skuu.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * jdk 动态代理,实现日志打印
 *限制：只能基于接口生成代理.
 * 重要细节：
 * 1.继承 Proxy
 * 2.实现目标接口（如 OrderService）
 * 3.每个接口方法调用委托给 h.invoke()
 * // 伪代码，展示代理类的结构
 *
 * public final class $Proxy0 extends Proxy implements OrderService {
 *
 *     private static Method m1;  // create方法
 *     private static Method m2;  // pay方法
 *
 *     static {
 *         m1 = Class.forName("OrderService").getMethod("create", int.class);
 *         m2 = Class.forName("OrderService").getMethod("pay", int.class);
 *     }
 *
 *     // 所有接口方法都会委托给InvocationHandler
 *     @Override
 *     public final void create(int var1) {
 *         try {
 *             h.invoke(this, m1, new Object[]{var1});
 *         } catch (Throwable e) {
 *             throw new UndeclaredThrowableException(e);
 *         }
 *     }
 *
 *     @Override
 *     public final void pay(int var1) {
 *         try {
 *             h.invoke(this, m2, new Object[]{var1});
 *         } catch (Throwable e) {
 *             throw new UndeclaredThrowableException(e);
 *         }
 *     }
 * }
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
