package com.skuu.springdesign.facade;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 外观模式用法：门面 Service 注入多个子组件，对外单一方法 doAll()。
 */
@SpringBootTest
class FacadeServiceTest {

    @Autowired
    private FacadeService facadeService;

    @Test
    void facadeUnifiedCall() {
        String result = facadeService.doAll();
        assertEquals("A-B", result);
    }
}
