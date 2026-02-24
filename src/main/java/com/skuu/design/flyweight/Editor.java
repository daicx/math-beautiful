package com.skuu.design.flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dcx
 * @description 文本编辑器 - 客户端类
 * @create 2025-01-27
 */
public class Editor {
    
    /**
     * 字符列表（存储外部状态）
     */
    private List<CharacterContext> characters;
    
    private CharacterFactory factory;
    
    public Editor() {
        characters = new ArrayList<>();
        factory = CharacterFactory.getInstance();
    }
    
    /**
     * 插入字符
     */
    public void insertCharacter(char c, int fontSize, String color, String position) {
        // 从工厂获取享元对象
        CharacterFlyweight flyweight = factory.getCharacter(c);
        
        // 创建上下文，存储外部状态
        CharacterContext context = new CharacterContext(flyweight, fontSize, color, position);
        characters.add(context);
    }
    
    /**
     * 显示文档内容
     */
    public void display() {
        System.out.println("\n📄 文档内容:");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        for (CharacterContext context : characters) {
            context.display();
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }
    
    /**
     * 获取字符数量
     */
    public int getCharacterCount() {
        return characters.size();
    }
    
    /**
     * 清空文档
     */
    public void clear() {
        characters.clear();
        System.out.println("📄 文档已清空");
    }
    
    /**
     * 字符上下文类 - 存储外部状态
     */
    private static class CharacterContext {
        private CharacterFlyweight flyweight;
        private int fontSize;
        private String color;
        private String position;
        
        public CharacterContext(CharacterFlyweight flyweight, int fontSize, String color, String position) {
            this.flyweight = flyweight;
            this.fontSize = fontSize;
            this.color = color;
            this.position = position;
        }
        
        public void display() {
            flyweight.display(fontSize, color, position);
        }
    }
}
