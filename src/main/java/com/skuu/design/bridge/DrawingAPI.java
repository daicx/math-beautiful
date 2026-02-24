package com.skuu.design.bridge;

/**
 * @author dcx
 * @description 绘图API接口 - 桥接模式的实现部分
 * @create 2025-01-27
 */
public interface DrawingAPI {
    
    /**
     * 绘制圆形
     * @param x 圆心X坐标
     * @param y 圆心Y坐标
     * @param radius 半径
     * @param color 颜色
     */
    void drawCircle(int x, int y, int radius, String color);
    
    /**
     * 绘制矩形
     * @param x 左上角X坐标
     * @param y 左上角Y坐标
     * @param width 宽度
     * @param height 高度
     * @param color 颜色
     */
    void drawRectangle(int x, int y, int width, int height, String color);
    
    /**
     * 绘制三角形
     * @param x1 第一个点X坐标
     * @param y1 第一个点Y坐标
     * @param x2 第二个点X坐标
     * @param y2 第二个点Y坐标
     * @param x3 第三个点X坐标
     * @param y3 第三个点Y坐标
     * @param color 颜色
     */
    void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, String color);
    
    /**
     * 获取API名称
     */
    String getAPIName();
}
