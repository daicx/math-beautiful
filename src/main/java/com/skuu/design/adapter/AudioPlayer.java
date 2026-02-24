package com.skuu.design.adapter;

import com.skuu.design.adapter.players.Mp3Player;

/**
 * @author dcx
 * @description 音频播放器 - 目标接口的实现类
 * @create 2025-01-27
 */
public class AudioPlayer implements MediaPlayer {
    
    private MediaAdapter mediaAdapter;
    private Mp3Player mp3Player;
    
    @Override
    public void play(String audioType, String fileName) {
        // 内置支持MP3格式
        if ("mp3".equalsIgnoreCase(audioType)) {
            if (mp3Player == null) {
                mp3Player = new Mp3Player();
            }
            mp3Player.playMp3(fileName);
        }
        // 对于其他格式，使用适配器
        else if ("vlc".equalsIgnoreCase(audioType) || 
                 "mp4".equalsIgnoreCase(audioType)) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        }
        else {
            System.out.println("❌ 不支持的音频格式: " + audioType);
        }
    }
    
    @Override
    public void stop() {
        System.out.println("⏹️ 音频播放器停止播放");
        if (mediaAdapter != null) {
            mediaAdapter.stop();
        }
        if (mp3Player != null) {
            mp3Player.stop();
        }
    }
    
    @Override
    public void pause() {
        System.out.println("⏸️ 音频播放器暂停播放");
        if (mediaAdapter != null) {
            mediaAdapter.pause();
        }
        if (mp3Player != null) {
            mp3Player.pause();
        }
    }
}
