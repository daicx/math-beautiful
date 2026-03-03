package com.skuu.springdesign.observer;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * 发布方：通过 ApplicationEventPublisher 发布事件，观察者用 @EventListener 接收。
 */
@Service
public class OrderService {

    private final ApplicationEventPublisher publisher;

    public OrderService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void createOrder(String orderId, double amount) {
        publisher.publishEvent(new OrderCreatedEvent(orderId, amount));
    }
}
