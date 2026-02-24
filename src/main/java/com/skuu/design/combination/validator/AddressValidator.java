package com.skuu.design.combination.validator;

import com.skuu.design.combination.model.Order;

/**
 * @author dcx
 * @description 地址校验器
 * @create 2025-01-27
 */
public class AddressValidator extends OrderValidator {
    @Override
    protected void doValidate(Order order) {
        System.out.println("✓ 地址校验通过");
        if (order.getAddress() == null || order.getAddress().isEmpty()) {
            throw new RuntimeException("配送地址不能为空");
        }
    }
}

