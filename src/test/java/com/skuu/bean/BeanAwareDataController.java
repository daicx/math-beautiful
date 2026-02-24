package com.skuu.bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeanAwareDataController {

    @Autowired
    private BeanAware beanAware;

    @Test
    void use() {
        beanAware.use();
    }
}