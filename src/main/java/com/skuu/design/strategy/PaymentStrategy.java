package com.skuu.design.strategy;

/**
 * @author dcx
 * @description 支付策略接口 - 定义支付行为的抽象
 * @create 2025-01-27
 */
public interface PaymentStrategy {
    /**
     * 执行支付
     * @param amount 支付金额
     */
    void pay(double amount);
}

