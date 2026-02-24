package com.skuu.design.status.states;

import com.skuu.design.status.OrderContext;
import com.skuu.design.status.OrderState;
import com.skuu.design.status.OrderStatusEnum;

/**
 * @author dcx
 * @description 待支付状态
 * @create 2025-01-27
 */
public class PendingPaymentState implements OrderState {
    
    @Override
    public void pay(OrderContext context) {
        System.out.println("处理支付...");
        if (Math.random() > 0.3) {
            System.out.println("支付成功！");
            context.changeState(OrderStatusEnum.PAID);
        } else {
            System.out.println("支付失败！");
            context.changeState(OrderStatusEnum.CANCELLED);
        }
    }

    @Override
    public void deliver(OrderContext context) {
        System.out.println("订单尚未支付，无法发货！");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("取消订单...");
        context.changeState(OrderStatusEnum.CANCELLED);
    }

    @Override
    public void complete(OrderContext context) {
        System.out.println("订单尚未支付，无法完成！");
    }

    @Override
    public OrderStatusEnum getStatus() {
        return OrderStatusEnum.PENDING_PAYMENT;
    }
}
