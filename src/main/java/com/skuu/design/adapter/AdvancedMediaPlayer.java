package com.skuu.design.adapter;

/**
 * @author dcx
 * @description 高级媒体播放器接口 - 被适配者接口
 * @create 2025-01-27
 */
public interface AdvancedMediaPlayer {
    
    /**
     * 播放VLC格式文件
     */
    void playVlc(String fileName);
    
    /**
     * 播放MP4格式文件
     */
    void playMp4(String fileName);
    
    /**
     * 停止播放
     */
    void stop();
}
