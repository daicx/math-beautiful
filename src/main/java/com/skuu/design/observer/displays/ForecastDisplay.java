package com.skuu.design.observer.displays;

import com.skuu.design.observer.DisplayElement;
import com.skuu.design.observer.Observer;
import com.skuu.design.observer.Subject;

/**
 * @author dcx
 * @description 天气预报显示板 - 具体观察者
 * @create 2025-01-27
 */
public class ForecastDisplay implements Observer, DisplayElement {
    
    private float currentPressure = 1013.0f;
    private float lastPressure;
    private Subject weatherData;
    
    public ForecastDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    
    @Override
    public void update(float temperature, float humidity, float pressure) {
        lastPressure = currentPressure;
        currentPressure = pressure;
        display();
    }
    
    @Override
    public void display() {
        System.out.println("🌤️ 天气预报:");
        System.out.print("   ");
        if (currentPressure > lastPressure) {
            System.out.println("天气正在改善！");
        } else if (currentPressure == lastPressure) {
            System.out.println("天气保持稳定");
        } else {
            System.out.println("注意降温降雨");
        }
    }
}
