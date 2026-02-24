package com.skuu.design.status.states;

import com.skuu.design.status.OrderContext;
import com.skuu.design.status.OrderState;
import com.skuu.design.status.OrderStatusEnum;

/**
 * @author dcx
 * @description 已取消状态
 * @create 2025-01-27
 */
public class CancelledState implements OrderState {
    
    @Override
    public void pay(OrderContext context) {
        System.out.println("订单已取消，无法支付！");
    }

    @Override
    public void deliver(OrderContext context) {
        System.out.println("订单已取消，无法发货！");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("订单已取消！");
    }

    @Override
    public void complete(OrderContext context) {
        System.out.println("订单已取消，无法完成！");
    }

    @Override
    public OrderStatusEnum getStatus() {
        return OrderStatusEnum.CANCELLED;
    }
}
