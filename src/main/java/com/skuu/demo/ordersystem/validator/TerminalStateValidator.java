package com.skuu.demo.ordersystem.validator;

import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.enums.OrderStatusEnum;
import com.skuu.demo.ordersystem.state.OrderState;

/**
 * @author dcx
 * @description 责任链节点：终态（已取消/已完成/已退款）不允许再转换
 * @create 2025-01-27
 */
public class TerminalStateValidator extends AbstractStateTransitionValidator {

    @Override
    protected boolean doValidate(OrderState currentState, OrderStatusEnum targetStatus, Order order) {
        OrderStatusEnum current = currentState.getStatus();
        if (current == OrderStatusEnum.CANCELLED
            || current == OrderStatusEnum.COMPLETED
            || current == OrderStatusEnum.REFUNDED) {
            fail("终态不可再转换", current, targetStatus);
            return false;
        }
        return true;
    }
}
