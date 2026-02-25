package com.skuu.demo.ordersystem.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author dcx
 * @description 订单状态变更 - 日志监听（Spring Events，替代 OrderObserver）
 * @create 2025-01-27
 */
@Component
public class OrderStatusChangedLoggingListener {

    @EventListener
    public void onOrderStatusChanged(OrderStatusChangedEvent event) {
        System.out.println(String.format(
            "[日志] 订单 %s 状态变化: %s -> %s (时间: %d)",
            event.getOrder().getOrderId(),
            event.getPreviousStatus().getName(),
            event.getNewStatus().getName(),
            System.currentTimeMillis()
        ));
    }
}
