package com.skuu.design.combination.state;

/**
 * @author dcx
 * @description 已发货状态
 * @create 2025-01-27
 */
public class ShippedState implements OrderState {
    @Override
    public void pay(OrderContext context) {
        System.out.println("❌ 订单已支付");
    }

    @Override
    public void ship(OrderContext context) {
        System.out.println("❌ 订单已发货");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("❌ 订单已发货，无法取消");
    }

    @Override
    public String getStateName() {
        return "已发货";
    }
}

