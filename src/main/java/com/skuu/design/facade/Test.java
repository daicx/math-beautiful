package com.skuu.design.facade;

import com.skuu.design.facade.subsystems.*;

/**
 * @author dcx
 * @description 外观模式测试类
 * @create 2025-01-27
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("=== 外观模式 - 家庭影院系统示例 ===\n");

        // 创建所有子系统组件
        Amplifier amplifier = new Amplifier("索尼5.1声道");
        DVDPlayer dvdPlayer = new DVDPlayer("索尼蓝光");
        Projector projector = new Projector("爱普生4K");
        TheaterLights lights = new TheaterLights("智能灯光系统");
        Screen screen = new Screen("电动投影幕布");
        PopcornPopper popper = new PopcornPopper("爆米花机");

        // ========== 对比：不使用外观模式 ==========
        System.out.println("【场景1：不使用外观模式 - 手动操作所有设备】");
        System.out.println("客户端需要了解所有子系统的细节，操作复杂：\n");
        
        System.out.println("观影前需要执行以下步骤：");
        System.out.println("1. popper.on()");
        System.out.println("2. popper.pop()");
        System.out.println("3. lights.dim(10)");
        System.out.println("4. screen.down()");
        System.out.println("5. projector.on()");
        System.out.println("6. projector.wideScreenMode()");
        System.out.println("7. amplifier.on()");
        System.out.println("8. amplifier.setVolume(8)");
        System.out.println("9. amplifier.setSurroundSound()");
        System.out.println("10. dvdPlayer.on()");
        System.out.println("11. dvdPlayer.play(movie)");
        System.out.println("\n❌ 太复杂了！容易出错，用户体验差！");

        // ========== 使用外观模式 ==========
        System.out.println("\n\n【场景2：使用外观模式 - 简化操作】");
        System.out.println("客户端只需要调用一个简单的方法：\n");
        
        // 创建外观对象
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(
            amplifier, dvdPlayer, projector, lights, screen, popper
        );

        // 测试1：观看电影
        homeTheater.watchMovie("阿凡达2：水之道");

        // 模拟观影过程
        System.out.println("\n⏳ 电影播放中...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 测试2：暂停电影
        homeTheater.pauseMovie();

        // 模拟暂停时间
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 测试3：继续电影
        homeTheater.resumeMovie("阿凡达2：水之道");

        // 模拟继续观影
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 测试4：调整音量
        homeTheater.setVolume(12);

        // 测试5：调整灯光
        homeTheater.setLights(20);

        // 模拟观影结束
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 测试6：结束电影
        homeTheater.endMovie();

        System.out.println("\n\n=== 外观模式说明 ===");
        System.out.println("1. 子系统: Amplifier、DVDPlayer等 - 各自独立的复杂系统");
        System.out.println("2. 外观类: HomeTheaterFacade - 提供简化的统一接口");
        System.out.println("3. 客户端: 只需要与外观类交互，无需了解子系统细节");
        System.out.println("4. 封装性: 将复杂的操作序列封装在外观类中");

        System.out.println("\n=== 外观模式优势 ===");
        System.out.println("✅ 简化接口: 为复杂子系统提供简单接口");
        System.out.println("✅ 降低耦合: 客户端与子系统解耦");
        System.out.println("✅ 提高可用性: 用户无需了解系统内部结构");
        System.out.println("✅ 更好的分层: 定义系统中每层的入口点");
        System.out.println("✅ 灵活性: 客户端仍可直接访问子系统");

        System.out.println("\n=== 外观模式应用场景 ===");
        System.out.println("📌 复杂系统简化: 为复杂系统提供简单入口");
        System.out.println("📌 分层架构: 定义各层之间的接口");
        System.out.println("📌 遗留系统封装: 为老旧系统提供新接口");
        System.out.println("📌 第三方库封装: 简化第三方API使用");
        System.out.println("📌 微服务网关: API网关就是外观模式");

        System.out.println("\n=== 外观模式 vs 适配器模式 ===");
        System.out.println("外观模式: 简化接口，封装多个子系统");
        System.out.println("适配器模式: 转换接口，让不兼容的接口协同工作");

        System.out.println("\n=== 外观模式的最佳实践 ===");
        System.out.println("✨ 最少知识原则: 客户端只与外观类交互");
        System.out.println("✨ 保留灵活性: 仍允许直接访问子系统");
        System.out.println("✨ 职责单一: 外观类只负责协调子系统");
        System.out.println("✨ 可扩展性: 易于添加新的外观方法");
    }
}
