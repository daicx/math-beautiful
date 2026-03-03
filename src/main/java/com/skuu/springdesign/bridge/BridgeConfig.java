package com.skuu.springdesign.bridge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class BridgeConfig {

    @Bean
    public Function<String, String> renderer() {
        return shape -> "draw(" + shape + ")";
    }
}
