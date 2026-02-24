package com.skuu.design.combination.validator;

import com.skuu.design.combination.model.Order;

/**
 * @author dcx
 * @description 价格校验器
 * @create 2025-01-27
 */
public class PriceValidator extends OrderValidator {
    @Override
    protected void doValidate(Order order) {
        System.out.println("✓ 价格校验通过");
        // 实际校验价格
    }
}

