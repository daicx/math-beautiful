package com.skuu.springdesign.templatemethod;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 统一履约结果：校验失败与步骤失败共用，便于调用方与监控处理。
 */
@Value
@Builder
public class OrderFulfillmentResult {

    boolean success;
    String orderId;
    String failureReason;
    List<String> logs;

    public static OrderFulfillmentResult success(String orderId, List<String> logs) {
        return OrderFulfillmentResult.builder()
                .success(true)
                .orderId(orderId)
                .failureReason(null)
                .logs(logs != null ? new ArrayList<>(logs) : Collections.<String>emptyList())
                .build();
    }

    public static OrderFulfillmentResult failure(String orderId, String failureReason, List<String> logs) {
        return OrderFulfillmentResult.builder()
                .success(false)
                .orderId(orderId)
                .failureReason(failureReason)
                .logs(logs != null ? new ArrayList<>(logs) : Collections.<String>emptyList())
                .build();
    }

    public static OrderFulfillmentResult fromContext(OrderContext ctx, boolean success) {
        return success
                ? success(ctx.getOrderId(), ctx.getLogs())
                : failure(ctx.getOrderId(), ctx.getFailureReason(), ctx.getLogs());
    }
}
