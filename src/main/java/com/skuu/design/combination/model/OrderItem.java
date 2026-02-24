package com.skuu.design.combination.model;

import java.math.BigDecimal;

/**
 * @author dcx
 * @description 订单项
 * @create 2025-01-27
 */
public class OrderItem {
    private String productId;
    private String productName;
    private int quantity;
    private BigDecimal price;

    public OrderItem(String productId, String productName, int quantity, BigDecimal price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public BigDecimal getSubtotal() {
        return price.multiply(new BigDecimal(quantity));
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

