package com.skuu.demo.ordersystem.state.states;

import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.model.OrderStatusEnum;
import com.skuu.demo.ordersystem.state.AbstractOrderStateBehavior;
import com.skuu.demo.ordersystem.state.StateContext;
import org.springframework.stereotype.Component;

/**
 * @author dcx
 * @description 退款中状态行为
 * @create 2025-01-27
 */
@Component
public class RefundingState extends AbstractOrderStateBehavior {
    
    @Override
    public void refund(StateContext context) {
        Order order = context.getOrder();
        // 模拟退款处理完成
        System.out.println(String.format("订单 %s 退款处理完成，金额: %.2f 元", 
            order.getOrderId(), order.getAmount()));
        order.addMetadata("refundCompleteTime", System.currentTimeMillis());
        context.transitionTo(OrderStatusEnum.REFUNDED);
    }

    @Override
    public boolean canTransitionTo(OrderStatusEnum targetStatus) {
        return targetStatus == OrderStatusEnum.REFUNDED;
    }

    @Override
    public OrderStatusEnum getStatus() {
        return OrderStatusEnum.REFUNDING;
    }
}
