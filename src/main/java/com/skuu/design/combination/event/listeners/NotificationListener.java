package com.skuu.design.combination.event.listeners;

import com.skuu.design.combination.event.OrderEvent;
import com.skuu.design.combination.event.OrderEventListener;

/**
 * @author dcx
 * @description 通知监听器
 * @create 2025-01-27
 */
public class NotificationListener implements OrderEventListener {
    @Override
    public void onEvent(OrderEvent event) {
        if ("ORDER_PAID".equals(event.getEventType())) {
            System.out.println("  📧 [通知监听器] 发送支付成功通知");
            // 实际发送通知
        }
    }
}

