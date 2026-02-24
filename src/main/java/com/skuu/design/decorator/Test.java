package com.skuu.design.decorator;

import com.skuu.design.decorator.coffees.*;
import com.skuu.design.decorator.decorators.*;

/**
 * @author dcx
 * @description 装饰模式测试类
 * @create 2025-01-27
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("=== 装饰模式 - 咖啡店示例 ===\n");

        // 测试1: 基础咖啡
        System.out.println("--- 测试基础咖啡 ---");
        Coffee espresso = new Espresso();
        printCoffee(espresso);

        Coffee americano = new Americano();
        printCoffee(americano);

        Coffee cappuccino = new Cappuccino();
        printCoffee(cappuccino);

        System.out.println("\n--- 测试单个装饰器 ---");
        // 测试2: 单个装饰器
        Coffee espressoWithMilk = new MilkDecorator(new Espresso());
        printCoffee(espressoWithMilk);


        System.out.println("\n--- 测试多个装饰器组合 ---");
        // 测试3: 多个装饰器组合
        Coffee complexCoffee1 = new CaramelDecorator(
                new VanillaDecorator(
                        new MilkDecorator(
                                new Espresso()
                        )
                )
        );
        printCoffee(complexCoffee1);

        System.out.println("\n--- 测试不同组合 ---");
        // 测试4: 不同的装饰器组合

        Coffee premiumCappuccino = new CaramelDecorator(
                new MilkDecorator(
                        new Cappuccino()
                )
        );
        printCoffee(premiumCappuccino);

        System.out.println("\n--- 测试装饰器的动态性 ---");
        // 测试5: 动态添加装饰器
        Coffee baseCoffee = new Espresso();
        System.out.println("初始咖啡: " + baseCoffee.getDetails());

        baseCoffee = new MilkDecorator(baseCoffee);
        System.out.println("添加牛奶: " + baseCoffee.getDetails());

        baseCoffee = new VanillaDecorator(baseCoffee);
        System.out.println("添加香草: " + baseCoffee.getDetails());

        System.out.println("\n=== 装饰模式说明 ===");
        System.out.println("1. 组件接口: Coffee - 定义咖啡的基本操作");
        System.out.println("2. 具体组件: Espresso, Americano等 - 基础咖啡类型");
        System.out.println("3. 装饰器抽象类: CoffeeDecorator - 持有组件引用");
        System.out.println("4. 具体装饰器: MilkDecorator等 - 添加具体功能");
        System.out.println("5. 客户端: 可以动态组合装饰器");

        System.out.println("\n=== 装饰模式优势 ===");
        System.out.println("✅ 动态扩展: 可以在运行时动态添加功能");
        System.out.println("✅ 组合灵活: 可以任意组合多个装饰器");
        System.out.println("✅ 职责单一: 每个装饰器只负责一个功能");
        System.out.println("✅ 符合开闭原则: 对扩展开放，对修改关闭");
        System.out.println("✅ 避免继承爆炸: 不需要为每种组合创建子类");

        System.out.println("\n=== 装饰模式应用场景 ===");
        System.out.println("📌 Java I/O流: InputStream, OutputStream等");
        System.out.println("📌 Servlet API: HttpServletRequestWrapper等");
        System.out.println("📌 GUI组件: 窗口、按钮的装饰");
        System.out.println("📌 缓存系统: 多层缓存装饰");
        System.out.println("📌 权限控制: 多层权限装饰");
    }

    /**
     * 打印咖啡信息
     */
    private static void printCoffee(Coffee coffee) {
        System.out.println("☕ " + coffee.getDetails());
    }
}
