package com.skuu.springdesign.strategy.payment;

import com.skuu.springdesign.strategy.PaymentStrategy;
import com.skuu.springdesign.strategy.pojo.PaymentRequest;
import com.skuu.springdesign.strategy.pojo.PaymentResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 银行卡支付策略（实际可对接银联/支付网关）。
 */
@Component
public class CardStrategy implements PaymentStrategy {

    private static final Logger log = LoggerFactory.getLogger(CardStrategy.class);

    @Override
    public String getMethod() {
        return "card";
    }

    @Override
    public PaymentResult pay(PaymentRequest request) {
        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return PaymentResult.fail(getMethod(), "INVALID_AMOUNT", "Amount must be positive");
        }
        log.info("[Card] order={}, amount={} {}, userId={}", request.getOrderId(),
                request.getAmount(), request.getCurrency(), request.getUserId());
        String transactionId = "CARD-" + System.currentTimeMillis();
        String externalId = "unionpay-" + transactionId;
        return PaymentResult.ok(getMethod(), transactionId, externalId);
    }
}
