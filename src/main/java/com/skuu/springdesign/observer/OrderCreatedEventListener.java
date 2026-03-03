package com.skuu.springdesign.observer;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 观察者：用 @EventListener 消费 Spring 事件，替代传统 Observer 接口。
 */
@Component
public class OrderCreatedEventListener {

    @EventListener
    public void onOrderCreated(OrderCreatedEvent event) {
        System.out.println("[Listener] Order created: " + event.getOrderId() + ", amount=" + event.getAmount());
    }
}
