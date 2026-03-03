package com.skuu.springdesign.strategy;

import com.skuu.springdesign.strategy.payment.AlipayStrategy;
import com.skuu.springdesign.strategy.payment.CardStrategy;
import com.skuu.springdesign.strategy.payment.WechatPayStrategy;
import com.skuu.springdesign.strategy.pojo.PaymentRequest;
import com.skuu.springdesign.strategy.pojo.PaymentResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 支付策略用法：按方式名获取策略并支付，校验 PaymentResult（流水号、错误码等）。
 */
@SpringBootTest(classes = {
        PaymentStrategyService.class,
        PaymentStrategyFactory.class,
        AlipayStrategy.class,
        WechatPayStrategy.class,
        CardStrategy.class
})
class PaymentStrategyServiceTest {

    @Autowired
    private PaymentStrategyService paymentStrategyService;

    @Test
    void payWithAlipay() {
        PaymentRequest req = PaymentRequest.builder()
                .orderId("ORD001")
                .amount(new BigDecimal("99.50"))
                .currency("CNY")
                .userId("u1")
                .subject("测试商品")
                .build();
        PaymentResult result = paymentStrategyService.pay("alipay", req);

        assertTrue(result.isSuccess());
        assertEquals("alipay", result.getMethod());
        assertNotNull(result.getTransactionId());
        assertTrue(result.getTransactionId().startsWith("TXN-"));
        assertNotNull(result.getExternalId());
        assertTrue(result.getExternalId().startsWith("alipay-"));
    }

    @Test
    void payWithWechat() {
        PaymentRequest req = PaymentRequest.builder()
                .orderId("ORD002")
                .amount(new BigDecimal("10.00"))
                .currency("CNY")
                .userId("u2")
                .build();
        PaymentResult result = paymentStrategyService.pay("wechat", req);

        assertTrue(result.isSuccess());
        assertEquals("wechat", result.getMethod());
        assertTrue(result.getTransactionId().startsWith("WX-"));
    }

    @Test
    void payWithCard() {
        PaymentRequest req = PaymentRequest.builder()
                .orderId("ORD003")
                .amount(new BigDecimal("199.00"))
                .currency("CNY")
                .userId("u3")
                .build();
        PaymentResult result = paymentStrategyService.pay("card", req);

        assertTrue(result.isSuccess());
        assertEquals("card", result.getMethod());
        assertTrue(result.getTransactionId().startsWith("CARD-"));
    }

    @Test
    void invalidAmountReturnsFailure() {
        PaymentRequest req = PaymentRequest.builder()
                .orderId("ORD004")
                .amount(BigDecimal.ZERO)
                .currency("CNY")
                .userId("u4")
                .build();
        PaymentResult result = paymentStrategyService.pay("alipay", req);

        assertFalse(result.isSuccess());
        assertEquals("alipay", result.getMethod());
        assertEquals("INVALID_AMOUNT", result.getErrorCode());
        assertNotNull(result.getErrorMsg());
    }

    @Test
    void supportsKnownMethods() {
        assertTrue(paymentStrategyService.supports("alipay"));
        assertTrue(paymentStrategyService.supports("wechat"));
        assertTrue(paymentStrategyService.supports("card"));
    }

    @Test
    void unknownMethodThrows() {
        assertFalse(paymentStrategyService.supports("crypto"));
        PaymentRequest req = PaymentRequest.builder()
                .orderId("ORD")
                .amount(BigDecimal.ONE)
                .currency("CNY")
                .userId("u")
                .build();
        assertThrows(IllegalArgumentException.class,
                () -> paymentStrategyService.pay("crypto", req));
    }
}
