package com.skuu.design.visitor;

import com.skuu.design.visitor.employees.*;

/**
 * @author dcx
 * @description 访问者接口 - 访问者模式的Visitor
 * @create 2025-01-27
 */
public interface Visitor {
    
    /**
     * 访问工程师
     */
    void visit(Engineer engineer);
    
    /**
     * 访问经理
     */
    void visit(Manager manager);
    
    /**
     * 访问CEO
     */
    void visit(CEO ceo);
}
