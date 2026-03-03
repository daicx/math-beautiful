package com.skuu.springdesign.templatemethod;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 履约执行上下文：只读订单数据 + 可变日志与失败原因，在步骤间传递。
 */
@Getter
@RequiredArgsConstructor
public class OrderContext {

    private final OrderData orderData;
    private final List<String> logs = new ArrayList<>();
    private String failureReason;

    public String getOrderId() {
        return orderData.getOrderId();
    }

    public OrderData.OrderType getOrderType() {
        return orderData.getOrderType();
    }

    public java.math.BigDecimal getAmount() {
        return orderData.getAmount();
    }

    public void log(String step, String message) {
        logs.add(step + ": " + message);
    }

    public void setFailureReason(String reason) {
        this.failureReason = reason;
    }

    public List<String> getLogs() {
        return Collections.unmodifiableList(logs);
    }
}
