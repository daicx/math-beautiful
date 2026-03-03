package com.skuu.demo.lambdaordersystem.enums;

/**
 * 订单动作枚举：与状态机中的 action 一一对应
 */
public enum OrderActionEnum {
    PAY("支付"),
    CANCEL("取消"),
    SHIP("发货"),
    REFUND("退款"),
    CONFIRM("确认收货"),
    COMPLETE("完成");

    private final String name;

    OrderActionEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
