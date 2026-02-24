package com.skuu.design.status;

import lombok.Data;
import java.util.Map;
import java.util.HashMap;

/**
 * @author dcx
 * @description 订单实体类
 * @create 2025-09-17 14:02
 **/
@Data
public class Order {
    private String orderId;
    private String customerName;
    private String userId;
    private String productId;
    private String productName;
    private double amount;
    private OrderStatusEnum status;
    private Map<String, Object> orderData;
    private long createdAt;
    private long updatedAt;
    
    // 构造函数
    public Order() {
        this.orderData = new HashMap<>();
    }
    
    public Order(String orderId, String customerName, String userId, String productId, 
                String productName, double amount, OrderStatusEnum status) {
        this();
        this.orderId = orderId;
        this.customerName = customerName;
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.amount = amount;
        this.status = status;
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }
    
    // 业务方法
    public void updateStatus(OrderStatusEnum newStatus) {
        this.status = newStatus;
        this.updatedAt = System.currentTimeMillis();
    }
    
    public void addOrderData(String key, Object value) {
        if (this.orderData == null) {
            this.orderData = new HashMap<>();
        }
        this.orderData.put(key, value);
    }
    
    public Object getOrderData(String key) {
        return this.orderData != null ? this.orderData.get(key) : null;
    }
}
