package com.skuu.demo.ordersystem.validator;

import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.enums.OrderStatusEnum;
import com.skuu.demo.ordersystem.state.OrderState;

/**
 * @author dcx
 * @description 责任链节点：转为已支付时校验订单金额
 * @create 2025-01-27
 */
public class PaymentAmountValidator extends AbstractStateTransitionValidator {

    @Override
    protected boolean doValidate(OrderState currentState, OrderStatusEnum targetStatus, Order order) {
        if (targetStatus != OrderStatusEnum.PAID) {
            return true;
        }
        if (order.getAmount() > 0) {
            return true;
        }
        fail("支付金额必须大于 0", currentState.getStatus(), targetStatus);
        return false;
    }
}
