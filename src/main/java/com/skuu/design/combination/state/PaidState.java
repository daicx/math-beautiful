package com.skuu.design.combination.state;

/**
 * @author dcx
 * @description 已支付状态
 * @create 2025-01-27
 */
public class PaidState implements OrderState {
    @Override
    public void pay(OrderContext context) {
        System.out.println("❌ 订单已支付，无需重复支付");
    }

    @Override
    public void ship(OrderContext context) {
        System.out.println("✅ 订单已发货");
        context.setState(new ShippedState());
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("✅ 订单已取消，将退款");
        context.setState(new RefundingState());
    }

    @Override
    public String getStateName() {
        return "已支付";
    }
}

