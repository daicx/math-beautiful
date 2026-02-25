package com.skuu.demo.ordersystem.event;

import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.enums.OrderStatusEnum;

/**
 * @author dcx
 * @description 订单状态变更事件 - Spring Events（替代 java.util.Observer）
 * 状态转换时发布，由 @EventListener 监听
 * @create 2025-01-27
 */
public class OrderStatusChangedEvent {

    private final Order order;
    private final OrderStatusEnum previousStatus;
    private final OrderStatusEnum newStatus;

    public OrderStatusChangedEvent(Order order, OrderStatusEnum previousStatus, OrderStatusEnum newStatus) {
        this.order = order;
        this.previousStatus = previousStatus;
        this.newStatus = newStatus;
    }

    public Order getOrder() {
        return order;
    }

    public OrderStatusEnum getPreviousStatus() {
        return previousStatus;
    }

    public OrderStatusEnum getNewStatus() {
        return newStatus;
    }
}
