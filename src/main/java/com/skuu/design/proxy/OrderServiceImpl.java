package com.skuu.design.proxy;

/**
 * @author dcx
 * @since 2023-06-21 09:59
 **/
public class OrderServiceImpl implements OrderService {

    @Override
    public void create(int id) {
        System.out.println("create order");
    }

    @Override
    public void pay(int id) {
        System.out.println("pay order");
    }
}
