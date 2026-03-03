package com.skuu.springdesign.strategy.payment;

import com.skuu.springdesign.strategy.PaymentStrategy;
import com.skuu.springdesign.strategy.pojo.PaymentRequest;
import com.skuu.springdesign.strategy.pojo.PaymentResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 微信支付策略（实际可对接微信支付 API）。
 */
@Component
public class WechatPayStrategy implements PaymentStrategy {

    private static final Logger log = LoggerFactory.getLogger(WechatPayStrategy.class);

    @Override
    public String getMethod() {
        return "wechat";
    }

    @Override
    public PaymentResult pay(PaymentRequest request) {
        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return PaymentResult.fail(getMethod(), "INVALID_AMOUNT", "Amount must be positive");
        }
        log.info("[WeChat] order={}, amount={} {}, userId={}", request.getOrderId(),
                request.getAmount(), request.getCurrency(), request.getUserId());
        String transactionId = "WX-" + System.currentTimeMillis();
        String externalId = "wx_prepay_" + transactionId;
        return PaymentResult.ok(getMethod(), transactionId, externalId);
    }
}
