package com.skuu.springdesign.mediator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 中介者模式用法：MediatorService 通过 ApplicationEventPublisher 广播，各 Colleague 用 @EventListener 接收。
 */
@SpringBootTest
class MediatorServiceTest {

    @Autowired
    private MediatorService mediatorService;

    @Test
    void broadcastMessage() {
        mediatorService.broadcast("hello");
    }
}
