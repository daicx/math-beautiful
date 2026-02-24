package com.skuu.design.observer;

/**
 * @author dcx
 * @description 主题接口 - 观察者模式的Subject（被观察者）
 * @create 2025-01-27
 */
public interface Subject {
    
    /**
     * 注册观察者
     */
    void registerObserver(Observer observer);
    
    /**
     * 移除观察者
     */
    void removeObserver(Observer observer);
    
    /**
     * 通知所有观察者
     */
    void notifyObservers();
}
