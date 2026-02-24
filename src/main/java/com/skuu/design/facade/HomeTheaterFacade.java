package com.skuu.design.facade;

import com.skuu.design.facade.subsystems.*;

/**
 * @author dcx
 * @description 家庭影院外观类 - 外观模式的核心类
 * @create 2025-01-27
 */
public class HomeTheaterFacade {
    
    private Amplifier amplifier;
    private DVDPlayer dvdPlayer;
    private Projector projector;
    private TheaterLights lights;
    private Screen screen;
    private PopcornPopper popper;
    
    /**
     * 构造函数 - 注入所有子系统
     */
    public HomeTheaterFacade(Amplifier amplifier,
                            DVDPlayer dvdPlayer,
                            Projector projector,
                            TheaterLights lights,
                            Screen screen,
                            PopcornPopper popper) {
        this.amplifier = amplifier;
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.lights = lights;
        this.screen = screen;
        this.popper = popper;
    }
    
    /**
     * 观看电影 - 封装了复杂的操作步骤
     */
    public void watchMovie(String movie) {
        System.out.println("\n🎬 ========== 准备观看电影 ==========");
        System.out.println("📺 正在为您准备观影环境...\n");
        
        // 1. 制作爆米花
        popper.on();
        popper.pop();
        
        // 2. 调暗灯光
        lights.dim(10);
        
        // 3. 放下幕布
        screen.down();
        
        // 4. 打开投影仪
        projector.on();
        projector.wideScreenMode();
        
        // 5. 打开功放
        amplifier.on();
        amplifier.setVolume(8);
        amplifier.setSurroundSound();
        
        // 6. 打开DVD播放器并播放电影
        dvdPlayer.on();
        dvdPlayer.play(movie);
        
        System.out.println("\n✅ 准备完成，请尽情享受电影！");
    }
    
    /**
     * 结束电影 - 封装了关闭所有设备的步骤
     */
    public void endMovie() {
        System.out.println("\n🎬 ========== 关闭影院系统 ==========");
        System.out.println("📺 正在关闭所有设备...\n");
        
        // 1. 关闭爆米花机
        popper.off();
        
        // 2. 调亮灯光
        lights.on();
        
        // 3. 收起幕布
        screen.up();
        
        // 4. 关闭投影仪
        projector.off();
        
        // 5. 停止DVD并关闭
        dvdPlayer.stop();
        dvdPlayer.eject();
        dvdPlayer.off();
        
        // 6. 关闭功放
        amplifier.off();
        
        System.out.println("\n✅ 所有设备已关闭，感谢观影！");
    }
    
    /**
     * 暂停电影
     */
    public void pauseMovie() {
        System.out.println("\n⏸️ ========== 暂停电影 ==========");
        dvdPlayer.pause();
        lights.dim(50);
        System.out.println("✅ 电影已暂停，灯光已调亮");
    }
    
    /**
     * 继续电影
     */
    public void resumeMovie(String movie) {
        System.out.println("\n▶️ ========== 继续电影 ==========");
        lights.dim(10);
        dvdPlayer.play(movie);
        System.out.println("✅ 电影继续播放");
    }
    
    /**
     * 调整音量
     */
    public void setVolume(int level) {
        System.out.println("\n🔊 调整音量");
        amplifier.setVolume(level);
    }
    
    /**
     * 调整灯光
     */
    public void setLights(int brightness) {
        System.out.println("\n💡 调整灯光");
        lights.dim(brightness);
    }
}
