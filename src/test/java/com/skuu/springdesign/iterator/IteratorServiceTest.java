package com.skuu.springdesign.iterator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 迭代器模式用法：asStream(Iterable) 转为 Stream，用 Stream API 遍历/过滤。
 */
@SpringBootTest
class IteratorServiceTest {

    @Autowired
    private IteratorService iteratorService;

    @Test
    void asStreamIterate() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        long count = iteratorService.asStream(list).filter(i -> i > 1).count();
        assertEquals(2, count);
    }
}
