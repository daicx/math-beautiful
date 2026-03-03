package com.skuu.springdesign.visitor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 访问者模式用法：accept(element, Map&lt;Class, Function&gt;) 按 element 类型选处理函数。
 */
@SpringBootTest
class VisitorServiceTest {

    @Autowired
    private VisitorService visitorService;

    @Test
    void dispatchByType() {
        Map<Class<?>, Function<Object, Integer>> visitors = new HashMap<>();
        visitors.put(String.class, s -> ((String) s).length());
        Integer len = visitorService.accept("hello", visitors);
        assertEquals(5, len);
    }
}
