package com.skuu.design.decorator.decorators;

import com.skuu.design.decorator.Coffee;
import com.skuu.design.decorator.CoffeeDecorator;

/**
 * @author dcx
 * @description 焦糖装饰器 - 具体装饰器
 * @create 2025-01-27
 */
public class CaramelDecorator extends CoffeeDecorator {
    
    public CaramelDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription() + " + 焦糖";
    }
    
    @Override
    public double getCost() {
        return coffee.getCost() + 5.0;
    }
}
