package com.skuu.design.combination.state;

/**
 * @author dcx
 * @description 订单状态接口 - 状态模式
 * @create 2025-01-27
 */
public interface OrderState {
    void pay(OrderContext context);
    void ship(OrderContext context);
    void cancel(OrderContext context);
    String getStateName();
}

