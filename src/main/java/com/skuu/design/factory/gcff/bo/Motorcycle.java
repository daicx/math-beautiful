package com.skuu.design.factory.gcff.bo;

import com.skuu.design.factory.gcff.Product;

/**
 * @author dcx
 * @description 3. 具体产品 - 摩托车
 * @create 2025-09-12 16:57
 **/
public class Motorcycle implements Product {
    @Override
    public void start() {
        System.out.println("摩托车启动：踩启动杆，发动机启动");
    }

    @Override
    public void stop() {
        System.out.println("摩托车停止：踩刹车，发动机熄火");
    }

    @Override
    public void drive() {
        System.out.println("摩托车行驶：拧油门，转动车把");
    }

    @Override
    public String getType() {
        return "摩托车";
    }
}
