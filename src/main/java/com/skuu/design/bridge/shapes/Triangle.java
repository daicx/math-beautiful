package com.skuu.design.bridge.shapes;

import com.skuu.design.bridge.DrawingAPI;
import com.skuu.design.bridge.Shape;

/**
 * @author dcx
 * @description 三角形类 - 扩展抽象类
 * @create 2025-01-27
 */
public class Triangle extends Shape {
    
    private int x2, y2, x3, y3;
    
    /**
     * 构造函数
     */
    public Triangle(DrawingAPI drawingAPI, String color, int x1, int y1, 
                   int x2, int y2, int x3, int y3) {
        super(drawingAPI, color, x1, y1);
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }
    
    @Override
    public void draw() {
        System.out.println("🔺 绘制三角形:");
        System.out.println("   " + getShapeInfo());
        drawingAPI.drawTriangle(x, y, x2, y2, x3, y3, color);
    }
    
    @Override
    public String getShapeInfo() {
        return String.format("三角形 - 顶点1:(%d,%d), 顶点2:(%d,%d), 顶点3:(%d,%d), 颜色:%s", 
                           x, y, x2, y2, x3, y3, color);
    }
    
    /**
     * 改变顶点位置
     */
    public void setVertices(int x1, int y1, int x2, int y2, int x3, int y3) {
        this.x = x1;
        this.y = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        System.out.println("📍 改变顶点位置");
    }
    
    /**
     * 获取顶点坐标
     */
    public int[][] getVertices() {
        return new int[][]{{x, y}, {x2, y2}, {x3, y3}};
    }
}
