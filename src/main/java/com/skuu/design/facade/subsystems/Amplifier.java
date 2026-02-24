package com.skuu.design.facade.subsystems;

/**
 * @author dcx
 * @description 功放系统 - 子系统
 * @create 2025-01-27
 */
public class Amplifier {
    
    private String description;
    private int volume;
    
    public Amplifier(String description) {
        this.description = description;
        this.volume = 5;
    }
    
    public void on() {
        System.out.println("🔊 " + description + " 功放打开");
    }
    
    public void off() {
        System.out.println("🔇 " + description + " 功放关闭");
    }
    
    public void setVolume(int level) {
        this.volume = level;
        System.out.println("🔊 " + description + " 音量设置为: " + level);
    }
    
    public void setSurroundSound() {
        System.out.println("🔊 " + description + " 环绕立体声模式已开启");
    }
    
    public void setStereoSound() {
        System.out.println("🔊 " + description + " 立体声模式已开启");
    }
    
    public int getVolume() {
        return volume;
    }
}
