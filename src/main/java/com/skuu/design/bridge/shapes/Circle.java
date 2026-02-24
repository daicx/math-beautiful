package com.skuu.design.bridge.shapes;

import com.skuu.design.bridge.DrawingAPI;
import com.skuu.design.bridge.Shape;

/**
 * @author dcx
 * @description 圆形类 - 扩展抽象类
 * @create 2025-01-27
 */
public class Circle extends Shape {
    
    private int radius;
    
    /**
     * 构造函数
     */
    public Circle(DrawingAPI drawingAPI, String color, int x, int y, int radius) {
        super(drawingAPI, color, x, y);
        this.radius = radius;
    }
    
    @Override
    public void draw() {
        System.out.println("🔵 绘制圆形:");
        System.out.println("   " + getShapeInfo());
        drawingAPI.drawCircle(x, y, radius, color);
    }
    
    @Override
    public String getShapeInfo() {
        return String.format("圆形 - 位置:(%d,%d), 半径:%dpx, 颜色:%s", 
                           x, y, radius, color);
    }
    
    /**
     * 改变半径
     */
    public void setRadius(int radius) {
        this.radius = radius;
        System.out.println("📏 改变半径为: " + radius + "px");
    }
    
    /**
     * 获取半径
     */
    public int getRadius() {
        return radius;
    }
}
