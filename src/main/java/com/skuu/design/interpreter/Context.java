package com.skuu.design.interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dcx
 * @description 上下文类 - 存储变量和值的映射
 * @create 2025-01-27
 */
public class Context {
    
    /**
     * 存储变量的值
     */
    private Map<String, Integer> variables;
    
    public Context() {
        this.variables = new HashMap<>();
    }
    
    /**
     * 设置变量的值
     */
    public void setVariable(String name, int value) {
        variables.put(name, value);
    }
    
    /**
     * 获取变量的值
     */
    public int getVariable(String name) {
        if (!variables.containsKey(name)) {
            throw new IllegalArgumentException("未定义的变量: " + name);
        }
        return variables.get(name);
    }
    
    /**
     * 是否包含变量
     */
    public boolean hasVariable(String name) {
        return variables.containsKey(name);
    }
    
    /**
     * 显示所有变量
     */
    public void showVariables() {
        System.out.println("📊 变量列表:");
        for (Map.Entry<String, Integer> entry : variables.entrySet()) {
            System.out.println("   " + entry.getKey() + " = " + entry.getValue());
        }
    }
}
