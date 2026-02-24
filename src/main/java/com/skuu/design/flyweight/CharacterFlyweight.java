package com.skuu.design.flyweight;

/**
 * @author dcx
 * @description 字符享元接口 - 享元模式的Flyweight
 * @create 2025-01-27
 */
public interface CharacterFlyweight {
    
    /**
     * 显示字符
     * @param fontSize 字体大小（外部状态）
     * @param color 颜色（外部状态）
     * @param position 位置（外部状态）
     */
    void display(int fontSize, String color, String position);
}
