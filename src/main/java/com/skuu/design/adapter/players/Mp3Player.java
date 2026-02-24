package com.skuu.design.adapter.players;

/**
 * @author dcx
 * @description MP3播放器实现类 - 独立的播放器，不实现AdvancedMediaPlayer
 * @create 2025-01-27
 */
public class Mp3Player {

    /**
     * 播放MP3文件
     */
    public void playMp3(String fileName) {
        System.out.println("🎵 MP3播放器正在播放: " + fileName);
        System.out.println("   - 支持MP3音频解码");
        System.out.println("   - 支持ID3标签显示");
        System.out.println("   - 支持播放列表");
    }

    /**
     * 停止播放
     */
    public void stop() {
        System.out.println("⏹️ MP3播放器停止播放");
    }

    /**
     * 暂停播放
     */
    public void pause() {
        System.out.println("⏸️ MP3播放器暂停播放");
    }
}
