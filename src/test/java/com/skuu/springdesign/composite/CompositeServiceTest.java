package com.skuu.springdesign.composite;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 组合模式用法：树节点 Node(name, children)，flatten 用 Stream 递归遍历。
 */
@SpringBootTest
class CompositeServiceTest {

    @Autowired
    private CompositeService compositeService;

    @Test
    void flattenTree() {
        CompositeService.Node leaf = new CompositeService.Node("leaf", Collections.emptyList());
        CompositeService.Node root = new CompositeService.Node("root", Collections.singletonList(leaf));
        compositeService.flatten(root).forEach(System.out::println);
        long count = compositeService.flatten(root).count();
        assertEquals(2, count);
    }
}
