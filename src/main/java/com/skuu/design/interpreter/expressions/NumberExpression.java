package com.skuu.design.interpreter.expressions;

import com.skuu.design.interpreter.Context;
import com.skuu.design.interpreter.Expression;

/**
 * @author dcx
 * @description 数字表达式 - 终结符表达式
 * @create 2025-01-27
 */
public class NumberExpression implements Expression {
    
    private int number;
    
    public NumberExpression(int number) {
        this.number = number;
    }
    
    @Override
    public int interpret(Context context) {
        return number;
    }
    
    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
