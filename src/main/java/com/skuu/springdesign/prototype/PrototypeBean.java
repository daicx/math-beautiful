package com.skuu.springdesign.prototype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * 原型：@Scope(SCOPE_PROTOTYPE) 每次获取新实例；或通过 ObjectFactory/Provider 注入。
 */
@Component
@Scope(SCOPE_PROTOTYPE)
public class PrototypeBean {
    private final String id = "proto-" + System.identityHashCode(this);

    public String getId() {
        return id;
    }
}
