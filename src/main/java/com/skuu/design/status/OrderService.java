package com.skuu.design.status;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * @author dcx
 * @description 订单服务 - 模拟从数据库加载订单
 * @create 2025-01-27
 */
public class OrderService {
    
    /**
     * 从数据库加载订单
     */
    public OrderContext loadOrderFromDatabase(String orderId) {
        System.out.println("从数据库加载订单: " + orderId);
        
        // 创建Order对象
        Order order = new Order(
            orderId,
            "张三",
            "USER001",
            "PROD001",
            "iPhone 15",
            299.99,
            OrderStatusEnum.PENDING_PAYMENT
        );
        
        // 设置订单数据
        order.addOrderData("paymentMethod", "支付宝");
        order.addOrderData("address", "北京市朝阳区");
        order.addOrderData("phone", "13800138000");
        
        // 设置时间
        order.setCreatedAt(System.currentTimeMillis() - 86400000); // 1天前创建
        order.setUpdatedAt(System.currentTimeMillis() - 3600000);  // 1小时前更新
        
        return new OrderContext(order);
    }
    
    /**
     * 批量加载订单
     */
    public List<OrderContext> loadOrdersByStatus(OrderStatusEnum status) {
        System.out.println("从数据库加载状态为 " + status.getName() + " 的订单");
        
        List<OrderContext> orders = new ArrayList<>();
        // 模拟查询结果
        for (int i = 1; i <= 3; i++) {
            String orderId = "ORD" + String.format("%03d", i);
            orders.add(loadOrderFromDatabase(orderId));
        }
        return orders;
    }
    
    /**
     * 创建新订单
     */
    public OrderContext createOrder(String customerName, String userId, String productId, 
                                  String productName, double amount) {
        String orderId = "ORD" + System.currentTimeMillis();
        
        Order order = new Order(
            orderId,
            customerName,
            userId,
            productId,
            productName,
            amount,
            OrderStatusEnum.PENDING_PAYMENT
        );
        
        return new OrderContext(order);
    }
}
