package com.skuu.design.combination.validator;

import com.skuu.design.combination.model.Order;

/**
 * @author dcx
 * @description 订单校验器抽象类 - 责任链模式
 * @create 2025-01-27
 */
public abstract class OrderValidator {
    protected OrderValidator next;

    public OrderValidator setNext(OrderValidator validator) {
        this.next = validator;
        return validator;
    }

    public void validate(Order order) {
        doValidate(order);
        if (next != null) {
            next.validate(order);
        }
    }

    protected abstract void doValidate(Order order);
}

