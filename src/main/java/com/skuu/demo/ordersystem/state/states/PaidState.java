package com.skuu.demo.ordersystem.state.states;

import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.model.OrderStatusEnum;
import com.skuu.demo.ordersystem.state.AbstractOrderStateBehavior;
import com.skuu.demo.ordersystem.state.StateContext;
import org.springframework.stereotype.Component;

/**
 * @author dcx
 * @description 已支付状态行为
 * @create 2025-01-27
 */
@Component
public class PaidState extends AbstractOrderStateBehavior {
    
    @Override
    public void ship(StateContext context) {
        Order order = context.getOrder();
        System.out.println(String.format("订单 %s 开始发货", order.getOrderId()));
        String trackingNumber = "TRK" + System.currentTimeMillis();
        order.addMetadata("shipTime", System.currentTimeMillis());
        order.addMetadata("trackingNumber", trackingNumber);
        System.out.println("物流单号: " + trackingNumber);
        context.transitionTo(OrderStatusEnum.SHIPPED);
    }

    @Override
    public void refund(StateContext context) {
        Order order = context.getOrder();
        System.out.println(String.format("订单 %s 申请退款，金额: %.2f 元", 
            order.getOrderId(), order.getAmount()));
        order.addMetadata("refundApplyTime", System.currentTimeMillis());
        context.transitionTo(OrderStatusEnum.REFUNDING);
    }

    @Override
    public boolean canTransitionTo(OrderStatusEnum targetStatus) {
        return targetStatus == OrderStatusEnum.SHIPPED ||
               targetStatus == OrderStatusEnum.REFUNDING;
    }

    @Override
    public OrderStatusEnum getStatus() {
        return OrderStatusEnum.PAID;
    }
}
