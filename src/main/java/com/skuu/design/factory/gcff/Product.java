package com.skuu.design.factory.gcff;

/**
 * 1.产品接口 - 定义产品的通用行为
 */
public interface Product {
    void start();

    void stop();

    void drive();

    String getType();
}
