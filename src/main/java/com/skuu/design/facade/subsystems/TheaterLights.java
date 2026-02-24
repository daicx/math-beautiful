package com.skuu.design.facade.subsystems;

/**
 * @author dcx
 * @description 影院灯光 - 子系统
 * @create 2025-01-27
 */
public class TheaterLights {
    
    private String description;
    private int brightness;
    
    public TheaterLights(String description) {
        this.description = description;
        this.brightness = 100;
    }
    
    public void on() {
        System.out.println("💡 " + description + " 灯光打开");
        this.brightness = 100;
    }
    
    public void off() {
        System.out.println("💡 " + description + " 灯光关闭");
        this.brightness = 0;
    }
    
    public void dim(int level) {
        this.brightness = level;
        System.out.println("💡 " + description + " 灯光调暗至: " + level + "%");
    }
    
    public int getBrightness() {
        return brightness;
    }
}
