package com.skuu.design.strategy.payment;

import com.skuu.design.strategy.PaymentStrategy;

/**
 * @author dcx
 * @description 支付宝支付策略
 * @create 2025-01-27
 */
public class AliPayPayment implements PaymentStrategy {
    private String account;

    public AliPayPayment(String account) {
        this.account = account;
    }

    @Override
    public void pay(double amount) {
        System.out.println("使用支付宝支付: " + amount + " 元");
        System.out.println("账户: " + account);
        System.out.println("支付成功！");
    }
}

