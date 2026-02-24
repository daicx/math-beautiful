package com.skuu.design.combination.event.listeners;

import com.skuu.design.combination.event.OrderEvent;
import com.skuu.design.combination.event.OrderEventListener;
import com.skuu.design.combination.model.Order;

/**
 * @author dcx
 * @description 积分监听器
 * @create 2025-01-27
 */
public class PointListener implements OrderEventListener {
    @Override
    public void onEvent(OrderEvent event) {
        if ("ORDER_PAID".equals(event.getEventType())) {
            Order order = event.getOrder();
            int points = order.getTotalAmount().intValue();
            System.out.println("  🎁 [积分监听器] 赠送积分: " + points);
        }
    }
}

