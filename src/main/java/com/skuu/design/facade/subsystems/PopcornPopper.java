package com.skuu.design.facade.subsystems;

/**
 * @author dcx
 * @description 爆米花机 - 子系统
 * @create 2025-01-27
 */
public class PopcornPopper {
    
    private String description;
    
    public PopcornPopper(String description) {
        this.description = description;
    }
    
    public void on() {
        System.out.println("🍿 " + description + " 爆米花机打开");
    }
    
    public void off() {
        System.out.println("🍿 " + description + " 爆米花机关闭");
    }
    
    public void pop() {
        System.out.println("🍿 " + description + " 开始制作爆米花...");
        System.out.println("🍿 爆米花制作完成！");
    }
}
