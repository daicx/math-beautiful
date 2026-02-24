package com.skuu.design.combination.model;

import com.skuu.design.combination.state.OrderState;
import com.skuu.design.combination.state.PendingPaymentState;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dcx
 * @description 订单实体类 - 使用建造者模式
 * @create 2025-01-27
 */
public class Order {
    private String orderId;
    private String userId;
    private List<OrderItem> items;
    private BigDecimal totalAmount;
    private String address;
    private OrderState state;
    private LocalDateTime createTime;

    // 私有构造函数
    private Order(Builder builder) {
        this.orderId = builder.orderId;
        this.userId = builder.userId;
        this.items = builder.items;
        this.totalAmount = builder.totalAmount;
        this.address = builder.address;
        this.state = builder.state;
        this.createTime = builder.createTime;
    }

    // 建造者
    public static class Builder {
        private String orderId;
        private String userId;
        private List<OrderItem> items = new ArrayList<>();
        private BigDecimal totalAmount;
        private String address;
        private OrderState state = new PendingPaymentState();
        private LocalDateTime createTime = LocalDateTime.now();

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder items(List<OrderItem> items) {
            this.items = items;
            return this;
        }

        public Builder addItem(OrderItem item) {
            this.items.add(item);
            return this;
        }

        public Builder totalAmount(BigDecimal amount) {
            this.totalAmount = amount;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder state(OrderState state) {
            this.state = state;
            return this;
        }

        public Builder createTime(LocalDateTime time) {
            this.createTime = time;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getAddress() {
        return address;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }
}

