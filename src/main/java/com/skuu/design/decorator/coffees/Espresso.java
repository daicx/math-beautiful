package com.skuu.design.decorator.coffees;

import com.skuu.design.decorator.Coffee;

/**
 * @author dcx
 * @description 浓缩咖啡 - 具体组件
 * @create 2025-01-27
 */
public class Espresso implements Coffee {

    @Override
    public String getDescription() {
        return "浓缩咖啡";
    }

    @Override
    public double getCost() {
        return 15.0;
    }
}
