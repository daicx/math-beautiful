package com.skuu.design.bridge;

import com.skuu.design.bridge.apis.*;
import com.skuu.design.bridge.shapes.*;

/**
 * @author dcx
 * @description 桥接模式测试类
 * @create 2025-01-27
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("=== 桥接模式 - 绘图系统示例 ===\n");

        // 创建不同平台的绘图API
        DrawingAPI windowsAPI = new WindowsDrawingAPI();
        DrawingAPI macAPI = new MacDrawingAPI();
        DrawingAPI linuxAPI = new LinuxDrawingAPI();

        System.out.println("--- 测试圆形在不同平台的绘制 ---");
        // 创建不同平台的圆形
        Circle windowsCircle = new Circle(windowsAPI, "红色", 100, 100, 50);
        Circle macCircle = new Circle(macAPI, "蓝色", 200, 100, 50);
        Circle linuxCircle = new Circle(linuxAPI, "绿色", 300, 100, 50);

        windowsCircle.draw();
        System.out.println();
        macCircle.draw();
        System.out.println();
        linuxCircle.draw();

        System.out.println("\n--- 测试矩形在不同平台的绘制 ---");
        // 创建不同平台的矩形
        Rectangle windowsRect = new Rectangle(windowsAPI, "黄色", 100, 200, 80, 60);
        Rectangle macRect = new Rectangle(macAPI, "紫色", 200, 200, 80, 60);
        Rectangle linuxRect = new Rectangle(linuxAPI, "橙色", 300, 200, 80, 60);

        windowsRect.draw();
        System.out.println();
        macRect.draw();
        System.out.println();
        linuxRect.draw();

        System.out.println("\n--- 测试三角形在不同平台的绘制 ---");
        // 创建不同平台的三角形
        Triangle windowsTriangle = new Triangle(windowsAPI, "粉色", 100, 300, 150, 350, 120, 380);
        Triangle macTriangle = new Triangle(macAPI, "青色", 200, 300, 250, 350, 220, 380);
        Triangle linuxTriangle = new Triangle(linuxAPI, "棕色", 300, 300, 350, 350, 320, 380);

        windowsTriangle.draw();
        System.out.println();
        macTriangle.draw();
        System.out.println();
        linuxTriangle.draw();

        System.out.println("\n--- 测试形状的动态操作 ---");
        // 演示形状的动态操作
        Circle dynamicCircle = new Circle(windowsAPI, "红色", 100, 400, 30);
        System.out.println("初始状态:");
        dynamicCircle.draw();
        
        System.out.println("\n改变颜色和位置:");
        dynamicCircle.changeColor("蓝色");
        dynamicCircle.move(150, 450);
        dynamicCircle.setRadius(60);
        dynamicCircle.draw();

        System.out.println("\n--- 测试桥接模式的独立性 ---");
        // 演示抽象部分和实现部分的独立性
        System.out.println("形状信息: " + dynamicCircle.getShapeInfo());
        System.out.println("绘图API: " + dynamicCircle.getDrawingAPIInfo());

        // 动态切换绘图API
        System.out.println("\n动态切换绘图API:");
        Circle switchedCircle = new Circle(macAPI, "绿色", 250, 400, 40);
        switchedCircle.draw();

        System.out.println("\n=== 桥接模式说明 ===");
        System.out.println("1. 抽象部分: Shape及其子类 - 定义形状的基本属性和行为");
        System.out.println("2. 实现部分: DrawingAPI及其实现类 - 定义具体的绘图操作");
        System.out.println("3. 桥接: Shape持有DrawingAPI的引用，将抽象与实现分离");
        System.out.println("4. 独立性: 形状类型和绘图平台可以独立变化和扩展");

        System.out.println("\n=== 桥接模式优势 ===");
        System.out.println("✅ 分离抽象与实现: 形状和绘图API可以独立变化");
        System.out.println("✅ 扩展性强: 可以轻松添加新形状或新平台");
        System.out.println("✅ 避免继承爆炸: 不需要为每种形状-平台组合创建类");
        System.out.println("✅ 运行时切换: 可以在运行时动态切换实现");
        System.out.println("✅ 符合开闭原则: 对扩展开放，对修改关闭");

        System.out.println("\n=== 桥接模式应用场景 ===");
        System.out.println("📌 图形界面系统: 不同操作系统的窗口组件");
        System.out.println("📌 数据库访问: 不同数据库的访问接口");
        System.out.println("📌 消息系统: 不同消息队列的实现");
        System.out.println("📌 文件系统: 不同存储介质的文件操作");
    }
}
