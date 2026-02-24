package com.skuu.design.factory.cxgc;

import com.skuu.design.factory.cxgc.factory.MacSystemFactory;
import com.skuu.design.factory.cxgc.factory.WindowsSystemFactory;

/**
 * 抽象工厂模式定义：
 * 提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类。
 *
 * 核心组件：
 * 1. AbstractFactory（抽象工厂）
 * 2. ConcreteFactory（具体工厂）
 * 3. AbstractProduct（抽象产品）
 * 4. ConcreteProduct（具体产品）
 * 5. Client（客户端）
 */
public class Test {
    public static void main(String[] args) {
        //windows 操作系统
        WindowsSystemFactory windowsSystemFactory = new WindowsSystemFactory();
        ComputerSystem windowSystem = new ComputerSystem(windowsSystemFactory);
        windowSystem.startSystem();
        windowSystem.showSystemInfo();
        windowSystem.shutdownSystem();

        //mac操作系统
        MacSystemFactory macSystemFactory = new MacSystemFactory();
        ComputerSystem macSystem = new ComputerSystem(macSystemFactory);
        macSystem.startSystem();
        macSystem.showSystemInfo();
        macSystem.shutdownSystem();

        // 演示工厂方法的灵活性
        System.out.println("=== 工厂方法灵活性演示 ===");
        OperatingSystem windowsOS = windowsSystemFactory.createOperatingSystem();
        Browser chromeBrowser = windowsSystemFactory.createBrowser();

        OperatingSystem macOS = macSystemFactory.createOperatingSystem();
        Browser safariBrowser = macSystemFactory.createBrowser();

        System.out.println("Windows系统 + Chrome浏览器组合：");
        windowsOS.boot();
        chromeBrowser.open();

        System.out.println("\nmacOS系统 + Safari浏览器组合：");
        macOS.boot();
        safariBrowser.open();

    }
}
