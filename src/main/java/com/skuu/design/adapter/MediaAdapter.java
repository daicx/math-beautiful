package com.skuu.design.adapter;

import com.skuu.design.adapter.players.*;

/**
 * @author dcx
 * @description 媒体适配器 - 适配器模式的核心类，专门适配AdvancedMediaPlayer
 * @create 2025-01-27
 */
public class MediaAdapter implements MediaPlayer {
    
    private AdvancedMediaPlayer advancedMediaPlayer;
    
    /**
     * 构造函数 - 创建高级媒体播放器
     */
    public MediaAdapter(String audioType) {
        if ("vlc".equalsIgnoreCase(audioType)) {
            advancedMediaPlayer = new VlcPlayer();
        } else if ("mp4".equalsIgnoreCase(audioType)) {
            advancedMediaPlayer = new Mp4Player();
        } else {
            throw new IllegalArgumentException("不支持的音频格式: " + audioType);
        }
    }
    
    @Override
    public void play(String audioType, String fileName) {
        if ("vlc".equalsIgnoreCase(audioType)) {
            advancedMediaPlayer.playVlc(fileName);
        } else if ("mp4".equalsIgnoreCase(audioType)) {
            advancedMediaPlayer.playMp4(fileName);
        } else {
            System.out.println("❌ 适配器不支持该格式: " + audioType);
        }
    }
    
    @Override
    public void stop() {
        if (advancedMediaPlayer != null) {
            advancedMediaPlayer.stop();
        }
    }
    
    @Override
    public void pause() {
        if (advancedMediaPlayer != null) {
            System.out.println("⏸️ 高级播放器暂停播放");
        }
    }
}
