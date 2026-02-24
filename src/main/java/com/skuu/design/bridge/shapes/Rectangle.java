package com.skuu.design.bridge.shapes;

import com.skuu.design.bridge.DrawingAPI;
import com.skuu.design.bridge.Shape;

/**
 * @author dcx
 * @description 矩形类 - 扩展抽象类
 * @create 2025-01-27
 */
public class Rectangle extends Shape {
    
    private int width, height;
    
    /**
     * 构造函数
     */
    public Rectangle(DrawingAPI drawingAPI, String color, int x, int y, int width, int height) {
        super(drawingAPI, color, x, y);
        this.width = width;
        this.height = height;
    }
    
    @Override
    public void draw() {
        System.out.println("🔲 绘制矩形:");
        System.out.println("   " + getShapeInfo());
        drawingAPI.drawRectangle(x, y, width, height, color);
    }
    
    @Override
    public String getShapeInfo() {
        return String.format("矩形 - 位置:(%d,%d), 尺寸:%dx%dpx, 颜色:%s", 
                           x, y, width, height, color);
    }
    
    /**
     * 改变尺寸
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        System.out.println("📐 改变尺寸为: " + width + "x" + height + "px");
    }
    
    /**
     * 获取宽度
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * 获取高度
     */
    public int getHeight() {
        return height;
    }
}
