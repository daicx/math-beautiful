package com.skuu.design.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dcx
 * @description 字符享元工厂 - 享元模式的FlyweightFactory
 * @create 2025-01-27
 */
public class CharacterFactory {
    
    /**
     * 享元池：存储已创建的享元对象
     * key: 字符, value: 享元对象
     */
    private Map<Character, CharacterFlyweight> flyweightPool;
    
    /**
     * 单例模式：确保只有一个工厂实例
     */
    private static CharacterFactory instance = new CharacterFactory();
    
    /**
     * 私有构造函数
     */
    private CharacterFactory() {
        flyweightPool = new HashMap<>();
    }
    
    /**
     * 获取工厂实例
     */
    public static CharacterFactory getInstance() {
        return instance;
    }
    
    /**
     * 获取字符享元对象
     * 如果池中存在则返回，否则创建新对象并放入池中
     */
    public CharacterFlyweight getCharacter(char c) {
        CharacterFlyweight character = flyweightPool.get(c);
        
        if (character == null) {
            // 池中不存在，创建新对象
            character = new ConcreteCharacter(c);
            flyweightPool.put(c, character);
        } else {
            System.out.println("♻️ 复用享元对象: '" + c + "'");
        }
        
        return character;
    }
    
    /**
     * 获取享元池大小
     */
    public int getPoolSize() {
        return flyweightPool.size();
    }
    
    /**
     * 清空享元池
     */
    public void clearPool() {
        flyweightPool.clear();
        System.out.println("🗑️ 享元池已清空");
    }
    
    /**
     * 显示享元池状态
     */
    public void showPoolStatus() {
        System.out.println("\n📊 享元池状态:");
        System.out.println("   池中对象数量: " + flyweightPool.size());
        System.out.println("   已创建的字符: " + flyweightPool.keySet());
    }
}
