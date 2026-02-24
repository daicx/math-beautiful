package com.skuu.demo.ordersystem.state.states;

import com.skuu.demo.ordersystem.model.OrderStatusEnum;
import com.skuu.demo.ordersystem.state.AbstractOrderStateBehavior;
import org.springframework.stereotype.Component;

/**
 * @author dcx
 * @description 已取消状态行为 - 终态
 * @create 2025-01-27
 */
@Component
public class CancelledState extends AbstractOrderStateBehavior {
    
    @Override
    public boolean canTransitionTo(OrderStatusEnum targetStatus) {
        // 已取消是终态，不能再转换
        return false;
    }

    @Override
    public OrderStatusEnum getStatus() {
        return OrderStatusEnum.CANCELLED;
    }
}
