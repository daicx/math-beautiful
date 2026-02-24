package com.skuu.design.bridge.apis;

import com.skuu.design.bridge.DrawingAPI;

/**
 * @author dcx
 * @description Linux平台绘图API实现
 * @create 2025-01-27
 */
public class LinuxDrawingAPI implements DrawingAPI {

    @Override
    public void drawCircle(int x, int y, int radius, String color) {
        System.out.println("🐧 Linux平台绘制圆形:");
        System.out.println("   - 位置: (" + x + ", " + y + ")");
        System.out.println("   - 半径: " + radius + "px");
        System.out.println("   - 颜色: " + color);
        System.out.println("   - 使用X11/Cairo渲染");
        System.out.println("   - 支持多种窗口管理器");
    }

    @Override
    public void drawRectangle(int x, int y, int width, int height, String color) {
        System.out.println("🐧 Linux平台绘制矩形:");
        System.out.println("   - 位置: (" + x + ", " + y + ")");
        System.out.println("   - 尺寸: " + width + "x" + height + "px");
        System.out.println("   - 颜色: " + color);
        System.out.println("   - 使用OpenGL渲染");
        System.out.println("   - 支持多显示器");
    }

    @Override
    public void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, String color) {
        System.out.println("🐧 Linux平台绘制三角形:");
        System.out.println("   - 顶点1: (" + x1 + ", " + y1 + ")");
        System.out.println("   - 顶点2: (" + x2 + ", " + y2 + ")");
        System.out.println("   - 顶点3: (" + x3 + ", " + y3 + ")");
        System.out.println("   - 颜色: " + color);
        System.out.println("   - 使用GTK+绘图");
        System.out.println("   - 支持主题定制");
    }

    @Override
    public String getAPIName() {
        return "Linux X11/Cairo";
    }
}
