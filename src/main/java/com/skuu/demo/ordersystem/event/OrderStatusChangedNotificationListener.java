package com.skuu.demo.ordersystem.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author dcx
 * @description 订单状态变更 - 通知监听（Spring Events）
 * @create 2025-01-27
 */
@Component
public class OrderStatusChangedNotificationListener {

    @EventListener
    public void onOrderStatusChanged(OrderStatusChangedEvent event) {
        String message = String.format(
            "订单 %s 状态已更新: %s -> %s",
            event.getOrder().getOrderId(),
            event.getPreviousStatus().getName(),
            event.getNewStatus().getName()
        );
        System.out.println("[通知] 发送给用户 " + event.getOrder().getCustomerName() + ": " + message);
    }
}
