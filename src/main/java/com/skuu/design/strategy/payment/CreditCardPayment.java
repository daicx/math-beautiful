package com.skuu.design.strategy.payment;

import com.skuu.design.strategy.PaymentStrategy;

/**
 * @author dcx
 * @description 信用卡支付策略
 * @create 2025-01-27
 */
public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String name;

    public CreditCardPayment(String cardNumber, String name) {
        this.cardNumber = cardNumber;
        this.name = name;
    }

    @Override
    public void pay(double amount) {
        System.out.println("使用信用卡支付: " + amount + " 元");
        System.out.println("卡号: " + maskCardNumber(cardNumber));
        System.out.println("持卡人: " + name);
        System.out.println("支付成功！");
    }

    private String maskCardNumber(String cardNumber) {
        // 隐藏部分卡号
        if (cardNumber == null || cardNumber.length() < 4) {
            return cardNumber;
        }
        return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
    }
}

