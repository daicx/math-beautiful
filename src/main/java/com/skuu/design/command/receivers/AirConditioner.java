package com.skuu.design.command.receivers;

/**
 * @author dcx
 * @description 空调类 - 命令模式的Receiver（接收者）
 * @create 2025-01-27
 */
public class AirConditioner {
    
    private String location;
    private boolean isOn = false;
    private int temperature = 26;
    
    public AirConditioner(String location) {
        this.location = location;
    }
    
    public void on() {
        isOn = true;
        System.out.println("❄️ " + location + "的空调打开了 (温度: " + temperature + "°C)");
    }
    
    public void off() {
        isOn = false;
        System.out.println("❄️ " + location + "的空调关闭了");
    }
    
    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("❄️ " + location + "的空调温度设置为: " + temperature + "°C");
    }
    
    public boolean isOn() {
        return isOn;
    }
    
    public int getTemperature() {
        return temperature;
    }
}
