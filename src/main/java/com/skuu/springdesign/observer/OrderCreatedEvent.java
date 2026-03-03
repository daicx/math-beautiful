package com.skuu.springdesign.observer;

/**
 * 观察者模式：Spring Events 事件体。
 */
public class OrderCreatedEvent {
    private final String orderId;
    private final double amount;

    public OrderCreatedEvent(String orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    public String getOrderId() { return orderId; }
    public double getAmount() { return amount; }
}
