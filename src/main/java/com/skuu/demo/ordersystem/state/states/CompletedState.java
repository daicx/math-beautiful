package com.skuu.demo.ordersystem.state.states;

import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.enums.OrderStatusEnum;
import com.skuu.demo.ordersystem.state.AbstractOrderState;
import com.skuu.demo.ordersystem.state.OrderStateContext;
import org.springframework.stereotype.Component;

/**
 * @author dcx
 * @description 已完成状态行为 - 终态
 * @create 2025-01-27
 */
@Component
public class CompletedState extends AbstractOrderState {
    
    @Override
    public boolean canTransitionTo(OrderStatusEnum targetStatus) {
        // 已完成是终态，不能再转换
        return false;
    }

    @Override
    public OrderStatusEnum getStatus() {
        return OrderStatusEnum.COMPLETED;
    }
    
    @Override
    public void onEnter(OrderStateContext context) {
        Order order = context.getOrder();
        System.out.println(String.format("订单 %s 已成功完成，感谢您的购买！", order.getOrderId()));
    }
}
