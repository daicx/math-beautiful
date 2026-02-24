package com.skuu.design.status;

/**
 * @author dcx
 * @description 订单状态接口
 * @create 2025-01-27
 */
public interface OrderState {
    void pay(OrderContext context);
    void deliver(OrderContext context);
    void cancel(OrderContext context);
    void complete(OrderContext context);
    OrderStatusEnum getStatus();
}