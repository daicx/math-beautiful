package com.skuu.demo.ordersystem.state.states;

import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.model.OrderStatusEnum;
import com.skuu.demo.ordersystem.state.AbstractOrderStateBehavior;
import com.skuu.demo.ordersystem.state.StateContext;
import org.springframework.stereotype.Component;

/**
 * @author dcx
 * @description 已送达状态行为
 * @create 2025-01-27
 */
@Component
public class DeliveredState extends AbstractOrderStateBehavior {
    
    @Override
    public void complete(StateContext context) {
        Order order = context.getOrder();
        System.out.println(String.format("订单 %s 已完成", order.getOrderId()));
        order.addMetadata("completeTime", System.currentTimeMillis());
        context.transitionTo(OrderStatusEnum.COMPLETED);
    }

    @Override
    public boolean canTransitionTo(OrderStatusEnum targetStatus) {
        return targetStatus == OrderStatusEnum.COMPLETED;
    }

    @Override
    public OrderStatusEnum getStatus() {
        return OrderStatusEnum.DELIVERED;
    }
}
