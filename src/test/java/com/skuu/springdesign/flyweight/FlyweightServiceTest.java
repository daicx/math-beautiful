package com.skuu.springdesign.flyweight;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 享元模式用法：get(key, factory) 用 computeIfAbsent 池化，同 key 返回同一实例。
 */
@SpringBootTest
class FlyweightServiceTest {

    @Autowired
    private FlyweightService flyweightService;

    @Test
    void sameKeyReused() {
        String a = flyweightService.get("k1", () -> "created");
        String b = flyweightService.get("k1", () -> "never");
        assertEquals("created", a);
        assertEquals("created", b);
    }
}
