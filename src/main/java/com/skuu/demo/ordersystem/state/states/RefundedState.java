package com.skuu.demo.ordersystem.state.states;

import com.skuu.demo.ordersystem.enums.OrderStatusEnum;
import com.skuu.demo.ordersystem.state.AbstractOrderState;
import org.springframework.stereotype.Component;

/**
 * @author dcx
 * @description 已退款状态行为 - 终态
 * @create 2025-01-27
 */
@Component
public class RefundedState extends AbstractOrderState {
    
    @Override
    public boolean canTransitionTo(OrderStatusEnum targetStatus) {
        // 已退款是终态，不能再转换
        return false;
    }

    @Override
    public OrderStatusEnum getStatus() {
        return OrderStatusEnum.REFUNDED;
    }
}
