package com.skuu.design.mediator;

import com.skuu.design.mediator.users.*;

/**
 * @author dcx
 * @description 中介者模式测试类
 * @create 2025-01-27
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("=== 中介者模式 - 聊天室示例 ===\n");

        // 创建聊天室（中介者）
        ChatRoom chatRoom = new ChatRoom("技术交流群");

        // 创建用户（同事对象）
        User alice = new RegularUser("Alice");
        User bob = new RegularUser("Bob");
        User charlie = new VIPUser("Charlie");
        User david = new RegularUser("David");

        System.out.println("【场景1：用户加入聊天室】\n");
        
        // 用户加入聊天室
        chatRoom.registerUser(alice);
        chatRoom.registerUser(bob);
        chatRoom.registerUser(charlie);
        chatRoom.registerUser(david);

        // 显示聊天室信息
        chatRoom.showRoomInfo();

        // ========== 场景2：群聊消息 ==========
        System.out.println("\n\n【场景2：群聊消息】\n");
        
        alice.send("大家好！我是新来的Alice");
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        bob.send("欢迎Alice！我是Bob");
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        charlie.send("欢迎欢迎！有什么技术问题可以随时问");

        // ========== 场景3：私聊消息 ==========
        System.out.println("\n\n【场景3：私聊消息】\n");
        
        alice.sendPrivate("Charlie，我想请教一个Java的问题", charlie);
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        charlie.sendPrivate("没问题，尽管问吧！", alice);

        // ========== 场景4：多人互动 ==========
        System.out.println("\n\n【场景4：多人互动】\n");
        
        david.send("有人在吗？我遇到了一个bug");
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        bob.send("在的在的，什么问题？");
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        charlie.send("说说看，大家一起帮你解决");

        // ========== 场景5：查看消息历史 ==========
        System.out.println("\n\n【场景5：查看消息历史】\n");
        
        System.out.println("📜 聊天记录:");
        for (String message : chatRoom.getMessageHistory()) {
            System.out.println("  " + message);
        }

        // ========== 对比不使用中介者模式 ==========
        System.out.println("\n\n【对比：不使用中介者模式】\n");
        
        System.out.println("❌ 不使用中介者模式:");
        System.out.println("  - 用户之间需要相互引用");
        System.out.println("  - 用户A要发消息给用户B、C、D，需要持有B、C、D的引用");
        System.out.println("  - 添加新用户E，所有现有用户都需要更新引用");
        System.out.println("  - 对象间耦合度高，难以维护");
        System.out.println("  - 代码示例: alice.sendTo(bob); alice.sendTo(charlie); ...");
        
        System.out.println("\n✅ 使用中介者模式:");
        System.out.println("  - 用户只需要引用中介者（聊天室）");
        System.out.println("  - 用户A发消息通过中介者转发");
        System.out.println("  - 添加新用户只需在中介者注册");
        System.out.println("  - 对象间解耦，易于维护和扩展");
        System.out.println("  - 代码示例: alice.send(\"消息\"); // 自动发给所有人");

        // ========== 总结 ==========
        System.out.println("\n\n=== 中介者模式说明 ===");
        System.out.println("1. 中介者接口: ChatRoomMediator - 定义同事对象交互的接口");
        System.out.println("2. 具体中介者: ChatRoom - 协调各个同事对象");
        System.out.println("3. 同事抽象类: User - 定义同事对象的基本行为");
        System.out.println("4. 具体同事: RegularUser、VIPUser - 通过中介者通信");
        System.out.println("5. 交互方式: 同事对象通过中介者进行交互，而不直接引用");

        System.out.println("\n=== 中介者模式优势 ===");
        System.out.println("✅ 降低耦合: 同事对象之间解耦");
        System.out.println("✅ 集中控制: 交互逻辑集中在中介者");
        System.out.println("✅ 简化对象: 同事对象不需要维护其他对象引用");
        System.out.println("✅ 易于扩展: 添加新的同事对象很容易");
        System.out.println("✅ 复用性: 中介者可以复用");

        System.out.println("\n=== 中介者模式缺点 ===");
        System.out.println("⚠️ 中介者可能变得复杂: 过多的交互逻辑集中在中介者");
        System.out.println("⚠️ 单点故障: 中介者出问题会影响整个系统");

        System.out.println("\n=== 中介者模式应用场景 ===");
        System.out.println("📌 聊天室: 用户通过聊天室通信");
        System.out.println("📌 MVC架构: Controller作为Model和View的中介");
        System.out.println("📌 机场调度: 飞机通过塔台协调起降");
        System.out.println("📌 GUI组件: 对话框协调各个控件");
        System.out.println("📌 智能家居: 中控系统协调各设备");
        System.out.println("📌 消息队列: 生产者和消费者通过队列通信");

        System.out.println("\n=== 中介者模式关键点 ===");
        System.out.println("🔑 中心化交互: 所有交互通过中介者");
        System.out.println("🔑 同事对象只知道中介者: 不直接引用其他同事");
        System.out.println("🔑 中介者维护同事对象: 管理所有同事对象");
        System.out.println("🔑 灵活的交互逻辑: 可以在中介者中灵活定义");

        System.out.println("\n=== 中介者模式 vs 观察者模式 ===");
        System.out.println("中介者模式: 双向通信，中介者协调同事对象");
        System.out.println("观察者模式: 单向通知，主题通知观察者");
        
        System.out.println("\n=== 中介者模式 vs 外观模式 ===");
        System.out.println("中介者模式: 同事对象也知道中介者，双向依赖");
        System.out.println("外观模式: 子系统不知道外观，单向依赖");

        // 最终显示聊天室状态
        chatRoom.showRoomInfo();
    }
}
