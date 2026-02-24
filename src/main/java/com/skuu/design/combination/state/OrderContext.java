package com.skuu.design.combination.state;

import com.skuu.design.combination.model.Order;

/**
 * @author dcx
 * @description 订单上下文
 * @create 2025-01-27
 */
public class OrderContext {
    private Order order;

    public OrderContext(Order order) {
        this.order = order;
    }

    public void setState(OrderState state) {
        System.out.println("订单状态变更: " + order.getState().getStateName() + " → " + state.getStateName());
        order.setState(state);
    }

    public void pay() {
        order.getState().pay(this);
    }

    public void ship() {
        order.getState().ship(this);
    }

    public void cancel() {
        order.getState().cancel(this);
    }

    public Order getOrder() {
        return order;
    }
}

