package com.skuu.demo.ordersystem.event;

import com.skuu.demo.ordersystem.enums.OrderStatusEnum;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author dcx
 * @description 订单状态变更 - 库存监听（Spring Events）
 * @create 2025-01-27
 */
@Component
public class OrderStatusChangedInventoryListener {

    @EventListener
    public void onOrderStatusChanged(OrderStatusChangedEvent event) {
        OrderStatusEnum prev = event.getPreviousStatus();
        OrderStatusEnum next = event.getNewStatus();

        if (next == OrderStatusEnum.PAID && prev == OrderStatusEnum.PENDING_PAYMENT) {
            System.out.println(String.format(
                "[库存] 订单 %s 已支付，扣减商品 %s 的库存",
                event.getOrder().getOrderId(),
                event.getOrder().getProductId()
            ));
        }

        if ((next == OrderStatusEnum.CANCELLED || next == OrderStatusEnum.REFUNDED)
            && prev != OrderStatusEnum.CANCELLED && prev != OrderStatusEnum.REFUNDED) {
            System.out.println(String.format(
                "[库存] 订单 %s 已取消/退款，恢复商品 %s 的库存",
                event.getOrder().getOrderId(),
                event.getOrder().getProductId()
            ));
        }
    }
}
