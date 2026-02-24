package com.skuu.design.adapter;

/**
 * @author dcx
 * @description 适配器模式测试类
 * @create 2025-01-27
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("=== 适配器模式 - 媒体播放器示例 ===\n");

        // 创建音频播放器
        AudioPlayer audioPlayer = new AudioPlayer();

        System.out.println("--- 测试内置支持的格式 ---");
        // 测试内置支持的MP3格式
        audioPlayer.play("mp3", "song.mp3");
        audioPlayer.pause();
        audioPlayer.stop();
        
        System.out.println("\n--- 测试需要适配的格式 ---");
        
        // 测试VLC格式（通过适配器）
        audioPlayer.play("vlc", "movie.vlc");
        audioPlayer.pause();
        audioPlayer.stop();
        
        System.out.println();
        
        // 测试MP4格式（通过适配器）
        audioPlayer.play("mp4", "video.mp4");
        audioPlayer.pause();
        audioPlayer.stop();
        
        System.out.println("\n--- 测试不支持的格式 ---");
        // 测试不支持的格式
        audioPlayer.play("avi", "movie.avi");
        
        System.out.println("\n=== 适配器模式演示完成 ===");
        
        System.out.println("\n=== 适配器模式说明 ===");
        System.out.println("1. 目标接口: MediaPlayer - 定义客户端期望的接口");
        System.out.println("2. 被适配者: AdvancedMediaPlayer - 需要适配的现有接口");
        System.out.println("3. 适配器: MediaAdapter - 将现有接口转换为目标接口");
        System.out.println("4. 客户端: AudioPlayer - 使用目标接口的类");
        
        System.out.println("\n=== 适配器模式优势 ===");
        System.out.println("✅ 接口转换: 让不兼容的接口能够协同工作");
        System.out.println("✅ 代码复用: 可以复用现有的类而不需要修改");
        System.out.println("✅ 解耦合: 客户端不需要了解具体的实现细节");
        System.out.println("✅ 扩展性: 易于添加新的适配器支持更多格式");
        
        System.out.println("\n=== 适配器模式类型 ===");
        System.out.println("📌 类适配器: 通过继承实现适配（本例未使用）");
        System.out.println("📌 对象适配器: 通过组合实现适配（本例使用）");
        System.out.println("📌 接口适配器: 通过抽象类实现适配（本例未使用）");
    }
}
