package com.skuu.design.observer;

/**
 * @author dcx
 * @description 观察者接口 - 观察者模式的Observer
 * @create 2025-01-27
 */
public interface Observer {
    
    /**
     * 更新方法 - 当主题状态改变时被调用
     * @param temperature 温度
     * @param humidity 湿度
     * @param pressure 气压
     */
    void update(float temperature, float humidity, float pressure);
}
