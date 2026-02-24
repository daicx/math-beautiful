package com.skuu.design.visitor;

/**
 * @author dcx
 * @description 员工接口 - 访问者模式的Element
 * @create 2025-01-27
 */
public interface Employee {
    
    /**
     * 接受访问者
     */
    void accept(Visitor visitor);
    
    /**
     * 获取员工姓名
     */
    String getName();
    
    /**
     * 获取员工职位
     */
    String getPosition();
}
