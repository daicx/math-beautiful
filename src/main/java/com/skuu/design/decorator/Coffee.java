package com.skuu.design.decorator;

/**
 * @author dcx
 * @description 咖啡接口 - 装饰模式的Component
 * @create 2025-01-27
 */
public interface Coffee {
    
    /**
     * 获取咖啡描述
     */
    String getDescription();
    
    /**
     * 获取咖啡价格
     */
    double getCost();
    
    /**
     * 获取咖啡详细信息
     */
    default String getDetails() {
        return String.format("%s - ¥%.2f", getDescription(), getCost());
    }
}
