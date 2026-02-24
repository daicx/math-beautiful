package com.skuu.design.combination.validator;

import com.skuu.design.combination.model.Order;
import com.skuu.design.combination.model.OrderItem;

/**
 * @author dcx
 * @description 库存校验器
 * @create 2025-01-27
 */
public class StockValidator extends OrderValidator {
    @Override
    protected void doValidate(Order order) {
        System.out.println("✓ 库存校验通过");
        // 实际检查库存
        for (OrderItem item : order.getItems()) {
            if (!checkStock(item.getProductId(), item.getQuantity())) {
                throw new RuntimeException("商品库存不足: " + item.getProductId());
            }
        }
    }

    private boolean checkStock(String productId, int quantity) {
        return true;  // 模拟库存充足
    }
}

