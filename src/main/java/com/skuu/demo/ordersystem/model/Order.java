package com.skuu.demo.ordersystem.model;

import com.skuu.demo.ordersystem.enums.OrderStatusEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dcx
 * @description 订单实体类 - 领域模型
 * @create 2025-01-27
 */
public class Order {
    private String orderId;
    private String customerName;
    private String userId;
    private String productId;
    private String productName;
    private double amount;
    private OrderStatusEnum status;
    private Map<String, Object> metadata;
    private long createdAt;
    private long updatedAt;
    
    public Order() {
        this.metadata = new HashMap<>();
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }
    
    public Order(String orderId, String customerName, String userId, 
                 String productId, String productName, double amount) {
        this();
        this.orderId = orderId;
        this.customerName = customerName;
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.amount = amount;
    }
    
    public void updateStatus(OrderStatusEnum newStatus) {
        this.status = newStatus;
        this.updatedAt = System.currentTimeMillis();
    }
    
    public void addMetadata(String key, Object value) {
        this.metadata.put(key, value);
    }
    
    public Object getMetadata(String key) {
        return this.metadata.get(key);
    }
    
    // Getters and Setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public OrderStatusEnum getStatus() { return status; }
    public void setStatus(OrderStatusEnum status) { this.status = status; }
    
    public Map<String, Object> getMetadata() { return metadata; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }
    
    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
    
    public long getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(long updatedAt) { this.updatedAt = updatedAt; }
}
