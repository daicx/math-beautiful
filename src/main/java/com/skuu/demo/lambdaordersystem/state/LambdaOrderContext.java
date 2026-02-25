package com.skuu.demo.lambdaordersystem.state;

import com.skuu.demo.lambdaordersystem.event.OrderStatusChangedEvent;
import com.skuu.demo.lambdaordersystem.model.Order;
import com.skuu.demo.lambdaordersystem.enums.OrderStatusEnum;
import com.skuu.demo.lambdaordersystem.validator.TransitionRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 订单状态上下文（lambda 版）：通过配置表 + Consumer 驱动，无子类状态
 */
public class LambdaOrderContext {
    private final Order order;
    private OrderStatusEnum currentStatus;
    private final StateMachineConfig config;
    private final Predicate<TransitionRequest> validator;
    private final List<Consumer<OrderStatusChangedEvent>> statusChangeListeners = new ArrayList<>();

    public LambdaOrderContext(Order order, StateMachineConfig config, Predicate<TransitionRequest> validator) {
        this.order = order;
        this.currentStatus = order.getStatus();
        this.config = config;
        this.validator = validator;
    }

    public void addStatusChangeListener(Consumer<OrderStatusChangedEvent> listener) {
        if (listener != null) statusChangeListeners.add(listener);
    }

    public Order getOrder() { return order; }
    public OrderStatusEnum getCurrentStatus() { return currentStatus; }

    /**
     * 执行状态转换（校验 -> 侧效 -> 更新 -> 通知）
     */
    public void transitionTo(OrderStatusEnum newStatus) {
        TransitionRequest req = new TransitionRequest(currentStatus, newStatus, order);
        if (!validator.test(req)) {
            throw new IllegalStateException(
                String.format("订单 %s 不能从 %s 转换到 %s",
                    order.getOrderId(), currentStatus.getName(), newStatus.getName()));
        }
        OrderStatusEnum previous = currentStatus;
        order.updateStatus(newStatus);
        this.currentStatus = newStatus;
        java.util.function.Consumer<LambdaOrderContext> onEnter = config.getOnEnter(newStatus);
        if (onEnter != null) onEnter.accept(this);
        OrderStatusChangedEvent event = new OrderStatusChangedEvent(order, previous, newStatus);
        statusChangeListeners.forEach(l -> l.accept(event));
    }

    /**
     * 根据动作名执行：查表得到 ActionHandler，校验后执行 beforeTransition 再 transitionTo
     */
    public void runAction(String actionName) {
        Map<String, ActionHandler> actions = config.getHandlers(currentStatus);
        ActionHandler handler = actions != null ? actions.get(actionName) : null;
        if (handler == null) {
            throw new IllegalStateException(
                String.format("当前状态 %s 不支持操作: %s", currentStatus.getName(), actionName));
        }
        if (!validator.test(new TransitionRequest(currentStatus, handler.getNextStatus(), order))) {
            throw new IllegalStateException(
                String.format("订单 %s 不能从 %s 转换到 %s",
                    order.getOrderId(), currentStatus.getName(), handler.getNextStatus().getName()));
        }
        handler.getBeforeTransition().accept(this);
        transitionTo(handler.getNextStatus());
    }

    public void pay()   { runAction("pay"); }
    public void cancel() { runAction("cancel"); }
    public void ship()   { runAction("ship"); }
    public void refund() { runAction("refund"); }
    public void confirm() { runAction("confirm"); }
    public void complete() { runAction("complete"); }
}
