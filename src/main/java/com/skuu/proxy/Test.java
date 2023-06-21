package com.skuu.proxy;


import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * @author dcx
 * @since 2023-06-21 10:00
 **/
public class Test {

    /**
     * 静态代理
     * @author dcx
     * @date 2023/6/21 10:57
     **/
    public static void staticProxy() {
        //生成被代理对象
        OrderService orderService = new OrderServiceImpl();
        //生成代理对象
        OrderServiceProxy orderServiceProxy = new OrderServiceProxy(orderService);
        //代理对象执行
        orderServiceProxy.create(1);
        orderServiceProxy.pay(1);
    }

    /**
     * jdk动态代理
     * @author dcx
     * @date 2023/6/21 10:57
     **/
    public static void jdkProxy(){
        //目标类
        OrderService orderService = new OrderServiceImpl();
        //获取类加载器
        ClassLoader classLoader = orderService.getClass().getClassLoader();
        //获取对象实现的接口类，OrderServiceImpl实现的是OrderService接口类
        Class<?>[] interfaces = orderService.getClass().getInterfaces();
        //创建代理处理器
        LogHandler logHandler = new LogHandler(orderService);
        //创建代理对象
        OrderService orderServiceProxy = (OrderService) Proxy.newProxyInstance(classLoader, interfaces, logHandler);
        //代理对象执行方法
        orderServiceProxy.create(1);
        orderServiceProxy.pay(1);
        //输出代理对象class文件
        ProxyUtils.generateClassFile(orderService.getClass(),"OrderServiceImpljdkProxy");
    }

    /**
     * cglib 动态代理，方法增强
     * @author dcx
     * @date 2023/6/21 10:58
     **/
    public static void cglibProxy(){
        //生成拦截处理器
        LogInterceptor logInterceptor = new LogInterceptor();
        //方法增强处理类
        Enhancer enhancer = new Enhancer();
        //设置超类
        enhancer.setSuperclass(Order.class);
        //设置回调时拦截增强
        enhancer.setCallback(logInterceptor);
        //创建增强类
        Order order = (Order) enhancer.create();
        order.create(1);
        order.pay(1);
    }


    public static void main(String[] args) {
//        staticProxy();
//        jdkProxy();
        cglibProxy();
    }
}
