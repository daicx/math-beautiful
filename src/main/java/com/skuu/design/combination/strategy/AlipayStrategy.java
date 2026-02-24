package com.skuu.design.combination.strategy;

import com.skuu.design.combination.model.Order;

import java.math.BigDecimal;

/**
 * @author dcx
 * @description 支付宝支付策略
 * @create 2025-01-27
 */
public class AlipayStrategy implements PaymentStrategy {
    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
        System.out.println("💰 使用支付宝支付: ¥" + amount);
        // 模拟支付处理
        return new PaymentResult(true, "支付成功", "ALIPAY_" + System.currentTimeMillis());
    }

    @Override
    public String getPaymentType() {
        return "支付宝";
    }
}

