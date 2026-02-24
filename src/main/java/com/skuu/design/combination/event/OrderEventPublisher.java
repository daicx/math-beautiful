package com.skuu.design.combination.event;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dcx
 * @description 订单事件发布器 - 观察者模式的Subject
 * @create 2025-01-27
 */
public class OrderEventPublisher {
    private List<OrderEventListener> listeners = new ArrayList<>();

    public void subscribe(OrderEventListener listener) {
        listeners.add(listener);
    }

    public void publishEvent(OrderEvent event) {
        System.out.println("\n📢 发布事件: " + event.getEventType());
        for (OrderEventListener listener : listeners) {
            listener.onEvent(event);
        }
    }
}

