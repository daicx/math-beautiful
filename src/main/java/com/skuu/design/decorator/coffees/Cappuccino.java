package com.skuu.design.decorator.coffees;

import com.skuu.design.decorator.Coffee;

/**
 * @author dcx
 * @description 卡布奇诺 - 具体组件
 * @create 2025-01-27
 */
public class Cappuccino implements Coffee {

    @Override
    public String getDescription() {
        return "卡布奇诺";
    }

    @Override
    public double getCost() {
        return 20.0;
    }
}
