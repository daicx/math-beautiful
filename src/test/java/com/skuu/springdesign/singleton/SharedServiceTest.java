package com.skuu.springdesign.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 单例模式用法：Spring Bean 默认单例，同一上下文内多次获取为同一实例。
 */
@SpringBootTest
class SharedServiceTest {

    @Autowired
    private SharedService sharedService;

    @Test
    void sameInstanceWithinContext() {
        assertNotNull(sharedService.getId());
        assertTrue(sharedService.getId().startsWith("singleton-"));
    }
}
