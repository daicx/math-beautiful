package com.skuu.demo.ordersystem.observer.impl;

import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.model.OrderStatusEnum;
import com.skuu.demo.ordersystem.observer.OrderObserver;

/**
 * @author dcx
 * @description 库存观察者 - 管理库存变化
 * @create 2025-01-27
 */
public class InventoryObserver implements OrderObserver {
    
    @Override
    public void onStatusChanged(Order order, OrderStatusEnum previousStatus, OrderStatusEnum newStatus) {
        // 订单支付成功，扣减库存
        if (newStatus == OrderStatusEnum.PAID && previousStatus == OrderStatusEnum.PENDING_PAYMENT) {
            System.out.println(String.format(
                "[库存] 订单 %s 已支付，扣减商品 %s 的库存", 
                order.getOrderId(), 
                order.getProductId()
            ));
            // 实际应用中调用库存服务扣减库存
        }
        
        // 订单取消或退款，恢复库存
        if ((newStatus == OrderStatusEnum.CANCELLED || newStatus == OrderStatusEnum.REFUNDED)
            && previousStatus != OrderStatusEnum.CANCELLED
            && previousStatus != OrderStatusEnum.REFUNDED) {
            System.out.println(String.format(
                "[库存] 订单 %s 已取消/退款，恢复商品 %s 的库存", 
                order.getOrderId(), 
                order.getProductId()
            ));
            // 实际应用中调用库存服务恢复库存
        }
    }
}
