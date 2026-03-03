package com.skuu.springdesign.singleton;

/**
 * 单例目标：全局共享服务（Spring 容器内唯一）。
 */
public class SharedService {
    private final String id = "singleton-" + System.identityHashCode(this);

    public String getId() {
        return id;
    }
}
