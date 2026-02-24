package com.skuu.design.status.states;

import com.skuu.design.status.OrderContext;
import com.skuu.design.status.OrderState;
import com.skuu.design.status.OrderStatusEnum;

/**
 * @author dcx
 * @description 已支付状态
 * @create 2025-01-27
 */
public class PaidState implements OrderState {
    
    @Override
    public void pay(OrderContext context) {
        System.out.println("订单已支付，无需重复支付！");
    }

    @Override
    public void deliver(OrderContext context) {
        System.out.println("开始发货...");
        context.getOrderData().put("deliveryTime", System.currentTimeMillis());
        context.getOrderData().put("trackingNumber", "TRK" + System.currentTimeMillis());
        context.changeState(OrderStatusEnum.SHIPPED);
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("订单已支付，需要退款处理...");
        context.changeState(OrderStatusEnum.REFUNDING);
    }

    @Override
    public void complete(OrderContext context) {
        System.out.println("订单尚未发货，无法完成！");
    }

    @Override
    public OrderStatusEnum getStatus() {
        return OrderStatusEnum.PAID;
    }
}
