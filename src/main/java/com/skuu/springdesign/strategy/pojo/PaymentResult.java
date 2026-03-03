package com.skuu.springdesign.strategy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 支付结果：是否成功、流水号、第三方交易号或错误码（贴近实际对账与排查）。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResult {
    private boolean success;
    private String method;         // 支付方式：alipay / wechat / card
    private String transactionId; // 成功时：我方或第三方流水号
    private String externalId;    // 成功时：第三方返回的交易号，用于对账
    private String errorCode;     // 失败时：错误码
    private String errorMsg;      // 失败时：错误描述

    public static PaymentResult ok(String method, String transactionId, String externalId) {
        return new PaymentResult(true, method, transactionId, externalId, null, null);
    }

    public static PaymentResult fail(String method, String errorCode, String errorMsg) {
        return new PaymentResult(false, method, null, null, errorCode, errorMsg);
    }
}
