package com.skuu.design.strategy.payment;

import com.skuu.design.strategy.PaymentStrategy;

/**
 * @author dcx
 * @description PayPal支付策略 - 演示如何轻松扩展新策略
 * @create 2025-01-27
 */
public class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("使用PayPal支付: " + amount + " 元");
        System.out.println("邮箱: " + email);
        System.out.println("支付成功！");
    }
}

