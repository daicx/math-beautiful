package com.skuu.springdesign.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工厂：按支付方式名返回对应的 PaymentStrategy 实现。
 */
@Component
@RequiredArgsConstructor
public class PaymentStrategyFactory {

    private final List<PaymentStrategy> strategies;
    private final Map<String, PaymentStrategy> strategyMap = new HashMap<>();

    @PostConstruct
    public void init() {
        strategies.forEach(s -> strategyMap.put(s.getMethod(), s));
    }

    public PaymentStrategy getStrategy(String method) {
        PaymentStrategy strategy = strategyMap.get(method);
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown payment method: " + method);
        }
        return strategy;
    }

    public boolean supports(String method) {
        return strategyMap.containsKey(method);
    }
}
