package com.skuu.demo.strategy;

public interface PaymentStrategyService {
    PayTypeEnum getPayType();
    void pay(long amount);
}
