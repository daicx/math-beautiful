package com.skuu.design.combination.event.listeners;

import com.skuu.design.combination.event.OrderEvent;
import com.skuu.design.combination.event.OrderEventListener;

/**
 * @author dcx
 * @description 库存监听器
 * @create 2025-01-27
 */
public class InventoryListener implements OrderEventListener {
    @Override
    public void onEvent(OrderEvent event) {
        if ("ORDER_PAID".equals(event.getEventType())) {
            System.out.println("  📦 [库存监听器] 扣减库存");
            // 实际扣减库存
        }
    }
}

