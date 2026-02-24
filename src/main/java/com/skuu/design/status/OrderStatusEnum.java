package com.skuu.design.status;

/**
 * @author dcx
 * @description 订单状态枚举
 * @create 2025-01-27
 */
public enum OrderStatusEnum {
    PENDING_PAYMENT("PENDING_PAYMENT", "待支付"),
    PAID("PAID", "已支付"),
    SHIPPED("SHIPPED", "已发货"),
    COMPLETED("COMPLETED", "已完成"),
    CANCELLED("CANCELLED", "已取消"),
    REFUNDING("REFUNDING", "退款中");

    private final String code;
    private final String name;

    OrderStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() { 
        return code; 
    }
    
    public String getName() { 
        return name; 
    }
}