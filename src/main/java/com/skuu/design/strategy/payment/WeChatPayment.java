package com.skuu.design.strategy.payment;

import com.skuu.design.strategy.PaymentStrategy;

/**
 * @author dcx
 * @description 微信支付策略
 * @create 2025-01-27
 */
public class WeChatPayment implements PaymentStrategy {
    private String openId;

    public WeChatPayment(String openId) {
        this.openId = openId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("使用微信支付: " + amount + " 元");
        System.out.println("OpenID: " + openId);
        System.out.println("支付成功！");
    }
}

