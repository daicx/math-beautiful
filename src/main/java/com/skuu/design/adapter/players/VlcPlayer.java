package com.skuu.design.adapter.players;

import com.skuu.design.adapter.AdvancedMediaPlayer;

/**
 * @author dcx
 * @description VLC播放器实现类
 * @create 2025-01-27
 */
public class VlcPlayer implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        System.out.println("🎬 VLC播放器正在播放: " + fileName);
        System.out.println("   - 支持高级视频解码");
        System.out.println("   - 支持字幕显示");
        System.out.println("   - 支持多种音频格式");
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("❌ VLC播放器不支持MP4格式: " + fileName);
    }

    @Override
    public void stop() {
        System.out.println("⏹️ VLC播放器停止播放");
    }
}
