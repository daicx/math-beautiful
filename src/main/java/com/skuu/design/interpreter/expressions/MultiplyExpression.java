package com.skuu.design.interpreter.expressions;

import com.skuu.design.interpreter.Context;
import com.skuu.design.interpreter.Expression;

/**
 * @author dcx
 * @description 乘法表达式 - 非终结符表达式
 * @create 2025-01-27
 */
public class MultiplyExpression implements Expression {
    
    private Expression left;
    private Expression right;
    
    public MultiplyExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public int interpret(Context context) {
        return left.interpret(context) * right.interpret(context);
    }
    
    @Override
    public String toString() {
        return "(" + left.toString() + " * " + right.toString() + ")";
    }
}
