package com.skuu.springdesign.strategy.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 支付请求：订单号、金额、币种、用户标识等（贴近实际支付参数）。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {
    private String orderId;
    private BigDecimal amount;
    private String currency;   // CNY, USD 等
    private String userId;     // 付款用户
    private String subject;    // 商品描述，用于展示
    private String returnUrl;  // 可选：H5/网页支付回调地址
}
