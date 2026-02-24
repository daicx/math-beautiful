package com.skuu.design.flyweight;

/**
 * @author dcx
 * @description 享元模式测试类
 * @create 2025-01-27
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("=== 享元模式 - 文本编辑器示例 ===\n");

        // 创建文本编辑器
        Editor editor = new Editor();
        CharacterFactory factory = CharacterFactory.getInstance();

        System.out.println("--- 场景1：输入文本 \"HELLO\" ---");
        // 输入 HELLO
        editor.insertCharacter('H', 12, "红色", "(0,0)");
        editor.insertCharacter('E', 12, "蓝色", "(1,0)");
        editor.insertCharacter('L', 12, "绿色", "(2,0)");
        editor.insertCharacter('L', 12, "黄色", "(3,0)");  // L重复使用
        editor.insertCharacter('O', 12, "紫色", "(4,0)");

        // 显示享元池状态
        factory.showPoolStatus();   

        System.out.println("\n--- 场景2：继续输入文本 \"WORLD\" ---");
        // 输入 WORLD（部分字符已存在于享元池）
        editor.insertCharacter('W', 14, "橙色", "(5,0)");
        editor.insertCharacter('O', 14, "粉色", "(6,0)");  // O重复使用
        editor.insertCharacter('R', 14, "青色", "(7,0)");
        editor.insertCharacter('L', 14, "棕色", "(8,0)");  // L重复使用
        editor.insertCharacter('D', 14, "灰色", "(9,0)");

        // 显示享元池状态
        factory.showPoolStatus();

        // 显示文档内容
        editor.display();

        System.out.println("\n--- 场景3：输入长文本测试内存优化 ---");
        String longText = "HELLO WORLD! THIS IS A FLYWEIGHT PATTERN DEMO!";
        Editor editor2 = new Editor();
        
        System.out.println("正在输入: \"" + longText + "\"");
        System.out.println("文本长度: " + longText.length() + " 个字符\n");
        
        for (int i = 0; i < longText.length(); i++) {
            char c = longText.charAt(i);
            editor2.insertCharacter(c, 12, "黑色", "(" + i + ",1)");
        }
        
        // 显示享元池状态
        factory.showPoolStatus();
        
        // 显示内存优化效果
        System.out.println("\n📊 内存优化效果分析:");
        System.out.println("   文本总字符数: " + longText.length());
        System.out.println("   实际创建的对象数: " + factory.getPoolSize());
        System.out.println("   节省对象数: " + (longText.length() - factory.getPoolSize()));
        System.out.println("   内存节省率: " + String.format("%.1f%%", 
            (1 - (double)factory.getPoolSize() / longText.length()) * 100));

        System.out.println("\n--- 场景4：对比不使用享元模式 ---");
        System.out.println("\n❌ 不使用享元模式:");
        System.out.println("   每个字符都创建新对象");
        System.out.println("   " + longText.length() + " 个字符需要创建 " + longText.length() + " 个对象");
        System.out.println("   内存占用: " + longText.length() + " × 对象大小");
        
        System.out.println("\n✅ 使用享元模式:");
        System.out.println("   相同字符共享对象");
        System.out.println("   " + longText.length() + " 个字符只需要创建 " + factory.getPoolSize() + " 个对象");
        System.out.println("   内存占用: " + factory.getPoolSize() + " × 对象大小 + 外部状态");
        System.out.println("   大大节省了内存！");

        System.out.println("\n=== 享元模式说明 ===");
        System.out.println("1. 享元接口: CharacterFlyweight - 定义享元对象的接口");
        System.out.println("2. 具体享元: ConcreteCharacter - 实现享元接口，存储内部状态");
        System.out.println("3. 享元工厂: CharacterFactory - 管理享元对象池");
        System.out.println("4. 客户端: Editor - 维护外部状态，使用享元对象");
        System.out.println("5. 内部状态: 字符本身（可共享）");
        System.out.println("6. 外部状态: 字号、颜色、位置（不可共享）");

        System.out.println("\n=== 享元模式优势 ===");
        System.out.println("✅ 减少对象数量: 通过共享技术减少内存占用");
        System.out.println("✅ 提高性能: 减少对象创建和垃圾回收开销");
        System.out.println("✅ 适合大量相似对象: 特别适合大量细粒度对象");
        System.out.println("✅ 外部状态分离: 将可变部分外部化");

        System.out.println("\n=== 享元模式应用场景 ===");
        System.out.println("📌 文本编辑器: 字符对象共享");
        System.out.println("📌 游戏开发: 大量相同的粒子、子弹、树木等");
        System.out.println("📌 图形界面: 大量相同的图标、按钮等");
        System.out.println("📌 字符串常量池: Java String.intern()");
        System.out.println("📌 数据库连接池: 连接对象复用");
        System.out.println("📌 线程池: 线程对象复用");

        System.out.println("\n=== 享元模式关键点 ===");
        System.out.println("🔑 内部状态: 存储在享元对象内部，可以共享");
        System.out.println("🔑 外部状态: 随环境改变，不可共享，由客户端维护");
        System.out.println("🔑 享元工厂: 管理对象池，确保对象正确共享");
        System.out.println("🔑 单例工厂: 通常使用单例模式实现工厂");

        System.out.println("\n=== 享元模式注意事项 ===");
        System.out.println("⚠️ 线程安全: 多线程环境需要考虑同步问题");
        System.out.println("⚠️ 状态分离: 正确区分内部状态和外部状态");
        System.out.println("⚠️ 复杂度: 增加了系统复杂度，需权衡");
        System.out.println("⚠️ 适用条件: 只有大量相似对象时才有优势");
    }
}
