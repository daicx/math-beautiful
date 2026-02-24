package com.skuu.demo.ordersystem.validator;

import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.model.OrderStatusEnum;
import com.skuu.demo.ordersystem.state.OrderStateBehavior;

/**
 * @author dcx
 * @description 状态转换验证器 - 责任链模式
 * 验证状态转换是否合法
 * @create 2025-01-27
 */
public class StateTransitionValidator {
    
    /**
     * 验证状态转换是否合法
     * @param currentState 当前状态
     * @param targetStatus 目标状态
     * @param order 订单对象
     * @return 是否合法
     */
    public boolean validate(OrderStateBehavior currentState, OrderStatusEnum targetStatus, Order order) {
        // 1. 检查状态对象是否支持转换到目标状态
        if (!currentState.canTransitionTo(targetStatus)) {
            System.err.println(String.format("状态转换验证失败: %s -> %s (状态不支持)", 
                currentState.getStatus().getName(), targetStatus.getName()));
            return false;
        }
        
        // 2. 检查订单业务规则
        if (!validateBusinessRules(currentState.getStatus(), targetStatus, order)) {
            System.err.println(String.format("状态转换验证失败: %s -> %s (业务规则不满足)", 
                currentState.getStatus().getName(), targetStatus.getName()));
            return false;
        }
        
        return true;
    }
    
    /**
     * 验证业务规则
     * 可以扩展更多的验证规则
     */
    private boolean validateBusinessRules(OrderStatusEnum currentStatus, OrderStatusEnum targetStatus, Order order) {
        // 示例：已取消或已完成的订单不能再转换
        if (currentStatus == OrderStatusEnum.CANCELLED ||
            currentStatus == OrderStatusEnum.COMPLETED ||
            currentStatus == OrderStatusEnum.REFUNDED) {
            return false;
        }
        
        // 示例：支付状态转换需要检查金额
        if (targetStatus == OrderStatusEnum.PAID && order.getAmount() <= 0) {
            return false;
        }
        
        // 可以添加更多业务规则验证
        return true;
    }
}
