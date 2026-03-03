package com.skuu.springdesign.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 代理模式用法：ProxyService 包装 RealService，前后可加日志等侧效。
 */
@SpringBootTest
class ProxyServiceTest {

    @Autowired
    private ProxyService proxyService;

    @Test
    void callThroughProxy() {
        String result = proxyService.doSomething("input");
        assertEquals("real:input", result);
    }
}
