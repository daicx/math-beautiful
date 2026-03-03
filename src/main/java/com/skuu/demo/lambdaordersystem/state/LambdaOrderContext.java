package com.skuu.demo.lambdaordersystem.state;

import com.skuu.demo.lambdaordersystem.enums.OrderActionEnum;
import com.skuu.demo.lambdaordersystem.enums.OrderStatusEnum;
import com.skuu.demo.lambdaordersystem.event.OrderStatusChangedEvent;
import com.skuu.demo.lambdaordersystem.model.Order;
import com.skuu.demo.lambdaordersystem.validator.TransitionRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 订单状态上下文（lambda 版）。高并发时由上层在调用 pay/ship/transitionTo 等前对订单加锁。
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
        Optional.ofNullable(listener).ifPresent(statusChangeListeners::add);
    }

    public Order getOrder() { return order; }
    public OrderStatusEnum getCurrentStatus() { return currentStatus; }

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
        Optional.ofNullable(config.getOnEnter(newStatus)).ifPresent(onEnter -> onEnter.accept(this));
        OrderStatusChangedEvent event = new OrderStatusChangedEvent(order, previous, newStatus);
        statusChangeListeners.forEach(listener -> listener.accept(event));
    }

    public void runAction(OrderActionEnum action) {
        ActionHandler handler = Optional.ofNullable(config.getHandlers(currentStatus))
            .map(m -> m.get(action))
            .orElseThrow(() -> new IllegalStateException(
                String.format("当前状态 %s 不支持操作: %s", currentStatus.getName(), action.getName())));
        TransitionRequest req = new TransitionRequest(currentStatus, handler.getNextStatus(), order);
        if (!validator.test(req)) {
            throw new IllegalStateException(
                String.format("订单 %s 不能从 %s 转换到 %s",
                    order.getOrderId(), currentStatus.getName(), handler.getNextStatus().getName()));
        }
        handler.getBeforeTransition().accept(this);
        transitionTo(handler.getNextStatus());
    }

    public void pay()    { runAction(OrderActionEnum.PAY); }
    public void cancel() { runAction(OrderActionEnum.CANCEL); }
    public void ship()   { runAction(OrderActionEnum.SHIP); }
    public void refund() { runAction(OrderActionEnum.REFUND); }
    public void confirm() { runAction(OrderActionEnum.CONFIRM); }
    public void complete() { runAction(OrderActionEnum.COMPLETE); }
}
