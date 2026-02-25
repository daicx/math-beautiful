package com.skuu.demo.lambdaordersystem.state;

import com.skuu.demo.lambdaordersystem.enums.OrderStatusEnum;

import java.util.function.Consumer;

/**
 * 某状态下某动作的处理器：侧效 + 目标状态（lambda 表达）
 */
public final class ActionHandler {
    private final OrderStatusEnum nextStatus;
    private final Consumer<LambdaOrderContext> beforeTransition;

    public ActionHandler(OrderStatusEnum nextStatus, Consumer<LambdaOrderContext> beforeTransition) {
        this.nextStatus = nextStatus;
        this.beforeTransition = beforeTransition != null ? beforeTransition : c -> {};
    }

    public OrderStatusEnum getNextStatus() { return nextStatus; }
    public Consumer<LambdaOrderContext> getBeforeTransition() { return beforeTransition; }
}
