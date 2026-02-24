package com.skuu.design.combination.factory;

import com.skuu.design.combination.model.Order;
import com.skuu.design.combination.model.OrderItem;
import com.skuu.design.combination.state.PendingPaymentState;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author dcx
 * @description 订单工厂 - 工厂模式
 * @create 2025-01-27
 */
public class OrderFactory {
    private static long orderCounter = 1000;

    public static Order createOrder(String userId, List<OrderItem> items, String address) {
        // 计算总金额
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : items) {
            total = total.add(item.getSubtotal());
        }

        // 使用建造者模式创建订单
        return Order.builder()
                .orderId("ORD" + (++orderCounter))
                .userId(userId)
                .items(items)
                .totalAmount(total)
                .address(address)
                .state(new PendingPaymentState())
                .createTime(LocalDateTime.now())
                .build();
    }
}

