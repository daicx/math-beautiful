package com.skuu.design.facade.subsystems;

/**
 * @author dcx
 * @description 投影幕布 - 子系统
 * @create 2025-01-27
 */
public class Screen {
    
    private String description;
    
    public Screen(String description) {
        this.description = description;
    }
    
    public void up() {
        System.out.println("🎬 " + description + " 幕布收起");
    }
    
    public void down() {
        System.out.println("🎬 " + description + " 幕布放下");
    }
}
