package com.skuu.design.flyweight;

/**
 * @author dcx
 * @description 具体字符享元类 - 享元模式的ConcreteFlyweight
 * @create 2025-01-27
 */
public class ConcreteCharacter implements CharacterFlyweight {
    
    /**
     * 内部状态：字符本身（可共享，不变）
     */
    private final char character;
    
    /**
     * 构造函数
     * @param character 字符（内部状态）
     */
    public ConcreteCharacter(char character) {
        this.character = character;
        System.out.println("✨ 创建享元对象: '" + character + "'");
    }
    
    @Override
    public void display(int fontSize, String color, String position) {
        // 内部状态（character）+ 外部状态（fontSize, color, position）
        System.out.println(String.format("字符: '%c' | 字号: %d | 颜色: %s | 位置: %s", 
                                       character, fontSize, color, position));
    }
    
    /**
     * 获取字符
     */
    public char getCharacter() {
        return character;
    }
}
