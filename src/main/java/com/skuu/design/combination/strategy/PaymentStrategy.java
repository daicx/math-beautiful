package com.skuu.design.combination.strategy;

import com.skuu.design.combination.model.Order;

import java.math.BigDecimal;

/**
 * @author dcx
 * @description 支付策略接口 - 策略模式
 * @create 2025-01-27
 */
public interface PaymentStrategy {
    PaymentResult pay(Order order, BigDecimal amount);
    String getPaymentType();
}

