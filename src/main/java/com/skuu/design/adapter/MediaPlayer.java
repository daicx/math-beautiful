package com.skuu.design.adapter;

/**
 * @author dcx
 * @description 目标接口 - 标准媒体播放器接口
 * @create 2025-01-27
 */
public interface MediaPlayer {
    
    /**
     * 播放音频文件
     * @param audioType 音频类型
     * @param fileName 文件名
     */
    void play(String audioType, String fileName);
    
    /**
     * 停止播放
     */
    void stop();
    
    /**
     * 暂停播放
     */
    void pause();
}
