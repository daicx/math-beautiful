package com.skuu.design.combination.state;

/**
 * @author dcx
 * @description 待支付状态
 * @create 2025-01-27
 */
public class PendingPaymentState implements OrderState {
    @Override
    public void pay(OrderContext context) {
        System.out.println("✅ 支付成功");
        context.setState(new PaidState());
    }

    @Override
    public void ship(OrderContext context) {
        System.out.println("❌ 订单未支付，无法发货");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("✅ 订单已取消");
        context.setState(new CancelledState());
    }

    @Override
    public String getStateName() {
        return "待支付";
    }
}

