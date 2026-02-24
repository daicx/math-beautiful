package com.skuu.design.factory.gcff;

import com.skuu.design.factory.gcff.factory.BicycleFactory;
import com.skuu.design.factory.gcff.factory.CarFactory;

/**
 * 工厂方法模式定义：
 * 定义一个创建对象的接口，但让子类决定实例化哪一个类。工厂方法让类的实例化推迟到子类。
 * <p>
 * 核心组件：
 * 1. Product（产品接口）
 * 2. ConcreteProduct（具体产品）
 * 3. Creator（创建者抽象类）
 * 4. ConcreteCreator（具体创建者）
 */
public class Test {
    public static void main(String[] args) {
        CarFactory carFactory = new CarFactory();
        carFactory.createAndUseProduct();
        BicycleFactory bicycleFactory = new BicycleFactory();
        bicycleFactory.createAndUseProduct();
    }
}
