package com.skuu.demo.ordersystem.enums;

/**
 * @author dcx
 * @description 订单状态枚举 - 使用枚举定义所有可能的状态
 * @create 2025-01-27
 */
public enum OrderStatusEnum {
    PENDING_PAYMENT("PENDING_PAYMENT", "待支付", 1),
    PAID("PAID", "已支付", 2),
    SHIPPED("SHIPPED", "已发货", 3),
    DELIVERED("DELIVERED", "已送达", 4),
    COMPLETED("COMPLETED", "已完成", 5),
    CANCELLED("CANCELLED", "已取消", 0),
    REFUNDING("REFUNDING", "退款中", 0),
    REFUNDED("REFUNDED", "已退款", 0);

    private final String code;
    private final String name;
    private final int priority; // 状态优先级，用于状态转换验证

    OrderStatusEnum(String code, String name, int priority) {
        this.code = code;
        this.name = name;
        this.priority = priority;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }
}
