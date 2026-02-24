package com.skuu.design.decorator.decorators;

import com.skuu.design.decorator.Coffee;
import com.skuu.design.decorator.CoffeeDecorator;

/**
 * @author dcx
 * @description 牛奶装饰器 - 具体装饰器
 * @create 2025-01-27
 */
public class MilkDecorator extends CoffeeDecorator {
    
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription() + " + 牛奶";
    }
    
    @Override
    public double getCost() {
        return coffee.getCost() + 3.0;
    }
}
