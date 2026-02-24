package com.skuu.demo.ordersystem.observer.impl;

import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.model.OrderStatusEnum;
import com.skuu.demo.ordersystem.observer.OrderObserver;

/**
 * @author dcx
 * @description 通知观察者 - 发送状态变化通知
 * @create 2025-01-27
 */
public class NotificationObserver implements OrderObserver {
    
    @Override
    public void onStatusChanged(Order order, OrderStatusEnum previousStatus, OrderStatusEnum newStatus) {
        // 模拟发送通知
        String message = String.format(
            "订单 %s 状态已更新: %s -> %s", 
            order.getOrderId(), 
            previousStatus.getName(), 
            newStatus.getName()
        );
        
        System.out.println("[通知] 发送给用户 " + order.getCustomerName() + ": " + message);
        // 实际应用中这里可以：
        // 1. 发送短信
        // 2. 发送邮件
        // 3. 推送App通知
        // 4. 发送站内信等
    }
}
