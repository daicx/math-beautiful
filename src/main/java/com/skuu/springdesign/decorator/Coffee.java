package com.skuu.springdesign.decorator;

import lombok.Value;

/**
 * 装饰器：不可变“咖啡”描述 + 价格，用 Function 叠加装饰。
 */
@Value
public class Coffee {
    String description;
    double cost;

    public static Coffee base(String description, double cost) {
        return new Coffee(description, cost);
    }

    public Coffee withMilk() {
        return new Coffee(description + ", Milk", cost + 2.0);
    }

    public Coffee withCaramel() {
        return new Coffee(description + ", Caramel", cost + 3.0);
    }
}
