package com.skuu.springdesign.flyweight;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * 享元：ConcurrentHashMap.computeIfAbsent(key, k -> create(key)) 做池化。
 */
@Service
public class FlyweightService {

    private final ConcurrentHashMap<String, String> pool = new ConcurrentHashMap<>();

    public String get(String key, Supplier<String> factory) {
        return pool.computeIfAbsent(key, k -> factory.get());
    }
}
