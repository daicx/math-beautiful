package com.skuu.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * cglib动态代理处理
 *
 * @author dcx
 * @since 2023-06-21 10:59
 **/
public class LogInterceptor implements MethodInterceptor {

    /**
     * 对象执行拦截
     *
     * @param o           被代理的对象
     * @param method      拦截的方法
     * @param objects     参数列表
     * @param methodProxy 对方法的代理
     * @return java.lang.Object
     * @author dcx
     * @date 2023/6/21 11:00
     **/
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        // 注意这里是调用 invokeSuper 而不是 invoke，否则死循环，methodProxy.invokesuper执行的是原始类的方法，method.invoke执行的是子类的方法
        Object result = methodProxy.invokeSuper(o, objects);
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
