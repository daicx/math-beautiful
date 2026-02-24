package com.skuu.design.status.states;

import com.skuu.design.status.OrderContext;
import com.skuu.design.status.OrderState;
import com.skuu.design.status.OrderStatusEnum;

/**
 * @author dcx
 * @description 退款中状态
 * @create 2025-01-27
 */
public class RefundingState implements OrderState {
    
    @Override
    public void pay(OrderContext context) {
        System.out.println("订单退款中，无法支付！");
    }

    @Override
    public void deliver(OrderContext context) {
        System.out.println("订单退款中，无法发货！");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("订单退款中！");
    }

    @Override
    public void complete(OrderContext context) {
        System.out.println("退款处理完成...");
        context.changeState(OrderStatusEnum.CANCELLED);
    }

    @Override
    public OrderStatusEnum getStatus() {
        return OrderStatusEnum.REFUNDING;
    }
}
