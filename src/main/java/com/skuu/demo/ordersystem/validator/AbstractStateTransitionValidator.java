package com.skuu.demo.ordersystem.validator;

import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.enums.OrderStatusEnum;
import com.skuu.demo.ordersystem.state.OrderState;

/**
 * @author dcx
 * @description 责任链抽象节点：完成本节点校验后交给 next，体现“链”
 * @create 2025-01-27
 */
public abstract class AbstractStateTransitionValidator implements StateTransitionValidator {

    protected AbstractStateTransitionValidator next;

    public void setNext(AbstractStateTransitionValidator next) {
        this.next = next;
    }

    @Override
    public final boolean validate(OrderState currentState, OrderStatusEnum targetStatus, Order order) {
        if (!doValidate(currentState, targetStatus, order)) {
            return false;
        }
        if (next != null) {
            return next.validate(currentState, targetStatus, order);
        }
        return true;
    }

    /**
     * 当前节点的校验逻辑，失败时返回 false 并中断链
     */
    protected abstract boolean doValidate(OrderState currentState, OrderStatusEnum targetStatus, Order order);

    protected void fail(String reason, OrderStatusEnum from, OrderStatusEnum to) {
        System.err.println(String.format("状态转换验证失败: %s -> %s (%s)", from.getName(), to.getName(), reason));
    }
}
