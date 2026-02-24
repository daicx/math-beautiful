package com.skuu.design.combination.state;

/**
 * @author dcx
 * @description 退款中状态
 * @create 2025-01-27
 */
public class RefundingState implements OrderState {
    @Override
    public void pay(OrderContext context) {
        System.out.println("❌ 订单退款中");
    }

    @Override
    public void ship(OrderContext context) {
        System.out.println("❌ 订单退款中");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("❌ 订单退款中");
    }

    @Override
    public String getStateName() {
        return "退款中";
    }
}

