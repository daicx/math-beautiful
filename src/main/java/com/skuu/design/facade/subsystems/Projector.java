package com.skuu.design.facade.subsystems;

/**
 * @author dcx
 * @description 投影仪 - 子系统
 * @create 2025-01-27
 */
public class Projector {
    
    private String description;
    
    public Projector(String description) {
        this.description = description;
    }
    
    public void on() {
        System.out.println("📽️ " + description + " 投影仪打开");
    }
    
    public void off() {
        System.out.println("📽️ " + description + " 投影仪关闭");
    }
    
    public void wideScreenMode() {
        System.out.println("📽️ " + description + " 设置为宽屏模式 (16:9)");
    }
    
    public void normalMode() {
        System.out.println("📽️ " + description + " 设置为标准模式 (4:3)");
    }
}
