package com.skuu.springdesign.adapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * 适配器：Function<LegacyService, ModernApi> 将旧接口适配为新 DTO。
 */
@Configuration
public class AdapterConfig {

    @Bean
    public Function<LegacyService, ModernApi> legacyToModern() {
        return legacy -> new ModernApi(legacy.fetchData());
    }
}
