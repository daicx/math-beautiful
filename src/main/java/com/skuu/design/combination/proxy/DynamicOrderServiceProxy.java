package com.skuu.design.combination.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author dcx
 * @description 动态代理 - 通用的服务代理（添加日志、性能监控）
 * @create 2025-01-27
 */
public class DynamicOrderServiceProxy implements InvocationHandler {
    
    private Object target;
    
    public DynamicOrderServiceProxy(Object target) {
        this.target = target;
    }
    
    /**
     * 创建代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(T target) {
        return (T) Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),
            new DynamicOrderServiceProxy(target)
        );
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 前置处理
        System.out.println("\n🔄 [动态代理] 方法调用开始");
        System.out.println("   方法名: " + method.getName());
        System.out.println("   参数数量: " + (args != null ? args.length : 0));
        
        long startTime = System.currentTimeMillis();
        
        // 调用真实方法
        Object result = method.invoke(target, args);
        
        // 后置处理
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("🔄 [动态代理] 方法调用结束");
        System.out.println("   执行时间: " + duration + "ms");
        System.out.println("   返回结果: " + (result != null ? result.getClass().getSimpleName() : "null"));
        
        return result;
    }
}

