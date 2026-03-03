package com.skuu.springdesign.strategy;

import com.skuu.springdesign.strategy.pojo.PaymentRequest;
import com.skuu.springdesign.strategy.pojo.PaymentResult;

/**
 * 支付策略抽象：由工厂按方式名返回不同实现（支付宝/微信/银行卡等）。
 */
public interface PaymentStrategy {

    /** 支付方式标识，用于工厂注册与选择 */
    String getMethod();

    /** 执行支付 */
    PaymentResult pay(PaymentRequest request);
}
