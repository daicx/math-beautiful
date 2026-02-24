package com.skuu.demo.ordersystem.state;

import com.skuu.demo.ordersystem.model.OrderStatusEnum;

/**
 * @author dcx
 * @description 订单状态行为接口 - 状态模式核心接口
 * 定义订单在某个状态下的所有行为（动作、转换规则、生命周期）
 * @create 2025-01-27
 */
public interface OrderStateBehavior {
    /**
     * 支付订单
     */
    void pay(StateContext context);
    
    /**
     * 发货
     */
    void ship(StateContext context);
    
    /**
     * 确认收货
     */
    void confirm(StateContext context);
    
    /**
     * 完成订单
     */
    void complete(StateContext context);
    
    /**
     * 取消订单
     */
    void cancel(StateContext context);
    
    /**
     * 申请退款
     */
    void refund(StateContext context);
    
    /**
     * 获取当前状态
     */
    OrderStatusEnum getStatus();
    
    /**
     * 是否可以转换到目标状态
     */
    boolean canTransitionTo(OrderStatusEnum targetStatus);
    
    /**
     * 状态进入时的钩子方法
     */
    default void onEnter(StateContext context) {
        // 默认实现为空，子类可以覆盖
    }
    
    /**
     * 状态退出时的钩子方法
     */
    default void onExit(StateContext context) {
        // 默认实现为空，子类可以覆盖
    }
}
