package com.skuu.springdesign.adapter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 适配器模式用法：Function&lt;LegacyService, ModernApi&gt; 将旧接口适配为新 DTO。
 */
@SpringBootTest
class AdapterTest {

    @Autowired
    private LegacyService legacyService;

    @Autowired
    private Function<LegacyService, ModernApi> legacyToModern;

    @Test
    void adaptToModernApi() {
        ModernApi api = legacyToModern.apply(legacyService);
        assertEquals("legacy-data", api.getData());
    }
}
