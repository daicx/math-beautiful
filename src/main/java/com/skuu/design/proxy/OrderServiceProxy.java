package com.skuu.design.proxy;

import java.util.Date;

/**
 * 静态代理
 *
 * @author dcx
 * @since 2023-06-21 10:04
 **/
public class OrderServiceProxy implements OrderService {

    //被代理的对象
    private OrderService orderService;

    public OrderServiceProxy(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void create(int id) {
        before();
        orderService.create(id);
        after();
    }

    @Override
    public void pay(int id) {
        before();
        orderService.pay(id);
        after();
    }

    private void before() {
        System.out.println("before: " + new Date());
    }

    private void after() {
        System.out.println("after: " + new Date());
    }
}
