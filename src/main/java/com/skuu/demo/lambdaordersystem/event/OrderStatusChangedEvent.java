package com.skuu.demo.lambdaordersystem.event;

import com.skuu.demo.lambdaordersystem.model.Order;
import com.skuu.demo.lambdaordersystem.enums.OrderStatusEnum;

/**
 * 订单状态变更事件（lambda 版，可用 Consumer 监听）
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

    public Order getOrder() { return order; }
    public OrderStatusEnum getPreviousStatus() { return previousStatus; }
    public OrderStatusEnum getNewStatus() { return newStatus; }
}
