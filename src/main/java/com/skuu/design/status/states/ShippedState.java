package com.skuu.design.status.states;

import com.skuu.design.status.OrderContext;
import com.skuu.design.status.OrderState;
import com.skuu.design.status.OrderStatusEnum;

/**
 * @author dcx
 * @description 已发货状态
 * @create 2025-01-27
 */
public class ShippedState implements OrderState {
    
    @Override
    public void pay(OrderContext context) {
        System.out.println("订单已支付！");
    }

    @Override
    public void deliver(OrderContext context) {
        System.out.println("订单已发货！");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("订单已发货，无法取消！");
    }

    @Override
    public void complete(OrderContext context) {
        System.out.println("确认收货...");
        context.getOrderData().put("completedTime", System.currentTimeMillis());
        context.changeState(OrderStatusEnum.COMPLETED);
    }

    @Override
    public OrderStatusEnum getStatus() {
        return OrderStatusEnum.SHIPPED;
    }
}
