package com.skuu.springdesign.bridge;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * 桥接：抽象侧依赖“实现”接口，实现侧为 Function 或 @Component 注入。
 */
@Service
@RequiredArgsConstructor
public class BridgeService {

    private final Function<String, String> renderer;

    public String draw(String shape) {
        return renderer.apply(shape);
    }
}
