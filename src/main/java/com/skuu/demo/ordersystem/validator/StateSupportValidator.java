package com.skuu.demo.ordersystem.validator;

import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.enums.OrderStatusEnum;
import com.skuu.demo.ordersystem.state.OrderState;

/**
 * @author dcx
 * @description 责任链节点：校验当前状态是否支持转换到目标状态
 * @create 2025-01-27
 */
public class StateSupportValidator extends AbstractStateTransitionValidator {

    @Override
    protected boolean doValidate(OrderState currentState, OrderStatusEnum targetStatus, Order order) {
        if (currentState.canTransitionTo(targetStatus)) {
            return true;
        }
        fail("状态不支持", currentState.getStatus(), targetStatus);
        return false;
    }
}
