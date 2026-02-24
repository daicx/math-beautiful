package com.skuu.design.factory.cxgc;

/**
 * 1. 抽象产品A - 操作系统
 */
public interface OperatingSystem {
    void boot();

    void shutdown();

    String getName();
}
