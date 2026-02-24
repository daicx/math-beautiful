package com.skuu.design.facade.subsystems;

/**
 * @author dcx
 * @description DVD播放器 - 子系统
 * @create 2025-01-27
 */
public class DVDPlayer {
    
    private String description;
    private String currentMovie;
    
    public DVDPlayer(String description) {
        this.description = description;
    }
    
    public void on() {
        System.out.println("📀 " + description + " DVD播放器打开");
    }
    
    public void off() {
        System.out.println("📀 " + description + " DVD播放器关闭");
    }
    
    public void play(String movie) {
        this.currentMovie = movie;
        System.out.println("📀 " + description + " 正在播放: \"" + movie + "\"");
    }
    
    public void pause() {
        System.out.println("⏸️ " + description + " 暂停播放: \"" + currentMovie + "\"");
    }
    
    public void stop() {
        System.out.println("⏹️ " + description + " 停止播放: \"" + currentMovie + "\"");
        currentMovie = null;
    }
    
    public void eject() {
        if (currentMovie != null) {
            System.out.println("⏏️ " + description + " 弹出光盘: \"" + currentMovie + "\"");
            currentMovie = null;
        } else {
            System.out.println("⏏️ " + description + " 弹出光盘");
        }
    }
}
