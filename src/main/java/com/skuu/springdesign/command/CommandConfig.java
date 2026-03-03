package com.skuu.springdesign.command;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CommandConfig {

    @Bean
    public Map<String, Runnable> commands() {
        Map<String, Runnable> map = new HashMap<>();
        map.put("lightOn", () -> System.out.println("Light ON"));
        map.put("lightOff", () -> System.out.println("Light OFF"));
        return map;
    }
}
