package com.skuu.design.interpreter.expressions;

import com.skuu.design.interpreter.Context;
import com.skuu.design.interpreter.Expression;

/**
 * @author dcx
 * @description 变量表达式 - 终结符表达式
 * @create 2025-01-27
 */
public class VariableExpression implements Expression {
    
    private String name;
    
    public VariableExpression(String name) {
        this.name = name;
    }
    
    @Override
    public int interpret(Context context) {
        return context.getVariable(name);
    }
    
    @Override
    public String toString() {
        return name;
    }
}
