package com.skuu.design.combination.strategy;

/**
 * @author dcx
 * @description 支付结果
 * @create 2025-01-27
 */
public class PaymentResult {
    private boolean success;
    private String message;
    private String transactionId;

    public PaymentResult(boolean success, String message, String transactionId) {
        this.success = success;
        this.message = message;
        this.transactionId = transactionId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getTransactionId() {
        return transactionId;
    }
}

