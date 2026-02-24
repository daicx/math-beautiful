package com.skuu.design.decorator.decorators;

import com.skuu.design.decorator.Coffee;
import com.skuu.design.decorator.CoffeeDecorator;

/**
 * @author dcx
 * @description 香草装饰器 - 具体装饰器
 * @create 2025-01-27
 */
public class VanillaDecorator extends CoffeeDecorator {
    
    public VanillaDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription() + " + 香草";
    }
    
    @Override
    public double getCost() {
        return coffee.getCost() + 4.0;
    }
}
