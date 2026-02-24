package com.skuu.design.adapter.players;

import com.skuu.design.adapter.AdvancedMediaPlayer;

/**
 * @author dcx
 * @description MP4播放器实现类
 * @create 2025-01-27
 */
public class Mp4Player implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        System.out.println("❌ MP4播放器不支持VLC格式: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("🎬 MP4播放器正在播放: " + fileName);
        System.out.println("   - 支持H.264视频编码");
        System.out.println("   - 支持AAC音频编码");
        System.out.println("   - 支持高清播放");
    }

    @Override
    public void stop() {
        System.out.println("⏹️ MP4播放器停止播放");
    }
}
