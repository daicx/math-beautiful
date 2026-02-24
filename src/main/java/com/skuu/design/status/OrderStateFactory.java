package com.skuu.design.status;

import com.skuu.design.status.states.*;

/**
 * @author dcx
 * @description 订单状态工厂 - 根据数据库状态创建状态对象
 * @create 2025-01-27
 */
public class OrderStateFactory {
    
    public static OrderState createState(OrderStatusEnum status) {
        switch (status) {
            case PENDING_PAYMENT:
                return new PendingPaymentState();
            case PAID:
                return new PaidState();
            case SHIPPED:
                return new ShippedState();
            case COMPLETED:
                return new CompletedState();
            case CANCELLED:
                return new CancelledState();
            case REFUNDING:
                return new RefundingState();
            default:
                throw new IllegalArgumentException("未知状态: " + status);
        }
    }
}