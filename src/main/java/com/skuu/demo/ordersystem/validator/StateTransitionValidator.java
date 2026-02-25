package com.skuu.demo.ordersystem.validator;

import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.enums.OrderStatusEnum;
import com.skuu.demo.ordersystem.state.OrderState;

/**
 * @author dcx
 * @description 状态转换验证器 - 责任链中的节点接口
 * @create 2025-01-27
 */
public interface StateTransitionValidator {

    /**
     * 验证状态转换是否合法（当前节点校验通过则交给下一节点）
     */
    boolean validate(OrderState currentState, OrderStatusEnum targetStatus, Order order);
}
