package com.skuu.springdesign.bridge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 桥接模式用法：抽象侧依赖 Function（绘制实现）注入，draw 时调用。
 */
@SpringBootTest
class BridgeServiceTest {

    @Autowired
    private BridgeService bridgeService;

    @Test
    void drawByShape() {
        String r = bridgeService.draw("Circle");
        assertTrue(r.contains("draw") && r.contains("Circle"));
    }
}
