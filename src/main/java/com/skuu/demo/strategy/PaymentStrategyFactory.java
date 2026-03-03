package com.skuu.demo.strategy;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dcx
 * @description Spring策略工程，自动注册
 * @create 2026-02-28 09:59
 **/
@Component
public class PaymentStrategyFactory {
    private final Map<PayTypeEnum, PaymentStrategyService> strategyMap = new HashMap<>();

    //构造注入所有策略，自动注册
    public PaymentStrategyFactory(List<PaymentStrategyService> paymentStrategyServices) {
        for (PaymentStrategyService paymentStrategyService : paymentStrategyServices) {
            strategyMap.put(paymentStrategyService.getPayType(), paymentStrategyService);
        }
    }

    //获取策略
    public PaymentStrategyService getStrategy(PayTypeEnum payType) {
        PaymentStrategyService paymentStrategyService = strategyMap.get(payType);
        if (paymentStrategyService == null) {
            throw new RuntimeException("不支持的支付策略");
        }
        return paymentStrategyService;
    }

}
