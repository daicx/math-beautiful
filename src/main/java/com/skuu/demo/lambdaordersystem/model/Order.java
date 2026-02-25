package com.skuu.demo.lambdaordersystem.model;

import com.skuu.demo.lambdaordersystem.enums.OrderStatusEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单实体（lambda 版）
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
    public long getCreatedAt() { return createdAt; }
    public long getUpdatedAt() { return updatedAt; }
}
