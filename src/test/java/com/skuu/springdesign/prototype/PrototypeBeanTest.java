package com.skuu.springdesign.prototype;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 原型模式用法：@Scope(SCOPE_PROTOTYPE) 每次 getBean 或 ObjectProvider.getObject() 得到新实例。
 */
@SpringBootTest
class PrototypeBeanTest {

    @Autowired
    private org.springframework.beans.factory.ObjectProvider<PrototypeBean> prototypeProvider;

    @Test
    void eachGetReturnsNewInstance() {
        PrototypeBean p1 = prototypeProvider.getObject();
        PrototypeBean p2 = prototypeProvider.getObject();
        assertNotEquals(p1.getId(), p2.getId());
    }
}
