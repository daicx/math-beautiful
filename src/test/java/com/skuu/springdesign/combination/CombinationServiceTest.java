package com.skuu.springdesign.combination;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 组合（业务）用法：Events 发布 + 校验链 List&lt;Predicate&gt;，综合多种模式。
 */
@SpringBootTest
class CombinationServiceTest {

    @Autowired
    private CombinationService combinationService;

    @Test
    void validatePasses() {
        boolean ok = combinationService.validate("request", Collections.singletonList(o -> true));
        assertTrue(ok);
    }

    @Test
    void publishEvent() {
        combinationService.publish(new Object());
    }
}
