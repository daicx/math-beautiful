package com.skuu.demo.ordersystem.observer.impl;

import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.model.OrderStatusEnum;
import com.skuu.demo.ordersystem.observer.OrderObserver;

/**
 * @author dcx
 * @description 日志观察者 - 记录状态变化日志
 * @create 2025-01-27
 */
public class LoggingObserver implements OrderObserver {
    
    @Override
    public void onStatusChanged(Order order, OrderStatusEnum previousStatus, OrderStatusEnum newStatus) {
        System.out.println(String.format(
            "[日志] 订单 %s 状态变化: %s -> %s (时间: %d)", 
            order.getOrderId(), 
            previousStatus.getName(), 
            newStatus.getName(),
            System.currentTimeMillis()
        ));
    }
}
