package com.skuu.design.combination.state;

/**
 * @author dcx
 * @description 已取消状态
 * @create 2025-01-27
 */
public class CancelledState implements OrderState {
    @Override
    public void pay(OrderContext context) {
        System.out.println("❌ 订单已取消");
    }

    @Override
    public void ship(OrderContext context) {
        System.out.println("❌ 订单已取消");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("❌ 订单已经是取消状态");
    }

    @Override
    public String getStateName() {
        return "已取消";
    }
}

