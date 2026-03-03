package com.skuu.springdesign.strategy;

import com.skuu.springdesign.strategy.pojo.PaymentRequest;
import com.skuu.springdesign.strategy.pojo.PaymentResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 支付服务：通过工厂按方式名获取 PaymentStrategy，再执行支付（贴近实际：选支付方式 → 调对应渠道）。
 */
@Service
@RequiredArgsConstructor
public class PaymentStrategyService {

    private final PaymentStrategyFactory factory;

    /** 按支付方式执行支付 */
    public PaymentResult pay(String method, PaymentRequest request) {
        PaymentStrategy strategy = factory.getStrategy(method);
        return strategy.pay(request);
    }

    /** 是否支持该支付方式 */
    public boolean supports(String method) {
        return factory.supports(method);
    }
}
