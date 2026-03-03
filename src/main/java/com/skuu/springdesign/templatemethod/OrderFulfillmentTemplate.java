package com.skuu.springdesign.templatemethod;

import com.skuu.springdesign.templatemethod.step.ChargePaymentStep;
import com.skuu.springdesign.templatemethod.step.NotifyStep;
import com.skuu.springdesign.templatemethod.step.ReserveInventoryStep;
import com.skuu.springdesign.templatemethod.step.ShipStep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 模板方法：固定流程「校验 → 执行步骤链（可中断）→ 统一结果」。
 * 只接收「订单数据 + 步骤链」；步骤链可由调用方传入或由 Registry 按类型提供。
 */
@Component
@RequiredArgsConstructor
public class OrderFulfillmentTemplate {

    private final OrderStepRegistry stepRegistry;

    /**
     * 执行履约：校验 → 取步骤（传入为空则从 Registry 按类型取）→ 顺序执行，某步返回 false 即中断并失败。
     */
    public OrderFulfillmentResult execute(OrderData data) {
        return execute(data, null);
    }

    /**
     * 执行履约，可显式指定步骤链；为空则使用 Registry 按 data.orderType 获取。
     */
    public OrderFulfillmentResult execute(OrderData data, List<OrderStep> steps) {
        Optional<OrderFulfillmentResult> validationFailure = validate(data);
        if (validationFailure.isPresent()) {
            return validationFailure.get();
        }

        List<OrderStep> toRun = steps != null && !steps.isEmpty()
                ? steps
                : stepRegistry.getStepsFor(data.getOrderType());

        if (toRun.isEmpty()) {
            return OrderFulfillmentResult.failure(
                    data.getOrderId(),
                    "No steps for order type: " + data.getOrderType(),
                    Collections.emptyList());
        }

        OrderContext ctx = new OrderContext(data);
        for (OrderStep step : toRun) {
            if (!step.execute(ctx)) {
                return OrderFulfillmentResult.fromContext(ctx, false);
            }
        }
        return OrderFulfillmentResult.fromContext(ctx, true);
    }

    private Optional<OrderFulfillmentResult> validate(OrderData data) {
        if (data.getOrderId() == null || data.getOrderId().trim().isEmpty()) {
            return Optional.of(OrderFulfillmentResult.failure(
                    data.getOrderId(), "orderId is required", Collections.emptyList()));
        }
        if (data.getAmount() == null || data.getAmount().signum() <= 0) {
            return Optional.of(OrderFulfillmentResult.failure(
                    data.getOrderId(), "amount must be positive", Collections.emptyList()));
        }
        return Optional.empty();
    }
}
