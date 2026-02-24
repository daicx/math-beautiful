package com.skuu.design.bridge;

/**
 * @author dcx
 * @description 形状抽象类 - 桥接模式的抽象部分
 * @create 2025-01-27
 */
public abstract class Shape {
    
    protected DrawingAPI drawingAPI;
    protected String color;
    protected int x, y;
    
    /**
     * 构造函数
     * @param drawingAPI 绘图API
     * @param color 颜色
     * @param x X坐标
     * @param y Y坐标
     */
    protected Shape(DrawingAPI drawingAPI, String color, int x, int y) {
        this.drawingAPI = drawingAPI;
        this.color = color;
        this.x = x;
        this.y = y;
    }
    
    /**
     * 绘制形状 - 由子类实现具体绘制逻辑
     */
    public abstract void draw();
    
    /**
     * 移动形状
     * @param newX 新的X坐标
     * @param newY 新的Y坐标
     */
    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
        System.out.println("📐 移动形状到位置: (" + newX + ", " + newY + ")");
    }
    
    /**
     * 改变颜色
     * @param newColor 新颜色
     */
    public void changeColor(String newColor) {
        this.color = newColor;
        System.out.println("🎨 改变颜色为: " + newColor);
    }
    
    /**
     * 获取形状信息
     */
    public abstract String getShapeInfo();
    
    /**
     * 获取绘图API信息
     */
    public String getDrawingAPIInfo() {
        return drawingAPI.getAPIName();
    }
}
