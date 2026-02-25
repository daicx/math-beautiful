package com.skuu.demo.lambdaordersystem.validator;

import com.skuu.demo.lambdaordersystem.model.Order;
import com.skuu.demo.lambdaordersystem.enums.OrderStatusEnum;

import java.util.Objects;

/**
 * 状态转换请求（供责任链 Predicate 使用）
 */
public class TransitionRequest {
    private final OrderStatusEnum from;
    private final OrderStatusEnum to;
    private final Order order;

    public TransitionRequest(OrderStatusEnum from, OrderStatusEnum to, Order order) {
        this.from = Objects.requireNonNull(from);
        this.to = Objects.requireNonNull(to);
        this.order = Objects.requireNonNull(order);
    }

    public OrderStatusEnum getFrom() { return from; }
    public OrderStatusEnum getTo() { return to; }
    public Order getOrder() { return order; }
}
