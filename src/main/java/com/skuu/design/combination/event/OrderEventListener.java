package com.skuu.design.combination.event;

/**
 * @author dcx
 * @description 订单事件监听器接口 - 观察者模式
 * @create 2025-01-27
 */
public interface OrderEventListener {
    void onEvent(OrderEvent event);
}

