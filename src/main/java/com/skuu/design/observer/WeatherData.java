package com.skuu.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dcx
 * @description 天气数据类 - 具体主题（被观察者）
 * @create 2025-01-27
 */
public class WeatherData implements Subject {
    
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;
    
    public WeatherData() {
        observers = new ArrayList<>();
    }
    
    @Override
    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("✅ 注册观察者: " + observer.getClass().getSimpleName());
        }
    }
    
    @Override
    public void removeObserver(Observer observer) {
        int index = observers.indexOf(observer);
        if (index >= 0) {
            observers.remove(index);
            System.out.println("❌ 移除观察者: " + observer.getClass().getSimpleName());
        }
    }
    
    @Override
    public void notifyObservers() {
        System.out.println("\n📢 通知所有观察者...");
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }
    
    /**
     * 当天气数据改变时调用
     */
    public void measurementsChanged() {
        notifyObservers();
    }
    
    /**
     * 设置天气数据
     */
    public void setMeasurements(float temperature, float humidity, float pressure) {
        System.out.println("\n🌡️ 天气数据更新:");
        System.out.println("   温度: " + temperature + "°C");
        System.out.println("   湿度: " + humidity + "%");
        System.out.println("   气压: " + pressure + " hPa");
        
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
    
    /**
     * 获取当前观察者数量
     */
    public int getObserverCount() {
        return observers.size();
    }
    
    // Getter方法
    public float getTemperature() {
        return temperature;
    }
    
    public float getHumidity() {
        return humidity;
    }
    
    public float getPressure() {
        return pressure;
    }
}
