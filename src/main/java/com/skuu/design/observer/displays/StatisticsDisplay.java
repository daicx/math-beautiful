package com.skuu.design.observer.displays;

import com.skuu.design.observer.DisplayElement;
import com.skuu.design.observer.Observer;
import com.skuu.design.observer.Subject;

/**
 * @author dcx
 * @description 统计显示板 - 具体观察者
 * @create 2025-01-27
 */
public class StatisticsDisplay implements Observer, DisplayElement {
    
    private float maxTemp = 0.0f;
    private float minTemp = 200.0f;
    private float tempSum = 0.0f;
    private int numReadings = 0;
    private Subject weatherData;
    
    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    
    @Override
    public void update(float temperature, float humidity, float pressure) {
        tempSum += temperature;
        numReadings++;
        
        if (temperature > maxTemp) {
            maxTemp = temperature;
        }
        
        if (temperature < minTemp) {
            minTemp = temperature;
        }
        
        display();
    }
    
    @Override
    public void display() {
        System.out.println("📈 统计显示板:");
        System.out.println("   平均温度: " + (tempSum / numReadings) + "°C");
        System.out.println("   最高温度: " + maxTemp + "°C");
        System.out.println("   最低温度: " + minTemp + "°C");
    }
}
