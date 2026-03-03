package com.skuu.springdesign.observer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 观察者模式用法：发布方 publishEvent，监听方用 @EventListener 自动接收。
 */
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    void createOrderFiresListener() {
        orderService.createOrder("ORD-OBS-1", 88.8);
        orderService.createOrder("ORD-OBS-2", 199.0);
        // 控制台可看到 OrderCreatedEventListener 输出
    }
}
