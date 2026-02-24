package com.skuu.design.combination.strategy;

import com.skuu.design.combination.model.Order;

import java.math.BigDecimal;

/**
 * @author dcx
 * @description 微信支付策略
 * @create 2025-01-27
 */
public class WechatPayStrategy implements PaymentStrategy {
    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
        System.out.println("💰 使用微信支付: ¥" + amount);
        return new PaymentResult(true, "支付成功", "WECHAT_" + System.currentTimeMillis());
    }

    @Override
    public String getPaymentType() {
        return "微信支付";
    }
}

