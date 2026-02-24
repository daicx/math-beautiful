package com.skuu.demo.ordersystem.observer;

import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.model.OrderStatusEnum;

/**
 * @author dcx
 * @description 订单观察者接口 - 观察者模式
 * 用于监听订单状态变化
 * @create 2025-01-27
 */
public interface OrderObserver {
    /**
     * 订单状态变化时的回调
     * @param order 订单对象
     * @param previousStatus 之前的状态
     * @param newStatus 新的状态
     */
    void onStatusChanged(Order order, OrderStatusEnum previousStatus, OrderStatusEnum newStatus);
}
