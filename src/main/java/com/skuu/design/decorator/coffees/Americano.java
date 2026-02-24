package com.skuu.design.decorator.coffees;

import com.skuu.design.decorator.Coffee;

/**
 * @author dcx
 * @description 美式咖啡 - 具体组件
 * @create 2025-01-27
 */
public class Americano implements Coffee {

    @Override
    public String getDescription() {
        return "美式咖啡";
    }

    @Override
    public double getCost() {
        return 12.0;
    }
}
