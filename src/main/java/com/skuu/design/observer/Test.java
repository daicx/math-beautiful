package com.skuu.design.observer;

import com.skuu.design.observer.displays.*;

/**
 * @author dcx
 * @description 观察者模式测试类
 * @create 2025-01-27
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("=== 观察者模式 - 天气监测站示例 ===\n");

        // 创建主题（被观察者）
        WeatherData weatherData = new WeatherData();

        System.out.println("--- 初始化观察者 ---");
        // 创建观察者并注册
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);

        System.out.println("\n当前注册的观察者数量: " + weatherData.getObserverCount());

        // 第一次更新天气数据
        System.out.println("\n===========================================");
        weatherData.setMeasurements(26.5f, 65.0f, 1013.2f);

        // 第二次更新天气数据
        System.out.println("\n===========================================");
        weatherData.setMeasurements(28.3f, 70.0f, 1012.8f);

        // 第三次更新天气数据
        System.out.println("\n===========================================");
        weatherData.setMeasurements(24.8f, 90.0f, 1012.5f);

        // 测试移除观察者
        System.out.println("\n===========================================");
        System.out.println("\n--- 移除统计显示板观察者 ---");
        weatherData.removeObserver(statisticsDisplay);
        System.out.println("当前注册的观察者数量: " + weatherData.getObserverCount());

        // 第四次更新天气数据
        System.out.println("\n===========================================");
        weatherData.setMeasurements(22.5f, 85.0f, 1014.0f);

        // 重新注册观察者
        System.out.println("\n===========================================");
        System.out.println("\n--- 重新注册统计显示板观察者 ---");
        weatherData.registerObserver(statisticsDisplay);
        System.out.println("当前注册的观察者数量: " + weatherData.getObserverCount());

        // 第五次更新天气数据
        System.out.println("\n===========================================");
        weatherData.setMeasurements(30.0f, 75.0f, 1010.5f);

        System.out.println("\n===========================================");
        System.out.println("\n=== 观察者模式说明 ===");
        System.out.println("1. 主题接口: Subject - 定义注册、移除和通知观察者的方法");
        System.out.println("2. 观察者接口: Observer - 定义更新方法");
        System.out.println("3. 具体主题: WeatherData - 维护观察者列表，状态改变时通知");
        System.out.println("4. 具体观察者: 各种Display - 实现更新方法，接收通知");
        System.out.println("5. 解耦: 主题和观察者之间松耦合");

        System.out.println("\n=== 观察者模式优势 ===");
        System.out.println("✅ 松耦合: 主题和观察者独立变化，互不影响");
        System.out.println("✅ 动态订阅: 可以在运行时添加或移除观察者");
        System.out.println("✅ 广播通信: 一次通知，所有观察者都能收到");
        System.out.println("✅ 符合开闭原则: 添加新观察者无需修改主题");
        System.out.println("✅ 支持一对多: 一个主题可以有多个观察者");

        System.out.println("\n=== 观察者模式应用场景 ===");
        System.out.println("📌 事件监听系统: GUI事件处理、消息队列");
        System.out.println("📌 MVC架构: Model变化通知View更新");
        System.out.println("📌 发布-订阅系统: 消息中间件、事件总线");
        System.out.println("📌 数据绑定: 前端框架的响应式数据");
        System.out.println("📌 监控告警: 系统监控、日志收集");

        System.out.println("\n=== 观察者模式 vs 发布-订阅模式 ===");
        System.out.println("观察者模式: 主题直接维护观察者列表");
        System.out.println("发布-订阅: 通过消息中心解耦，发布者和订阅者互不知晓");
    }
}
