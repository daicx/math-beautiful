package com.skuu.design.observer.displays;

import com.skuu.design.observer.DisplayElement;
import com.skuu.design.observer.Observer;
import com.skuu.design.observer.Subject;

/**
 * @author dcx
 * @description 当前状况显示板 - 具体观察者
 * @create 2025-01-27
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    
    private float temperature;
    private float humidity;
    private Subject weatherData;
    
    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    
    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }
    
    @Override
    public void display() {
        System.out.println("📊 当前状况显示板:");
        System.out.println("   温度: " + temperature + "°C");
        System.out.println("   湿度: " + humidity + "%");
    }
}
