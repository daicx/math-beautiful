package com.skuu.design.combination.event;

import com.skuu.design.combination.model.Order;

import java.time.LocalDateTime;

/**
 * @author dcx
 * @description 订单事件
 * @create 2025-01-27
 */
public class OrderEvent {
    private String eventType;
    private Order order;
    private LocalDateTime timestamp;

    public OrderEvent(String eventType, Order order) {
        this.eventType = eventType;
        this.order = order;
        this.timestamp = LocalDateTime.now();
    }

    public String getEventType() {
        return eventType;
    }

    public Order getOrder() {
        return order;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

