package com.skuu.springdesign.strategy.payment;

import com.skuu.springdesign.strategy.PaymentStrategy;
import com.skuu.springdesign.strategy.pojo.PaymentRequest;
import com.skuu.springdesign.strategy.pojo.PaymentResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 支付宝支付策略（实际可对接支付宝 SDK / 开放平台）。
 */
@Component
public class AlipayStrategy implements PaymentStrategy {

    private static final Logger log = LoggerFactory.getLogger(AlipayStrategy.class);

    @Override
    public String getMethod() {
        return "alipay";
    }

    @Override
    public PaymentResult pay(PaymentRequest request) {
        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return PaymentResult.fail(getMethod(), "INVALID_AMOUNT", "Amount must be positive");
        }
        log.info("[Alipay] order={}, amount={} {}, userId={}", request.getOrderId(),
                request.getAmount(), request.getCurrency(), request.getUserId());
        String transactionId = "TXN-" + System.currentTimeMillis();
        String externalId = "alipay-" + transactionId;
        return PaymentResult.ok(getMethod(), transactionId, externalId);
    }
}
