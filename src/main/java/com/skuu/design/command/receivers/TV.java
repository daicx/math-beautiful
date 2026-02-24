package com.skuu.design.command.receivers;

/**
 * @author dcx
 * @description 电视类 - 命令模式的Receiver（接收者）
 * @create 2025-01-27
 */
public class TV {
    
    private String location;
    private boolean isOn = false;
    private int channel = 1;
    private int volume = 10;
    
    public TV(String location) {
        this.location = location;
    }
    
    public void on() {
        isOn = true;
        System.out.println("📺 " + location + "的电视打开了 (频道: " + channel + ", 音量: " + volume + ")");
    }
    
    public void off() {
        isOn = false;
        System.out.println("📺 " + location + "的电视关闭了");
    }
    
    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("📺 " + location + "的电视切换到频道: " + channel);
    }
    
    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("📺 " + location + "的电视音量设置为: " + volume);
    }
    
    public boolean isOn() {
        return isOn;
    }
    
    public int getChannel() {
        return channel;
    }
    
    public int getVolume() {
        return volume;
    }
}
