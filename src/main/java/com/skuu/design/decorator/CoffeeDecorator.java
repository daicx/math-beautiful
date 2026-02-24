package com.skuu.design.decorator;

/**
 * @author dcx
 * @description 咖啡装饰器抽象类 - 装饰模式的Decorator
 * @create 2025-01-27
 */
public abstract class CoffeeDecorator implements Coffee {
    
    protected Coffee coffee;
    
    /**
     * 构造函数
     * @param coffee 被装饰的咖啡对象
     */
    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
    
    /**
     * 获取描述 - 由子类实现具体的装饰逻辑
     */
    @Override
    public abstract String getDescription();
    
    /**
     * 获取价格 - 由子类实现具体的价格计算
     */
    @Override
    public abstract double getCost();
}
