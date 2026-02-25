package com.skuu.demo.ordersystem.state;

import com.skuu.demo.ordersystem.event.OrderStatusChangedEvent;
import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.enums.OrderStatusEnum;
import com.skuu.demo.ordersystem.validator.StateTransitionValidator;
import com.skuu.demo.ordersystem.validator.StateTransitionValidatorChain;
import com.skuu.demo.ordersystem.factory.StateFactory;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author dcx
 * @description 状态上下文 - 状态模式的核心类
 * 状态变更通过 Spring Events 发布，由 @EventListener 监听（替代 java.util.Observer）
 * @create 2025-01-27
 */
public class OrderStateContext {
    private final Order order;
    private OrderState orderState;
    private final StateFactory stateFactory;
    private final StateTransitionValidator validator;
    private final ApplicationEventPublisher eventPublisher;

    public OrderStateContext(Order order, StateFactory stateFactory) {
        this(order, stateFactory, null);
    }

    public OrderStateContext(Order order, StateFactory stateFactory, ApplicationEventPublisher eventPublisher) {
        this.order = order;
        this.stateFactory = stateFactory;
        this.validator = StateTransitionValidatorChain.createDefault();
        this.eventPublisher = eventPublisher;
        this.orderState = stateFactory.createState(order.getStatus());
    }

    /**
     * 转换状态 - 模板方法模式
     */
    public void transitionTo(OrderStatusEnum newStatus) {
        // 1. 验证状态转换是否合法（责任链模式）
        if (!validator.validate(orderState, newStatus, order)) {
            throw new IllegalStateException(
                String.format("订单 %s 不能从 %s 转换到 %s", 
                    order.getOrderId(), 
                    orderState.getStatus().getName(),
                    newStatus.getName())
            );
        }

        // 2. 执行状态转换前的钩子方法
        orderState.onExit(this);

        // 3. 创建新状态对象（工厂模式）
        OrderState previousState = orderState;
        this.orderState = stateFactory.createState(newStatus);
        
        // 4. 更新订单状态
        order.updateStatus(newStatus);

        // 5. 执行状态转换后的钩子方法
        orderState.onEnter(this);

        // 6. 发布 Spring 事件（由 @EventListener 监听）
        if (eventPublisher != null) {
            eventPublisher.publishEvent(new OrderStatusChangedEvent(order, previousState.getStatus(), orderState.getStatus()));
        }
    }

    /**
     * 委托方法 - 支付
     */
    public void pay() {
        orderState.pay(this);
    }

    /**
     * 委托方法 - 发货
     */
    public void ship() {
        orderState.ship(this);
    }

    /**
     * 委托方法 - 确认收货
     */
    public void confirm() {
        orderState.confirm(this);
    }

    /**
     * 委托方法 - 完成订单
     */
    public void complete() {
        orderState.complete(this);
    }

    /**
     * 委托方法 - 取消订单
     */
    public void cancel() {
        orderState.cancel(this);
    }

    /**
     * 委托方法 - 退款
     */
    public void refund() {
        orderState.refund(this);
    }

    // Getters
    public Order getOrder() {
        return order;
    }

    public OrderState getOrderStateBehavior() {
        return orderState;
    }

    public OrderStatusEnum getCurrentStatus() {
        return orderState.getStatus();
    }
}
