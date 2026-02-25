package com.skuu.demo.ordersystem.state.states;

import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.enums.OrderStatusEnum;
import com.skuu.demo.ordersystem.state.AbstractOrderState;
import com.skuu.demo.ordersystem.state.OrderStateContext;
import org.springframework.stereotype.Component;

/**
 * @author dcx
 * @description 待支付状态行为
 * @create 2025-01-27
 */
@Component
public class PendingPaymentState extends AbstractOrderState {
    
    @Override
    public void pay(OrderStateContext context) {
        Order order = context.getOrder();
        System.out.println(String.format("订单 %s 支付成功，金额: %.2f 元", 
            order.getOrderId(), order.getAmount()));
        order.addMetadata("paymentTime", System.currentTimeMillis());
        order.addMetadata("paymentMethod", "默认支付方式");
        context.transitionTo(OrderStatusEnum.PAID);
    }

    @Override
    public void cancel(OrderStateContext context) {
        Order order = context.getOrder();
        System.out.println(String.format("订单 %s 已取消", order.getOrderId()));
        order.addMetadata("cancelTime", System.currentTimeMillis());
        order.addMetadata("cancelReason", "用户主动取消");
        context.transitionTo(OrderStatusEnum.CANCELLED);
    }

    @Override
    public boolean canTransitionTo(OrderStatusEnum targetStatus) {
        return targetStatus == OrderStatusEnum.PAID ||
               targetStatus == OrderStatusEnum.CANCELLED;
    }

    @Override
    public OrderStatusEnum getStatus() {
        return OrderStatusEnum.PENDING_PAYMENT;
    }
}
