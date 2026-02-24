package com.skuu.design.factory.gcff.bo;

import com.skuu.design.factory.gcff.Product;

/**
 * @author dcx
 * @description 2. 具体产品 - 汽车
 * @create 2025-09-12 16:56
 **/
public class Car implements Product {
    @Override
    public void start() {
        System.out.println("汽车启动：转动钥匙，发动机启动");
    }

    @Override
    public void stop() {
        System.out.println("汽车停止：踩刹车，发动机熄火");
    }

    @Override
    public void drive() {
        System.out.println("汽车行驶：踩油门，转动方向盘");
    }

    @Override
    public String getType() {
        return "汽车";
    }
}
