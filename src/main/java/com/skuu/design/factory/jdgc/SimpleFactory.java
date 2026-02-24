package com.skuu.design.factory.jdgc;

import com.skuu.design.factory.jdgc.bo.Circle;
import com.skuu.design.factory.jdgc.bo.Rectangle;

/**
 * ========== 简单工厂模式 ==========
 * <p>
 * 特点：
 * - 一个工厂类负责创建所有产品
 * - 通过参数决定创建哪种产品
 * - 违反开闭原则（添加新产品需要修改工厂类）
 * <p>
 * 适用场景：
 * - 产品类型较少且稳定
 * - 创建逻辑简单
 * - 不需要扩展性
 */
public class SimpleFactory {
    /**
     * 简单工厂
     */
    public Shape createShape(String type) {
        switch (type.toLowerCase()) {
            case "circle":
                return new Circle();
            case "rectangle":
                return new Rectangle();
            default:
                throw new IllegalArgumentException("不支持的形状类型: " + type);
        }
    }
}
