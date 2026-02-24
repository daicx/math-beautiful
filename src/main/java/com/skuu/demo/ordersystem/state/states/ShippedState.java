package com.skuu.demo.ordersystem.state.states;

import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.model.OrderStatusEnum;
import com.skuu.demo.ordersystem.state.AbstractOrderStateBehavior;
import com.skuu.demo.ordersystem.state.StateContext;
import org.springframework.stereotype.Component;

/**
 * @author dcx
 * @description 已发货状态行为
 * @create 2025-01-27
 */
@Component
public class ShippedState extends AbstractOrderStateBehavior {
    
    @Override
    public void confirm(StateContext context) {
        Order order = context.getOrder();
        System.out.println(String.format("订单 %s 已确认收货", order.getOrderId()));
        order.addMetadata("confirmTime", System.currentTimeMillis());
        context.transitionTo(OrderStatusEnum.DELIVERED);
    }

    @Override
    public boolean canTransitionTo(OrderStatusEnum targetStatus) {
        return targetStatus == OrderStatusEnum.DELIVERED;
    }

    @Override
    public OrderStatusEnum getStatus() {
        return OrderStatusEnum.SHIPPED;
    }
}
