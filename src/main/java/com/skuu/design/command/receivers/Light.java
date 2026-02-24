package com.skuu.design.command.receivers;

/**
 * @author dcx
 * @description 电灯类 - 命令模式的Receiver（接收者）
 * @create 2025-01-27
 */
public class Light {
    
    private String location;
    private int brightness = 0;  // 亮度 0-100
    
    public Light(String location) {
        this.location = location;
    }
    
    public void on() {
        brightness = 100;
        System.out.println("💡 " + location + "的灯打开了 (亮度: " + brightness + "%)");
    }
    
    public void off() {
        brightness = 0;
        System.out.println("💡 " + location + "的灯关闭了");
    }
    
    public void dim(int level) {
        brightness = level;
        System.out.println("💡 " + location + "的灯调暗至: " + level + "%");
    }
    
    public int getBrightness() {
        return brightness;
    }
    
    public String getLocation() {
        return location;
    }
}
