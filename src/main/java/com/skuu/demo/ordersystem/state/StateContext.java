package com.skuu.demo.ordersystem.state;

import com.skuu.demo.ordersystem.model.Order;
import com.skuu.demo.ordersystem.model.OrderStatusEnum;
import com.skuu.demo.ordersystem.observer.OrderObserver;
import com.skuu.demo.ordersystem.validator.StateTransitionValidator;
import com.skuu.demo.ordersystem.factory.StateFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dcx
 * @description 状态上下文 - 状态模式的核心类
 * 结合观察者模式、责任链模式等
 * @create 2025-01-27
 */
public class StateContext {
    private Order order;
    private OrderStateBehavior orderStateBehavior;
    private StateFactory stateFactory;
    private List<OrderObserver> observers;
    private StateTransitionValidator validator;

    public StateContext(Order order, StateFactory stateFactory) {
        this.order = order;
        this.stateFactory = stateFactory;
        this.observers = new ArrayList<>();
        this.validator = new StateTransitionValidator();
        // 根据订单当前状态初始化状态对象
        this.orderStateBehavior = stateFactory.createState(order.getStatus());
    }

    /**
     * 转换状态 - 模板方法模式
     */
    public void transitionTo(OrderStatusEnum newStatus) {
        // 1. 验证状态转换是否合法（责任链模式）
        if (!validator.validate(orderStateBehavior, newStatus, order)) {
            throw new IllegalStateException(
                String.format("订单 %s 不能从 %s 转换到 %s", 
                    order.getOrderId(), 
                    orderStateBehavior.getStatus().getName(),
                    newStatus.getName())
            );
        }

        // 2. 执行状态转换前的钩子方法
        orderStateBehavior.onExit(this);

        // 3. 创建新状态对象（工厂模式）
        OrderStateBehavior previousState = orderStateBehavior;
        this.orderStateBehavior = stateFactory.createState(newStatus);
        
        // 4. 更新订单状态
        order.updateStatus(newStatus);

        // 5. 执行状态转换后的钩子方法
        orderStateBehavior.onEnter(this);

        // 6. 通知观察者（观察者模式）
        notifyObservers(previousState, orderStateBehavior);
    }

    /**
     * 委托方法 - 支付
     */
    public void pay() {
        orderStateBehavior.pay(this);
    }

    /**
     * 委托方法 - 发货
     */
    public void ship() {
        orderStateBehavior.ship(this);
    }

    /**
     * 委托方法 - 确认收货
     */
    public void confirm() {
        orderStateBehavior.confirm(this);
    }

    /**
     * 委托方法 - 完成订单
     */
    public void complete() {
        orderStateBehavior.complete(this);
    }

    /**
     * 委托方法 - 取消订单
     */
    public void cancel() {
        orderStateBehavior.cancel(this);
    }

    /**
     * 委托方法 - 退款
     */
    public void refund() {
        orderStateBehavior.refund(this);
    }

    /**
     * 注册观察者
     */
    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    /**
     * 移除观察者
     */
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    /**
     * 通知所有观察者
     */
    private void notifyObservers(OrderStateBehavior previousState, OrderStateBehavior newState) {
        for (OrderObserver observer : observers) {
            observer.onStatusChanged(order, previousState.getStatus(), newState.getStatus());
        }
    }

    // Getters
    public Order getOrder() {
        return order;
    }

    public OrderStateBehavior getOrderStateBehavior() {
        return orderStateBehavior;
    }

    public OrderStatusEnum getCurrentStatus() {
        return orderStateBehavior.getStatus();
    }
}
